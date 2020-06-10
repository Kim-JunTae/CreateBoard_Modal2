<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" errorPage="error.jsp"%>
<!-- 추가 -->
<%@ page import="com.company.Model2_Board.board.BoardDO" %>
<%@ page import="com.company.Model2_Board.board.BoardDAO" %>

<% //[중요]
	BoardDO board = (BoardDO)session.getAttribute("board");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>게시글 상세보기</title>
</head>
<body>
	<center>
	<h1>게시글 상세 보기</h1>
	<a href="logout.do">로그아웃</a>
	<hr>
	<form method="POST" action="updateBoard.do">
		<input type="hidden" name="seq" value="${board.getSeq()}"/>
		<table border="1" cellpadding="0" cellspacing="0">
			<tr>
				<td bgcolor="orange">제목</td>
				<td align="left">
					<input name="title" type="text" value="${board.getTitle()}"/>
				</td>
			</tr>
			<tr>
				<td bgcolor="orange">작성자</td>
				<td align="left"><%=board.getWriter()%></td>
			</tr>
			<tr>
				<td bgcolor="orange">내용</td>
				<td align="left">
					<textarea name="content" rows="10" cols="40"><%=board.getContent()%></textarea>
				</td>
			</tr>
			<tr>
				<td bgcolor="orange">등록일</td>
				<td align="left"><%=board.getRegDate()%></td>
			</tr>
			<tr>
				<td bgcolor="orange">조회수</td>
				<td align="left"><%=board.getCnt()%></td>
			</tr>
			
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="글 수정"/>
				</td>
			</tr>
		</table>
	</form>
	<hr>
	<a href="insertBoard.jsp">새 게시글 등록</a> &nbsp;&nbsp;
	<a href="deleteBoard.do?seq=<%=board.getSeq()%>">게시글 삭제</a> &nbsp;&nbsp;
	<a href="getBoardList.do">전체 게시글 목록 보기</a>
	</center>
</body>
</html>