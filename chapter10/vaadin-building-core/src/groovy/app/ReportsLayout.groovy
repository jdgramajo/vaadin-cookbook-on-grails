package app

import com.vaadin.ui.Label
import com.vaadin.ui.VerticalLayout

public class ReportsLayout extends VerticalLayout {

    public ReportsLayout() {
        Label lbl = new Label("Reports")
        addComponent(lbl)
    }
}
