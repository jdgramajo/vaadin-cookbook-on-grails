package app

import com.vaadin.server.VaadinRequest
import com.vaadin.ui.*

@SuppressWarnings("serial")
public class MyVaadinUI extends UI {

    @Override
    protected void init(VaadinRequest request) {
        final VerticalLayout layout = new VerticalLayout()
        layout.setMargin(true)
        setContent(layout)

        TextField txt = new TextField("Enter PIN Code")
        txt.with {
            addValidator(new PinValidator())
            setImmediate(true)
        }
        layout.addComponent(txt)
    }

}
