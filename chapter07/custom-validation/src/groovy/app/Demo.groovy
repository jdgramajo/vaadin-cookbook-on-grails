package app

import app.validation.ChangePasswordForm
import com.vaadin.server.VaadinRequest
import com.vaadin.ui.UI

@SuppressWarnings("serial")
public class Demo extends UI {
    @Override
    protected void init(VaadinRequest request) {
        setContent(new ChangePasswordForm())
    }
}
