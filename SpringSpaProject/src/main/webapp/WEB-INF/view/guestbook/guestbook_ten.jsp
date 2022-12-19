<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>

<div class='guestbook_items'>
			<c:forEach var='vo' items='${list }'>
				<div class='item'>

						<label>작성자</label><br /> 
						<input type='text' name='id' value='${vo.id }' /><br /> 
						<label>작성일</label><br /> 
						<input type='text' value='${vo.nal } ' name='nal' value='${vo.nal }' /><br />

						<label>내용</label><br />
						<textarea rows='5' cols='40' name='doc' class='doc' readonly>${vo.doc } 
						</textarea>
						<hr/>
				</div>
			</c:forEach>
		</div>
