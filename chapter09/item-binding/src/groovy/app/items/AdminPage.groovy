package app.items

import com.vaadin.data.fieldgroup.FieldGroup
import com.vaadin.data.fieldgroup.FieldGroup.CommitException
import com.vaadin.data.util.ObjectProperty
import com.vaadin.data.util.PropertysetItem
import com.vaadin.ui.Button
import com.vaadin.ui.Button.ClickEvent
import com.vaadin.ui.Button.ClickListener
import com.vaadin.ui.FormLayout
import com.vaadin.ui.Layout
import com.vaadin.ui.Notification
import com.vaadin.ui.Notification.Type
import com.vaadin.ui.TabSheet
import com.vaadin.ui.VerticalLayout

@SuppressWarnings("serial")
class AdminPage extends TabSheet {
	
	private PropertysetItem item = new PropertysetItem()	

	AdminPage() {
		item.with { 
			addItemProperty(CustomTextArea.WIDTH, new ObjectProperty<Integer>(50))
			addItemProperty(CustomTextArea.HEIGHT, new ObjectProperty<Integer>(40))
		}
		addTab(createPreview())
		addTab(createAdminEditor())		
	}

	private Layout createPreview() {
		VerticalLayout layout = new VerticalLayout()
		layout.with {
			setCaption("Preview")
			setSpacing(true)
			setMargin(true)		
			setSizeFull()
		}
		CustomTextArea textArea = new CustomTextArea(item)		
		layout.addComponent(textArea)
		return layout
	}

	private Layout createAdminEditor() {
		FormLayout layout = new FormLayout()
		layout.with {
			setCaption("Admin page")		
			setSpacing(true)
			setMargin(true)		
		}
		FieldGroup group = new FieldGroup()
		group.itemDataSource = item
		group.unboundPropertyIds.each { propertyId ->
			layout.addComponent(group.buildAndBind(propertyId))
		}
		layout.addComponent(createCommitButton(group))
		return layout
	}

	private Button createCommitButton(final FieldGroup group) {
		Button button = new Button("Commit")
		button.addClickListener({ event ->
			try {
				group.commit()
			} catch (CommitException e) {
				Notification.show(e.getMessage(), Type.ERROR_MESSAGE)
			}
		} as ClickListener)
		return button
	}

}
