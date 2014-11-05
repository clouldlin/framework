<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>메인페이지</title>
</head>
<body>
${module}
<div>
<form method="post" action="${pageContext.request.contextPath}/framework/login/login.do">
	<input type="text" name="id">
	<input type="password" name="pasword">
	<input type="submit" value="로그인">
</form>
</div>
</body>
</html>