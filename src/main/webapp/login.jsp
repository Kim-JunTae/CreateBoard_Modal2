<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" errorPage="error.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>�α��� ������</title>
</head>
<body>
	<center>
		<h1>�α���</h1>
		<%-- action="login.do"�ϴ� ����=> "*.do" ������ ��û�� ���ؼ���
			 DispatcherServlet Ŭ������ �����ϱ� ������ --%>
		<form name="form1" method="POST" action="login.do">
			<table border="1" cellpadding="0" cellspacing="0">
				<tr>
					<td bgcolor="orange"><label for="id">���̵�</label></td>
					<td><input type="text" name="id"/></td>
				</tr>
				
				<tr>
					<td bgcolor="orange"><label for="pw">��й�ȣ</label></td>
					<td><input name="password" type="password"/></td>
				</tr>
			
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="�α���"/>
					</td>
				</tr>
			</table>
		</form>
	</center>
</body>
</html>