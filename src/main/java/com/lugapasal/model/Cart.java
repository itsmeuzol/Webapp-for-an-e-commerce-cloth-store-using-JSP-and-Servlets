package com.lugapasal.model;

public class Cart {
	private String username, productID, productName;
	
	public Cart(String username, String productID, String productName) {
		this.username = username;
		this.productID = productID;
		this.productName = productName;
	}
	/**
	* Returns the username associated with this user. This is a String that can be used to create a username for an API call.
	* 
	* 
	* @return the username associated with this user or null if there is no username associated with this user or if the username is
	*/
	public String getUsername() {
		return username;
	}

	/**
	* Sets the username that will be used for authenticating. This is a required field in the request.
	* 
	* @param username - the username to use for authenticating the request
	*/
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	* Returns the product ID. This is used to generate the URL to the web service when it is called.
	* 
	* 
	* @return the product ID or null if not set or the server does not support product ID generation ( such as if a product has been created
	*/
	public String getProductID() {
		return productID;
	}

	/**
	* Sets the product ID. This is used to identify the product when it is associated with a product.
	* 
	* @param productID - The product ID to identify the product when it is associated with
	*/
	public void setProductID(String productID) {
		this.productID = productID;
	}

	/**
	* Returns the product name. This is used to generate the URL for the product's URL. If you don't want this to be a good default use { @link getProductUrl ( String ) } instead.
	* 
	* 
	* @return the product name or null if there is no product name to be used by the product's URL
	*/
	public String getProductName() {
		return productName;
	}

	/**
	* Sets the product name. This is used to create a reference to the product in the database. If you want to add a reference to the product use #addProduct ( java. lang. String )
	* 
	* @param productName - The product name to
	*/
	public void setProductName(String productName) {
		this.productName = productName;
	}


}
