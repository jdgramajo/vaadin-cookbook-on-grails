package app

import com.vaadin.server.VaadinRequest
import com.vaadin.ui.UI

@SuppressWarnings("serial")
class MyVaadinUI extends UI {

    @Override
    protected void init(VaadinRequest request) {
        SystemStatusLayout layout = new SystemStatusLayout()
        setContent(layout)
    }
}
