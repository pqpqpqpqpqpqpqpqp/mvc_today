<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Template</title>

<style>
	* {
		margin: 0;
		padding: 0;
	}
	
	body {
		width: 100vw;
		height: 100vh;
	}
	
	header {
		border: 2px solid red;
		height: 10%;
	}
	
	aside {
		border: 2px solid green;
		width: 15%;
		
		padding: 10px;
		box-sizing: border-box;
	}
	
	main_box {
		border: 2px solid blue;
		width: 85%;
		
		padding: 10px;
		box-sizing: border-box;
		
		overflow: scroll;
	}

	
	footer {
		border: 2px solid orange;
		height: 10%;
	}
	
	.container {
		display: flex;
		
		height: 100%;
	}
	
	article {
		border: 2px solid violet;
	}
</style>
</head>
<body>
<%
	String movePage = (String)request.getAttribute("movePage");
	if (movePage == null) {
		
	}
	
%>

<!--  header -->
<header>
	<h1> 헤더 영역</h1>
</header>

<div class="container">
<!-- aside -->
<aside>
	<h2> 사이드 영역 </h2><br/>
	<nav>

	<jsp:include page="left.jsp"/>

	</nav>
</aside>

<!-- main -->

<main_box>
	<main>
		<jsp:include page='<%=movePage+".jsp" %>'/>
	</main>
</main_box>
</div>


<!--  footer  -->
<footer>
	<h2> footer 영역 </h2>
</footer>
</body>
</html>