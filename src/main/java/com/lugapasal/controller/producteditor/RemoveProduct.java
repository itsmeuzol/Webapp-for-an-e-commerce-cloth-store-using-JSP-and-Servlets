package com.lugapasal.controller.producteditor;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lugapasal.controller.dbConnection.DbConnection;
import com.lugapasal.resources.MyConstants;

/**
 * Servlet implementation class RemoveProduct
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/RemoveProduct" })
public class RemoveProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoveProduct() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	/**
	* Deletes a product from the database. This method is called by the service when a user clicks the delete button in the web interface
	* 
	* @param request - The servlet request we are processing
	* @param response - The servlet response we are creating ( not used
	*/
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String productId = request.getParameter("productId");
		DbConnection con = new DbConnection();
		int result = con.deleteProduct(MyConstants.DELETE_PRODUCT, productId);

		// This method is used to redirect to admin page
		if(result > 0) {
			response.sendRedirect(request.getContextPath() + "/pages/admin.jsp");
		}
		else {
			response.sendRedirect(request.getContextPath() + "/pages/admin.jsp");
		}
	}

}
