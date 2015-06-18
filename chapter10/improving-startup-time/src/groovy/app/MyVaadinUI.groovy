package app

import com.vaadin.server.VaadinRequest
import com.vaadin.ui.Button
import com.vaadin.ui.Button.ClickListener
import com.vaadin.ui.Label
import com.vaadin.ui.UI
import com.vaadin.ui.VerticalLayout
import org.vaadin.artur.widgetsetoptimizer.WidgetSetOptimizer

@SuppressWarnings("serial")
public class MyVaadinUI extends UI
{

    @Override
    protected void init(VaadinRequest request) {
        new WidgetSetOptimizer().extend(this)

        final VerticalLayout layout = new VerticalLayout()
        layout.setMargin(true)
        setContent(layout)
        
        Button button = new Button("Click Me")
        button.addClickListener({
            layout.addComponent(new Label("Thank you for clicking"))
        } as ClickListener)
        layout.addComponent(button)
    }
}
