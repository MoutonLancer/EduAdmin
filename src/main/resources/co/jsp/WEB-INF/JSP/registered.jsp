<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--<!DOCTYPE html>--%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>注册</title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Style/style.css" type="text/css" charset="UTF-8">
    <script>
        function usernameUsable(){

            let contextPath = "${pageContext.request.contextPath}";
            let xhttp;
            if(window.XMLHttpRequest)
                xhttp = new XMLHttpRequest();
            else
                xhttp = new ActiveXObject();

            xhttp.open("post","http://localhost:8080"+contextPath+"/users/usernameUsable",true);
            xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            xhttp.send("username="+document.getElementById("username").value);

            xhttp.onreadystatechange = function (){
                if (this.readyState === 4 && this.status === 200){
                    if (this.responseText==="false")
                        document.getElementById("username_err").style.display="none";
                    else
                        document.getElementById("username_err").style.display ="inline";
                }
            }
        }


    </script>
</head>
<body>
<form id="login_box" method="post" action="${pageContext.request.contextPath}/users/register" name="register" >
    <h2>Registered</h2>
    <div id="Msg">${register_msg}</div>
    <div id="input_box" aria-checked="true">
        <input id="username" type="text" placeholder="请输入用户名" onblur="usernameUsable()" name="username">
        <br>
        <span id="username_err" style="display: none;color:red;">该用户名已经被占用</span>
    </div>
    <div class="input_box">
        <input type="password" placeholder="请输入密码" name="password">
    </div>
    <!--        可添加一个确认密码框-->

    <button>提交</button><br>
    <span class="text">已有账号？</span>
    <a  id="login" href="${pageContext.request.contextPath}/pages/loginPage">登录</a>
</form>
</body>
</html>
