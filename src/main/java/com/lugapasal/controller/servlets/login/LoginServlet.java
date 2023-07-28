package com.lugapasal.controller.servlets.login;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lugapasal.controller.dbConnection.DbConnection;
import com.lugapasal.resources.MyConstants;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	* Handles POST requests. This method is called when the user enters a form that has been submitted by the client.
	* 
	* @param request - The servlet request we are processing. This is the request we are processing on the client.
	* @param response - The servlet response we are creating on the client
	*/
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user = request.getParameter ("user_name");
		String pwd = request.getParameter ("password");
		
		// This method is used to check if the user is admin or login page
		if (user.equals("admin") && pwd.equals("admin")) {
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			session.setAttribute("password", pwd);
		
			//setting session to expire in 30 minutes
			session.setMaxInactiveInterval(30*60);
			response.sendRedirect(request.getContextPath()+"/pages/admin.jsp");
		}
		else {
			DbConnection connection = new DbConnection();
			Boolean isUserRegistered = connection.UserRegistered(MyConstants.CHECK_LOGIN_INFO, user, pwd);
			System.out.println(isUserRegistered);
			// This method is called by the user to check if the user is registered.
			if(isUserRegistered != null && isUserRegistered){
				HttpSession session = request.getSession();
				session.setAttribute("user", user);
				session.setAttribute("password", pwd);
		
				//setting session to expire in 30 minutes
				session.setMaxInactiveInterval(30*60);

				Cookie userName = new Cookie("user", user);
				userName.setMaxAge(30*60);
				response.addCookie(userName);
				response.sendRedirect(request.getContextPath()+"/home.jsp");
			}else{
				// set error message
			    request.setAttribute("errorMessage", "Invalid username or password");
			    // forward request to login page
			    RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
			    dispatcher.include(request, response);
			}
		}
		
		
	}

}