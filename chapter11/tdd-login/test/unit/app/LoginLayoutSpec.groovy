package app

import spock.lang.Specification
import spock.lang.Shared
import spock.lang.IgnoreRest

import com.vaadin.ui.Button
import com.vaadin.ui.Component
import com.vaadin.ui.TextField
import com.vaadin.ui.UI

class LoginLayoutSpec extends Specification {

	@Shared loginLayout = new LoginLayout()
	
	void "login button is present"() {
		when:"get the index of the login button"
			int index = loginLayout.with { 
				getComponentIndex(btnLogin)
			}

		then:"the index gets the same component"
			loginLayout.with {
				getComponent(index) == btnLogin
			}
	}

	void "login is working"() {
		given:"there is a MyVaadinUI object as current UI"
			UI.current = new MyVaadinUI()

		when:"form is filled and login button clicked"
			loginLayout.with {
				txtUsername.value = "New"
				txtPassword.value = "User"
				btnLogin.click()
			}

		then:"there's a new user in the UI data"
			UI.current.data as User
	}

	void "login button with proper caption, visible and enabled"() {
		when:"get the button in layout"
			Button btn = loginLayout.btnLogin
		then:
			btn.caption.equals("Login")
			btn.visible
			btn.enabled
	}

}