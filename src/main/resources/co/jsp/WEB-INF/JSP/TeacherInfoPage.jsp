<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>教师信息表</title>
</head>
<body>

<h2>教师信息表</h2>
<input type="button" value="新增" id="add">
    <table border="1">
        <th>序号</th>
        <th>教师编号</th>
        <th>姓名</th>
        <th>院系</th>
        <th>职位</th>
        <th>电话</th>

        <c:forEach items="${teachers}" var="teacher" varStatus="id">
            <tr align="center">
                <td>${id.count}</td>
                <td>${teacher.teacherID}</td>
                <td>${teacher.teacherName}</td>
                <td>${teacher.department}</td>
                <td>${teacher.position}</td>
                <td>${teacher.number}</td>


                <td>
                    <form action="${pageContext.request.contextPath}/pages/updateTeacher/${teacher.teacherID}" method="get">
                        <input type="submit" value="修改">
                    </form>
                    <form action="${pageContext.request.contextPath}/teachers/${teacher.teacherID}" method="post">
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
        location.href ="${pageContext.request.contextPath}"+"/pages/addTeacher";
    }
    document.getElementById("admin").onclick=function (){
        location.href ="${pageContext.request.contextPath}"+"/pages/adminPage";
    }

</script>

</body>
</html>