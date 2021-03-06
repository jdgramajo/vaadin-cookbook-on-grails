package app

import com.vaadin.annotations.PreserveOnRefresh
import com.vaadin.annotations.Theme
import com.vaadin.server.*
import com.vaadin.ui.*
import com.vaadin.ui.Button.ClickListener

@Theme("mytheme")
public class MyVaadinUI extends UI {

    @Override
    protected void init(VaadinRequest request) {
        final VerticalLayout layout = new VerticalLayout()
        layout.setMargin(true)
        setContent(layout)

        Button btn = new Button("Click Button")
        btn.addClickListener({ event ->
            Label lbl = new Label("Turn off the server and click the button again.")
            layout.addComponent(lbl)
        } as ClickListener)
        layout.addComponent(btn)
    }
}
