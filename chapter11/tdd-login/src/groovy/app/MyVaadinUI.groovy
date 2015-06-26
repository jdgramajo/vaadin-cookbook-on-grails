package app

import com.vaadin.ui.UI
import com.vaadin.ui.VerticalLayout
import com.vaadin.server.VaadinRequest

class MyVaadinUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
		VerticalLayout layout = new LoginLayout()      
		setContent(layout)
    }
}
