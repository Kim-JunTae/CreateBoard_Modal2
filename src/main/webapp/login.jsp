<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" errorPage="error.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>로그인 페이지</title>
</head>
<body>
	<center>
		<h1>로그인</h1>
		<%-- action="login.do"하는 이유=> "*.do" 형태의 요청에 대해서만
			 DispatcherServlet 클래스가 동작하기 때문에 --%>
		<form name="form1" method="POST" action="login.do">
			<table border="1" cellpadding="0" cellspacing="0">
				<tr>
					<td bgcolor="orange"><label for="id">아이디</label></td>
					<td><input type="text" name="id"/></td>
				</tr>
				
				<tr>
					<td bgcolor="orange"><label for="pw">비밀번호</label></td>
					<td><input name="password" type="password"/></td>
				</tr>
			
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="로그인"/>
					</td>
				</tr>
			</table>
		</form>
	</center>
</body>
</html>