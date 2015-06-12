package app.views

import com.vaadin.ui.VerticalLayout
import com.vaadin.navigator.View
import com.vaadin.navigator.ViewChangeListener
import com.vaadin.ui.Button
import com.vaadin.ui.TextField
import com.vaadin.ui.Notification
import com.vaadin.grails.Grails

import app.UserService

class LoginView extends VerticalLayout implements View {

	static final String VIEW_NAME = "login"

	UserService userService = Grails.get(UserService)

	@Override
	void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
		TextField userName = new TextField("User:")
		TextField password = new TextField("Password:")
		Button loginButton = new Button("Login")

		addComponent(userName)
		addComponent(password)
		addComponent(loginButton)

		loginButton.addClickListener({ event ->
			if(userService.doLogin(userName.value, password.value)) {
				current.navigator.navigateTo(TagsView.VIEW_NAME)
			} else {
				Notification.show("Incorrect user/password.", Notification.Type.ERROR_MESSAGE)
			}
		} as Button.ClickListener)

	}

}