package app.complextable

import com.vaadin.data.Container
import com.vaadin.data.Item
import com.vaadin.data.fieldgroup.FieldGroup
import com.vaadin.data.fieldgroup.FieldGroup.CommitException
import com.vaadin.data.util.BeanItemContainer
import com.vaadin.event.Action
import com.vaadin.event.Action.Handler
import com.vaadin.event.ItemClickEvent.ItemClickListener
import com.vaadin.shared.MouseEventDetails.MouseButton
import com.vaadin.ui.Button
import com.vaadin.ui.Button.ClickListener
import com.vaadin.ui.FormLayout
import com.vaadin.ui.HorizontalSplitPanel
import com.vaadin.ui.Layout
import com.vaadin.ui.Notification
import com.vaadin.ui.Notification.Type
import com.vaadin.ui.Table

@SuppressWarnings("serial")
public class CRUD extends HorizontalSplitPanel {

	private final static Action ACTION_ADD = new Action("Add")
	private final static Action ACTION_DELETE = new Action("Delete")
	private int currentProductId = 0
	private BeanItemContainer<Product> products = new BeanItemContainer<>(Product.class)
	private final propertiesToShow = ['productId', 'name', 'price']

	CRUD() {		
		fillContainer(products)
		setFirstComponent(createTable(products))
	}

	private Table createTable(Container container) {		
		final Table table = new Table(null, container)	
		table.with {
			setSelectable(true)
			setSizeFull()
			addItemClickListener({ event ->
				if (MouseButton.LEFT.getName().equals(event.getButtonName())) {
					setSecondComponent(createForm(event.getItem()))
				}
			} as ItemClickListener)

			addActionHandler(new Handler() {
				@Override
				public void handleAction(Action action, Object sender, Object target) {
					if (ACTION_DELETE == action) {
						products.removeItem(target)
					}
					if (ACTION_ADD == action) {					
						products.addBean(new Product(productId: currentProductId++, name: "", price: 0.0))					
					}
				}

				@Override
				public Action[] getActions(Object target, Object sender) {
					[ ACTION_ADD, ACTION_DELETE ] as Action[]
				}
			})
			setVisibleColumns(propertiesToShow.toArray())
		}
		return table
	}

	private Layout createForm(Item item) {
		FormLayout layout = new FormLayout()
		layout.with {
			setSpacing(true)
			setMargin(true)
		}
		final FieldGroup group = new FieldGroup(item)
		group.unboundPropertyIds.each { propertyId ->
			if (propertiesToShow.contains(propertyId)) {
				layout.addComponent(group.buildAndBind(propertyId))
			}
		}
		Button button = new Button("Commmit")
		button.addClickListener({ 
			try {
				group.commit()
			} catch (CommitException e) {
				Notification.show(e.getCause().getMessage(), Type.ERROR_MESSAGE)
			}
		} as ClickListener)
		layout.addComponent(button)
		return layout
	}

	private void fillContainer(Container container) {
		container.addItem(new Product(productId: currentProductId++, name: "Computer", price: 599.90))
		container.addItem(new Product(productId: currentProductId++, name: "Phone", price: 14.5))
		container.addItem(new Product(productId: currentProductId++, name: "Tablet", price: 99.90))
		container.addItem(new Product(productId: currentProductId++, name: "Mouse", price: 0.99))
	}

}
