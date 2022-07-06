package com.korea.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.korea.dao.MemberDAO;
import com.korea.dto.MemberDTO;

public class DaoTest {
	
	// 
	@Test
	public void test() {
		MemberDTO dto = new MemberDTO();
		dto.setEmail("example@example.com");
		dto.setPwd("1234");
		dto.setAddr1("대구");
		dto.setAddr2("00아파트");
		
		MemberDAO dao = MemberDAO.getInstance();
		boolean result =dao.insert(dto);
		
		if(result) {
			System.out.println("INSERT성공");
		}else {
			System.out.println("INSERT실패");
		}
	}

}
