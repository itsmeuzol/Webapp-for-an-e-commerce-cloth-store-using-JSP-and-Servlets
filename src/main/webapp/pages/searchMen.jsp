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
<% String user = (String) session.getAttribute("user");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <meta content="width=device-width, initial-scale=1" name="viewport" />
<title>Insert title here</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/styles/nav.css"/>
<link rel="stylesheet" href="<%= request.getContextPath() %>/styles/search.css"/>
    <title>Log In Here</title>
    	
</head>
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
 <a href="view.jsp">View Orders</a>
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
    	<div class="search-options">
        <form class="search-bar" action = "search.jsp">
            <input type= "text" name= "productName" placeholder="Search by product name.."/>
            <input type="submit" value="Search" />
        </form>
        <form class="searches" action = "searchMen.jsp">
            <input type="submit" value="For men" />
        </form>
        <form class="searches" action = "searchWomen.jsp">
            <input type="submit" value="For women" />
        </form>
        <form class="searches" action = "searchChildren.jsp">
            <input type="submit" value="For children" />
        </form>
        <form class="searches" action = "sortPrice.jsp">
            <input type="submit" value="Sort by Price" />
        </form>
        <form class="searches" action = "sortRating.jsp">
            <input type="submit" value="Sort by Rating" />
        </form>
    </div>
      <div class="card-grid">
      
      	<!-- Database Connection using taglib directive -->
				<sql:setDataSource var="dbConnection" driver="com.mysql.jdbc.Driver"
					url="jdbc:mysql://localhost:3306/e-commerce" user="root"
					password="" />
				
        <!-- SQL query to select products from the "Men" category -->
				<sql:query var="products" dataSource="${dbConnection}">
          	SELECT  * FROM product WHERE category_name = "Men";
          </sql:query>
          
          <!-- Loop through the products returned by the query and display them in cards -->
          	<c:forEach var="product" items="${products.rows}">
      		<div class="card">
	          <img src="../${product.image}" alt="Product Image">
	          <h2>${product.product_name}</h2>
          		<p>Price: ${product.unit_price}</p>
          		<p>Brand: ${product.brand}</p>
          		<p>Rating: ${product.rating}</p>
          		<form class="add-cart">
           	 	<input type="submit" value="Add to Cart"/>
          		</form>
          	</div>
      	
      	</c:forEach>
      	
      </div>

      

  </div>
    <footer class="footer-section">
              <p>&copy; 2023 Luga Pasal. All rights reserved.</p>          
    </footer>
</body>
</html>