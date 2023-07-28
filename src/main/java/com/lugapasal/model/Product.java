package com.lugapasal.model;

public class Product {
	private String productName, productDescription, categoryName, unitPrice, brand, imageRelativePath, rating;

	public Product(String productName, String productDescription, String categoryName, String unitPrice,
			String brand, String imageRelativePath, String rating) {
		this.productName = productName;
		this.productDescription = productDescription;
		this.categoryName = categoryName;
		this.unitPrice = unitPrice;
		this.brand = brand;
		this.imageRelativePath = imageRelativePath;
		this.rating = rating;
	}
	/**
	* Returns the rating of the person. This is used to generate the rating of the person based on the information provided by the user.
	* 
	* 
	* @return the rating of the person as a string or null if there is no rating for the user ( for example if the user doesn't have a rating
	*/
	public String getRating() {
		return rating;
	}
	/**
	* Sets the rating of the item. Note that this is a property of the Item and should not be changed in the future
	* 
	* @param rating - The rating of the
	*/
	public void setRating(String rating) {
		this.rating = rating;
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
	* Returns the path to the image. This is used to determine where the image is stored in the file system.
	* 
	* 
	* @return the path to the image relative to the file system or null if the file does not exist or cannot be
	*/
	public String getImageRelativePath() {
		return imageRelativePath;
	}
	/**
	* Sets the relative path to the image. For example if you want to set the image relative path to the images folder you can do : myImage. setImageRelativePath ( " images / myImage. png " )
	* 
	* @param imageRelativePath - the relative path to the image. For example if you want to set the image relative path to the images folder you can do : myImage
	*/
	public void setImageRelativePath(String imageRelativePath) {
		this.imageRelativePath = imageRelativePath;
	}
	/**
	* Sets the product name. This is used to create a reference to the product in the database. If you want to add a reference to the product use #addProduct ( java. lang. String )
	* 
	* @param productName - The product name to
	*/
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	* Returns the description of the product. This can be used to create a link to the product's information in the web.
	* 
	* 
	* @return the description of the product or null if there is no description to be associated with the product or it is
	*/
	public String getProductDescription() {
		return productDescription;
	}

	/**
	* Sets the product description. Note that this is a public method and should not be called by anything other than the client
	* 
	* @param productDescription - the product description to
	*/
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	/**
	* Returns the name of the category. This is used to distinguish the category from other categories that are part of the same category ( such as an article ).
	* 
	* 
	* @return the name of the category or null if there is no category or it is not a subcategory of a
	*/
	public String getCategoryName() {
		return categoryName;
	}

	/**
	* Sets the category name. This is used to distinguish this class from other classes that are part of the same class hierarchy
	* 
	* @param categoryName - the name of the
	*/
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	/**
	* Returns the unit price. This is used to generate the tax of the unit in the form of a string that can be used in XML or XML - RPC.
	* 
	* 
	* @return the unit price as a string or null if there is no unit price in the form of a string
	*/
	public String getUnitPrice() {
		return unitPrice;
	}

	/**
	* Sets the unit price of the price. This is used to determine the amount of money that will be displayed in the shopping cart when it is created.
	* 
	* @param unitPrice - The unit price of the price in the format " % "
	*/
	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}

	/**
	* Returns the Brand of the Game. Note that this is a copy of the brand in the Game and should be replaced by something that can be used to build the game.
	* 
	* 
	* @return the brand of the Game or null if none is set ( for example if it's an unsaved game
	*/
	public String getBrand() {
		return brand;
	}

	/**
	* Sets the brand of the player. Note that this is a public method and should not be called by user code.
	* 
	* @param brand - the brand of the player ( can be null
	*/
	public void setBrand(String brand) {
		this.brand = brand;
	}

	

}
