package com.korea.test;

import java.util.List;

import org.junit.Test;

import com.korea.dao.BoardDAO;
import com.korea.dto.BoardDTO;
//juit : server 비활성화 상태에서 특정 테스트 진행 
public class DaoTest {

//	@Test
//	public void test() {
//		MemberDTO dto = new MemberDTO();
//		dto.setEmail("example@example.com");
//		dto.setPwd("1234");
//		dto.setAddr1("대구");
//		dto.setAddr2("아파트아파트");
//		
//		MemberDAO dao = MemberDAO.getInstance();
//		boolean result=dao.insert(dto);
//		
//		if(result)
//			System.out.println("Insert 성공");
//		else
//			System.out.println("Insert 실패");
//	}
	
//	@Test
//	public void test2() {
//		//MemberDAO의 Select(email) test
//		MemberDAO dao=MemberDAO.getInstance();
//		MemberDTO dto=dao.Select("dbsgpwl97@naver.com");
//		System.out.println("결과 : "+dto.toString());
//	}
	
//	@Test
//	public void test3() {
//		MemberDTO dto = new MemberDTO();
//		dto.setEmail("gpwl@gpwl");
//		dto.setPwd("1234");
//		dto.setAddr1("서울");
//		dto.setAddr2("우하하");
//		
//		MemberDAO dao = MemberDAO.getInstance();
//		dao.Update(dto);
//	}
	
//	@Test
//	public void test4() {
//		MemberDTO dto = new MemberDTO();
//		dto.setEmail("admin@admin.com");
//		dto.setPwd("1234");
//		dto.setAddr1("");
//		dto.setAddr2("");
//		dto.setGrade(2);
//		
//		MemberService service=MemberService.getInstance();
//		service.MemberInsert(dto); //관리자 계정 등록
//
//		
//		dto.setEmail("guest@guest.com");
//		dto.setPwd("1234");
//		dto.setAddr1("");
//		dto.setAddr2("");
//		dto.setGrade(0);
//		
//		service.MemberInsert(dto); //게스트 계정 등록
		
//	}
//	@Test
//	public void test4() {
//		BoardDAO dao = BoardDAO.getInstance();
//		List<BoardDTO> list = dao.Select(1, 10);
//		System.out.println(list.size());
//		
//		list.forEach(dto -> System.out.println(dto));
//	}
//	@Test
//	public void test5() {
//		BoardDAO dao = BoardDAO.getInstance();
//		int result = dao.getTotalCount();
//		System.out.println(result);
//	}
//	
//	@Test
	
//	public void test6() {
//		
//		BoardDAO dao = BoardDAO.getInstance();
//		BoardDTO dto = new BoardDTO();
//		
//		dto.setTitle("New");
//		dto.setContent("내용");
//		dto.setWriter("나");
//		dto.setPwd("123114");
//		dto.setIp("192.160.10.1");
//		
//		
//		dao.Insert(dto);
//	}
	
	@Test
	public void test7() {
		BoardDAO dao = BoardDAO.getInstance();
		BoardDTO dto = dao.Select(500);
		System.out.println(dto);
	}
	

}
