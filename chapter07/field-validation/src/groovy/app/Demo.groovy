package app

import app.validation.EmailField
import com.vaadin.server.VaadinRequest
import com.vaadin.ui.FormLayout
import com.vaadin.ui.UI

@SuppressWarnings("serial")
public class Demo extends UI {
    @Override
    protected void init(VaadinRequest request) {
        FormLayout layout = new FormLayout()
        layout.margin = true
        setContent(layout)
        EmailField field = new EmailField("Email:")        
        layout.addComponent(field)     
    }
}
