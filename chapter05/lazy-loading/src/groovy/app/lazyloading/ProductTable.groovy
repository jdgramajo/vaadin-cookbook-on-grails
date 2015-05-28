package app.lazyloading

import com.vaadin.data.util.BeanItemContainer
import com.vaadin.ui.Table

@SuppressWarnings("serial")
class ProductTable extends Table {

	private BeanItemContainer<Product> tableContainer = new BeanItemContainer<Product>(Product.class)

	ProductTable() {
		if(tableContainer.getContainerPropertyIds().contains("metaClass")) { 
			// Expando metaclass, Grails stuff, TODO: investigate
			println "Removing metaClass from container..."
			tableContainer.removeContainerProperty("metaClass")
		}
		fillTableContainer(tableContainer, 10000)
		setPageLength(10)
		setCacheRate(4)
		setContainerDataSource(tableContainer)		
	}

	private void fillTableContainer(BeanItemContainer<Product> container, int count) {		
		count.times {
			container.addItem(new Product(id: it, name: "Product-${it}", price: 599 as double))
		}		
	}

}
