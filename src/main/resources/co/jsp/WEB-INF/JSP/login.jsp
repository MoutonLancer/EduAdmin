<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--<!DOCTYPE html>--%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>登录</title>
<%-- ico图标需放在根目录，否则无法加载   W3C标准   --%>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Style/style.css" type="text/css" charset="UTF-8">
</head>


<body>
<form id="login_box" method="post" action="${pageContext.request.contextPath}/users/login" name="login" >
    <h2>LOGIN</h2>
    <div id="Msg">${login_msg}</div>
    <div id="input_box" aria-checked="true">
        <span>Username: </span>
        <input type="text" placeholder="请输入用户名" name="username" value="${cookie.username.value}">
    </div>
    <div class="input_box">
        <span>Password: </span>
        <input type="password" placeholder="请输入密码" name="password" value="${cookie.password.value}">
    </div>
    <span>记住密码:</span><input id="remember" name="remember" type="checkbox" value="1">
    <button>登录</button><br>
    <span class="text">还没有账号？</span>
    <a  id="registered" href="${pageContext.request.contextPath}/pages/registerPage">注册</a>

</form>
</body>
</html>
