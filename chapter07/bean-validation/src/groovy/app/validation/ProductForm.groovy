package app.validation

import com.vaadin.grails.Grails
import com.vaadin.data.fieldgroup.BeanFieldGroup
import com.vaadin.data.fieldgroup.FieldGroup
import com.vaadin.data.fieldgroup.FieldGroup.CommitException
import com.vaadin.data.validator.*
import com.vaadin.data.util.BeanItem
import com.vaadin.ui.Button
import com.vaadin.ui.Button.ClickEvent
import com.vaadin.ui.Button.ClickListener
import com.vaadin.ui.FormLayout
import com.vaadin.ui.Notification
import com.vaadin.ui.Notification.Type
import com.vaadin.ui.TextField

@SuppressWarnings("serial")
public class ProductForm extends FormLayout {

	private TextField code = new TextField("Product code:")
	private TextField name = new TextField("Product name:")
	private TextField price = new TextField("Price (USD):")
	private FieldGroup fieldGroup
	private ProductService productService = Grails.get(ProductService)
	private Product product = productService.getProductByCode(1)
	
	public ProductForm() {
		setSizeUndefined()
		setMargin(true)

		fieldGroup = new BeanFieldGroup<Product>(Product.class)
		fieldGroup.with {
			setItemDataSource(new BeanItem<Product>(product))
			bindMemberFields(this)
		}

		def constraints = Product.constraints
		constraints.with {
			this.code.addValidator(new IntegerRangeValidator("Code out of range", code.min, code.max))
			this.name.addValidator(new StringLengthValidator("Bad name", name.minSize, name.maxSize, name.blank))
			this.price.addValidator(new DoubleRangeValidator("Price out of range", price.min, price.max))
		}

		addComponent(code)
		addComponent(name)
		addComponent(price)
		addComponent(createOkButton())
	}

	private Button createOkButton() {
		Button okButton = new Button("OK")
		okButton.addClickListener({
			try {
				fieldGroup.commit()
				Notification.show("Product committed: ${product.name}")
			} catch (CommitException e) {
				Notification.show(e.getMessage(), Type.ERROR_MESSAGE)
			}
		} as ClickListener)
		return okButton
	}

}
