<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SPA Mysql</title>
<link rel='stylesheet' href='css/board.css'>
<script src='js/board.js'></script>
</head>
<body>
<div id='board'>
    <h2>게시판</h2>
    <form class='frm frm_view'>
        <label>작성자</label>
        <input type='text' name='id' value='${bVo.id }'/>
        
        <label>작성일</label>
        <span>${bVo.nal }</span>
        
        <hr/>
        <label>제목</label>
        <input type='text' name='subject' value='${bVo.subject }'/>
        
        <span>[조회: ${bVo.hit }]</span>
    
        <div class='doc'>
            ${bVo.doc }
        </div>
        <div class='attFileZone'>
            <c:forEach var="att" items='${bVo.attList }'>
                <span class='attFile'>
               		<img src='/upload/${att.sysFile}'/><br/>
                    <a href='/upload/${att.sysFile }' download='${att.oriFile }'>
                        ${att.oriFile }
                    </a>
                </span>
            </c:forEach>
        </div>
        <br/>
        <div class='btnZone'>
            <input type='button' value='목록' class='btnSelect'>
            <input type='button' value='수정' class='btnUpdate'>
            <input type='button' value='삭제' class='btnDeleteR'>
            <input type='button' value='댓글' class='btnRepl'>
           
            <input type='text' name='findStr' value='${pVo.findStr }'/>
            <input type='text' name='nowPage' value='${pVo.nowPage }'/>
            <input type='text' name='sno' value='${bVo.sno }'/>
            <input type='text' name='grp' value='${bVo.grp }'/>
            <input type='text' name='seq' value='${bVo.seq }'/>
            <input type='text' name='deep' value='${bVo.deep }'/>
        </div>
       
    </form>
</div>
</body>
</html>