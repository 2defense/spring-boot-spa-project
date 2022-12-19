package com.example.demo.guestbook;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.init.PlatformPlaceholderDatabaseDriverResolver;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.example.demo.mybatis.GuestbookMapper;

@Component //스프링이 메모리에 올려놓음 (auto wired - by type으로 찾겠다)
@Transactional // transaction 처리를 위함. 데이터 상태변화 시키기 위해 하는 작업
public class GuestbookDao {
	GPageVo gVo;
	
	@Autowired
	GuestbookMapper mapper; //매퍼도 가져와야징
	
	@Autowired
	PlatformTransactionManager manager; // transaction 얘만 autowired됨. 매니저 만들어주고
	
	TransactionStatus status; // transaction status도 만들어주고

	public int getTotSize(GPageVo gVo) {
		int totSize = 0;
		totSize = mapper.totSize(gVo);
		return totSize;
	}
	
	public List<GuestbookVo> select(GPageVo gVo){
		List<GuestbookVo> list = null;
		int totSize = getTotSize(gVo); //위에서 구한 totSize로

		this.gVo = gVo; //필드에 선언해줘야 가지고 다니지
		gVo.setTotSize(totSize); //set 해주고
		list = mapper.list(gVo); //mapper의 list 실행해서 list에 담기
		
		return list;
	}
	
	public boolean insert(GuestbookVo vo) {
		boolean b = false;
		status = manager.getTransaction(new DefaultTransactionDefinition());
		Object savePoint = status.createSavepoint();
		
		int cnt = mapper.insert(vo);
		
		if(cnt>0) {
			b=true;
			manager.commit(status);
		}else {
			status.rollbackToSavepoint(savePoint);
		}
		return b;
	}
	
	

	public boolean delete(GuestbookVo vo) {
		boolean b = false;
		status = manager.getTransaction(new DefaultTransactionDefinition());
		Object savePoint = status.createSavepoint();
		

		int cnt= mapper.delete(vo);
		
		if(cnt>0) {
			b=true;
			manager.commit(status);
		}else {
			status.rollbackToSavepoint(savePoint);
		}
		
		
		return b;
	}
	
	public boolean update(GuestbookVo vo) {
		boolean b = false;
		status = manager.getTransaction(new DefaultTransactionDefinition());
		Object savePoint = status.createSavepoint();
		

		int cnt= mapper.update(vo);
		
		if(cnt>0) {
			b=true;
			manager.commit(status);
		}else {
			status.rollbackToSavepoint(savePoint);
		}

		return b;
	}
	
	
	
	
	public GPageVo getgVo() {
		return gVo;
	}

	public List<GuestbookVo> selectTen(GPageVo gVo) {
		List<GuestbookVo> list = null;
		int totSize = getTotSize(gVo); //위에서 구한 totSize로

		this.gVo = gVo; //필드에 선언해줘야 가지고 다니지
		gVo.setTotSize(totSize); //set 해주고
		list = mapper.list(gVo); //mapper의 list 실행해서 list에 담기
		
		return list;
	}
	

	
}
