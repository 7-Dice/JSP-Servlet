<!-- 
単価と個数を入力して合計金額を表示するプログラムを作る
I/OはJSP、処理はServlet
References[result.jsp][countServlet.java]
 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品情報を入力</title>
</head>
<body>
	<h2>商品情報を入力してください</h2>

	<!-- 空文字打たれた際に、サーブレット側のif文でキャッチしてエラーを返す -->
	<p style="color: red; font-weight: bold;">${errMsg}</p>
		
	<form action="countServlet" method="post">
	<input type="text" placeholder="単価を入力" name="cost"><br>
	<input type="text" placeholder="個数を入力" name="num"><br>
	<input type="text" placeholder="送料を入力" name="shipping"><br>
	<input type="submit" value="送信" name="submit">  
	</form>
</body>
</html>