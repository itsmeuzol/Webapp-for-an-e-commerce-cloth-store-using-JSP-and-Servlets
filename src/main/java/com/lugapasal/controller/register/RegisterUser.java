package com.lugapasal.controller.register;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.lugapasal.controller.dbConnection.DbConnection;
import com.lugapasal.model.User;
import com.lugapasal.resources.MyConstants;


/**
 * Servlet implementation class RegisterUser
 */
@WebServlet(urlPatterns = { "/RegisterUser" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, //2MB
maxFileSize = 1024 * 1024 * 10, //10MB
maxRequestSize = 1024 * 1024 * 50 )

public class RegisterUser extends HttpServlet {
	private static final long serialVersionUID = 1L;


    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterUser() {
        super();
        // TODO Auto-generated constructor stub
    }

   
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
	/**
	* Handles the POST request. This method is called by the service method register ( HttpServletRequest ). The request contains the information needed to register a new user.
	* 
	* @param request - The servlet request we are processing. This is the request that was sent to the server.
	* @param response - The servlet response we are creating. This is the response that is sent to the server
	*/
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String phoneNumber = request.getParameter("phoneNumber");
		Part imagePart = request.getPart("image");
		String imageRelativePath = "";
		// Uploads the image part to the image part.
		if(imagePart != null) {
			PhotoUploader upload = new PhotoUploader();
			
			upload.uploadPhoto(imagePart);
			
			imageRelativePath = "images/" + upload.getFileName(imagePart);
		}

		User userModel = new User(firstName, lastName, username, password, email, address, phoneNumber, imageRelativePath);
		
		DbConnection con = new DbConnection();
		int result = con.registerUser(MyConstants.USER_REGISTER, userModel);
		
		// Register user with the user.
		if(result > 0) {
			request.setAttribute("registerMessage", "Successfully Registered!");
			response.sendRedirect(request.getContextPath()+"/login.jsp?errorMessage=Successfully%20Registered!\"");
		}
		// Register User Already Exists. If result is 1 then the user is not registered.
		else if(result == -1){
			request.setAttribute("registerMessage", "User Already Exists!");
			request.getRequestDispatcher("pages/registration.jsp").forward(request,response);
		}
		else {
			request.getRequestDispatcher("pages/registration.jsp").forward(request, response);
		}
	}
	
	

}
