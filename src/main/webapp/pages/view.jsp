<%@page import="com.lugapasal.controller.statemanagement.SessionManage"%>

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
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta content="width=device-width, initial-scale=1" name="viewport" />
<title>Insert title here</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/styles/nav.css"/>
<link rel="stylesheet" href="<%= request.getContextPath() %>/styles/profile.css"/>
</head>
<style>
	table {
	border-collapse: collapse;
	width: 100%;
}

th, td {
	text-align: center;
	padding: 8px;
	border-bottom: 3px solid white;
}
td{
	text-align:center;
}

th {
	background-color: #1c6d6a;
	color: #fff;
}

.alternate-row:nth-child(even) {
  background-color: #f2f2f2;
}

.alternate-row:nth-child(odd) {
  background-color: #ddd;
}
	
</style>
<body>


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
 <a href="">View Orders</a>
 <a href="profile.jsp">Profile</a>
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
	<sql:setDataSource var="dbConnection" driver="com.mysql.jdbc.Driver"
					url="jdbc:mysql://localhost:3306/e-commerce" user="root"
					password="" />
			
	<sql:query var="orders" dataSource="${dbConnection}">
          	SELECT order_id, username, product_id, product_name FROM orders WHERE username = "${user}";
          </sql:query>
			<table>
				<thead>
					<tr>
						<th>OrderID</th>
						<th>Username</th>
						<th>Product ID</th>
						<th>Product Name</th>
					</tr>
				</thead>
				<c:forEach var="order" items="${orders.rows}">
						<tbody>
							<tr class="alternate-row">
								<td><c:out value="${order.order_id}" /></td>
								<td><c:out value="${order.username}" /></td>
								<td><c:out value="${order.product_id}" /></td>
								<td><c:out value="${order.product_name}" /></td>
								
							</tr>
						</tbody>
					</c:forEach>
			</table>
	
	

    
  </div>
       
</body>
</html>