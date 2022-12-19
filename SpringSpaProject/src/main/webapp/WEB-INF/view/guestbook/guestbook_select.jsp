<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel='stylesheet' href='./css/guestbook.css' />
<script defer src='./js/guestbook.js'></script>
<title>guestbook/guestbook_select.jsp</title>
</head>
<body>	
	<div id='guestbook_list'>

		<%@include file='guestbook_insert.jsp'%>
		<hr />
		<div class='find'>
			<form name='frm_gb_search' class='frm_gb_search' method='post'>
				<input type='text' name='findStr' value='${gVo.findStr }' /> 
				<input type='button' value='검색' class='btnSearch' /> 
				<input type='hidden' name='nowPage' value='${gVo.nowPage }' /> 
			</form>
		</div>

		<div class='guestbook_btn'>
			<c:if test='${gVo.startNo>1 }'>
				<input type='button' value='&lt;' class='btnPrev'>
			</c:if>
			<c:if test='${gVo.totSize>gVo.endNo }'>
				<input type='button' value='&gt;' class='btnNext'>
			</c:if>
		</div>

		<hr />
		<div class='guestbook_items'>
			<c:forEach var='vo' items='${list }'>
				<div class='item'>


					<form name='frm_guestbook' class='frm_guestbook' method='post'>
						<div class='btnZone'>
							<input type='button' class='btnUpdate' value='내용수정'
								onclick='gb.modifyView(this.form)' /> <input type='button'
								class='btnDelete' value='삭제' onclick='gb.modalView(this.form)' />
						</div>
						<label>작성자</label><br /> <input type='text' name='id'
							value='${vo.id }' /><br /> <label>작성일</label><br /> 
						<input type='text' value='2022-12-09' name='nal' value='${vo.nal }' /><br />

						<label>내용</label><br />
						<textarea rows='5' cols='40' name='doc' class='doc' readonly>${vo.doc } 
						</textarea>
						<br /> 
						<input type='hidden' name='sno' value='${vo.sno }' />

						<div class='updateZone'>
							<label>암호</label><br /> <input type='text' name='pwd' /><br /> 
							<input type='button' class='btnGuestbookUpdate' value='수정'
								onclick='gb.update(this.form)' /> <input type='button'
								value='취소' onclick='gb.modifyCancel(this.form)' />
						</div>

					</form>
				</div>

			</c:forEach>
		</div>

	</div>

	<div id='modal'>
		<div id='content'>
			<input type='button' value='X' id='btnClose' /> 
			<span>암호를 입력하세요</span> 
			<input type='password' id='pwd' /> 
			<input type='button' value='확인' id='btnCheck' />
		</div>
	</div>


</body>
</html>