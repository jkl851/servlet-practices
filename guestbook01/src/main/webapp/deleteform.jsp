<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
	String strNo = request.getParameter("no");
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>방명록</title>
</head>
<body>
	<form method="post" action="delete.jsp">
		<input type="hidden" name="no" value=<%=strNo %>> <!-- value에 글 no -->
		<input type="password" name="password">
		<input type="submit" value="확인">
	</form>
	<br>
	<a href="/emaillist02/el">메인으로 돌아가기</a></td>

</body>
</html>