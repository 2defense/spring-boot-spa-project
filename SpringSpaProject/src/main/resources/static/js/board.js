/**
 * 게시판용. 자바스크립트 라이브러리 충돌 최소화 하기위해 즉시 실행함수 썼음
 */
 
 (board = function(){
    var frm = $('.frm')[0];
    var param;
    var url='board/board_main.jsp?';
    
        $('.btnSelect').on('click', function(){
        param = $(frm).serialize();
        $.post("/board/board_select", param, function(data){
            $('#section').html(data);
        })
    })
    
    $('.btnSearch').on('click', function(){
        frm = $('.frm_search')[0];
        frm.nowPage.value = 1;
        param = $(frm).serialize();
        $.post("/board/board_select", param, function(data){
            $('#section').html(data);
        })
       
    })
    
//입력폼.     
    $('.btnInsert').on('click', function(){
        param = $('.frm_search').serialize();
        $.post("/board/board_insert", param, function(data){
            $('#section').html(data);
        })
    })
//입력 - 저장    
    $('.btnInsertR').on('click', function(){
        var frm = $('.frm')[0];
        var param = new FormData(frm);
       
        $.ajax({
            type : 'POST',
            url : 'board/board_insertR',
            contentType : false,
            processData : false,
            data : param,
            dataType : 'html',
            success : function(data){
				if(data != "")alert(data);
				frm.enctype='';
				param=$(frm).serialize();
                $('#section').load("/board/board_select",param);
            }
        })
    })
    
//수정-------------------------------------------------
    $('.btnUpdate').on('click', function(){
        param = $(frm).serialize();
        $.post("/board/board_update", param, function(data){
            $('#section').html(data);
        })
    })
    $('.btnUpdateR').on('click', function(){
        var frm = $('.frm')[0];
        var param = new FormData(frm);
       
        $.ajax({
            type : 'POST',
            url : '/board/board_updateR',
            contentType : false,
            processData : false,
            data : param,
            dataType : 'html',
            success : function(data){
               if(data != "")alert(data);
				frm.enctype='';//멀티파트해제
				param=$(frm).serialize();
                $('#section').load("/board/board_select",param);
            }
        })
    })
    
//댓글 -----------------------------------------------------------
    $('.btnRepl').on('click', function(){
        param = $(frm).serialize();
        $.post("/board/board_repl", param, function(data){
            $('#section').html(data);
        })
    })
    $('.btnReplR').on('click', function(){
        var frm = $('.frm')[0];
        var param = new FormData(frm);
       
        $.ajax({
            type : 'POST',
            url : "/board/board_replR",
            contentType : false,
            processData : false,
            data : param,
            dataType : 'html',
            success : function(data){
                if(data != "")alert(data);
				frm.enctype='';
				param=$(frm).serialize();
                $('#section').load("/board/board_select",param);
            }
        })
    })
    /*삭제 */
    $('.btnDeleteR').on('click', function(){
        let yn = confirm('자료를 삭제하시겠습니까 ?');
        if( !yn ) return;
       
        param = $('.frm').serialize();
        $.post("/board/board_delete", param, function(data){
            $('#section').html(data);
        })
    })
    board.move = function(nowPage){
        frm = $('.frm_search')[0];
        frm.nowPage.value = nowPage;
        param = $(frm).serialize();
        $.post("/board/board_select", param, function(data){
            $('#section').html(data);
        })
    }
    board.view = function(sno){
        frm = $('.frm_search')[0];
        frm.sno.value = sno;
        param = $(frm).serialize();
        $.post("/board/board_view", param, function(data){
            $('#section').html(data);
        })
    }   
})() 
