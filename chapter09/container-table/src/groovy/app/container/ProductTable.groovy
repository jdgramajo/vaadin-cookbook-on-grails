package app.container

import com.vaadin.grails.Grails
import com.vaadin.data.Container
import com.vaadin.data.util.BeanItemContainer
import com.vaadin.ui.Table

@SuppressWarnings("serial")
public class ProductTable extends Table {

	private ProductService productService = Grails.get(ProductService)

	private BeanItemContainer<Product> products = new BeanItemContainer<Product>(Product.class)
	private final propertiesToShow = ['id', 'name', 'price']
	
	public ProductTable() {
		fillContainer(products)		
		setContainerDataSource(products)
		removeGroovyExtraProperties(containerPropertyIds - propertiesToShow)
		setPageLength(size())		
	}
	
	private void fillContainer(Container container) {
		productService.findAllProducts().each { container.addItem(it) }
	}

	private void removeGroovyExtraProperties(propertiesToRemove) {
		propertiesToRemove.each { removeContainerProperty(it) }
	}
}
