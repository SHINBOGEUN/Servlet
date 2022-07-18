package com.korea.controller.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.korea.controller.SubController;
import com.korea.dto.BoardDTO;
import com.korea.service.BoardService;

public class BoardListController implements SubController {
	BoardService service = BoardService.getInstance();
	
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		
		try {
			String tmpstart = req.getParameter("start");
			String tmpend = req.getParameter("end");
			String nowPage = req.getParameter("nowPage");
			
			int start = 0;
			int end = 0;
			if(tmpstart == null || tmpend == null) {
				 start = 1;
				 end = 10;
			}else {
				start = Integer.parseInt(tmpstart);
				end = Integer.parseInt(tmpend);
			}
			
			List<BoardDTO> list = service.getBoardList(start, end);
			int tcnt = service.getTotalCnt();
			
			
			req.setAttribute("list", list);
			req.setAttribute("tcnt", tcnt);
			
			//정리 6일차 (페이지 정리)
			req.setAttribute("nowPage", nowPage);
			
			req.getRequestDispatcher("/WEB-INF/board/list.jsp").forward(req,resp);
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
