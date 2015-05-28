package app.reorder

import com.vaadin.data.util.BeanItemContainer
import com.vaadin.event.DataBoundTransferable
import com.vaadin.event.dd.DragAndDropEvent
import com.vaadin.event.dd.DropHandler
import com.vaadin.event.dd.acceptcriteria.AcceptAll
import com.vaadin.event.dd.acceptcriteria.AcceptCriterion
import com.vaadin.ui.Table
import com.vaadin.ui.Table.TableDragMode
import com.vaadin.ui.AbstractSelect.AbstractSelectTargetDetails
import com.vaadin.shared.ui.dd.VerticalDropLocation

@SuppressWarnings("serial")
public class ReorderTable extends Table {

	private BeanItemContainer<Product> container = new BeanItemContainer<Product>(Product.class)

	public ReorderTable() {
		if(container.getContainerPropertyIds().contains("metaClass")) {
			println "Removing metaClass..."
			container.removeContainerProperty("metaClass")
		}
		fillTableContainer(container)
		pageLength = size()
		containerDataSource = container
		columnReorderingAllowed = true
		dragMode = TableDragMode.ROW
		dropHandler = createDropHandler()
	}

	private DropHandler createDropHandler() {
		DropHandler dropHandler = new DropHandler() {

			@Override
			public AcceptCriterion getAcceptCriterion() {
				return AcceptAll.get()
			}

			@Override
			public void drop(DragAndDropEvent event) {
				DataBoundTransferable transferable = event.getTransferable() as DataBoundTransferable
				Product sourceItemId = transferable.getItemId() as Product

				AbstractSelectTargetDetails dropData = event.getTargetDetails() as AbstractSelectTargetDetails
				Object targetItemId = dropData.getItemIdOver()

				if (sourceItemId == targetItemId) {
					return
				}

				switch (dropData.getDropLocation()) {
					case VerticalDropLocation.BOTTOM:
						container.removeItem(sourceItemId)
						container.addItemAfter(targetItemId, sourceItemId)
						break
					case VerticalDropLocation.MIDDLE:					
					case VerticalDropLocation.TOP:
						container.removeItem(sourceItemId)
						Object prevItemId = container.prevItemId(targetItemId)
						container.addItemAfter(prevItemId, sourceItemId)
						break
				}
			}
		}

		return dropHandler
	}

	private void fillTableContainer(BeanItemContainer<Product> container) {
		int id = 0
		container.addItem(new Product(id: id++, name: "Computer", price: 599.90 as double))
		container.addItem(new Product(id: id++, name: "Mobile phone", price: 14.5 as double))
		container.addItem(new Product(id: id++, name: "Tablet", price: 99.90 as double))
		container.addItem(new Product(id: id++, name: "Mouse", price: 0.99 as double))
	}

}
