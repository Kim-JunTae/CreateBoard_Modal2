<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" errorPage="error.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>�� �Խñ� ���</title>
</head>
<body>
	<center>
		<h1>�� �Խñ� ���</h1>
		<a href="logout_proc.jsp">�α׾ƿ�</a>
		<hr>
		<form method="POST" action="insertBoard.do">
			<table border="1" cellpadding="0" cellspacing="0">
				<tr>
					<td bgcolor="orange" width="70">����</td>
					<td align="left">
						<input name="title" type="text"/>
					</td>
				</tr>
				<tr>
					<td bgcolor="orange">�ۼ���</td>
					<td align="left">
						<input name="writer" type="text"/>
					</td>
				</tr>
				<tr>
					<td bgcolor="orange">����</td>
					<td align="left">
						<textarea name="content" rows="10" cols="40"></textarea>
					</td>
				</tr>
	
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="�� ���"/>
					</td>
				</tr>
			</table>
		</form>
		<hr>
		<a href="getBoardList.do">��ü �Խñ� ��� ����</a>
	</center>
</body>
</html>