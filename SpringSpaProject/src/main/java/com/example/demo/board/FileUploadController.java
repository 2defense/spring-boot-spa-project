package com.example.demo.board;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileUploadController {
	static String path ="C:\\Users\\y\\eclipse-workspace\\SpringSpaProject\\src\\main\\resources\\static\\upload\\";
	
	@Autowired
	BoardService service;
	
	@RequestMapping("/board/board_insertR")
	public synchronized String insertR(@RequestParam("attFile") List<MultipartFile> mul,
			@ModelAttribute BoardVo vo) {
		String msg="";
		try {
			System.out.println("id : " + vo.getId());
			System.out.println("sub : " + vo.getSubject());
			List<AttVo> attList = new ArrayList<AttVo>();
			
			//본문 내용 저장
			attList = fileupload(mul);
			boolean flag = service.insertR(vo);
			if(!flag) {
				msg= "저장중오류발생";
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return msg;
	}
	
	@RequestMapping("/board/board_updateR")
	public String updateR(@RequestParam("attFile")List<MultipartFile> mul,
			@ModelAttribute BoardVo bVo,
			@ModelAttribute PageVo pVo, 
			@RequestParam(name="delFile", required =false) String[] delFiles) {
		//
		String msg="";
		try {
			List<AttVo> attList = fileupload(mul);
			bVo.setAttList(attList);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		boolean flag = service.updateR(bVo, delFiles);
		if(!flag)msg="수정중오류발생";
		return msg;
	}
	
	//file upload 공통부분(입력, 수정, 댓글)
	public List<AttVo> fileupload(List<MultipartFile> mul)throws Exception{
		List<AttVo> attList = new ArrayList<AttVo>();
		
		for(MultipartFile m : mul) {
			if(m.isEmpty()) continue; //파일이 없어도 진행되게
			
			UUID uuid = UUID.randomUUID(); //랜덤 숫자. 이름중복 방지
			String oriFile = m.getOriginalFilename();
			String sysFile = "";
			File temp = new File(path + oriFile);//path + 파일명 -> temp
			m.transferTo(temp); // 선택한 파일 -> temp로 
			
			sysFile = (uuid.getLeastSignificantBits()*-1) + "-" + oriFile; //경로+랜덤문자+파일명
			File f = new File(path + sysFile); 
			temp.renameTo(f); //f로 이름 바꿔줌
			
			AttVo attVo = new AttVo();//boardVo가 먼저 만들어져서 sno가 있어야 pSno도 추가되어야겠찌
			attVo.setOriFile(oriFile);
			attVo.setSysFile(sysFile);
			//attVo.setpSno(pVo.getSno()); //미확정
			
			attList.add(attVo); //이거 있어야 넘어감
			
			System.out.println(uuid.toString());
		}
		
		return attList;
	}
	
	//댓글
	@RequestMapping("/board/board_replR")
	public synchronized String replR(@RequestParam("attFile") List<MultipartFile> mul,
			@ModelAttribute BoardVo bVo, @ModelAttribute PageVo pVo) {
			String msg="";
		try {
			List<AttVo> attList = new ArrayList<AttVo>();
			attList = fileupload(mul);
			bVo.setAttList(attList);//저장하고 실행해야지
			//본문 내용 저장
			boolean flag = service.replR(bVo);
			if(!flag)return "댓글 오류발생";
			
			
			service.insertAttList(attList);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return msg;
	}
	
	
	
}
