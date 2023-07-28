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
import com.lugapasal.model.Product;
import com.lugapasal.resources.MyConstants;

/**
 * Servlet implementation class AddProduct
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/AddProduct" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, //2MB
maxFileSize = 1024 * 1024 * 10, //10MB
maxRequestSize = 1024 * 1024 * 50 )

public class AddProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddProduct() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String productName = request.getParameter("productName");
		String productDescription = request.getParameter("productDescription");
		String categoryName = request.getParameter("categoryName");
		String unitPrice = request.getParameter("unitPrice");
		String brand = request.getParameter("brand");
		String rating = request.getParameter("rating");
		Part imagePart = request.getPart("image");
		String imageRelativePath = "";
		if(imagePart != null) {
			PhotoUploader upload = new PhotoUploader();
			upload.uploadPhoto(imagePart);
			imageRelativePath = "images/" + upload.getFileName(imagePart);
		}

		
		Product productModal = new Product(productName, productDescription, categoryName, unitPrice, brand, imageRelativePath,  rating);
		
		DbConnection con = new DbConnection();
		int result = con.addProduct(MyConstants.PRODUCT_REGISTER, productModal);
		
		if(result > 0) {
			request.setAttribute("registerMessage", "Successfully Registered!");
			response.sendRedirect(request.getContextPath() + "/pages/admin.jsp");
		}
		else if(result == -1){
			request.setAttribute("registerMessage", "Product Already Exists!");
			response.sendRedirect(request.getContextPath() + "/pages/admin.jsp");
		}
		else {
			response.sendRedirect(request.getContextPath() + "/pages/admin.jsp");
		}

	}

}
