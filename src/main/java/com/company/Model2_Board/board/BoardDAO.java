package com.company.Model2_Board.board;

//사용할 클래스들 추가
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.company.Model2_Board.common.JDBCUtil;



public class BoardDAO {
	//DB 관련 변수 선언
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	//전체 게시글 목록 조회 메소드 구현
	public List<BoardDO> getBoardList(String searchField, String searchText){
		
		List<BoardDO> boardList = new ArrayList<BoardDO>();
		
		try {
			System.out.println("===> JDBC로 getBoardList() 기능 처리");
			conn = JDBCUtil.getConnection();
		
			/* 
			 * [매우 중요] SQL 문장 만들기
			 */
			String condition = "";
			
			if(searchField != "" && searchText != ""){
				condition = "where " + searchField + " like '%" + searchText + "%'";
			}
			
			String BOARDLIST_GET = "select * from board " + condition + " order by seq desc";
			pstmt = conn.prepareStatement(BOARDLIST_GET);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				BoardDO boardDO = new BoardDO();
				boardDO.setSeq(rs.getInt("SEQ"));
				boardDO.setTitle(rs.getString("TITLE"));
				boardDO.setWriter(rs.getString("WRITER"));
				boardDO.setContent(rs.getString("CONTENT"));
				boardDO.setRegDate(rs.getDate("REGDATE"));
				boardDO.setCnt(rs.getInt("CNT"));
				//배열에 저장
				boardList.add(boardDO);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			JDBCUtil.close(rs, pstmt, conn);
		}
		return boardList;
	}//end getBoardList()
	
	//게시글 상세보기 메소드 구현
	public BoardDO getBoard(BoardDO boardDO){
		System.out.println("===> JDBC로 getBoard() 기능 처리");
		BoardDO board = null;
		
		try {
			conn = JDBCUtil.getConnection();
			
			//[중요] 해당 게시글의 조회수(cnt)를 1 증가 시킨다.
			String UPDATE_CNT = "update board set cnt=cnt+1 where seq=?";
			
			pstmt = conn.prepareStatement(UPDATE_CNT);
			pstmt.setInt(1, boardDO.getSeq());
			pstmt.executeUpdate();
			
			//해당 게시글 가져오기
			String BOARD_GET = "select * from board where seq=?";
			
			pstmt = conn.prepareStatement(BOARD_GET);
			pstmt.setInt(1, boardDO.getSeq());
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				board = new BoardDO();
				board.setSeq(rs.getInt("SEQ"));
				board.setTitle(rs.getString("TITLE"));
				board.setWriter(rs.getString("WRITER"));
				board.setContent(rs.getString("CONTENT"));
				board.setRegDate(rs.getDate("REGDATE"));
				board.setCnt(rs.getInt("CNT"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			JDBCUtil.close(rs, pstmt, conn);
		}
		return board;
	}//end getBoard()
	
	//게시글 등록 메소드 구현
	public void insertBoard(BoardDO boardDO){
		System.out.println("===> JDBC로 insertBoard() 기능 처리");
		
		try {
			conn = JDBCUtil.getConnection();
			/*
			//해당 게시글번호를 가져온다.
			String SELECT_SEQ = "select MAX(seq) from board";
			pstmt = conn.prepareStatement(SELECT_SEQ);
			ResultSet rs = pstmt.executeQuery();
			
			String maxSeq = rs.getString("MAX(SEQ)");
			int nextSeq = Integer.parseInt(maxSeq) + 1;*/
			
			//해당 게시글 등록하기
			String INSERT_BOARD = "insert into board(seq,title,writer,content) values((select nvl(max(seq),0)+1 from board),?,?,?)";
			pstmt = conn.prepareStatement(INSERT_BOARD);
			//pstmt.setInt(1, nextSeq);
			pstmt.setString(1, boardDO.getTitle());
			pstmt.setString(2, boardDO.getWriter());
			pstmt.setString(3, boardDO.getContent());
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			JDBCUtil.close(pstmt, conn);
		}
	}//end insertBoard()
	
	//게시글 수정 메소드 구현
	public void updateBoard(BoardDO boardDO){
		System.out.println("===> JDBC로 updateBoard() 기능 처리");
		
		try {
			conn = JDBCUtil.getConnection();
			
			String UPDATE_BOARD = "update board set title=?, content=? where seq=?";
			
			pstmt = conn.prepareStatement(UPDATE_BOARD);
			pstmt.setString(1, boardDO.getTitle());
			pstmt.setString(2, boardDO.getContent());
			pstmt.setInt(3, boardDO.getSeq());
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			JDBCUtil.close(pstmt, conn);
		}
	}//end updateBoard()
	
	//게시글 삭제 메소드 구현
	public void deleteBoard(BoardDO boardDO){
		System.out.println("===> JDBC로 deleteBoard() 기능 처리");
		
		try {
			conn = JDBCUtil.getConnection();
			
			String DELETE_BOARD = "delete from board where seq=?";
			
			pstmt = conn.prepareStatement(DELETE_BOARD);
			pstmt.setInt(1, boardDO.getSeq());
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			JDBCUtil.close(pstmt, conn);
		}
	}//end deleteBoard()
}