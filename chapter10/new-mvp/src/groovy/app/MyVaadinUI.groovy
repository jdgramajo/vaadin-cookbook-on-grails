package app

import com.vaadin.ui.UI
import com.vaadin.server.VaadinRequest
import com.vaadin.navigator.Navigator

import app.views.*

class MyVaadinUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {

        Navigator navigator = new Navigator(this, this)

        navigator.addView(LoginView.VIEW_NAME, LoginView)
        navigator.addView(TagsView.VIEW_NAME, TagsView)

        navigator.navigateTo(LoginView.VIEW_NAME)
		
    }
}
