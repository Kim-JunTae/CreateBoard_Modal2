<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" errorPage="error.jsp"%>
<!-- �߰� -->
<%@ page import="com.company.Model2_Board.board.BoardDO" %>
<%@ page import="com.company.Model2_Board.board.BoardDAO" %>

<% //[�߿�]
	BoardDO board = (BoardDO)session.getAttribute("board");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>�Խñ� �󼼺���</title>
</head>
<body>
	<center>
	<h1>�Խñ� �� ����</h1>
	<a href="logout.do">�α׾ƿ�</a>
	<hr>
	<form method="POST" action="updateBoard.do">
		<input type="hidden" name="seq" value="${board.getSeq()}"/>
		<table border="1" cellpadding="0" cellspacing="0">
			<tr>
				<td bgcolor="orange">����</td>
				<td align="left">
					<input name="title" type="text" value="${board.getTitle()}"/>
				</td>
			</tr>
			<tr>
				<td bgcolor="orange">�ۼ���</td>
				<td align="left"><%=board.getWriter()%></td>
			</tr>
			<tr>
				<td bgcolor="orange">����</td>
				<td align="left">
					<textarea name="content" rows="10" cols="40"><%=board.getContent()%></textarea>
				</td>
			</tr>
			<tr>
				<td bgcolor="orange">�����</td>
				<td align="left"><%=board.getRegDate()%></td>
			</tr>
			<tr>
				<td bgcolor="orange">��ȸ��</td>
				<td align="left"><%=board.getCnt()%></td>
			</tr>
			
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="�� ����"/>
				</td>
			</tr>
		</table>
	</form>
	<hr>
	<a href="insertBoard.jsp">�� �Խñ� ���</a> &nbsp;&nbsp;
	<a href="deleteBoard.do?seq=<%=board.getSeq()%>">�Խñ� ����</a> &nbsp;&nbsp;
	<a href="getBoardList.do">��ü �Խñ� ��� ����</a>
	</center>
</body>
</html>