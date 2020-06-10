<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" errorPage="error.jsp"%>
<!-- 추가 -->
<%@ page import="com.company.Model2_Board.board.BoardDO" %>
<%@ page import="com.company.Model2_Board.board.BoardDAO" %>
<%@ page import="java.util.List" %>	<%-- 가변배열 사용 --%>
<% //[중요]
	List<BoardDO> boardList = (List)session.getAttribute("boardList");
%>

<!-- JSTL 추가 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%	
	//[중요] '총 게시글' 갯수 구하기 방법
	int totalList = boardList.size();
	request.setAttribute("totalList", totalList);
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>게시판 목록</title>
</head>
<body>
	<div align="center">
	<h1>전체 게시글 목록 보기</h1>
	<h3><b>test</b>님 환영합니다.&nbsp;&nbsp;&nbsp;<a href="logout.do">로그아웃</a></h3>
	<form name="form2" method="POST" action="getBoardList.do"><!-- 검색기능 구현을 위해 자기자신 페이지로 이동 -->
		<p>총 게시글 : ${totalList} 건</p>
		<table border="1" cellpadding="0" cellspacing="0" width="700">
			<tr>
				<td align="right">
					<select name="searchCondition">
						<option value="WRITER" selected>작성자</option>
						<option value="TITLE">제목</option>
					</select>
					<input name="searchKeyword" type="text"/>
					<input type="submit" value="검색"/>
				</td>
			</tr>
		</table>
	</form>
	
	<table border="1" cellpadding="0" cellspacing="0" width="700">
		<tr>
			<th bgcolor="orange" width="100">번호</th>
			<th bgcolor="orange" width="200">제목</th>
			<th bgcolor="orange" width="150">작성자</th>
			<th bgcolor="orange" width="150">등록일</th>
			<th bgcolor="orange" width="100">조회수</th>
		</tr>
		
		<%-- <%for(BoardDO board : boardList){%>
			<tr>
				<td align="center"><%=board.getSeq() %></td>
				<td align="left">
					<a href="getBoard.jsp?seq=<%=board.getSeq() %>">
						<%=board.getTitle() %>
					</a>
				</td>
				<td align="center"><%=board.getWriter() %></td>
				<td align="center"><%=board.getRegDate() %></td>
				<td align="center"><%=board.getCnt() %></td>
			</tr>
		<%}%> --%>
		
		<%-- 위 소스를 표현언어와 JSTL을 적용하여 소스 변경 --%>
		<c:forEach var="board" items="${boardList}">
			<tr>
				<td align="center">${board.getSeq()}</td>
				<td align="left">
					<a href="getBoard.do?seq=${board.getSeq()}">
						${board.getTitle()}
					</a>
				</td>
				<td align="center">${board.getWriter()}</td>
				<td align="center">${board.getRegDate()}</td>
				<td align="center">${board.getCnt()}</td>
			</tr>
		</c:forEach>
	</table>
	<br>
	<a href="insertBoard.jsp"><button>새 게시글 등록</button></a> &nbsp;&nbsp;&nbsp;
	<a href="getBoardList.do"><button>전체 게시글 목록 보기</button></a>
	</div>
</body>
</html>