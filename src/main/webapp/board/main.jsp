<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>main</title>
<style>
	*{
		margin: 0px;
		padding: 0px;	
	}
	
	body {
		background-color: pink;
		width: 100vw;
		height: 100vh;
	}
	
</style>
</head>
<body>
<%
	String text = request.getParameter("text");

	if(text == null) {
		text = "gangnam";
	}
%>
<h1>main영역</h1>

<%= text %>
</body>
</html>