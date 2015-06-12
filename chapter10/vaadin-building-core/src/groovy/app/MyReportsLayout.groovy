package app

import com.vaadin.ui.Label
import com.vaadin.ui.VerticalLayout

public class MyReportsLayout extends VerticalLayout {

    public MyReportsLayout() {
        Label lbl = new Label("My reports")
        addComponent(lbl)
    }
}
