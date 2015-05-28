package app

import app.lazyloading.ProductTable
import com.vaadin.server.VaadinRequest
import com.vaadin.ui.UI

@SuppressWarnings("serial")
public class Demo extends UI {

    @Override
    protected void init(VaadinRequest request) {
        setContent(new ProductTable())
    }

}