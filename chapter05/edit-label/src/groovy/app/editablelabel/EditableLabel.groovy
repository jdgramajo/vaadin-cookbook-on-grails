package app.editablelabel

import com.vaadin.event.FieldEvents.BlurListener
import com.vaadin.event.LayoutEvents.LayoutClickListener
import com.vaadin.ui.Label
import com.vaadin.ui.TextField
import com.vaadin.ui.VerticalLayout

@SuppressWarnings("serial")
public class EditableLabel extends VerticalLayout {
	private Label label = new Label()
	private TextField textField = new TextField()

	EditableLabel(String value) {
		label.setValue(value)
		label.setSizeUndefined()
		textField.setPropertyDataSource(label)
		setMargin(true)
		addComponent(label)
		addListeners()
	}

	private void addListeners() {	

		addLayoutClickListener({ event ->
			if (event.isDoubleClick() && event.getClickedComponent() instanceof Label) {
				removeComponent(label)
				addComponent(textField)
				textField.focus()
			}				
		} as LayoutClickListener)

		textField.addBlurListener({ event ->
			removeComponent(textField)
			addComponent(label)
		} as BlurListener)

	}

}
