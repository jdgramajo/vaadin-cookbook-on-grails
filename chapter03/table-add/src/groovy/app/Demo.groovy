package app

import app.table.PriceList
import app.table.Product
import com.vaadin.data.util.BeanItemContainer
import com.vaadin.server.VaadinRequest
import com.vaadin.ui.UI

/**
 * Main UI class
 */
@SuppressWarnings("serial")
public class Demo extends UI {

    @Override
    protected void init(VaadinRequest request) {
        PriceList priceList = new PriceList(createProductContainer())
        setContent(priceList)
    }
    
    private BeanItemContainer<Product> createProductContainer() {
        BeanItemContainer<Product> container = new BeanItemContainer<>(Product.class)
        container.with {
            addItem(new Product(name: "Computer", price: 599.90))
            addItem(new Product(name: "Mobile phone", price: 14.5))
            addItem(new Product(name: "Tablet", price: 99.90))
            addItem(new Product(name: "Mouse", price: 0.99))
        }
        container //return, the Groovy way
    }
    
}