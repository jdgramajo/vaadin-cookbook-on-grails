package app

class UserService {

	User loginUser(String login, String password) {
		new User(login: login, password: password)
	}

}