<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>新增教师信息</title>
</head>
<body>
    <h3>个人信息</h3>
    <form action="${pageContext.request.contextPath}/teachers"  method="post">
        教师编号：  <input name="teacherID">   <br>
        姓名：     <input name="teacherName"> <br>
        院系：     <input name="department">  <br>
        职位：     <input name="position">    <br>
        电话：     <input name="number">      <br>
        <br>
        <input type="submit" value="提交">
    </form>

</body>
</html>