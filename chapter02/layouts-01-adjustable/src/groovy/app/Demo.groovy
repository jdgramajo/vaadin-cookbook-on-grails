package app

import app.adjustable.AdjustableLayout
import com.vaadin.ui.UI
import com.vaadin.server.VaadinRequest

class Demo extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        setContent(new AdjustableLayout())
    }
}
