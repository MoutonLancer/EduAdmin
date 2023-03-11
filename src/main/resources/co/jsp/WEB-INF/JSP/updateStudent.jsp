<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>MVC</title>
</head>
<body>
<h3>修改学生信息</h3>
<form action="${pageContext.request.contextPath}/students"  method="post">
    <input type="hidden"  name="_method" value="put">
    学号:<input id="studentID"  name="studentID"   value="${student.studentID}" readonly  unselectable="on"><br>
    姓名:<input id="studentName"name="studentName" value="${student.studentName}"><br>
    院系:<input id="department" name="department"  value="${student.department}"><br>
    专业:<input id="subject"    name="subject"     value="${student.subject}"><br>
    班级:<input id="clas"       name="clas"        value="${student.clas}"><br>
    电话:<input id="number"     name="number"      value="${student.number}"><br>
    住址:<input id="address"    name="address"     value="${student.address}"><br>
    生日:<input id="birthday"   name="birthday"    value="${student.birthday}"><br>
    <br>
    <input type="submit" value="提交">
    </form>
</body>
</html>

