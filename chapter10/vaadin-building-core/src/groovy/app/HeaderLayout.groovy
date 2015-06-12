package app

import com.vaadin.ui.HorizontalLayout
import com.vaadin.ui.Label

public class HeaderLayout extends HorizontalLayout {

    public HeaderLayout() {
        Label label = new Label("User: Juan Ejemplo")
        addComponent(label)
    }
}
