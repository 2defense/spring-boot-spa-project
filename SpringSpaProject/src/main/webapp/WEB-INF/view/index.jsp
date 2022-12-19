<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel='stylesheet' href='./css/index.css'/>
<link rel ='icon' href=''/>
<script src="//code.jquery.com/jquery-3.6.1.min.js"></script>
<script defer src='./js/index.js'></script>
<title>index.jsp</title>
</head>
<body>
<header>
	<div class='main_title'>
		<img src=''>
	</div>
	<nav>
	<a href='/'>HOME</a>
	<a href='/#' class='btnGuestBook'>방명록</a>
	<a href='/#' class='btnBoard'>게시판</a>
	</nav>
</header>

<div id='section'>
	<div class='guestbook'>최신 방명록
	<img alt="" src="images/coo.png" width="500px" height="650px">
	</div>
	
	<div class='board'>최신 게시물
	<img alt="" src="images/cake.png" width="500px" height="650px">
	</div>
</div>

<footer>
	Spring SPA Project<br/>
	2022-08
</footer>
</body>
</html>