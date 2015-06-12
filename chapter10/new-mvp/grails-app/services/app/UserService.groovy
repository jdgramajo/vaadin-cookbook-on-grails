package app

import grails.transaction.Transactional

@Transactional
class UserService {

    boolean doLogin(name, password) {
    	try {
    	   	User user = User.findByName(name)
    	   	return user.password.equals(password)
    	} catch (Exception e) {
   			println e.message
    		return false
    	}
    }
}
