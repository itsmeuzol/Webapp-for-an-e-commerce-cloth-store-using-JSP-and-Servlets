package com.lugapasal.resources;

public class MyConstants {
	// Start Region: Database Configuration
		public static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
		public static final String DB_URL = "jdbc:mysql://localhost:3306/e-commerce";
		public static final String DB_USER_NAME = "root";
		public static final String DB_USER_PASSWORD= "";
		// End Region: Database Configuration

		// Start Region: Query\
		public static final String GET_USERNAME = "SELECT user_name from user WHERE user_name = ?";
		public static final String GET_PASSWORD = "SELECT password from user WHERE user_name = ?";
		public static final String CHECK_LOGIN_INFO = "SELECT user_name, password FROM user WHERE user_name = ? AND password = ?";
		public static final String GET_ALL_INFO = "SELECT * FROM register";
		public static final String GET_ALL_INFO_BY_ID = "SELECT * FROM register WHERE id = ?";
		public static final String USER_REGISTER = "INSERT INTO user(first_name,last_name,user_name,password,email,address,phone_number,image) VALUES(?,?,?,?,?,?,?,?) ";
		public static final String PRODUCT_REGISTER = "INSERT INTO product(product_name, product_description, category_name, unit_price, brand, image, rating) VALUES(?,?,?,?,?,?,?)";
		public static final String DELETE_PRODUCT = "DELETE FROM product WHERE product_id = ?";
		public static final String UPDATE_PRODUCT = "UPDATE product SET product_name = ?, product_description = ?, category_name = ?,unit_price = ?, brand = ?, image = ?, rating=? WHERE product_id=?";
		public static final String UPDATE_USER = "UPDATE user SET first_name=?, last_name=?, user_name=?, password=?, email=?, address=?, phone_number=?, image=? WHERE user_id = ?";
		public static final String ADD_CART = "INSERT INTO orders(username, product_id, product_name) VALUES(?,?,?)";
		// End Region: Query
}

//file input stream
//file output streamimage1