## Servlet 클래스 작성

![image-20211008153321337](img/image-20211008153321337.png)



### 서블릿 작성 방법

**서블릿은 HttpServlet 클래스를 상속받는다. HttpServlet 클래스를 사용하기 위해서는 javax.servlet, javax.servlet.http, 그리고 java.io 패키지를 임포트 해야 한다.**

**HttpServlet 클래스의 doGet 메소드를 재정의(overriding)하고, GET 방식을 사용하는 모든 HTTP 
 요청을 처리한다.**

**HttpServlet 클래스의 doPost 메소드를 재정의(overriding)하고, POST 방식을 사용하는 모든 HTTP 
 요청을 처리한다.**

**doGet과 doPost 메소드는 웹 서버가 전달한 두 개의 객체를 인자로 받는다 : (1) HttpServletRequest 
 객체(*request* 객체*)*, 그리고 (2) HttpServletResponse 객체(*response* 객체*)***

**response 객체의 setContentType 메소드는 브라우저에 전달되는 응답의 콘텐트 타입(content type)을 설정한다. ** 

**response 객체의 getWriter 메소드는 브라우저에 HTML 코드를 보내는 PrinterWriter 객체를 리턴한다.**

**getWriter 메소드가 적절한 PrintWriter 객체를 리턴하기 위해서는, 반드시 PrintWriter 객체를 생성하기 전에 콘텐트 타입을 설정해야 한다. **



## 포워드 

- 서블릿에서 서블릿, 서블릿에서 JSP로 연동하는 방법

- 요청에 대한 추가 작업을 다른 서블릿에게 수행하게 한다

- 요청에 포함된 정보를 다른 서블릿이나 JSP와 공유할 수 있다

- 요청에 정보를 포함시켜 다른 서블릿에게 전달할 수 있다

- 모델2 개발시 서블릿에서 JSP로 데이터를 전달하는데 사용



### 포워드 종류

![image-20211008154830733](img/image-20211008154830733.png)



## **redirect, refresh, location**

**1. 웹 브라우저에서 FirstServlet 을 요청한다**

  **2. FirstServlet은 sendRedirect()를 통해 SecondServlet을 웹 브라우저에 재요청한다**

  **3. 웹 브라우저는 SecondServlet을 다시 요청한다**

  **※ 이때 1, 2번에서의 요청, 응답객체와 3번에서의 요청, 응답객체는 서로 다르다!**

​    **(dispatch.forward 방식과 차이가 되는 특징이다)**



![image-20211008155042897](img/image-20211008155042897.png)

 **- redirect 방식으로 다른 서블릿에 데이터 전달하는 방법(GET 방식)**

​    **response.sendRedirect("second?name=park");**



##  **dispatch**

 **1. 웹 브라우저에서 FirstServlet을 요청한다**

  **2. FirstServlet은 RequestDispatcher를 이용해 SecondServlet으로 포워드한다**

  **※ 이때 1번에서 생성한 요청, 응답 객체를 2번에서 넘겨준다**

​    **(1번과 2번 응답, 요청 객체가 서로 같다는 점에서 redirect 방식과 차이를 갖는다)**

![image-20211008155200157](img/image-20211008155200157.png)



## web-xml 세팅

![image-20211008161504281](img/image-20211008161504281.png)

**url pattern은 여러개 매핑가능**







-------------------------------------------------------------------------

#### 서블릿 파일 생성

![image-20211008183655994](img/image-20211008183655994.png)

#### 매핑 명칭은 사용자 임의로 설정

![image-20211008183751608](img/image-20211008183751608.png)

#### 생성

![image-20211008183827216](img/image-20211008183827216.png)

![](img/image-20211008183918130.png)



생성한 파일

####  com.douzone.emaillist.controller/EmaillistController.java

```
package com.douzone.emaillist.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.emaillist.dao.EmaillistDao;
import com.douzone.emaillist.vo.EmaillistVo;



public class EmaillistController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("a");
		
		if("form".equals(action)) {
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/form.jsp");
			rd.forward(request, response);
			
		}else if("add".equals(action)){
			
			String firstName = request.getParameter("fn");
			String laseName = request.getParameter("ln");
			String email = request.getParameter("email");
			
			EmaillistVo vo = new EmaillistVo();
			vo.setFirstName(firstName);
			vo.setLastName(laseName);
			vo.setEmail(email);
			
			new EmaillistDao().insert(vo); 
			
			response.sendRedirect("/emaillist02/el"); 
			
			
		}else { // "/emaillist02/el" url이 들어왔을때 실행되는 조건문
			EmaillistDao dao = new EmaillistDao();
			List<EmaillistVo> list = dao.findAll();
			
			request.setAttribute("list", list);
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/index.jsp");
			rd.forward(request, response);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}

```



##### webapp/WEB-INF/views/form.jsp

```
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>메일 리스트 가입</h1>
	<p>
		메일 리스트에 가입하려면,<br>
		아래 항목을 기입하고 submit 버튼을 클릭하세요.
	</p>
	<form action="/emaillist02/el" method="post">
		<input type='hidden' name="a" value="add">
	    First name: <input type="text" name="fn" value="" ><br>
	    Last name: <input type="text" name="ln" value=""><br>
	    Email address: <input type="text" name="email" value=""><br>
	    <input type="submit" value="등록">
	</form>
	<br>
	<p>
	<a href="/emaillist02/el">리스트 바로가기</a>
	
	</p>
</body>
</html>
```

##### webapp/WEB-INF/views/form.jsp, index.jsp 

```
<%@ page import="java.util.List"%>
<%@ page import="com.douzone.emaillist.dao.EmaillistDao"%>
<%@ page import="com.douzone.emaillist.vo.EmaillistVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	List<EmaillistVo> list = (List<EmaillistVo>)request.getAttribute("list");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>메일 리스트에 가입되었습니다.</h1>
	<p>입력한 정보 내역입니다.</p>
	<!-- 메일정보 리스트 -->
	<%
		for(EmaillistVo vo : list) {
	%>
		<table border="1" cellpadding="5" cellspacing="2">
			<tr>
				<td align=right>First name: </td>
				<td><%=vo.getFirstName() %></td>
			</tr>
			<tr>
				<td align=right width="110">Last name: </td>
				<td width="110"><%=vo.getLastName() %></td>
			</tr>
			<tr>
				<td align=right>Email address: </td>
				<td><%=vo.getEmail() %></td>
			</tr>
		</table>
		<br>
	<%
		}
	%>
	<p>
		<a href="/emaillist02/el?a=form">추가메일 등록</a>
	</p>
	<br>
</body>
</html>
```



##### EmaillistController.java(서블릿) 소스의 내부 작동원리

>  브라우저로 http://localhost:8080/emaillist02/el 접속시 아래 조건실행

![image-20211008184657946](img/image-20211008184657946.png)





>  RequestDispatcher 작동원리

![image-20211008185058370](img/image-20211008185058370.png)

 

>  url  http://localhost:8080/emaillist02/el?a=form 값이 넘어올경우

![image-20211008185610656](img/image-20211008185610656.png)

![image-20211008185720993](img/image-20211008185720993.png)

>  url  http://localhost:8080/emaillist02/el?a=form 값이 넘어올경우

![image-20211008185837770](img/image-20211008185837770.png)  

![image-20211008185857673](img/image-20211008185857673.png)

