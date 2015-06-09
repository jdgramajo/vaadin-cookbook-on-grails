package app.container

import com.vaadin.data.Container
import com.vaadin.data.util.BeanItemContainer
import com.vaadin.ui.Table

@SuppressWarnings("serial")
public class ProductTable extends Table {

	private BeanItemContainer<Product> products = new BeanItemContainer<Product>(Product.class)
	
	public ProductTable() {
		fillContainer(products)		
		setContainerDataSource(products)
		removeContainerProperty('metaClass')
		setPageLength(size())		
	}
	
	private void fillContainer(Container container) {	
		int id = 0
		container.addItem(new Product(id: id++, name: "Computer", price: 599.90))
		container.addItem(new Product(id: id++, name: "Phone", price: 14.5))
		container.addItem(new Product(id: id++, name: "Tablet", price: 99.90))
		container.addItem(new Product(id: id++, name: "Mouse", price: 0.99))
	}
}
