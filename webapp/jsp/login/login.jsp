<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>로그인 페이지</title>
</head>
<body>
${module}
${message}
<div>
로그인 처리
<br />
<a href="${pageContext.request.contextPath}/framework/login/logout.do">로그아웃</a>
</div>
</body>
</html>