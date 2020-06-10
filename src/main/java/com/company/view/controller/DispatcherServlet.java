package com.company.view.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.company.Model2_Board.board.BoardDAO;
import com.company.Model2_Board.board.BoardDO;
import com.company.Model2_Board.user.UserDAO;
import com.company.Model2_Board.user.UserDO;

public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	//생성자
	public DispatcherServlet() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("EUC-KR");
		process(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("EUC-KR");
		process(request, response);
	}
	
	//사용자 정의 메소드
	private void process(HttpServletRequest request, HttpServletResponse response) 
			throws IOException{
		//1. 클라이언트의 요청 path 정보를 추출한다.
		String uri = request.getRequestURI();
		String path = uri.substring(uri.lastIndexOf("/"));
		
		//2. 클라이언트의 요청 path에 따라 적절히 분기처리한다.
		if(path.equals("/login.do")) {
			System.out.println("로그인 처리됨!");
			
			//2.1. 사용자 입력 정보 추출
			String id = request.getParameter("id");
			String password = request.getParameter("password");
			
			//2.2. H2 DB 연동 처리
			//2.2.1. UserDO 클래스의 객체 생성 후 중간저장소에 값 초기화 작업
			UserDO userObj = new UserDO();	//UserDO의 참조변수 생성
			userObj.setId(id);				//id 값 초기화
			userObj.setPassword(password);	//password 값 초기화
			
			//2.2.2. UserDAO 클래스의 객체 생성 후 getUser() 메소드 호출
			UserDAO userDAO = new UserDAO();		//UserDAO의 참조변수 생성
			UserDO user = userDAO.getUser(userObj);	//user 참조변수가 가리키는 메모리에 가면 null아니면 객체가 생성되어 있다.
			
			//2.3. 화면 네비게이션 = 포워딩
			if(user != null){
				//System.out.println("로그인 성공!");
				//out.println("<script>alert('로그인 성공!');</script>");
				response.sendRedirect("getBoardList.do");
			}else{
				response.sendRedirect("login.jsp");
			}
		}else if(path.equals("/getBoardList.do")) {
			System.out.println("전체 게시글 목록 보기 처리됨!");
			
			//1. 검색 대상(작성자, 제목) 및 검색 내용 객체를 저장할 변수 설정
			String searchField = "";	//비어있는 객체
			String searchText = "";
			
			//2. 사용자 입력 정보 추출
			if(request.getParameter("searchCondition") != null 
			   && request.getParameter("searchKeyword") != null){
				searchField = request.getParameter("searchCondition");
				searchText = request.getParameter("searchKeyword");
			}
			
			//3. BoardDAO 클래스 객체 생성 후 getBoardList() 메소드 호출
			BoardDAO boardDAO = new BoardDAO();
			List<BoardDO> boardList = boardDAO.getBoardList(searchField, searchText);
			
			//4. [중요] 검색 결과를 session에 저장하고 포워딩(클라이언트에 응답)
			HttpSession session = request.getSession();
			session.setAttribute("boardList", boardList);
			
			response.sendRedirect("getBoardList.jsp");	//포워딩
			
		}else if(path.equals("/getBoard.do")) {
			System.out.println("게시글 상세 보기 처리됨!");
			
			//1. 폼에서 넘어온 상세 보기할 게시글 번호 추출
			String seq = request.getParameter("seq");
			
			//2. BoardDO 객체 생성하여 seq 중간저장소에 저장(초기화)한다.
			BoardDO boardDO = new BoardDO();
			boardDO.setSeq(Integer.parseInt(seq));
			
			//3. BoardDAO 객체 생성 후 게시글 상세보기 메소드 호출
			BoardDAO boardDAO = new BoardDAO();
			BoardDO board = boardDAO.getBoard(boardDO);
			
			//4. [중요] 검색 결과를 session에 저장하고 포워딩(클라이언트에 응답)
			HttpSession session = request.getSession();
			
			/*
			 * session 객체의 setAttribute() 메소드를 사용해서 세션에 속성을
			 * 지정하게 되면 계속 상태를 유지하는 기능을 사용할 수 있다.
			 * 즉, 검색 결과를 .jsp 페이지에서 공유하기 위해서 세션에 저장한다.
			 */
			session.setAttribute("board", board);
			
			response.sendRedirect("getBoard.jsp");	//포워딩
			
		}else if(path.equals("/updateBoard.do")){
			System.out.println("게시글 수정 처리됨!");
			
			request.setCharacterEncoding("EUC-KR");
			
			//1. 사용자 입력정보 추출
			String seq = request.getParameter("seq");
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			
			//2. BoardDO 객체 생성하여 seq 중간저장소에 저장(초기화)한다.
			BoardDO boardDO = new BoardDO();
			boardDO.setSeq(Integer.parseInt(seq));
			boardDO.setTitle(title);
			boardDO.setContent(content);
			
			//3. BoardDAO 객체 생성 후 게시글 수정 메소드 호출
			BoardDAO boardDAO = new BoardDAO();
			boardDAO.updateBoard(boardDO);
			
			//4. 포워딩 => 화면 네비게이션
			response.sendRedirect("getBoardList.do");
			
		}else if(path.equals("/insertBoard.do")) {
			System.out.println("새 게시글 등록 처리됨!");
			
			//1. 사용자 입력정보 추출
			String title = request.getParameter("title");
			String writer = request.getParameter("writer");
			String content = request.getParameter("content");
			
			//2. BoardDO 객체 생성하여 제목, 글쓴이, 내용을 중간저장소에 저장(초기화)한다.
			BoardDO boardDO = new BoardDO();
			boardDO.setTitle(title);
			boardDO.setWriter(writer);
			boardDO.setContent(content);
			
			//3. BoardDAO 객체 생성 후 게시글 등록 메소드 호출
			BoardDAO boardDAO = new BoardDAO();
			boardDAO.insertBoard(boardDO);
			
			//4. 포워딩 => 화면 네비게이션
			response.sendRedirect("getBoardList.do");
			
		}else if(path.equals("/deleteBoard.do")) {
			System.out.println("게시글 삭제 처리됨!");
			
			//1. 사용자 입력정보 추출
			String seq = request.getParameter("seq");
			
			//2. BoardDO 객체 생성하여 게시글번호를 중간저장소에 저장(초기화)한다.
			BoardDO boardDO = new BoardDO();
			boardDO.setSeq(Integer.parseInt(seq));
			
			//3. BoardDAO 객체 생성 후 게시글 수정 메소드 호출
			BoardDAO boardDAO = new BoardDAO();
			boardDAO.deleteBoard(boardDO);
			
			//4. 포워딩 => 화면 네비게이션
			response.sendRedirect("getBoardList.do");
		}else if(path.equals("/logout.do")) {
			System.out.println("로그아웃 처리됨!");
			//1. 브라우저와 연결된 세션 객체를 강제 종료
			HttpSession session = request.getSession();
			session.invalidate();

			//2. 세션 종료 후 로그인 페이지로 이동
			response.sendRedirect("login.jsp");
		}
	}
}
