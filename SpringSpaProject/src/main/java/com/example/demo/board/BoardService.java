package com.example.demo.board;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.example.demo.mybatis.BoardMapper;

@Service //그래 얘가 서비스다
@Transactional
public class BoardService {
	PageVo pVo;
	
	@Autowired
	PlatformTransactionManager manager;
	TransactionStatus status;
	
	@Autowired
	BoardMapper boardMapper;
	
	Object savePoint;
	
	public List<BoardVo> select(PageVo pVo) {
		int totSize = boardMapper.totList(pVo);
		 pVo.setTotSize(totSize);
         this.pVo = pVo;
         
         List<BoardVo> list = boardMapper.select(pVo);
		return list;
	}
	
	public List<BoardVo> board10(){
		List<BoardVo> list = boardMapper.board10();
		return list;
	}

	public BoardVo view(int sno, String up){
		BoardVo bVo = null;
		if(up != null && up.equals("up")) {
			boardMapper.hitUp(sno);
		}
		bVo = boardMapper.view(sno);
		List<AttVo> attList = boardMapper.attList(sno);
		bVo.setAttList(attList);
		
		return bVo;
	}
	
	public boolean delete(BoardVo bVo) {
		boolean b = true;
		
		int replCnt = boardMapper.replCheck(bVo);
		
        if(replCnt>0) {
            b=false;
            return b;
        }
        
        //sno에 해당하는 테이블 삭제
        status = manager.getTransaction(new DefaultTransactionDefinition());
        Object savePoint = status.createSavepoint();
        
        int cnt = boardMapper.delete(bVo);
        if(cnt<1) {
        	b=false;
        } else {
        	List<String> attList = boardMapper.delFileList(bVo.getSno());
        	
        	if(attList.size()>0) {
        		if(attList.size()>0) {

        		cnt = boardMapper.attDeleteAll(bVo.getSno());
        		if(cnt>0) {
        		/*	String[] delList = attList.toArray(new String[0]);*/
        		/*	fileDelete(delList);*/
        			
        		}
        	}else {
        		b=false;
        	}
        }
        }
	
	if(b) manager.commit(status);
	else status.rollbackToSavepoint(savePoint);
		
		
		return b;
	}
	
	public boolean insertR(BoardVo vo) { 
		  status = manager.getTransaction(new DefaultTransactionDefinition());
	      this.savePoint = status.createSavepoint(); //필드에 있는거 쓸래
	      boolean flag = true;
	        
		int cnt = boardMapper.insertR(vo);
		
		if(cnt <1) { //정상 저장안되면 롤백
			flag = false;
		}else if(vo.getAttList().size()<0){
			int attCnt = boardMapper.insertAttList(vo.getAttList());
			if(attCnt<0)flag=false;
		}
		
		if(flag) {
			manager.commit(status);
		}else {
			status.rollbackToSavepoint(savePoint);
			String[] delFiles = new String[vo.getAttList().size()];
			for(int i=0;i<vo.getAttList().size();i++) {
				delFiles[i] = vo.getAttList().get(i).getSysFile();
			}
			fileDelete(delFiles);
		}
		
		return flag;
	}
	
	public void insertAttList(List<AttVo> attList) {
		int cnt = boardMapper.insertAttList(attList);
		if(cnt>0) {
			manager.commit(status);
			
		}else {
			status.rollbackToSavepoint(savePoint);
		}
	}
	
	public PageVo getpVo() {
		return pVo;
	}

	public boolean updateR(BoardVo bVo, String[] delFiles) {
		status = manager.getTransaction(new DefaultTransactionDefinition());
	    this.savePoint = status.createSavepoint(); //필드에 있는거 쓸래
	      
		boolean b = true;
		int cnt = boardMapper.update(bVo);
		if(cnt<1) { //수정된내용없다면
			b=false;
		}else if(bVo.getAttList().size()>0){//기존 첨부파일 있으면 
			int attCnt = boardMapper.attUpdate(bVo);//새로운 첨부파일 개수 구해줌
			if(attCnt<1) b= false;//없으면 false
		}
		
		if(b) {
			manager.commit(status);
			if(delFiles != null && delFiles.length>0) {
				//펌부파일 데이터 삭제
				cnt = boardMapper.attDelete(delFiles);//삭제할파일
				if(cnt>0) {              //있으면
					fileDelete(delFiles);//아래 메소드 실행
				}else {
					b = false;
				}
			}
		}else {
			status.rollbackToSavepoint(savePoint);
			
			delFiles = new String[bVo.getAttList().size()];
			for(int i=0;i<bVo.getAttList().size();i++) {
				delFiles[i] = bVo.getAttList().get(i).getSysFile();
			}
			fileDelete(delFiles);
		}
		
		return b;
	}

	public void fileDelete(String[] delFiles) {
		System.out.println("file deleting....");
		System.out.println(Arrays.toString(delFiles));

		for (String f : delFiles) {
			System.out.println(f + " is delete..");
			File file = new File(FileUploadController.path + f); //파일저장경로
			if (file.exists())
				file.delete(); //삭제
		}
	}

	public boolean replR(BoardVo bVo) {
		status = manager.getTransaction(new DefaultTransactionDefinition());
	    this.savePoint = status.createSavepoint(); //필드에 있는거 쓸래
	      
		//추가 답글 달릴 때마다 sno 하나씩 증가되므로 다른작업 동시에 할 수 없게 싱크로나이즈드 썼음
	    boolean b=true;
	    boardMapper.seqUp(bVo);//증가된 sno가지고 
	    int cnt = boardMapper.replR(bVo);//실행
	    if(cnt<1) {
	        b=false;
	    }else if(bVo.getAttList().size()>0) {
	       int attCnt = boardMapper.insertAttList(bVo.getAttList());
	       if(attCnt<0) b = false;
	    }
   
	    else  {
	    	status.rollbackToSavepoint(savePoint);
	    	status.rollbackToSavepoint(savePoint);
			
			String[] delFiles = new String[bVo.getAttList().size()];
			for(int i=0;i<bVo.getAttList().size();i++) {
				delFiles[i] = bVo.getAttList().get(i).getSysFile();
			}
			fileDelete(delFiles);
	    }
	       
	    return b;
		}
	
	
	
	
}
