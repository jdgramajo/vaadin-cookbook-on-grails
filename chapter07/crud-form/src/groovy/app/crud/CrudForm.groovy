package app.crud

import com.vaadin.grails.Grails
import com.vaadin.data.Item
import com.vaadin.data.fieldgroup.BeanFieldGroup
import com.vaadin.data.fieldgroup.FieldGroup
import com.vaadin.data.fieldgroup.FieldGroup.CommitException
import com.vaadin.data.util.BeanItem
import com.vaadin.data.util.BeanItemContainer
import com.vaadin.event.Action
import com.vaadin.event.Action.Handler
import com.vaadin.event.ItemClickEvent.ItemClickListener
import com.vaadin.ui.Button
import com.vaadin.ui.Button.ClickListener
import com.vaadin.ui.FormLayout
import com.vaadin.ui.Notification
import com.vaadin.ui.Notification.Type
import com.vaadin.ui.Table
import com.vaadin.ui.VerticalLayout
import com.vaadin.ui.Window

@SuppressWarnings("serial")
public class CrudForm extends VerticalLayout {

	Table table = new Table()
	BeanItemContainer<Product> tableContainer
	FieldGroup fieldGroup
	Action actionDelete = new Action("Delete")
	static final int MAX_PAGE_LENGTH = 15
	ProductService productService = Grails.get(ProductService)
	final visibleProductProperties = ['code', 'name', 'price', 'salable']
	def propertiesToRemove

	CrudForm() {
		setSizeUndefined()
		setMargin(true)
		
		initTable()
		addComponent(createAddButton())
		addComponent(table)
		table.setPageLength(table.size())
	}

	private void initTable() {
		tableContainer = new BeanItemContainer<Product>(Product.class)
		propertiesToRemove = tableContainer.containerPropertyIds - visibleProductProperties
		propertiesToRemove.each { propertyId ->
			tableContainer.removeContainerProperty(propertyId)
		}
		fillTableContainer(tableContainer)
		table.setContainerDataSource(tableContainer)
		table.setSelectable(true)
		table.addItemClickListener({ event ->
			if (event.isDoubleClick()) {
				openProductWindow(event.getItem(), "Edit product")
			}
		} as ItemClickListener)

		table.addActionHandler(new Handler() {
			@Override
			public void handleAction(Action action, Object sender, Object target) {
				if (actionDelete == action) {
					tableContainer.removeItem(target)
					updateTable()
				}
			}

			@Override
			public Action[] getActions(Object target, Object sender) {
				[ actionDelete ] as Action[]
			}
		})
	}

	private Button createAddButton() {
		Button button = new Button("Add product")
		button.addClickListener({
			openProductWindow(new BeanItem<Product>(new Product(code: productService.getMaxCode() + 1,
				name: "New Product", price: 0.00)), "Add product")				
		} as ClickListener)
		return button
	}

	private void openProductWindow(Item beanItem, String caption) {
		Window window = new Window(caption)
		window.setModal(true)
		
		FormLayout layout = new FormLayout()
		layout.setMargin(true)
		window.setContent(layout)
		
		fieldGroup = new BeanFieldGroup<Product>(Product.class)
		fieldGroup.setItemDataSource(beanItem)
		fieldGroup.getUnboundPropertyIds().findAll { visibleProductProperties.contains(it) }. each { propertyId ->
			layout.addComponent(fieldGroup.buildAndBind(propertyId))
		}
		layout.addComponent(createOkButton(window))
		getUI().addWindow(window)
	}

	private Button createOkButton(final Window window) {
		Button okButton = new Button("OK")
		okButton.addClickListener({
			try {
				fieldGroup.commit()
				BeanItem<Product> beanItem = fieldGroup.getItemDataSource() as BeanItem<Product>
				if(!productService.saveProduct(beanItem.getBean())) {
					throw new Exception("Error persisting product.")
				}
				tableContainer.addItem(beanItem.getBean())					
				updateTable()
				window.close()
			} catch (CommitException ce) {
				Notification.show(ce.getMessage(), Type.ERROR_MESSAGE)					
			} catch (Exception e) {
				Notification.show(e.getMessage(), Type.ERROR_MESSAGE)
			}
		} as ClickListener)
		return okButton
	}

	private void updateTable() {
		table.setPageLength( table.size() > MAX_PAGE_LENGTH ? MAX_PAGE_LENGTH : table.size() )
		table.markAsDirtyRecursive()
	}

	private void fillTableContainer(BeanItemContainer<Product> tableContainer) {
		def products = productService.findAllProducts()
		products.each { product ->
			tableContainer.addItem(product)
		}
	}

}
