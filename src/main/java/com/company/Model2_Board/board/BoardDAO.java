package com.company.Model2_Board.board;

//����� Ŭ������ �߰�
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.company.Model2_Board.common.JDBCUtil;



public class BoardDAO {
	//DB ���� ���� ����
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	//��ü �Խñ� ��� ��ȸ �޼ҵ� ����
	public List<BoardDO> getBoardList(String searchField, String searchText){
		
		List<BoardDO> boardList = new ArrayList<BoardDO>();
		
		try {
			System.out.println("===> JDBC�� getBoardList() ��� ó��");
			conn = JDBCUtil.getConnection();
		
			/* 
			 * [�ſ� �߿�] SQL ���� �����
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
				//�迭�� ����
				boardList.add(boardDO);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			JDBCUtil.close(rs, pstmt, conn);
		}
		return boardList;
	}//end getBoardList()
	
	//�Խñ� �󼼺��� �޼ҵ� ����
	public BoardDO getBoard(BoardDO boardDO){
		System.out.println("===> JDBC�� getBoard() ��� ó��");
		BoardDO board = null;
		
		try {
			conn = JDBCUtil.getConnection();
			
			//[�߿�] �ش� �Խñ��� ��ȸ��(cnt)�� 1 ���� ��Ų��.
			String UPDATE_CNT = "update board set cnt=cnt+1 where seq=?";
			
			pstmt = conn.prepareStatement(UPDATE_CNT);
			pstmt.setInt(1, boardDO.getSeq());
			pstmt.executeUpdate();
			
			//�ش� �Խñ� ��������
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
	
	//�Խñ� ��� �޼ҵ� ����
	public void insertBoard(BoardDO boardDO){
		System.out.println("===> JDBC�� insertBoard() ��� ó��");
		
		try {
			conn = JDBCUtil.getConnection();
			/*
			//�ش� �Խñ۹�ȣ�� �����´�.
			String SELECT_SEQ = "select MAX(seq) from board";
			pstmt = conn.prepareStatement(SELECT_SEQ);
			ResultSet rs = pstmt.executeQuery();
			
			String maxSeq = rs.getString("MAX(SEQ)");
			int nextSeq = Integer.parseInt(maxSeq) + 1;*/
			
			//�ش� �Խñ� ����ϱ�
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
	
	//�Խñ� ���� �޼ҵ� ����
	public void updateBoard(BoardDO boardDO){
		System.out.println("===> JDBC�� updateBoard() ��� ó��");
		
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
	
	//�Խñ� ���� �޼ҵ� ����
	public void deleteBoard(BoardDO boardDO){
		System.out.println("===> JDBC�� deleteBoard() ��� ó��");
		
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