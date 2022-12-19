package com.example.demo.guestbook;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class GuestbookController {
	@Autowired //얘로 dao가져올거임 bean으로 만들자
	GuestbookDao dao;

	@RequestMapping("/guestbook/guestbook_select") //이 매핑 정보가 들어왔을 때
	public ModelAndView select(GPageVo gVo) {//매개변수로 pagevo 넣으면 들어옴 신기하다!(RESTCONTROLLER 있기에 가능)
		ModelAndView mv = new ModelAndView();
		// service or dao
		
		//검색어에 따른 LIST 가져옴
		List<GuestbookVo> list = dao.select(gVo); //dao의 select 메소드 리턴값을 list로
		
		System.out.println("NOWPAGE: " + gVo.getNowPage());
		System.out.println("TOTPAGE: " + gVo.getTotPage());
		//LIST를 model & view에 추가
		gVo = dao.getgVo(); //새로 갱신된 페이지 정보
		
		mv.addObject("gVo",gVo); //페이징 처리 위해 얘도 날려줌
		mv.addObject("list",list);
		mv.setViewName("guestbook/guestbook_select"); //view에 세팅한대로 문장완성
		return mv;
	}
	
	@RequestMapping("/guestbook/guestbook_insert")
	public void insert(GuestbookVo vo,HttpServletResponse resp) { //js의 param일로 set됨
		String msg="";
		
		boolean b = dao.insert(vo);
		
		try {
		PrintWriter out = resp.getWriter();
		if( !b ) {
			out.print("<script>");
			out.print(" alert('저장중 오류발생')");
			out.print("</script>");
		}
		}catch(Exception e) {
			e.printStackTrace();
		}

	}
	
	@RequestMapping("/guestbook/guestbook_delete")
	public void delete(GuestbookVo vo,HttpServletResponse resp) { //js의 param일로 set됨
		
		boolean b = dao.delete(vo);
		
		try {
		PrintWriter out = resp.getWriter();
		if( !b ) {
			out.print("<script>");
			out.print(" alert('저장중 오류발생')");
			out.print("</script>");
		}
		}catch(Exception e) {
			e.printStackTrace();
		}

	}
	
	@RequestMapping("/guestbook/guestbook_update")
	public void update(GuestbookVo vo,HttpServletResponse resp) { //js의 param일로 set됨
		
		boolean b = dao.update(vo);
		
		try {
		PrintWriter out = resp.getWriter();
		if( !b ) {
			out.print("<script>");
			out.print(" alert('수정 중 오류발생')");
			out.print("</script>");
		}
		}catch(Exception e) {
			e.printStackTrace();
		}

	}
	
	@RequestMapping("/guestbook/guestbook10") //이 매핑 정보가 들어왔을 때
	public ModelAndView guestbookTen(GPageVo gVo) {//매개변수로 pagevo 넣으면 들어옴 신기하다!(RESTCONTROLLER 있기에 가능)
		ModelAndView mv = new ModelAndView();
		// service or dao
		
		//검색어에 따른 LIST 가져옴
		List<GuestbookVo> list = dao.select(gVo); //dao의 select 메소드 리턴값을 list로
		
		System.out.println("NOWPAGE: " + gVo.getNowPage());
		System.out.println("TOTPAGE: " + gVo.getTotPage());
		//LIST를 model & view에 추가
		gVo = dao.getgVo(); //새로 갱신된 페이지 정보
		
		//페이징 처리 위해 얘도 날려줌
		mv.addObject("list",list);
		mv.setViewName("guestbook/guestbook_ten"); //view에 세팅한대로 문장완성
		return mv;
	}
	
}
