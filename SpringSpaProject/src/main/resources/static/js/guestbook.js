/**
 * 
 */

 	(gb = function(){
	 
	 //검색버튼
	 $('.btnSearch').on('click',function(){
		 let frm = $('.frm_gb_search')[0];
		 frm.nowPage.value = 1;
		 let param = $(frm).serialize();
		 $('#section').load('/guestbook/guestbook_select',param);
	 });
	 //페이지 전으로 이동 
	 $('.btnPrev').on('click',function(){
		  let frm = $('.frm_gb_search')[0];
		 frm.nowPage.value = Number(frm.nowPage.value)-1;
		 let param = $(frm).serialize();
		 $('#section').load('/guestbook/guestbook_select',param);
	 });
	 //페이지 다음으로 이동
	 	 $('.btnNext').on('click',function(){
		  let frm = $('.frm_gb_search')[0];
		 frm.nowPage.value = Number(frm.nowPage.value)+1;
		 let param = $(frm).serialize();
		 $('#section').load('/guestbook/guestbook_select',param);
	 });
 //저장버튼. 저장성공하면 검색창 부분 정보 다시 가져와서 select페이지 다시 로드 해줄것임
 	 	 $('.btnSave').on('click',function(){
		  let frm = $('.frm_guestbook_insert')[0];
		 let param = $(frm).serialize();
		 $.post('/guestbook/guestbook_insert',param,function(){
			  frm = $('.frm_gb_search')[0];
			 param = $(frm).serialize();
			 $('#section').load('/guestbook/guestbook_select',param);
		 });
	 });
 //수정하기 버튼 누르면 버튼 생김
 gb.modifyView = function(frm){
	 let div = frm.querySelector('.updateZone');
	 let doc = frm.doc;
	 div.style.visibility='visible';
	 $(doc).attr('readonly',false); 
 }
 //취소버튼 누르면 버튼 숨겨짐
 gb.modifyCancel= function(frm){
	 let div=frm.querySelector('.updateZone');
	 div.style.visibility='hidden';
	 }
	//modal box-------------
	$('#btnClose').on('click',function(){
		$('#modal').css('display','none');
	});
	var delForm; //변수선언원래 맨위에 해주는게맞음
	gb.modalView = function(frm){
		delForm = frm;
		$('#modal').css('display','block');
	}
	
	//방명록 삭제--------------------
	$('#btnCheck').on('click',function(){
		alert('삭제버튼');
		delForm.pwd.value=$('#pwd').val();
		
		let frm = delForm;

		 let param = $(frm).serialize();
		 $.post('/guestbook/guestbook_delete',param,function(){
			  frm = $('.frm_gb_search')[0];
			 param = $(frm).serialize();
			 $('#section').load('/guestbook/guestbook_select',param);
		});
	
	});
	
	/*방명록 수정------------------------- */
	gb.update=function(frm){
		let param = $(frm).serialize();
		 $.post('/guestbook/guestbook_update',param,function(){
			frm = $('.frm_gb_search')[0];
			param = $(frm).serialize();
			$('#section').load('/guestbook/guestbook_select',param);
		});
	
	};

 	})();