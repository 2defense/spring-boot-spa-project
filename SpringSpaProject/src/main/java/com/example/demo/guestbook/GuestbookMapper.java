package com.example.demo.guestbook;

import java.util.List;

public interface GuestbookMapper {
	public List<GuestbookVo> list(String findStr);

	public int totSize();
}
