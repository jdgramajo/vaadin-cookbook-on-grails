package app.table

import com.vaadin.data.util.BeanItemContainer
import com.vaadin.ui.Button
import com.vaadin.ui.Button.ClickEvent
import com.vaadin.ui.Button.ClickListener
import com.vaadin.ui.CustomComponent
import com.vaadin.ui.Table
import com.vaadin.ui.Table.ColumnGenerator
import com.vaadin.ui.VerticalLayout

@SuppressWarnings("serial")
public class PriceList extends CustomComponent {

	private BeanItemContainer<Product> container

	PriceList(container) {
		this.container = container
		Table table = createTable()
		// table.setContainerDataSource(container)

		VerticalLayout layout = new VerticalLayout()
		layout.addComponent(createAddProductButton())
		layout.addComponent(table)
		setCompositionRoot(layout)
	}

	private Button createAddProductButton() {
		Button addProductButton = new Button("Add product")
		addProductButton.addClickListener({ event ->
			container.addItem(new Product(name: "New product", price: 0.0))
		} as ClickListener)
		addProductButton
	}

	private Table createTable() {
		Table table = new Table()
		container.removeContainerProperty("metaClass")
		table.setContainerDataSource(container)
		table.setEditable(true)
		ColumnGenerator generator = new Table.ColumnGenerator() {
			
			Object generateCell(Table source, final Object itemId, Object columnId) {
				Button removeButton = new Button("x")
				removeButton.addClickListener({ event ->
						container.removeItem(itemId)
					} as ClickListener)
				return removeButton
			}
			
		}
		table.addGeneratedColumn("Remove", generator)
		return table
	}

}
