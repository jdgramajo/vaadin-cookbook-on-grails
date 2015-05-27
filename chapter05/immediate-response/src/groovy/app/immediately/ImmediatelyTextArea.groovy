package app.immediately

import com.vaadin.event.FieldEvents.TextChangeListener
import com.vaadin.ui.AbstractTextField.TextChangeEventMode
import com.vaadin.ui.Button
import com.vaadin.ui.Button.ClickListener
import com.vaadin.ui.TextArea
import com.vaadin.ui.VerticalLayout

@SuppressWarnings("serial")
public class ImmediatelyTextArea extends VerticalLayout{
	
	public ImmediatelyTextArea() {
		TextArea textArea = new TextArea()
		textArea.with {
			setImmediate(true)
			setTextChangeEventMode(TextChangeEventMode.EAGER)
		}
		
		final Button saveButton = new Button("Saved")
		saveButton.setEnabled(false)
		
		textArea.addTextChangeListener({ event ->
			saveButton.with {
				setCaption("Save")
				setEnabled(true)
			}
		} as TextChangeListener)		
		
		saveButton.addClickListener({ event ->
			saveButton.with {
				setCaption("Saved")
				setEnabled(false)
			}
		} as ClickListener)		
		
		addComponent(textArea)
		addComponent(saveButton)		
	}

}
