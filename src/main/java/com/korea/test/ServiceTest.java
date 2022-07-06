package com.korea.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.korea.dao.MemberDAO;
import com.korea.dto.MemberDTO;
import com.korea.service.MemberService;

public class ServiceTest {

	@Test
	public void test() {
		MemberDTO dto = new MemberDTO();
		dto.setEmail("example@exassmple.com");
		dto.setPwd("1234");
		dto.setAddr1("대구");
		dto.setAddr2("00아파트");
		MemberService service = MemberService.getInstance();
		service.MemberInsert(dto);
	}
}
