<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>学生信息表</title>
</head>
<body>

<h2>学生信息表</h2>
<input type="button" value="新增" id="add">
<table border="1">
    <th>序号</th>
    <th>学号</th>
    <th>姓名</th>
    <th>院系</th>
    <th>专业</th>
    <th>班级</th>
    <th>电话</th>
    <th>住址</th>
    <th>生日</th>

    <c:forEach items="${students}" var="student" varStatus="id">
        <tr align="center">
            <td>${id.count}</td>
            <td>${student.studentID}</td>
            <td>${student.studentName}</td>
            <td>${student.department}</td>
            <td>${student.subject}</td>
            <td>${student.clas}</td>
            <td>${student.number}</td>
            <td>${student.address}</td>
            <td>${student.birthday}</td>
            <td>
                <form action="${pageContext.request.contextPath}/pages/updateStudent/${student.studentID}" method="get">
                    <input type="submit" value="修改">
                </form>
                <form action="${pageContext.request.contextPath}/students/${student.studentID}" method="post">
                    <input type="hidden" name="_method" value="delete" />
                    <input type="submit" value="删除">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
<input type="button" value="返回管理页面" id="admin">

<script>
    document.getElementById("add").onclick=function (){
        location.href ="${pageContext.request.contextPath}"+"/pages/addStudent";
    }

    document.getElementById("admin").onclick=function (){
        location.href ="${pageContext.request.contextPath}"+"/pages/adminPage";
    }

</script>

</body>
</html>