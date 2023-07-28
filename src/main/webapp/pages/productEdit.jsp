<%@page import="com.lugapasal.controller.statemanagement.SessionManage"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
<meta content="width=device-width, initial-scale=1" name="viewport" />
<meta charset="UTF-8">
<title>Edit Product</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/styles/nav.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/styles/productEdit.css" />

<title>Log In Here</title>

</head>
<body>
	<!-- Navigation Bar -->
	<div id="nav">
		<!-- Hamburger Menu Checkbox -->
		<input type="checkbox" id="nav-check" />
		<!-- Navigation Header -->
		<div id="nav-header">
			<div id="nav-title">LUGA PASAL</div>
		</div>
		<!-- Hamburger Menu Button -->
		<div id="nav-btn">
			<label for="nav-check"> <span></span> <span></span> <span></span>
			</label>
		</div>
		<!-- Navigation Links -->
		<div id="nav-links">
			<form action="<%=request.getContextPath()%>/LogoutServlet"
				method="post" style="width: 100%;">
				<input type="submit" value="Logout" style="width: 100%;" />
			</form>
		</div>
	</div>

	<!-- Product Editing Form -->
	<div class="wrapper">
		<!-- Product Image Display -->
		<div class="image">
			<img src="../<%=request.getParameter("image")%>" />
		</div>
		<!-- Product Editing Form Wrapper -->
		<div class="form-wrapper">
			<form class="form" action="../EditProduct" method="post"
				enctype="multipart/form-data">
				<!-- Product Name Input -->
				<input class="input" type="text"
					placeholder=<%=request.getParameter("productName")%>
					name="productName" />
				<!-- Product Description Input -->
				<input class="input" type="text"
					placeholder=<%=request.getParameter("productDescription")%>
					name="productDescription" required />
				<!-- Category Name Input -->
				<input class="input" type="text"
					placeholder=<%=request.getParameter("categoryName")%>
					name="categoryName" required />
				<!-- Unit Price Input -->
				<input class="input" type="text"
					placeholder=<%=request.getParameter("unitPrice")%>
					name="unitPrice" required />
				<!-- Product Brand Input -->
				<input class="input" type="text"
					placeholder=<%=request.getParameter("brand")%> name="brand"
					required />
				<!-- Product Rating Input -->
				<input class="input" type="text"
					placeholder=<%=request.getParameter("rating")%> name="rating"
					required />
				<!-- Product Image Input -->
				<input class="input" type="file" id="file" name="image"
					accept="image/*" />
				<!-- Edit Product Button -->
				<input type="submit" value="Edit Product" id="button" />
				<!-- Hidden Fields -->
				<input type="hidden" name="oldImage"
					value="<%=request.getParameter("image")%>" /> <input
					type="hidden" name="productID"
					value="<%=request.getParameter("productId")%>" />
			</form>
		</div>
	</div>


</body>
</html>