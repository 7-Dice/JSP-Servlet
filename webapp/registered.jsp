<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登録が完了しました</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
    <div class="container">
        <div class="card">
            <h2>登録内容</h2>

            <div class="notice-box">登録が完了しました</div>

            <table>
                <tr>
                    <th>項目</th>
                    <th>内容</th>
                </tr>
                <tr>
                    <td>商品名</td>
                    <td>${ pd.name }</td>
                </tr>
                <tr>
                    <td>商品コード</td>
                    <td>${ pd.code }</td>
                </tr>
                <tr>
                    <td>値段</td>
                    <td>${ pd.price }</td>
                </tr>
                <tr>
                    <td>メーカー</td>
                    <td>${ pd.maker }</td>
                </tr>
                <tr>
                    <td>梱包サイズ</td>
                    <td>${ pd.size }</td>
                </tr>
                <tr>
                    <td>特典有無</td>
                    <td>${ pd.option ? "あり" : "なし" }</td>
                </tr>
            </table>

            <div class="inline-links">
                <a href="list.jsp">登録一覧</a>
                <a href="register.jsp">戻る</a>
            </div>
        </div>
    </div>
</body>
</html>