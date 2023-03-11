<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>新增学生信息</title>
</head>
<body>
<h3>个人信息</h3>
<form action="${pageContext.request.contextPath}/students"  method="post">
    学号：   <input name="studentID"  ><br>
    姓名：   <input name="studentName"><br>
    院系：   <input name="department" ><br>
    专业：   <input name="subject"    ><br>
    班级：   <input name="clas"      ><br>
    电话：   <input name="number"     ><br>
    住址：   <input name="address"    ><br>
    生日：   <input name="birthday"   ><br>
    <br>
    <input type="submit" value="提交">
</form>

</body>
</html>