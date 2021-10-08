
<%@page import="com.douzone.guestbook01.dao.Guestbook01Dao"%>
<%@page import="com.douzone.guestbook01.vo.Guestbook01Vo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	String name = request.getParameter("name");
	String password = request.getParameter("password");
	String message = request.getParameter("message");
	
	Guestbook01Vo vo = new Guestbook01Vo();
	vo.setName(name);
	vo.setPassword(password);
	vo.setMsg(message);
	
	new Guestbook01Dao().insert(vo);
	
	response.sendRedirect("index.jsp");
%>