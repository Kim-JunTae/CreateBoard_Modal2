<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>게시판 에러 페이지</title>
</head>
<jsp:useBean id="now" class="java.util.Date" />
<body>
	<div>
		<h2>error_page.jsp</h2>
		<hr>
		<table>
			<tr>
				<td>발생 시간 :</td>
				<td>${now}</td>
			</tr>
			<tr>
				<td>요청실패 URI :</td>
				<td>${pageContext.errorData.requestURI}</td>
			</tr>
			
			<tr>
				<td>상태코드 :</td>
				<td>${pageContext.errorData.statusCode}</td>
			</tr>
			
			<tr>
				<td>예외유형 :</td>
				<td>${pageContext.errorData.throwable}</td>
			</tr>
			
			<tr style="width: 100%" bgcolor="pink">
				<td colspan="2">
					웹 서버 관리자에게 문의해 주세요.
					빠른 시일내로 복구하겠습니다.
				</td>
			</tr>
		</table>
	</div>
</body>
</html>