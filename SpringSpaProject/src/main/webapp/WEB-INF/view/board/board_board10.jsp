<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>

<div class='board10'>

<c:forEach var='vo' items="${list }">

<DIV>
	<b>[${vo.sno }]</b> <span>${vo.subject }</span>
</DIV>

</c:forEach>
</div>