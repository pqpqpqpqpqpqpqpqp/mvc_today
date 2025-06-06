<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 - 여행사진 공유</title>
<style>
* {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
}

body {
	font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
	background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
	min-height: 100vh;
	display: flex;
	justify-content: center;
	align-items: center;
	padding: 20px;
}

.container {
	background: white;
	padding: 40px;
	border-radius: 15px;
	box-shadow: 0 10px 25px rgba(0, 0, 0, 0.1);
	width: 100%;
	max-width: 500px;
}

h1 {
	text-align: center;
	color: #333;
	margin-bottom: 30px;
	font-size: 2em;
}

.form-group {
	margin-bottom: 20px;
}

label {
	display: block;
	margin-bottom: 8px;
	color: #555;
	font-weight: 500;
}

input {
	width: 100%;
	padding: 12px;
	border: 2px solid #ddd;
	border-radius: 8px;
	font-size: 16px;
	transition: border-color 0.3s;
}

input:focus {
	border-color: #667eea;
	outline: none;
}

.btn {
	width: 100%;
	padding: 12px;
	background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
	color: white;
	border: none;
	border-radius: 8px;
	font-size: 16px;
	cursor: pointer;
	transition: transform 0.3s;
}

.btn:hover {
	transform: translateY(-2px);
}

.register-link {
	text-align: center;
	margin-top: 20px;
	color: #666;
}

.register-link a {
	color: #667eea;
	text-decoration: none;
	font-weight: 500;
}

.register-link a:hover {
	text-decoration: underline;
}

.error-message {
	color: #ff4444;
	font-size: 14px;
	margin-top: 5px;
	text-align: center;
}
</style>
</head>
<body>

	<div class="container">
		<h1>로그인</h1>
		<form action="login.me" method="post">
			<div class="form-group">
				<label for="userId">아이디</label> <input type="text" id="userId"
					name="userId" required>
			</div>
			<div class="form-group">
				<label for="password">비밀번호</label> <input type="password"
					id="password" name="password" required>
			</div>
			<button type="submit" class="btn">로그인</button>
		</form>
		<div class="register-link">
			계정이 없으신가요? <a href="register.jsp">회원가입</a>
		</div>
	</div>

</body>
</html>
