package com.korea.service;

import com.korea.dao.MemberDAO;
import com.korea.dto.MemberDTO;

public class MemberService {
	
	MemberDAO dao = MemberDAO.getInstance();
	
	
	private static MemberService instance = null; 
	public static MemberService getInstance() {
		if(instance == null) {
			instance = new MemberService();
		}
		return instance;
	}
	private MemberService() {}
	
	public boolean MemberInsert(MemberDTO dto) {
		return dao.insert(dto);
	}
}
