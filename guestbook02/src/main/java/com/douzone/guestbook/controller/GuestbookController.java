package com.douzone.guestbook.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.guestbook01.dao.Guestbook01Dao;
import com.douzone.guestbook01.vo.Guestbook01Vo;



public class GuestbookController extends HttpServlet {
	private static final long serialVersionUID = 1L;
//test
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String action = request.getParameter("a");
		
		if("del".equals(action)) {
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/deleteform.jsp");
			rd.forward(request, response);
			
		} else if ("add".equals(action)){
			
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String message = request.getParameter("message");
			
			Guestbook01Vo vo = new Guestbook01Vo();
			vo.setName(name);
			vo.setPassword(password);
			vo.setMsg(message);
			
			new Guestbook01Dao().insert(vo);
			
			response.sendRedirect("/guestbook02/el");
			
		} else if ("check".equals(action)){
			
			Long no = Long.parseLong(request.getParameter("no"));
			String password = request.getParameter("password");
			
			new Guestbook01Dao().delete(no, password);
			
			response.sendRedirect("/guestbook02/el");
			
		} else {
			
			Guestbook01Dao dao = new Guestbook01Dao(); 
			List<Guestbook01Vo> list = dao.findAll();
			
			request.setAttribute("list", list);
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/index.jsp");
			rd.forward(request, response);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
