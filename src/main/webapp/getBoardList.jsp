<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" errorPage="error.jsp"%>
<!-- �߰� -->
<%@ page import="com.company.Model2_Board.board.BoardDO" %>
<%@ page import="com.company.Model2_Board.board.BoardDAO" %>
<%@ page import="java.util.List" %>	<%-- �����迭 ��� --%>
<% //[�߿�]
	List<BoardDO> boardList = (List)session.getAttribute("boardList");
%>

<!-- JSTL �߰� -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%	
	//[�߿�] '�� �Խñ�' ���� ���ϱ� ���
	int totalList = boardList.size();
	request.setAttribute("totalList", totalList);
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>�Խ��� ���</title>
</head>
<body>
	<div align="center">
	<h1>��ü �Խñ� ��� ����</h1>
	<h3><b>test</b>�� ȯ���մϴ�.&nbsp;&nbsp;&nbsp;<a href="logout.do">�α׾ƿ�</a></h3>
	<form name="form2" method="POST" action="getBoardList.do"><!-- �˻���� ������ ���� �ڱ��ڽ� �������� �̵� -->
		<p>�� �Խñ� : ${totalList} ��</p>
		<table border="1" cellpadding="0" cellspacing="0" width="700">
			<tr>
				<td align="right">
					<select name="searchCondition">
						<option value="WRITER" selected>�ۼ���</option>
						<option value="TITLE">����</option>
					</select>
					<input name="searchKeyword" type="text"/>
					<input type="submit" value="�˻�"/>
				</td>
			</tr>
		</table>
	</form>
	
	<table border="1" cellpadding="0" cellspacing="0" width="700">
		<tr>
			<th bgcolor="orange" width="100">��ȣ</th>
			<th bgcolor="orange" width="200">����</th>
			<th bgcolor="orange" width="150">�ۼ���</th>
			<th bgcolor="orange" width="150">�����</th>
			<th bgcolor="orange" width="100">��ȸ��</th>
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
		
		<%-- �� �ҽ��� ǥ������ JSTL�� �����Ͽ� �ҽ� ���� --%>
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
	<a href="insertBoard.jsp"><button>�� �Խñ� ���</button></a> &nbsp;&nbsp;&nbsp;
	<a href="getBoardList.do"><button>��ü �Խñ� ��� ����</button></a>
	</div>
</body>
</html>