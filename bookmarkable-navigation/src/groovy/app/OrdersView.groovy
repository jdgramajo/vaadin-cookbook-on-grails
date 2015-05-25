package app

import com.vaadin.navigator.View
import com.vaadin.navigator.ViewChangeListener
import com.vaadin.ui.Label
import com.vaadin.ui.VerticalLayout

/**
 * @author Ondrej Kvasnovsky
 */
public class OrdersView extends VerticalLayout implements View {

    public static final String VIEW_NAME = "orders"

    OrdersView() {
        Label lblOrders = new Label("New orders: ")
        addComponent(lblOrders)
    }

    @Override
    void enter(ViewChangeListener.ViewChangeEvent event) {
        // e.g. load / reload data from database
        String parameters = event.getParameters()
        addComponent(new Label(parameters))
    }
}
