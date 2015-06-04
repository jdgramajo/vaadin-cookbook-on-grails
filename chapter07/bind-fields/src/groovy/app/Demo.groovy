package app

import app.bind.BoundProductForm
import com.vaadin.server.VaadinRequest
import com.vaadin.ui.UI

@SuppressWarnings("serial")
public class Demo extends UI {
    @Override
    protected void init(VaadinRequest request) {
        setContent(new BoundProductForm())
    }
}
