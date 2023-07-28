package com.lugapasal.controller.statemanagement;

public class SessionManage {

	/**
	* Checks if user is logged in. This is used to prevent login to user if there is no session
	* 
	* @param userSession - the user's session
	* 
	* @return true if user is logged in false if not logged in or session is not set in userSession.
	*/
	public Boolean checkUser(String userSession){
    	// Returns true if the user session is empty.
    	if(userSession != null && !userSession.isEmpty()) return true;
    	else return false;
    }
}
