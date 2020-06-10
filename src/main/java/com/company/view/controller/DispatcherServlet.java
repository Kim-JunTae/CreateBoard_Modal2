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
    
	//������
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
	
	//����� ���� �޼ҵ�
	private void process(HttpServletRequest request, HttpServletResponse response) 
			throws IOException{
		//1. Ŭ���̾�Ʈ�� ��û path ������ �����Ѵ�.
		String uri = request.getRequestURI();
		String path = uri.substring(uri.lastIndexOf("/"));
		
		//2. Ŭ���̾�Ʈ�� ��û path�� ���� ������ �б�ó���Ѵ�.
		if(path.equals("/login.do")) {
			System.out.println("�α��� ó����!");
			
			//2.1. ����� �Է� ���� ����
			String id = request.getParameter("id");
			String password = request.getParameter("password");
			
			//2.2. H2 DB ���� ó��
			//2.2.1. UserDO Ŭ������ ��ü ���� �� �߰�����ҿ� �� �ʱ�ȭ �۾�
			UserDO userObj = new UserDO();	//UserDO�� �������� ����
			userObj.setId(id);				//id �� �ʱ�ȭ
			userObj.setPassword(password);	//password �� �ʱ�ȭ
			
			//2.2.2. UserDAO Ŭ������ ��ü ���� �� getUser() �޼ҵ� ȣ��
			UserDAO userDAO = new UserDAO();		//UserDAO�� �������� ����
			UserDO user = userDAO.getUser(userObj);	//user ���������� ����Ű�� �޸𸮿� ���� null�ƴϸ� ��ü�� �����Ǿ� �ִ�.
			
			//2.3. ȭ�� �׺���̼� = ������
			if(user != null){
				//System.out.println("�α��� ����!");
				//out.println("<script>alert('�α��� ����!');</script>");
				response.sendRedirect("getBoardList.do");
			}else{
				response.sendRedirect("login.jsp");
			}
		}else if(path.equals("/getBoardList.do")) {
			System.out.println("��ü �Խñ� ��� ���� ó����!");
			
			//1. �˻� ���(�ۼ���, ����) �� �˻� ���� ��ü�� ������ ���� ����
			String searchField = "";	//����ִ� ��ü
			String searchText = "";
			
			//2. ����� �Է� ���� ����
			if(request.getParameter("searchCondition") != null 
			   && request.getParameter("searchKeyword") != null){
				searchField = request.getParameter("searchCondition");
				searchText = request.getParameter("searchKeyword");
			}
			
			//3. BoardDAO Ŭ���� ��ü ���� �� getBoardList() �޼ҵ� ȣ��
			BoardDAO boardDAO = new BoardDAO();
			List<BoardDO> boardList = boardDAO.getBoardList(searchField, searchText);
			
			//4. [�߿�] �˻� ����� session�� �����ϰ� ������(Ŭ���̾�Ʈ�� ����)
			HttpSession session = request.getSession();
			session.setAttribute("boardList", boardList);
			
			response.sendRedirect("getBoardList.jsp");	//������
			
		}else if(path.equals("/getBoard.do")) {
			System.out.println("�Խñ� �� ���� ó����!");
			
			//1. ������ �Ѿ�� �� ������ �Խñ� ��ȣ ����
			String seq = request.getParameter("seq");
			
			//2. BoardDO ��ü �����Ͽ� seq �߰�����ҿ� ����(�ʱ�ȭ)�Ѵ�.
			BoardDO boardDO = new BoardDO();
			boardDO.setSeq(Integer.parseInt(seq));
			
			//3. BoardDAO ��ü ���� �� �Խñ� �󼼺��� �޼ҵ� ȣ��
			BoardDAO boardDAO = new BoardDAO();
			BoardDO board = boardDAO.getBoard(boardDO);
			
			//4. [�߿�] �˻� ����� session�� �����ϰ� ������(Ŭ���̾�Ʈ�� ����)
			HttpSession session = request.getSession();
			
			/*
			 * session ��ü�� setAttribute() �޼ҵ带 ����ؼ� ���ǿ� �Ӽ���
			 * �����ϰ� �Ǹ� ��� ���¸� �����ϴ� ����� ����� �� �ִ�.
			 * ��, �˻� ����� .jsp ���������� �����ϱ� ���ؼ� ���ǿ� �����Ѵ�.
			 */
			session.setAttribute("board", board);
			
			response.sendRedirect("getBoard.jsp");	//������
			
		}else if(path.equals("/updateBoard.do")){
			System.out.println("�Խñ� ���� ó����!");
			
			request.setCharacterEncoding("EUC-KR");
			
			//1. ����� �Է����� ����
			String seq = request.getParameter("seq");
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			
			//2. BoardDO ��ü �����Ͽ� seq �߰�����ҿ� ����(�ʱ�ȭ)�Ѵ�.
			BoardDO boardDO = new BoardDO();
			boardDO.setSeq(Integer.parseInt(seq));
			boardDO.setTitle(title);
			boardDO.setContent(content);
			
			//3. BoardDAO ��ü ���� �� �Խñ� ���� �޼ҵ� ȣ��
			BoardDAO boardDAO = new BoardDAO();
			boardDAO.updateBoard(boardDO);
			
			//4. ������ => ȭ�� �׺���̼�
			response.sendRedirect("getBoardList.do");
			
		}else if(path.equals("/insertBoard.do")) {
			System.out.println("�� �Խñ� ��� ó����!");
			
			//1. ����� �Է����� ����
			String title = request.getParameter("title");
			String writer = request.getParameter("writer");
			String content = request.getParameter("content");
			
			//2. BoardDO ��ü �����Ͽ� ����, �۾���, ������ �߰�����ҿ� ����(�ʱ�ȭ)�Ѵ�.
			BoardDO boardDO = new BoardDO();
			boardDO.setTitle(title);
			boardDO.setWriter(writer);
			boardDO.setContent(content);
			
			//3. BoardDAO ��ü ���� �� �Խñ� ��� �޼ҵ� ȣ��
			BoardDAO boardDAO = new BoardDAO();
			boardDAO.insertBoard(boardDO);
			
			//4. ������ => ȭ�� �׺���̼�
			response.sendRedirect("getBoardList.do");
			
		}else if(path.equals("/deleteBoard.do")) {
			System.out.println("�Խñ� ���� ó����!");
			
			//1. ����� �Է����� ����
			String seq = request.getParameter("seq");
			
			//2. BoardDO ��ü �����Ͽ� �Խñ۹�ȣ�� �߰�����ҿ� ����(�ʱ�ȭ)�Ѵ�.
			BoardDO boardDO = new BoardDO();
			boardDO.setSeq(Integer.parseInt(seq));
			
			//3. BoardDAO ��ü ���� �� �Խñ� ���� �޼ҵ� ȣ��
			BoardDAO boardDAO = new BoardDAO();
			boardDAO.deleteBoard(boardDO);
			
			//4. ������ => ȭ�� �׺���̼�
			response.sendRedirect("getBoardList.do");
		}else if(path.equals("/logout.do")) {
			System.out.println("�α׾ƿ� ó����!");
			//1. �������� ����� ���� ��ü�� ���� ����
			HttpSession session = request.getSession();
			session.invalidate();

			//2. ���� ���� �� �α��� �������� �̵�
			response.sendRedirect("login.jsp");
		}
	}
}
