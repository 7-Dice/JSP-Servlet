<!-- 
登録後のデータを記録、同一クライアントであれば一覧表示を見られる
各画面から一覧表示・登録画面へ遷移できる
さらにログイン画面を実装
ユーザーID・パス・HN・年齢・メルアドを登録できる
これらのページへのアクセス権限の確認をする
商品の出品者情報を追加する
 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="Beans.web.ProductsBeans" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%
	HttpSession sessionObj = request.getSession(false);
	if(sessionObj == null || sessionObj.getAttribute("loginUser") == null){
		response.sendRedirect("index.jsp");
		return;
	}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登録一覧表</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
	<div class="container">
		<div class="card">
			<h2>商品一覧</h2>
	
			<table>
				<caption class="caption-title">Information</caption>
				<tr>
					<th>商品名</th>
					<th>商品コード</th>
					<th>値段</th>
					<th>メーカー</th>
					<th>梱包時サイズ</th>
					<th>特典</th>
					<th>出品者</th>
				</tr>
				
				<!-- スクリプトレット方式(List & Beans import必須)
				HTMLにJavaが混在して可読性が落ちるので、JSTLを使うほうがよいが…
				＜％@ taglib prefix="c" uri="jakarta.tags.core" ％＞
				jakartaのtaglibがないので使えない、残念 -->
				<%
					@SuppressWarnings("unchecked")
					
					List<ProductsBeans>productList = 
					(List<ProductsBeans>)session.getAttribute("productList");
					
					if(productList == null || productList.isEmpty()){
				%>
					<tr>
						<td colspan="7">登録商品はありません</td>
					</tr>
				<% 
				}else{
					for(ProductsBeans pb : productList){
				%>
					<tr>
						<td><%= pb.getName() %></td>
						<td><%= pb.getCode() %></td>
						<td><%= pb.getPrice() %></td>
						<td><%= pb.getMaker() %></td>
						<td><%= pb.getSize() %></td>
						<td><%= pb.isOption() ? "あり" : "なし" %></td>
						<td><%= pb.getSeller() %>
					 </tr>
				 <%
						}
					}
				 %>
				
				<!--  JSTLでループ処理(上のスクリプトレットと同じ) -->
				<!-- <c:forEach var="pl" items="${sessionScope.productList}">
				
				<tr>
					<td>${ pl.name }</td>
					<td>${ pl.code }</td>
					<td>${ pl.price }</td>
					<td>${ pl.maker }</td>
					<td>${ pl.size }</td>
					<td>${ pl.option ? "あり" : "なし" }</td>
				</tr>
				</c:forEach>
				-->
			</table>
			
			<div class="inline-links">
				<a href="register.jsp">入力画面へ</a>
			</div>
		</div>
	</div>	
</body>
</html>