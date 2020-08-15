<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Insert title here</title>
</head>
<body>

<jsp:include page="header.jsp"></jsp:include>

<form action="register" method="post">

    <label for="name">First Name :</label> <input name="name">
    <br>
    <label for="surname">Last Name :</label> <input name="surname">
    <br>
    <label for="email">Email :</label> <input name="email">
    <br>
    <label for="password">Password : </label> <input name="password">
    <br>
    <input type="submit" value="submit">
</form>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html> 