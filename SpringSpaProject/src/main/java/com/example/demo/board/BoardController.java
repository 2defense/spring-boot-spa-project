package com.example.demo.board;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class BoardController {
	
	@Autowired
	BoardService service;

	@RequestMapping("/board/board_select")
	public ModelAndView select(PageVo pVo) {
		ModelAndView mv = new ModelAndView();
		List<BoardVo> list = service.select(pVo);
		
		mv.addObject("pVo",pVo);
		mv.addObject("list",list);
		mv.setViewName("board/board_select");
		return mv;
	}
	
	@RequestMapping("/board/board10")
	public ModelAndView board10() {
		ModelAndView mv = new ModelAndView();
		List<BoardVo> list = service.board10();
		
		mv.addObject("list", list);
		mv.setViewName("/board/board_board10");
		
		return mv;
	}
	
	@RequestMapping("/board/board_view")
	public ModelAndView view(BoardVo bVo, PageVo pVo) {
		ModelAndView mv = new ModelAndView();
		
		bVo = service.view(bVo.getSno(),"up");
		
		String temp = bVo.getDoc();
		bVo.setDoc(temp.replace("\n", "<br/>"));
		mv.addObject("bVo",bVo);
		mv.addObject("pVo",pVo);
		
		return mv;
	}
	
	@RequestMapping("/board/board_delete")
	public ModelAndView delete(BoardVo bVo, PageVo pVo) {
		String msg = "";
		ModelAndView mv = new ModelAndView();
		
		boolean b = service.delete(bVo);
		
		if(!b) {
			msg = "오류";
		}

		mv = select(pVo);
		mv.addObject("msg",msg);
		mv.addObject("pVo",pVo);
		mv.setViewName("/board/board_select");
		
		return mv;
	}

	//입력창 생성 - 창 띄울거라서 페이지값만 넘겨주면 된다.
	@RequestMapping("/board/board_insert")
	public ModelAndView insert(PageVo pVo) {
		ModelAndView mv = new ModelAndView();

		mv.addObject("pVo",pVo);

		mv.setViewName("board/board_insert");
		return mv;
	}
	
//수정페이지로
	@RequestMapping("/board/board_update")
	public ModelAndView update(PageVo pVo) {
		ModelAndView mv = new ModelAndView();
		BoardVo bVo = service.view(pVo.getSno(), "");//수정시에는 조회수 안올라가니까 no up

		mv.addObject("bVo",bVo);
		mv.addObject("pVo",pVo);

		mv.setViewName("board/board_update");
		return mv;
	}

	@RequestMapping("/board/board_repl")
	public ModelAndView repl(PageVo pVo, BoardVo bVo) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("bVo",bVo);
		mv.addObject("pVo",pVo);
		mv.setViewName("board/board_repl");
		return mv;
	}
	
	
	
	
	
	
	
	
	
}
