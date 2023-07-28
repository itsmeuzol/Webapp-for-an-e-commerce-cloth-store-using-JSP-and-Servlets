package com.lugapasal.controller.userUpdate;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.lugapasal.controller.dbConnection.DbConnection;
import com.lugapasal.controller.register.PhotoUploader;
import com.lugapasal.model.User;
import com.lugapasal.resources.MyConstants;

/**
 * Servlet implementation class UpdateUser
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/UpdateUser" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, //2MB
maxFileSize = 1024 * 1024 * 10, //10MB
maxRequestSize = 1024 * 1024 * 50 )
public class UpdateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	/**
	* Serves the request. This method is called by the service method to handle the GET request. The default implementation does nothing
	* 
	* @param request - The servlet request we are processing
	* @param response - The servlet response we are creating for the servlet
	*/
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	/**
	* Creates a new user. If successful the user is returned to the client. If unsuccessful an exception is thrown
	* 
	* @param request - The servlet request we are processing
	* @param response - The servlet response we are creating ( can be null
	*/
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userID =  Integer.parseInt(request.getParameter("userID"));
		String firstName = request.getParameter("FirstName");
		String lastName = request.getParameter("LastName");
		String username = request.getParameter("Username");
		String password = request.getParameter("Password");
		String email = request.getParameter("Email");
		String address = request.getParameter("Address");
		String phoneNumber = request.getParameter("PhoneNumber");
		Part imagePart = request.getPart("image");
		String oldImage = request.getParameter("oldImage");
		String imageRelativePath = oldImage;
		// Uploads the image part of the photo.
		if(imagePart != null) {
			PhotoUploader upload = new PhotoUploader();
			upload.uploadPhoto(imagePart);
			imageRelativePath = "images/" + upload.getFileName(imagePart);
			upload.removePhoto(oldImage);
			
		}
		User userModel = new User(firstName, lastName, username, password, email, address, phoneNumber, imageRelativePath);
	
		DbConnection con = new DbConnection();
		int result = con.updateUser(MyConstants.UPDATE_USER, userModel, userID);
		HttpSession session = request.getSession(false);
		session.setAttribute("user", username);
		session.setAttribute("password", password);
		// This method is used to redirect to the profile page.
		if(result > 0) {
			response.sendRedirect(request.getContextPath() + "/pages/profile.jsp");
		}
		// This method is used to redirect to the profile page.
		else if(result == -1){
			response.sendRedirect(request.getContextPath() + "/pages/profile.jsp");
		}
		else {
			response.sendRedirect(request.getContextPath() + "/pages/profile.jsp");
		}
	
	}

}
