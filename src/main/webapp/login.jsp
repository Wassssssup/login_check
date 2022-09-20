<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	td{
		border:1px solid:black;
		width:200px;
		text-align:center;
	}
</style>
</head>
<body>
<form action="login.do" method="post">
	<table align="center">
		<tr>
			<td colspan="2" align="center">로그인</td>
		</tr>
		<tr>
			<td><input type="hidden" name="index"></td>
		</tr>
		<tr>
			<td align="center">아이디</td>
			<td><input type="text" name="user_id"></td>
		</tr>
		<tr>
			<td align="center">비밀 번호</td>
			<td><input type="password" name="user_pwd"></td>
		</tr>
		<tr align="center">
			<td colspan="2">
				<input type="submit" value="로그인">
				<input type="reset" value="취소">
			</td>
		</tr>
	</table>
</form>
	
</body>
</html>