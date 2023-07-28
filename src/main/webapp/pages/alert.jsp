<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Product added</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/styles/alert.css"/>
</head>
<body>
<form class="alert-form" action = "../home.jsp">
        <h3> Alert!</h3>
        <label>Added to Cart Successfully!!</label>
        <input type="submit" value="OK"/>
    </form>
</body>
</html>