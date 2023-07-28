package com.lugapasal.controller.dbConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.lugapasal.model.Cart;
import com.lugapasal.model.Product;
import com.lugapasal.model.User;
import com.lugapasal.resources.Encryption;
import com.lugapasal.resources.MyConstants;


public class DbConnection {
	/**
	* This method returns a connection to the database. The connection is created by DriverManager. getConnection ( MyConstants. DB_URL MyConstants. DB_USER_NAME MyConstants. DB_USER_PASSWORD )
	* 
	* 
	* @return connection to the database or null if there is an error connecting to the database or connection cannot be created
	*/
	public Connection getConnection(){
		try {
			Class.forName(MyConstants.DRIVER_NAME);
			Connection connection = DriverManager.getConnection(
					MyConstants.DB_URL,
					MyConstants.DB_USER_NAME,
					MyConstants.DB_USER_PASSWORD);
			return connection;
		}catch(SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/**
	* Executes a select query and returns all results. This is a convenience method that calls getConnection () and executeQuery () on the connection returned by getConnection ().
	* 
	* @param query - the query to execute. It must be a SELECT statement.
	* 
	* @return a ResultSet or null if there are no results in the query or an error occurred while executing the query
	*/
	public ResultSet selectAllQuery(String query) {
		Connection dbConnection = getConnection();
		// Returns the result set or null if the database connection is null.
		if(dbConnection != null) {
			try {
				PreparedStatement statement = dbConnection.prepareStatement(query);
				ResultSet result = statement.executeQuery();
				return result;
			} catch (SQLException e) {
				return null;
			}
		}else {
			return null;
		}
	}

	/**
	* Select rows from the database based on a WHERE clause. This is useful for queries that don't have a primary key or an auto - increment ID
	* 
	* @param query - The query to use in the query
	* @param id - The primary key of the row to select.
	* 
	* @return A ResultSet containing the rows or null if there is no connection to the database or the query fails for any
	*/
	public ResultSet selectWhereQuery(String query, String id) {
		Connection dbConnection = getConnection();
		// Returns the result set of the query.
		if(dbConnection != null) {
			try {
				PreparedStatement statement = dbConnection.prepareStatement(query);
				statement.setString(1, id);
				ResultSet result = statement.executeQuery();
				return result;
			} catch (SQLException e) {
				return null;
			}
		}else {
			return null;
		}
	}

	/**
	* This method checks if the user is registered in the database. If the user is registered it returns true else it returns false
	* 
	* @param query - The query to be executed
	* @param username - The username of the user to check if it exists
	* @param password - The password of the user to check if it exists
	* 
	* @return true if the user is registered false if the user is not registered null if there is an error in the
	*/
	public Boolean UserRegistered(String query, String username, String password) {
		Connection dbConnection = getConnection();
		// This method will check the password and username provided by the user.
		if(dbConnection != null) {
			try {
				//Get the user name provided by the user
				PreparedStatement userStatement = dbConnection.prepareStatement(MyConstants.GET_USERNAME);
				userStatement.setString(1, username);
				ResultSet userResult = userStatement.executeQuery();
				// Returns true if the user exists and has the password encrypted.
				if(userResult.next()) {
					//if the user exists...
					PreparedStatement passwordStatement = dbConnection.prepareStatement(MyConstants.GET_PASSWORD);
					passwordStatement.setString(1,username);
					ResultSet passwordResult = passwordStatement.executeQuery();
					// Returns true if the password is valid and the password is valid.
					if(passwordResult.next()) {
						String encryptedPassword = passwordResult.getString("password");
						String decryptedPassword = Encryption.decrypt(encryptedPassword);
						System.out.println(encryptedPassword);
						System.out.println(decryptedPassword);
						System.out.println(password);
						boolean out = checkPassword(decryptedPassword, password);
						System.out.println(out);
						return out;
					}else return false;
				}
				else {
					return false;
				}
				
			} catch(SQLException e){ 
				return null;
			}
		}
		else return null; 
	}
	
	/**
	* Checks if the password is correct. This method is used to check if the user's password is correct.
	* 
	* @param decryptedPassword - The decrypted password that was used to decrypt the user
	* @param password - The password that was used to decrypt the user
	* 
	* @return True if the password is correct false otherwise. False is returned if the password is incorrect or if the user is not logged in
	*/
	private Boolean checkPassword(String decryptedPassword, String password) {
		// TODO Auto-generated method stub
		// Returns true if the decrypted password is equal to the password
		if(decryptedPassword.equals(password)) return true;
		else return false;
	}

	/**
	* Registers a user in the database. This method is used to register a new user in the database.
	* 
	* @param query - The query to be executed. Must be a stored procedure
	* @param userModel - The user to be
	*/
	public int registerUser(String query, User userModel) {
		Connection dbConnection = getConnection();
		int result = 0;
		try {
			PreparedStatement st= dbConnection.prepareStatement(query);
			st.setString(1, userModel.getFirstName());
			st.setString(2, userModel.getLastName());
			st.setString(3, userModel.getUsername());
			st.setString(4, Encryption.encrypt(userModel.getPassword()) );
			st.setString(5, userModel.getEmail());
			st.setString(6, userModel.getAddress());
			st.setString(7, userModel.getPhoneNumber());
			st.setString(8, userModel.getImageRelativePath());
			result = st.executeUpdate();
			
			return result;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return result;
		}
	}
	/**
	* Method to add a product to the database. It uses prepared statement to insert the data. If the data is invalid it returns 0
	* 
	* @param query - The query to insert.
	* @param productModal - The product to insert. Product. getId ()
	*/
	public int addProduct(String query, Product productModal) {
		Connection dbConnection = getConnection();
		int result = 0;
		try {
			PreparedStatement st = dbConnection.prepareStatement(query);
			st.setString(1, productModal.getProductName());
			st.setString(2, productModal.getProductDescription());
			st.setString(3, productModal.getCategoryName());
			st.setString(4, productModal.getUnitPrice());
			st.setString(5, productModal.getBrand());
			st.setString(6, productModal.getImageRelativePath());
			st.setString(7, productModal.getRating());
			result = st.executeUpdate();
			return result;
		} catch(SQLException e) {
			e.printStackTrace();
			return result;
		}
	}
	/**
	* Deletes a product from the database. This method is used to delete a product from the database. The query should contain a WHERE clause that is the same as the select statement
	* 
	* @param query - The query to be used
	* @param productId - The id of the
	*/
	public int deleteProduct(String query, String productId) {
		Connection dbConnection = getConnection();
		int result = 0;
		try {
			PreparedStatement st = dbConnection.prepareStatement(query);
			st.setString(1, productId);
			result = st.executeUpdate();
			return result;
		}
		catch(SQLException e) {
			e.printStackTrace();
			return result;
		}
	}
	/**
	* Updates a row in the Product table. This method is used to update the Product data in the database
	* 
	* @param query - The query to be used
	* @param productModal - The Product object to be updated
	* @param productID - The ID of the Product that is being
	*/
	public int updateProduct(String query, Product productModal, int productID) {
		Connection dbConnection = getConnection();
		int result = 0;
		try {
			PreparedStatement st = dbConnection.prepareStatement(query);
			st.setString(1, productModal.getProductName());
			st.setString(2, productModal.getProductDescription());
			st.setString(3, productModal.getCategoryName());
			st.setString(4, productModal.getUnitPrice());
			st.setString(5, productModal.getBrand());
			st.setString(6, productModal.getImageRelativePath());
			st.setString(7, productModal.getRating());
			st.setInt(8, productID);
			result = st.executeUpdate();
			return result;
		} catch(SQLException e) {
			e.printStackTrace();
			return result;
		}
	}
	/**
	* Updates user in database. This method is used for updating user information in database. The query is passed to update the row in user table
	* 
	* @param query - SQL query to update the row in user table
	* @param userModel - User that is to be updated in database
	* @param userID - ID of the user that is to be
	*/
	public int updateUser(String query, User userModel, int userID) {
		Connection dbConnection = getConnection();
		int result = 0;
		try {
			PreparedStatement st= dbConnection.prepareStatement(query);
			st.setString(1, userModel.getFirstName());
			st.setString(2, userModel.getLastName());
			st.setString(3, userModel.getUsername());
			st.setString(4, Encryption.encrypt(userModel.getPassword()) );
			st.setString(5, userModel.getEmail());
			st.setString(6, userModel.getAddress());
			st.setString(7, userModel.getPhoneNumber());
			st.setString(8, userModel.getImageRelativePath());
			st.setInt(9,userID);
			result = st.executeUpdate();
			
			return result;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return result;
		}
	}
	/**
	* Adds a cart to the database. This method is used to add an item to the cart. The query should be a select statement with one or more column to be inserted ( username productID productName )
	* 
	* @param query - The query to be used
	* @param cartModal - The cart to be
	*/
	public int addCart(String query, Cart cartModal) {
		Connection dbConnection = getConnection();
		int result = 0;
		try {
			PreparedStatement st= dbConnection.prepareStatement(query);
			st.setString(1, cartModal.getUsername());
			st.setString(2, cartModal.getProductID());
			st.setString(3, cartModal.getProductName());
			result = st.executeUpdate();
			
			return result;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return result;
		}
		
	}
}
