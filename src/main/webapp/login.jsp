<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- JSP declaration with content type and encoding. -->
<!DOCTYPE html>
<!-- HTML5 DOCTYPE declaration. -->
<html>
<!-- HTML root element. -->
<head>
<!-- Head section of the HTML page. -->
<meta charset="UTF-8">
<!-- Character encoding for the page. -->
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- Compatibility mode for Internet Explorer. -->
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Viewport size for the page. -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/styles/login.css" />
<!-- External CSS stylesheet for login page. -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/styles/nav.css" />
<!-- External CSS stylesheet for navigation bar. -->
<title>Log In Here</title>
<!-- Page title. -->
</head>
<body>
	<!-- Body section of the HTML page. -->
	<div id="nav">
		<!-- Navigation bar section. -->
		<input type="checkbox" id="nav-check" />
		<!-- Hidden checkbox input for responsive navigation menu. -->
		<div id="nav-header">
			<!-- Navigation header. -->
			<div id="nav-title">LUGA PASAL</div>
			<!-- Navigation title. -->
		</div>
		<div id="nav-btn">
			<!-- Navigation button. -->
			<label for="nav-check"> <!-- Label for the hidden checkbox input. -->
				<span></span> <!-- Span element for the first line of the navigation button. -->
				<span></span> <!-- Span element for the second line of the navigation button. -->
				<span></span> <!-- Span element for the third line of the navigation button. -->
			</label>
		</div>

		<div id="nav-links">
			<!-- Navigation links. -->
			<a href="home.jsp"> Home</a>
			<!-- Navigation link for the homepage. -->
		</div>
	</div>
	<%
	String errorMessage = (String) request.getAttribute("errorMessage");
	%>
	<!-- JSP code to retrieve error message. -->
	<%
	if (errorMessage != null) {
	%>
	<!-- JSP code for conditional statement. -->
	<div id="error-message"><%=errorMessage%></div>
	<!-- Error message display section. -->
	<%
	}
	%>
	<%
	String registerMessage = (String) request.getAttribute("registerMessage");
	%>
	<!-- JSP code to retrieve registration message. -->
	<%
	if (registerMessage != null) {
	%>
	<!-- JSP code for conditional statement. -->
	<div id="error-message"><%=registerMessage%></div>
	<!-- Registration message display section. -->
	<%
	}
	%>
	<div id="wrapper">
		<!-- Login page wrapper. -->
		<div id="left">
			<!-- Login page header. -->
			<div id="register-head">
				<!-- Login page title. -->
				<p>Log In !</p>
			</div>
			<div id="register-text">
				<!-- Login page message. -->
				<p>By registering you accept all the terms and conditions that
					you have not read.</p>
			</div>
		</div>
		<form action="<%=request.getContextPath()%>/LoginServlet"
			method="post" id="form">
			<!-- Login form. -->
			<input placeholder="Username" type="text" name="user_name" />
			<!-- Input field for username. -->
			<input placeholder="Password" type="password" name="password" />
			<!-- Input field for password. -->
			<input type="submit" value="Log In" id="button">
			<!-- Submit button for login. -->
			<a href="<%=request.getContextPath()%>/pages/registration.jsp"
				id="link"> Don't have an account? </a>
			<!-- Link for registration page. -->
		</form>
	</div>
</body>

</html>