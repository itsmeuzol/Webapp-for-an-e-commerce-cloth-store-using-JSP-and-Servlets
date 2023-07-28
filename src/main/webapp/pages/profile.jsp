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
    

<%! SessionManage mySession = new SessionManage(); %>
<% String mainPath = request.getContextPath(); %>

<% 
  String user = (String) session.getAttribute("user");
  String password = (String) session.getAttribute("password");
%>
<c:set var="encPassword" value="<%= Encryption.encrypt(password) %>" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta content="width=device-width, initial-scale=1" name="viewport" />
<title>Insert title here</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/styles/nav.css"/>
<link rel="stylesheet" href="<%= request.getContextPath() %>/styles/profile.css"/>
</head>
<body>
<sql:setDataSource var="dbConnection" driver="com.mysql.jdbc.Driver"
					url="jdbc:mysql://localhost:3306/e-commerce" user="root"
					password="" />
			<sql:query var="users" dataSource="${dbConnection}">
          	SELECT user_id FROM user WHERE user_name = '${user}' AND password = '${encPassword}';
          </sql:query>
<c:if test="${not empty users}">
  <c:set var="firstRow" value="${users.rows[0]}" /> 
</c:if>
<%
  String log = "LogIn"; // default value
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
      <label for="nav-check">
        <span></span>
        <span></span>
        <span></span>
      </label>
    </div>
    <div id="nav-links">
      <a href="../home.jsp">Home</a>
      <a href="search.jsp">Search</a>
 	<% if (mySession.checkUser(user)) { %>
 <a href="view.jsp">View Orders</a>
 <a href="pages/profile.jsp">Profile</a>
<% } %>

      <form action=" <%if(!mySession.checkUser(user)){
	    					out.print(mainPath);%>/login.jsp<%
	   					} 
	    				else {
	    					out.print(mainPath);%>/LogoutServlet<%
	   					}%>"	
	    	method="post">
        <input type="submit" value="<%= log %>">
      </form>
    </div>
  </div>
<div class="wrapper">
	<!-- Setting up the database connection and running a query to retrieve the user_id based on the provided username and encrypted password -->
	<sql:setDataSource var="dbConnection" driver="com.mysql.jdbc.Driver"
					url="jdbc:mysql://localhost:3306/e-commerce" user="root"
					password="" />
			<sql:query var="users" dataSource="${dbConnection}">
          	SELECT user_id FROM user WHERE user_name = '${user}' AND password = '${encPassword}';
          </sql:query>
	<!-- If a result was returned, store the first row in the 'firstRow' variable -->
	<c:if test="${not empty users}">
  	<c:set var="firstRow" value="${users.rows[0]}" />
	</c:if>
	
	<!-- Using the retrieved user_id to run another query to retrieve the user's information -->
	<sql:query var="user" dataSource = "${dbConnection}">
	SELECT first_name, last_name, user_name, password, email, address, phone_number, image FROM user WHERE user_id = ${firstRow.user_id}
	</sql:query>
	<!-- If a result was returned, store the first row in the 'userData' variable -->
	<c:if test="${not empty user}">
  	<c:set var="userData" value="${user.rows[0]}" />
  	   
	</c:if>


    <div class="image">
      <!-- Displaying the user's profile image -->
      <img src="../${userData.image}" alt="User image here"/>
    </div>
    <div class="form-wrapper">
      <!-- Form to update the user's profile information -->
      <form class="form"action="../UpdateUser" method="post" enctype = "multipart/form-data">
      	<!-- Hidden input to store the user_id -->
      	<input type="hidden" name="userID" value="${firstRow.user_id}"  required/>
      	<!-- Hidden input to store the user's previous profile image -->
      	<input type="hidden" name="oldImage" value="${userData.image}" />
        <input class="input" value="${userData.first_name}" name="FirstName" required/>
        <input class="input" value="${userData.last_name}" name="LastName" required/>
        <input class="input" value="${userData.user_name}" name="Username" required/>
        <input class="input" value="Password" name="Password" required/>
        <input class="input" value="${userData.email}" name="Email" required/>
        <input class="input" value="${userData.address}" name="Address" required/>
        <input class="input" value="${userData.phone_number} " name="PhoneNumber" required/>
        <!-- Input to upload a new profile image -->
        <input type="file" id="file" accept="image/*" name="image" />
        <input type="submit" value="Update Profile" id="button" />
      </form>
    </div>
</div>

</body>
</html>