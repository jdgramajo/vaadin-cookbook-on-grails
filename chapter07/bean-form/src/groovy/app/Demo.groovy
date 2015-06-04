package app

import app.generate.GeneratedProductForm
import com.vaadin.server.VaadinRequest
import com.vaadin.ui.UI
import com.vaadin.ui.Panel

@SuppressWarnings("serial")
public class Demo extends UI {

    @Override
    protected void init(VaadinRequest request) {
        GeneratedProductForm productForm = new GeneratedProductForm()
        setContent(productForm)
    }

}
