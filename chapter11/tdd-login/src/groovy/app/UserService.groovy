package app

class UserService {

	void loginUser(String login, String password) {
		User newUser = new User(login: login, password: password)
		println "[login: ${newUser.login}, password: ${newUser.password}]"
	}

}