package com.korea.service;

import java.util.List;

import org.mindrot.bcrypt.BCrypt;

import com.korea.dao.BoardDAO;
import com.korea.dto.BoardDTO;

public class BoardService {
	BoardDAO dao = BoardDAO.getInstance();
	public BCrypt passwordEncoder = new BCrypt(); //salt table이 포함 /패스워드 암호화
	
	//싱글톤 패턴
	private static BoardService instance=null;
	public static BoardService getInstance() {
		if(instance==null)
			instance=new BoardService();
		return instance;
	}
	private BoardService() {}
	
	public List<BoardDTO> getBoardList(int start, int end){
		return dao.Select(start, end);
	}
	public int getTotalCount() {
		return dao.getTotalCount();
	}
}
