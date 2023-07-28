<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!--Including the core tag library-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!--Including the formatting tag library-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!--Including the SQL tag library-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>

<html>
<head>
<title>LugaPasal |ADMIN</title>
<meta content="width=device-width, initial-scale=1" name="viewport" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/styles/admin.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/styles/nav.css" />
</head>
<body>
	<div id="nav">
		<input type="checkbox" id="nav-check" />
		<div id="nav-header">
			<div id="nav-title">ADMIN PANEL</div>
		</div>
		<div id="nav-btn">
			<label for="nav-check"> <span></span> <span></span> <span></span>
			</label>
		</div>

		<div id="nav-links">
			<form action="../LogoutServlet" method="post" style="width: 100%;">
				<input type="submit" value="Logout" style="width: 100%;" />
			</form>
		</div>
	</div>
	<div class="wrapper">
		<%-- Check if the registerMessage attribute is set --%>
		<%
		if (request.getAttribute("registerMessage") != null) {
		%>
		<div class="alert alert-info">
			<strong><%=request.getAttribute("registerMessage")%></strong>
		</div>
		<%
		}
		%>
		<div class="top">
			<div class="add-product-form">
				<form action="../AddProduct" method="post"
					enctype="multipart/form-data">
					<span> Add Products </span> <input type="text"
						placeholder="Product Name" name="productName" required /> <input
						type="text" placeholder="Product Description"
						name="productDescription" required /> <input type="text"
						placeholder="Category Name" name="categoryName" required /> <input
						type="text" placeholder="Unit Price" name="unitPrice" required />
					<input type="text" placeholder="Brand Name" name="brand" required />
					<input type="text" placeholder="Rating" name="rating" required /> <input
						type="file" id="file" name="image" accept="image/*" /> <input
						type="submit" value="Add" id="button" />
				</form>
			</div>
			<!-- Start of product list -->
			<div class="product-list">

				<!-- Display header for product details -->
				<span> Product Details </span>

				<!-- Database Connection using taglib directive -->
				<!-- Set up a data source to connect to the MySQL database -->
				<sql:setDataSource var="dbConnection" driver="com.mysql.jdbc.Driver"
					url="jdbc:mysql://localhost:3306/e-commerce" user="root"
					password="" />

				<!-- Query the database to retrieve all products -->
				<sql:query var="products" dataSource="${dbConnection}">
        SELECT product_id, product_name, product_description, category_name, unit_price, brand, rating, image FROM product;
    </sql:query>

				<!-- Display the products in a table -->
				<table>
					<!-- Define the table headers -->
					<thead>
						<tr>
							<th>Product ID</th>
							<th>Product Name</th>
							<th>Product Description</th>
							<th>Category Name</th>
							<th>Unit Price (IN $)</th>
							<th>Brand Name</th>
							<th>Rating</th>
							<th>Action</th>
						</tr>
					</thead>

					<!-- Loop through each product and display it in a table row -->
					<c:forEach var="product" items="${products.rows}">
						<tbody>
							<tr class="alternate-row">
								<td><c:out value="${product.product_id}" /></td>
								<td><c:out value="${product.product_name}" /></td>
								<td><c:out value="${product.product_description}" /></td>
								<td><c:out value="${product.category_name}" /></td>
								<td><c:out value="${product.unit_price}" /></td>
								<td><c:out value="${product.brand}" /></td>
								<td><c:out value="${product.rating}" /></td>

								<!-- Provide links to edit and remove the product -->
								<td>
									<!-- Edit product link -->
									<form
										action="<%=request.getContextPath()%>/pages/productEdit.jsp">
										<input type="hidden" name="productId"
											value="${product.product_id}" /> <input type="hidden"
											name="productName" value="${product.product_name}" /> <input
											type="hidden" name="productDescription"
											value="${product.product_description}" /> <input
											type="hidden" name="categoryName"
											value="${product.category_name}" /> <input type="hidden"
											name="unitPrice" value="${product.unit_price}" /> <input
											type="hidden" name="brand" value="${product.brand}" /> <input
											type="hidden" name="rating" value="${product.rating}" /> <input
											type="hidden" name="image" value="${product.image}" /> <input
											type="submit" value="✍🏼" class="tiny-buttons">
									</form> <!-- Remove product link -->
									<form action="<%=request.getContextPath()%>/RemoveProduct"
										method="post">
										<input type="hidden" name="productId"
											value="${product.product_id}" /> <input type="submit"
											value="❌ " class="tiny-buttons">
									</form>
								</td>
							</tr>
						</tbody>
					</c:forEach>

				</table>
			</div>
			<!-- End of product list -->

		</div>

		<!-- This block of code displays order details in a table -->
		<div class="bottom">
			<!-- The title of the table -->
			<span> Order Details </span>
			<!-- Database Connection using taglib directive -->
			<sql:query var="orders" dataSource="${dbConnection}">
		SELECT order_id, username, product_id, product_name FROM orders;
	</sql:query>
			<!-- Table for displaying orders -->
			<table>
				<thead>
					<tr>
						<th>OrderID</th>
						<th>Username</th>
						<th>Product ID</th>
						<th>Product Name</th>
					</tr>
				</thead>
				<!-- A loop to iterate through each order in the orders.rows list -->
				<c:forEach var="order" items="${orders.rows}">
					<tbody>
						<tr class="alternate-row">
							<!-- Displaying the order details in each row -->
							<td><c:out value="${order.order_id}" /></td>
							<td><c:out value="${order.username}" /></td>
							<td><c:out value="${order.product_id}" /></td>
							<td><c:out value="${order.product_name}" /></td>
						</tr>
					</tbody>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>
