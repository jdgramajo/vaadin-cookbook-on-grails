package app

import spock.lang.Specification
import spock.lang.Shared

import com.vaadin.ui.Button
import com.vaadin.ui.Component
import com.vaadin.ui.TextField

class LoginLayoutSpec extends Specification {

	@Shared loginLayout = new LoginLayout()
	
	void "test login button is present"() {
		when:"get the index of the login button"
			int index = loginLayout.with { 
				getComponentIndex(btnLogin)
			}

		then:"the index gets the same component"
			loginLayout.with {
				getComponent(index) == btnLogin
			}
	}

}