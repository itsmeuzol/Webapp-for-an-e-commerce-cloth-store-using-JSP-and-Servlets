package com.lugapasal.model;



public class User {
	String firstName, lastName, username, password, email, address, phoneNumber, imageRelativePath;

	public User (String firstName, String lastName, String username, String password, String email, String address, String phoneNumber, String imageRelativePath){
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.email = email;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.imageRelativePath = imageRelativePath;
	}

	/**
	* Returns the email associated with this user. Note that this is a copy of the value returned by #getEmail ().
	* 
	* 
	* @return the email associated with this user or null if there is no email associated with this user or if the user is
	*/
	public String getEmail() {
		return email;
	}

	/**
	* Sets the email to be sent to the user. This is a convenience method for setting the email to be sent to the user
	* 
	* @param email - the e - mail
	*/
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	* Returns the first name of the person. This is the person's first name if it has one.
	* 
	* 
	* @return the person's first name or null if there is no first name to be returned by this method
	*/
	public String getFirstName() {
		return firstName;
	}

	/**
	* Sets the first name of the person. Note that this will be ignored if the person is a person group
	* 
	* @param firstName - the person's first
	*/
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	* Returns the last name of the person. This is used to check if the person is indeed a person and if so return it.
	* 
	* 
	* @return the last name of the person or null if there is no last name ( in which case it is an empty string
	*/
	public String getLastName() {
		return lastName;
	}

	/**
	* Sets the last name of the person. Note that this will be ignored if the person is a member of the org. bukkit. PersonGroup.
	* 
	* @param lastName - the person's last name to set
	*/
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
	* Returns the password to use for this authentication. If this method is called before #authenticate ( java. lang. String ) it will return null.
	* 
	* 
	* @return the password to use for this authentication null if not set or if there is no password to use for
	*/
	public String getPassword() {
		return password;
	}

	/**
	* Sets the password to use for authenticating. By default this is null. You can override this with a password that is stored in the database or by passing it to #setPassword ( String ).
	* 
	* @param password - the password to use for authenticating. If null the password will be reset
	*/
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	* Returns the address of the peer. This is used to verify that the peer is indeed a member of the group or not.
	* 
	* 
	* @return the address of the peer or null if it is not a member of the group or if the address is
	*/
	public String getAddress() {
		return address;
	}

	/**
	* Sets the address of the peer. This is used to connect to the peer when it is in the middle of a connection.
	* 
	* @param address - the address of the peer to connect to in
	*/
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	* Returns the phone number. This is a string formatted as " X. X. Y ". If you don't care about the number it will return " ".
	* 
	* 
	* @return the phone number as a string formatted as " X. Y. Z " or " " if you don't care about the number
	*/
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	* Sets the phone number. This is used to determine the country where the user is logged in. If you don't want to use this you can use setCountry ()
	* 
	* @param phoneNumber - The phone number to
	*/
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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

}

