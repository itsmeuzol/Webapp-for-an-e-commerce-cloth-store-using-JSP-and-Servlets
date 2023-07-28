package com.lugapasal.controller.filters.login;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@WebFilter("/requestloggingfilter")
public class RequestLoggingFilter implements Filter {
	private ServletContext context;

	/**
	* Initialize the filter. This method is called by the container to initialize the filter. Subclasses should override this method to perform initialization specific to the filter.
	* 
	* @param filterConfig - Configuration information for the filter being initialized. Not
	*/
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.context = filterConfig.getServletContext();
		this.context.log("RequestLoggingFilter initialized");
	}

	/**
	* Logs the request parameters and cookies. This method is called by the container to forward the request to the next filter in the chain.
	* 
	* @param request - The servlet request we are processing. Cannot be null.
	* @param response - The servlet response we are creating. Cannot be null.
	* @param chain - The filter chain we are processing. Cannot be null
	*/
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		Enumeration<String> params = req.getParameterNames();
		// Get the next request parameters from the params array.
		while(params.hasMoreElements()){
			String name = params.nextElement();
			String value = request.getParameter(name);
			this.context.log(req.getRemoteAddr() + "::Request Params::{"+name+"="+value+"}");
		}

		Cookie[] cookies = req.getCookies();
		// Logs the cookies in the request.
		if(cookies != null){
			for(Cookie cookie : cookies){
				this.context.log(req.getRemoteAddr() + "::Cookie::{"+cookie.getName()+","+cookie.getValue()+"}");
			}
		}
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	* Destroy the object. This is called when the object is no longer needed to be used by the user
	*/
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}
}
