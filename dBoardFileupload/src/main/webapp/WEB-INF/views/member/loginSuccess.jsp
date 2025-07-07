<%@ page contentType="text/html; charset=UTF-8"%>

<!DOCTYPE>
<head>
<meta charset="UTF-8">
<title>로그인성공</title>
</head>

<body>
 
	
	<h1>로그인성공페이지</h1>
	<div> ${sessionScope.userinfo.name} 님로그인성공</div>
	
	<div>회원정보</div>
	<div> 아이디 : ${sessionScope.userinfo.id}</div>
	
	<div>파일</div>
	<div> ${sessionScope.userinfo.originFilename}</div>
	<img src='../memberFiles/${sessionScope.userinfo.filename}'>
	<hr/>
	<a href="/member/logout">로그아웃</a>
</body>

</html>
