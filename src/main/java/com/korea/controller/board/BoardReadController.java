package com.korea.controller.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.korea.controller.SubController;
import com.korea.dto.BoardDTO;
import com.korea.service.BoardService;

public class BoardReadController implements SubController{
	BoardService service = BoardService.getInstance();
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		
		String no = req.getParameter("no");
		String nowPage = req.getParameter("nowPage");
		int num = Integer.parseInt(no);
		BoardDTO dto = service.getBoardDTO(num);
		
		
		try {
			req.setAttribute("dto", dto);
			req.setAttribute("nowPage", nowPage);
			req.getRequestDispatcher("/WEB-INF/board/read.jsp").forward(req, resp);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
