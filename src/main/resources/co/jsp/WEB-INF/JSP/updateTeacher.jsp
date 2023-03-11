<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>MVC</title>
</head>
<body>
    <h3>修改教师信息</h3>
    <form action="${pageContext.request.contextPath}/teachers"  method="post">
        <input type="hidden"  name="_method" value="put">
        教师编号:<input id="teacherID"  name="teacherID"   value="${teacher.teacherID}" readonly  unselectable="on"><br>
        姓名:<input id="teacherName"name="teacherName" value="${teacher.teacherName}"><br>
        院系:<input id="department" name="department"  value="${teacher.department}"><br>
        职位:<input id="position"    name="position"     value="${teacher.position}"><br>
        电话:<input id="number"     name="number"      value="${teacher.number}"><br>
        <br>
        <input type="submit" value="提交">
    </form>
</body>
</html>

