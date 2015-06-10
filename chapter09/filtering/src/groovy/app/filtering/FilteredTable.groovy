package app.filtering

import com.vaadin.data.Container.Filter
import com.vaadin.data.util.BeanItemContainer
import com.vaadin.data.util.filter.Compare.Greater
import com.vaadin.event.FieldEvents.TextChangeEvent
import com.vaadin.event.FieldEvents.TextChangeListener
import com.vaadin.ui.CustomComponent
import com.vaadin.ui.HorizontalLayout
import com.vaadin.ui.Notification
import com.vaadin.ui.Table
import com.vaadin.ui.TextField
import com.vaadin.ui.VerticalLayout

@SuppressWarnings("serial")
public class FilteredTable extends CustomComponent {

	private BeanItemContainer<Product> container = new BeanItemContainer<>(Product.class)
	private static final Object[] COLUMN_IDS = ["productId", "name", "price"] as Object[]
	private static final int[] COLUMN_WIDTHS = [50, 100, 70] as int[]
	private static final int COLUMN_SPACE = 13

	public FilteredTable() {		
		VerticalLayout layout = new VerticalLayout()
		setCompositionRoot(layout)
		
		Table table = new Table()
		layout.addComponent(createFilters(table))

		fillContainer(container)
		
		table.with {
			pageLength = table.size()
			containerDataSource = container
			visibleColumns = COLUMN_IDS
		}

		layout.addComponent(table)
	}

	private HorizontalLayout createFilters(final Table table) {
		HorizontalLayout filtersLayout = new HorizontalLayout()
		int i = 0
		COLUMN_IDS.each { columnID ->
			int columnWidth = COLUMN_WIDTHS.getAt(i++)
			table.setColumnWidth(columnID, columnWidth)
			final TextField field = new TextField()
			field.setWidth(columnWidth + COLUMN_SPACE, Unit.PIXELS)
			if ("productId".equals(columnID)) {
				field.setConverter(Integer.class)
			}
			field.addTextChangeListener({ event ->
				filterTable(table, columnID, event.getText())
			} as TextChangeListener)

			filtersLayout.addComponent(field)
		}
		return filtersLayout
	}

	private void filterTable(Table table, Object columnID, String value) {
		container.removeContainerFilters(columnID)	

		if ("productId".equals(columnID)) {
			try {
				Filter greater = new Greater(columnID, new Integer(value))
				container.addContainerFilter(greater)				
			} catch (NumberFormatException e) {
				if (!value.isEmpty()) {
					Notification.show("Cannot filter by value: " + value)
				}
			}
		} else {
			container.addContainerFilter(columnID, value, true, false)
		}
	}

	private void fillContainer(BeanItemContainer<Product> container) {
		int id = 0
		container.addItem(new Product(productId: id++, name: "Computer", price: 599.90))
		container.addItem(new Product(productId: id++, name: "Phone", price: 14.5))
		container.addItem(new Product(productId: id++, name: "Tablet", price: 99.90))
		container.addItem(new Product(productId: id++, name: "Mouse", price: 0.99))
	}

}
