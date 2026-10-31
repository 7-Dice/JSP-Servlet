<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    HttpSession sessionObj = request.getSession(false);
    if (sessionObj != null && sessionObj.getAttribute("loginUser") != null) {
        response.sendRedirect("register.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<link rel="stylesheet" href="style.css"> 
</head>
<body>
	<div class="container">
		<div class="card">
			<h2>ログイン情報入力</h2>
			
			<form action="Authrization" method="post" class="form-box">
				
				<input type="hidden" name="action" value="login">
				
				<c:if test="${not empty errMsg }">
					<p class="error-message">${ errMsg }</p>
				</c:if>
				
				<div class="form-row">
					<label for="id">ID</label>
					<input type="text" id="id" placeholder="userID" name="id">
				</div>
				
				<div class="form-row">
					<label for="pass">Password</label>
					<input type="password" id="pass" placeholder="password" name="pass"><br><br>
				</div>
				
				<input type="submit" value="送信"><br><br>
			</form>	
				
			<div class="inline-links">	
				<a href="user.jsp">新規アカウント登録</a>
			</div>
		</div>
	</div>		
</body>
</html>

<!--  ログイン画面に戻った際に再度ログインできない修正 
request.getSession() は「セッションがなければ新規作成」します
ここで loginUser を session に保存しておくと、戻ったときに持続します
これで「登録後 → register.jsp → ログイン画面へ戻る → まだログイン中」という状態を維持できます
-->