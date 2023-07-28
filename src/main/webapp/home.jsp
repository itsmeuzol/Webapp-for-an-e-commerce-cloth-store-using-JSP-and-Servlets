<%@page import="com.lugapasal.controller.statemanagement.SessionManage"%>
<%@page import="com.lugapasal.resources.Encryption"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!--Including the core tag library-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!--Including the formatting tag library-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!--Including the SQL tag library-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<%!SessionManage mySession = new SessionManage();%>
<%
String mainPath = request.getContextPath();
%>
<%
String user = (String) session.getAttribute("user");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta content="width=device-width, initial-scale=1" name="viewport" />
<title>Insert title here</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/styles/nav.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/styles/home.css" />
<title>Log In Here</title>

</head>
<body>
	<%
	String log = "LogIn"; // default value
	// Check if the user is logged out
	if (mySession.checkUser(user)) {
		log = "LogOut"; // override default value
	}
	%>
	<div id="nav">
		<input type="checkbox" id="nav-check" />
		<div id="nav-header">
			<div id="nav-title">LUGA PASAL</div>
		</div>
		<div id="nav-btn">
			<label for="nav-check"> <span></span> <span></span> <span></span>
			</label>
		</div>
		<div id="nav-links">
			<a href="home.jsp">Home</a> <a href="pages/search.jsp">Search</a>
			<%
			if (mySession.checkUser(user)) {
			%>
			<a href="pages/view.jsp">View Orders</a> <a href="pages/profile.jsp">Profile</a>
			<%
			}
			%>
			<form
				action=" <%if (!mySession.checkUser(user)) {
	out.print(mainPath);%>/login.jsp<%} else {
							out.print(mainPath);%>/LogoutServlet<%}%>"
				method="post">
				<input type="submit" value="<%=log%>">
			</form>
		</div>
	</div>
	<div class="wrapper">
		<div class="hero-section">
			<div class="hero-text">
				<h1>Welcome to Luga Pasal!</h1>
				<p>One point stop for all your clothing needs</p>
			</div>
		</div>
		<h1 class="recent">RECENT RELEASES</h1>
		<div class="card-grid">

			<sql:setDataSource var="dbConnection" driver="com.mysql.jdbc.Driver"
				url="jdbc:mysql://localhost:3306/e-commerce" user="root" password="" />
			<sql:query var="products" dataSource="${dbConnection}">
          	SELECT  product_id, product_name,  unit_price, brand, rating, image FROM product;
          </sql:query>
			<c:forEach var="product" items="${products.rows}">
				<div class="card">
					<img src="${product.image}" alt="Product Image">
					<h2>${product.product_name}</h2>
					<p>Price: ${product.unit_price}</p>
					<p>Brand: ${product.brand}</p>
					<p>Rating: ${product.rating}</p>
					<form class="add-cart"
						action="<%=request.getContextPath()%>/AddCart" method="post">
						<input type="submit" value="Add to Cart" /> <input type="hidden"
							value="${user}" name="username" /> <input type="hidden"
							name="productID" value="${product.product_id}" /> <input
							type="hidden" name="productName" value="${product.product_name}" />
					</form>
				</div>
			</c:forEach>
		</div>
		<div class="footer-section">
			<p>&copy; 2023 Luga Pasal. All rights reserved.</p>
		</div>
	</div>
</body>
</html>
