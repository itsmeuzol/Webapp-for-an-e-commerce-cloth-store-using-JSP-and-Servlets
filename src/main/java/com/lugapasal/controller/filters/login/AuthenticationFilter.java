package com.lugapasal.controller.filters.login;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/AuthenticationFilter")
public class AuthenticationFilter implements Filter {
	private ServletContext context;

	/**
	* Initialize the filter. This method is called by the container to initialize the filter. Subclasses should override this method to perform initialization specific to the type of filter being used.
	* 
	* @param filterConfig - Configuration information for the filter being used by
	*/
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.context = filterConfig.getServletContext();
		this.context.log("AuthenticationFilter initialized");
	}

	/**
	* This method is called when the servlet receives a request. It checks if the request is a login or logout request and if so redirects to the appropriate page.
	* 
	* @param request - The servlet request we are processing. This is the servlet request we are processing.
	* @param response - The servlet response we are creating. This is the servlet response we are creating.
	* @param chain - The filter chain we are processing. This is the filter
	*/
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		// check if the request is a login or logout request
		String uri = req.getRequestURI();
		HttpSession session = req.getSession(false);
		boolean loggedIn = session != null && session.getAttribute("user") != null;

		String loginURI = req.getContextPath() + "/login.jsp";
		String registerURI = req.getContextPath() + "/pages/registration.jsp";
		String adminURI = req.getContextPath() + "/pages/admin.jsp";
		String homeURI = req.getContextPath() + "/home.jsp";
		String searchURI = req.getContextPath() + "/pages/search.jsp";
		String menURI = req.getContextPath() + "/pages/searchMen.jsp";
		String womenURI = req.getContextPath() + "/pages/searchWomen.jsp";
		String childURI = req.getContextPath() + "/pages/searchChildren.jsp";
		String rateURI = req.getContextPath() + "/pages/sortRating.jsp";
		String priceURI = req.getContextPath() + "/pages/sortPrice.jsp";

		boolean isLoginURI = uri.equals(loginURI);
		boolean isRegisterURI = uri.equals(registerURI);
		boolean isAdminURI = uri.equals(adminURI);
		boolean isHomeURI = uri.equals(homeURI);
		boolean isCSS = uri.endsWith(".css");
		boolean isLoginServlet = uri.endsWith("LoginServlet");
		boolean isLogoutServlet = uri.endsWith("LogourServlet");
		boolean isRegisterServlet = uri.endsWith("RegisterUser");
		boolean isImg = uri.endsWith(".jpg");
		boolean isSearch = (uri.equals(searchURI)) || (uri.equals(menURI)) || (uri.equals(womenURI)) || (uri.equals(rateURI)) || (uri.equals(priceURI)) || (uri.equals(childURI));
		// This method is called by the servlet.
		if (isLoginURI || isRegisterURI || isHomeURI || isCSS || isLoginServlet || isLogoutServlet
				|| isRegisterServlet || isImg || isSearch) {
			this.context.log("Requested Resource::" + uri);
			chain.doFilter(request, response);
		// This method is called by the servlet when the user is logged in.
		} else if (isAdminURI) {
			// This method is called by the servlet when the user is logged in.
			if(session != null) {this.context.log("Requested Resource::" + uri);
			String username = (String) session.getAttribute("user");
			String password = (String) session.getAttribute("password");
			// If username and password are null then redirect to home.
			if (username != null && username.equals("admin") && password != null && password.equals("admin")) {
				chain.doFilter(request, response);
			// If username is null redirect to home page.
			} else if (username != null) {
				res.sendRedirect(req.getContextPath() + "/home.jsp");
			}

			else {
				res.sendRedirect(req.getContextPath() + "/login.jsp");
			}
			}
			else {
				res.sendRedirect(req.getContextPath() + "/login.jsp");
			}
			
			
		// If logged in redirect to login. jsp
		} else if (!loggedIn) {
			res.sendRedirect(req.getContextPath() + "/login.jsp");
		} else {
			chain.doFilter(request, response);
		}
	}

	/**
	* Destroy the object. This is called when the object is no longer needed to be used by the user
	*/
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
