<%@page import="com.douzone.guestbook01.dao.Guestbook01Dao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	Long no = Long.parseLong(request.getParameter("no"));
	String password = request.getParameter("password");
	
	new Guestbook01Dao().delete(no, password);
	
	response.sendRedirect("index.jsp");
%>