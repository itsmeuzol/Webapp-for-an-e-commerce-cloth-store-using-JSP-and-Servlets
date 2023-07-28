<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<meta content="width=device-width, initial-scale=1" name="viewport" />
    <link rel="stylesheet" href="<%= request.getContextPath() %>/styles/nav.css"/>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/styles/registration.css"/>
    <title>Register Here</title>
    	
</head>
<body>
	<div id="nav">
        	<input type="checkbox" id="nav-check" />
       		 <div id="nav-header">
         	 <div id="nav-title">LUGA PASAL</div>
        	</div>
        <div id="nav-btn">
          <label for="nav-check">
            <span></span>
            <span></span>
            <span></span>
          </label>
        </div>
  
        <div id="nav-links">
          <a href="../home.jsp" >Home</a>
        </div>
      </div>
    <div class="wrapper">
    <% String errorMessage =(String) request.getAttribute("errorMessage"); %>
	<%if (errorMessage!=null){ %>
		<div id="error-message"><%=errorMessage %></div>
	<%} %>
      <div class="left">
        <div class="register-head">
          <p>Register Here!</p>
        </div>
        <div class="register-text">
          <p>
            By registering you accept all the terms and conditions that you have
            not read.
          </p>
        </div>
      </div>
      <form action="../RegisterUser" class="form" method="post" enctype = "multipart/form-data">
        <div id="two-cols">
          <input
            type="text"
            placeholder="First Name"
            required
            id="two-cols-input"
            name="firstName"
            
          />
          <input
            type="text"
            placeholder="Last Name"
            id="two-cols-input"
            name="lastName"
            required
          />
        </div>
        <div id="two-cols">
          <input
            type="text"
            placeholder="Username"
            id="two-cols-input"
            name="username"
            required
          />
          <input
            type="password"
            placeholder="Password"
            id="two-cols-input"
            name="password"
            required
          />
        </div>
        <div id="one-col">
          <input
            type="email"
            placeholder="Email address or phone number"
            id="one-col-input"
            name="email"
            required
          />
          <input
            type="text"
            placeholder="Address"
            id="one-col-input"
            name="address"
            required
          />
          <input
            type="text"
            placeholder="Phone Number"
            id="one-col-input"
            name="phoneNumber"
            required
          />
          <div id="one-col-input">
            <input type="file" id="file" accept="image/*" name="image" />
          </div>
        </div>
        <div id="btnlink">
          <input type="submit" value="Register" id="button" />
          <a href="<%= request.getContextPath() %>/login.jsp" id="link"> Already have an account? </a>
        </div>
      </form>
    </div>

</body>
</html>