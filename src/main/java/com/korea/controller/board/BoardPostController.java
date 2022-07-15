package com.korea.controller.board;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.korea.controller.SubController;
import com.korea.dto.BoardDTO;
import com.korea.service.BoardService;

public class BoardPostController implements SubController {
	BoardService service = BoardService.getInstance();
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		String flag = req.getParameter("flag");
		try {
			if(flag == null) { // list.jsp에서 글쓰기 버튼을 누른 경우
				req.getRequestDispatcher("/WEB-INF/board/post.jsp").forward(req,resp);
			}else { 	//Post.jsp에서 등록할 내용을 기입하고 글쓰기 버튼누른 경우
				//1파라미터 
				String title = req.getParameter("title");
				String content = req.getParameter("content");
				String pwd = req.getParameter("pwd");
				String ip = req.getRemoteAddr();
				
				HttpSession session = req.getSession();
				String writer = (String)session.getAttribute("email");
				//2입력값 검증
				if(title == null || content == null || pwd == null ||ip ==null) {
					req.getRequestDispatcher("/WEB-INF/board/post.jsp").forward(req,resp);
					return;
				}
				//3서비스실행
				BoardDTO dto = new BoardDTO();
				dto.setTitle(title);
				dto.setContent(content);
				dto.setPwd(pwd);
				dto.setIp(ip);
				dto.setWriter(writer);
				
				//추가파일전달 part 전달
				
				ArrayList<Part> parts = (ArrayList<Part>) req.getParts();
				boolean result = false;
				
				System.out.println(parts.get(3).getName());
				
				long size = parts.get(3).getSize();
				if(size == 0) {
					System.out.println("1");
					result = service.PostBoard(dto);
				}else {
					System.out.println("2");
					result = service.PostBoard(dto,parts);
				}
				
				
				if(result) {
//					int tcnt = service.getTotalCount();
//					req.setAttribute("tcnt", tcnt);
//					ArrayList<BoardDTO> list = (ArrayList<BoardDTO>) service.getBoardList(1, 10);
//					req.setAttribute("list", list);
//					req.getRequestDispatcher("/WEB-INF/board/list.jsp?nowPage=1").forward(req,resp);
					resp.sendRedirect("/Board/list.do");
					return;
				}else {
					req.getRequestDispatcher("/WEB-INF/board/post.jsp").forward(req,resp);
				}
				
				//4View
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
