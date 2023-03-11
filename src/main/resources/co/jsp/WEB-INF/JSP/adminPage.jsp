<%--
  Created by IntelliJ IDEA.
  User: 31961
  Date: 2023/2/19
  Time: 17:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Panel</title>
</head>
<body>

<h2 id="welcomeText">${username},欢迎您</h2>
    <table>
        <tr>
            <td>学生信息表</td>
            <td>
                <input type="button" value="查看" id="StudentInfo">
            </td>
        </tr>
        <tr>
            <td>教师信息表</td>
            <td>
                <input type="button" value="查看" id="TeacherInfo">
            </td>
        </tr>


    </table>

</body>
<script>
    document.getElementById("StudentInfo").onclick=function (){
        location.href ="${pageContext.request.contextPath}"+"/students";
    }
    document.getElementById("TeacherInfo").onclick=function (){
        location.href ="${pageContext.request.contextPath}"+"/teachers";
    }

</script>
</html>
