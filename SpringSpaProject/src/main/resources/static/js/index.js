/**
 * index.js
 */
 
 //컨트롤러 가는것임 방명록
  $('.btnGuestBook').on('click', function(){
 	$('#section').load('/guestbook/guestbook_select');
 
 });
 
 //게시판
 $('.btnBoard').on('click', function(){
 	$('#section').load('/board/board_select');
 
 });
 
 //최근 방명록 10개
  $('.guestbook').on('click', function(){
 	$('.guestbook').load('/guestbook/guestbook10');
 
 });
 
  //최근 게시물 10개
  $('.board').on('click', function(){
 	$('.board').load('/board/board10');
 
 });
 
 
 /*
 
  //최근 방명록 10개
  $('#section>.board').load('/board/board10');
*/
 