package app.bind

import com.vaadin.data.fieldgroup.BeanFieldGroup
import com.vaadin.data.fieldgroup.FieldGroup
import com.vaadin.data.fieldgroup.PropertyId
import com.vaadin.data.util.BeanItem
import com.vaadin.ui.FormLayout
import com.vaadin.ui.TextField
import com.vaadin.ui.DateField
import com.vaadin.ui.CheckBox

@SuppressWarnings("serial")
public class BoundProductForm extends FormLayout {

	private TextField code = new TextField("Product code:")	
	@PropertyId("name")	
	private TextField nameField = new TextField("Product name:")	
	@PropertyId("price")
	private TextField priceField = new TextField("Price (USD):")
	@PropertyId("date")
	private DateField dateField = new DateField("Product creation date:")
	private CheckBox sellable = new CheckBox("Sellable:")

	public BoundProductForm() {		
		setSizeUndefined()
		margin = true			

		FieldGroup fieldGroup = new BeanFieldGroup<Product>(Product.class)		
		fieldGroup.with {
			setItemDataSource(new BeanItem<Product>(new Product(code: 1, name: "Tablet", price: 299.99 as double)))
			bindMemberFields(this)		
		}
		addComponent(code)
		addComponent(nameField)
		addComponent(priceField)
		addComponent(dateField)
		addComponent(sellable)
	}

}
