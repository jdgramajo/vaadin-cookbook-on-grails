package app.list

public class Contact {

	String firstName
	String lastName
	String email

	String getFullName() {
		"${firstName} ${lastName}"
	}

}
