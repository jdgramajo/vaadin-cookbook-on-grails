package app.list

import com.google.gwt.safehtml.shared.SafeHtml
import com.google.gwt.safehtml.shared.SafeHtmlUtils
import com.vaadin.data.Property.ValueChangeListener
import com.vaadin.data.util.BeanItemContainer
import com.vaadin.shared.ui.label.ContentMode
import com.vaadin.ui.CustomComponent
import com.vaadin.ui.HorizontalSplitPanel
import com.vaadin.ui.Label
import com.vaadin.ui.ListSelect

@SuppressWarnings("serial")
public class ContactViewer extends CustomComponent {

	ContactViewer(BeanItemContainer<Contact> contacts) {

		final HorizontalSplitPanel panel = new HorizontalSplitPanel()
		setCompositionRoot(panel)

		final ListSelect contactSelect = new ListSelect("Contacts", contacts)
		contactSelect.with { 
			setSizeFull()
			setImmediate(true)
			setItemCaptionPropertyId("fullName")
			addValueChangeListener({ event ->
				Contact contact = (Contact) event.getProperty().getValue()
				panel.setSecondComponent(createInfoLabel(contact))
				focus()
			} as ValueChangeListener)
		}
		panel.setFirstComponent(contactSelect)
	}

	private Label createInfoLabel(Contact contact) {
		String info = ""
		if (contact) {
			info = "<ul><li><b>First name: </b> ${contact.firstName} </li>" +
				"<li><b>Last name: </b> ${contact.lastName} </li>" +
				"<li><b>Email: </b> ${contact.email} </li></ul>"			
		}
		SafeHtml safeHtml = SafeHtmlUtils.fromSafeConstant(info)
		return new Label(safeHtml.asString(), ContentMode.HTML)
	}

}
