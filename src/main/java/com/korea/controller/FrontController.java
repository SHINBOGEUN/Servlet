package com.korea.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.korea.controller.member.MemberJoinController;


public class FrontController extends HttpServlet{
	//URL 서브컨트롤러 객체
	HashMap<String, SubController> list = null;
	
	@Override
	public void init() throws ServletException {
		list = new HashMap();
		
		list.put("/MemberJoin.do", new MemberJoinController());
		
	}
	
	
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		
		String url = req.getRequestURI();
		System.out.println("url : " + url);
		SubController sub = list.get(url);
		if(sub !=null) {
			list.get(url).execute(req, resp);;			
		}
	}

	
}
