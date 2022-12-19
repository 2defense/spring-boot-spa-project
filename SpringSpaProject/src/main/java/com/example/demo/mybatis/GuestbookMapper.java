package com.example.demo.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.demo.guestbook.GPageVo;
import com.example.demo.guestbook.GuestbookVo;

@Repository
@Mapper
public interface GuestbookMapper {
	//메소드명 = mapper id, 매개변수 = parameterType, 반환형 = resulttype 
	public int totSize(GPageVo vo); 
	public List<GuestbookVo> list(GPageVo vo);
	public int insert(GuestbookVo vo);
	public int delete(GuestbookVo vo);
	public int update(GuestbookVo vo);
	public List<GuestbookVo> listTen(GPageVo vo);
}
