package com.lugapasal.controller.producteditor;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lugapasal.controller.dbConnection.DbConnection;
import com.lugapasal.model.Cart;
import com.lugapasal.resources.MyConstants;
/**
 * Servlet implementation class AddCart
 */
@WebServlet("/AddCart")
public class AddCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	/**
	* Adds a new Cart to the database. This method is called by the doPost method of the servlet.
	* 
	* @param request - The servlet request we are processing. This is the request that was sent to the server.
	* @param response - The servlet response we are creating. This is the response that was sent to the server
	*/
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String productID = request.getParameter("productID");
		String username = request.getParameter("username");
		String productName = request.getParameter("productName");
		Cart cartModal = new Cart(username, productID, productName);
		DbConnection con = new DbConnection();
		int result = con.addCart(MyConstants.ADD_CART, cartModal);
		// If result 0 redirect to home page.
		if(result > 0) {
			response.sendRedirect("pages/alert.jsp");
		}
		else {
			response.sendRedirect("home.jsp");
		}
	}

}
