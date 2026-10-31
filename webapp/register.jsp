<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%
    HttpSession sessionObj = request.getSession(false);
    if (sessionObj == null || sessionObj.getAttribute("loginUser") == null) {
        response.sendRedirect("index.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品登録</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
    <div class="container">
        <div class="card">
            <h2>商品情報登録</h2>

            <c:if test="${not empty errMsg}">
                <p class="error-message">${errMsg}</p>
            </c:if>

            <form action="mgServlet" method="post" class="form-box">
                <div class="form-row">
                    <label for="name">商品名</label>
                    <input type="text" id="name" placeholder="商品名" name="name">
                </div>

                <div class="form-row">
                    <label for="code">商品コード</label>
                    <input type="text" id="code" placeholder="商品コード" name="code">
                </div>

                <div class="form-row">
                    <label for="price">値段</label>
                    <input type="text" id="price" inputmode="numeric" pattern="[0-9]*" placeholder="値段(数値のみ)" name="price">
                </div>

                <div class="form-row">
                    <label for="maker">メーカー</label>
                    <input type="text" id="maker" placeholder="メーカー" name="maker">
                </div>

                <div class="form-row">
                    <label>梱包時サイズを選択</label>
                    <div class="radio-group">
                        <label><input type="radio" checked="checked" name="size" value="S"> S</label>
                        <label><input type="radio" name="size" value="M"> M</label>
                        <label><input type="radio" name="size" value="L"> L</label>
                        <label><input type="radio" name="size" value="LL"> LL</label>
                    </div>
                </div>

                <div class="form-row">
                    <label>特典</label>
                    <div class="radio-group">
                        <label><input type="radio" name="option" value="あり" checked="checked"> あり</label>
                        <label><input type="radio" name="option" value="なし"> なし</label>
                    </div>
                </div>

                <input type="submit" value="送信">
            </form>

            <div class="inline-links">
                <a href="list.jsp">登録一覧表</a>
            </div>

            <form action="Authrization" method="post">
                <input type="hidden" name="action" value="logout">
                <input type="submit" value="ログアウト">
            </form>
        </div>
    </div>
</body>
</html>