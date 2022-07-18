package com.korea.controller.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.korea.controller.SubController;
import com.korea.dto.BoardDTO;
import com.korea.service.BoardService;

public class BoardReadController implements SubController{
	BoardService service = BoardService.getInstance();
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		
		String no = req.getParameter("no");
		String nowPage = req.getParameter("nowPage");
		
		
		//서비스 실행
		int num = Integer.parseInt(no);
		BoardDTO dto = service.getBoardDTO(num);
		
		//세션에 읽고있는 게시물 저장(수정 삭제 이동시 현재 읽는 게시물 확인하기 쉽다.)
		HttpSession session = req.getSession();
		session.setAttribute("dto", dto);
		
		//뷰로 이동
		try {
			req.setAttribute("dto", dto);
			req.setAttribute("nowPage", nowPage);
			req.getRequestDispatcher("/WEB-INF/board/read.jsp").forward(req, resp);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
