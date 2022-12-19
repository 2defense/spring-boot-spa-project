<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.Date" %>
<%@taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel='stylesheet' src='./css/guestbook.css'/>
<title>guestbook_insert.jsp</title>
</head>
<body>
<div class='gInsert'>
	<h3>guestbook_insert</h3>
	
	<form name='frm_guestbook' class='frm_guestbook_insert' method='post'>
		<span>
		<label>작성자</label>
		<input type='text' name='id'/>
		</span>
		<br/>
		
		<span>
		<label>암호</label>
		<input type='text' name='pwd'/>
		</span>
		<br/>
		
		<span>
		<label>작성일</label>
		<output>
		
			<fmt:formatDate value="<%=new Date() %>" pattern="yyyy-MM-dd"/>
			
		</output>
		</span>
		<br/>
		
		<label>[내용]</label><br/>
		<textarea rows='10' cols='50' name='doc'>
		</textarea><br/>

		<hr/>
		<input type='button' class='btnSave' value='작성'/>
	</form>
</div>

</body>
</html>