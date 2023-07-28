package com.lugapasal.controller.producteditor;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.lugapasal.controller.dbConnection.DbConnection;
import com.lugapasal.controller.register.PhotoUploader;
import com.lugapasal.model.Product;
import com.lugapasal.resources.MyConstants;

/**
 * Servlet implementation class EditProduct
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/EditProduct" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, //2MB
maxFileSize = 1024 * 1024 * 10, //10MB
maxRequestSize = 1024 * 1024 * 50 )
public class EditProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditProduct() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	/**
	* Handles the POST request. This method is called when the user clicks on the button to edit a product
	* 
	* @param request - The servlet request we are processing
	* @param response - The servlet response we are creating - not used
	*/
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int productID =  Integer.parseInt(request.getParameter("productID"));
		String productName = request.getParameter("productName");
		String productDescription = request.getParameter("productDescription");
		String categoryName = request.getParameter("categoryName");
		String unitPrice = request.getParameter("unitPrice");
		String brand = request.getParameter("brand");
		String rating = request.getParameter("rating");
		Part imagePart = request.getPart("image");
		String oldImage = request.getParameter("oldImage");
		String imageRelativePath = oldImage;
		// Upload the image part to the image part.
		if(imagePart != null) {
			PhotoUploader upload = new PhotoUploader();
			upload.removePhoto(oldImage);
			upload.uploadPhoto(imagePart);
			imageRelativePath = "images/" + upload.getFileName(imagePart);
		
		}

		
		Product productModal = new Product(productName, productDescription, categoryName, unitPrice, brand, imageRelativePath,  rating);
		DbConnection con = new DbConnection();
		int result = con.updateProduct(MyConstants.UPDATE_PRODUCT, productModal, productID);
		
		// This method is used to redirect to admin page
		if(result > 0) {
			response.sendRedirect(request.getContextPath() + "/pages/admin.jsp");
		}
		// This method is used to redirect to admin page
		else if(result == -1){
			response.sendRedirect(request.getContextPath() + "/pages/admin.jsp");
		}
		else {
			response.sendRedirect(request.getContextPath() + "/pages/admin.jsp");
		}
	}

}
