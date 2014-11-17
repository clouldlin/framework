<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>메인페이지</title>
<link rel="stylesheet" type="text/css" href="/framework/css/common.css" />
<link rel="stylesheet" type="text/css" href="/framework/css/main/main.css" />
<script src="/framework/js/common.js"></script>
<script src="/framework/js/login/login.js"></script>
<script src="/framework/js/main/main.js"></script>
</head>
<body>
<div class="wrapper">  
	<div class="header">
		<div class="main_title">메인</div>
		<div class="login">
			<form method="post" action="${pageContext.request.contextPath}/framework/login/login.do">
				<input type="text" size="15" name="id" id="id">
				<input type="password" size="15" name="pasword" id="password">
				<input type="submit" id="submit" value="로그인">
				<div><input type="checkbox" id="id_save"><span class="id_save">아이디 저장</span></div>
			</form>
		</div>
	</div> 
	<div class="sidebar"></div>
	<div class="content"></div>
	<div class="footer"></div>
	${noticeCount}
</div>

</body>
</html>