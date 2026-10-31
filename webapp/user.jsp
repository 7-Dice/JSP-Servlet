<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー情報</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
	<div class="container">
		<div class="card">
			<h2>ユーザー情報の登録</h2>
		
		<form action="Authrization" method="post" class="form-box">
		<input type="hidden" name="action" value="register">
		
			<c:if test="${not empty errMsg }">
				<p class="error-message">${ errMsg }</p>
			</c:if>
			
				<div class="form-row">
					<label for="id">ユーザーID</label>
					<input type="text" id="id" placeholder="ID" name="id">
				</div>
				
				<div class="form-row">
					<label for="id">パスワード</label>
				<input type="password" oninput="this.value = this.value.replace(/[^\x01-}-\s]/g, '');"
					placeholder="password" name="pass" id="pass">
				</div>
				
				<div class="form-row">
					<label for="hn">ニックネーム</label>
					<input type="text" id="hn" placeholder="名前" name="hn">
				</div>
				
				<div class="form-row">
					<p>年齢</p>
					<input type="text" id="age" inputmode="numeric" pattern="[0-9]*" placeholder="年齢" name="age">
				</div>
				
				<div class="form-row">
					<label for="mail">メールアドレス</label>
					<input type="email" id="mail" placeholder="email" name="mail"><br><br>
				</div>
				
				<input type="submit" value="送信"><br><br>
		</form>	
			
			<div class="inline-links">	
				<a href="index.jsp">ログイン画面に戻る</a>
			</div>
		</div>
	</div>
</body>
</html>