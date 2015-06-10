package app.converter

import com.vaadin.data.util.converter.Converter.ConversionException
import com.vaadin.event.ShortcutAction.KeyCode
import com.vaadin.ui.Alignment
import com.vaadin.ui.Button
import com.vaadin.ui.Button.ClickEvent
import com.vaadin.ui.Button.ClickListener
import com.vaadin.ui.GridLayout
import com.vaadin.ui.Label
import com.vaadin.ui.Panel
import com.vaadin.ui.TextField

@SuppressWarnings("serial")
public class RomanNumeralPanel extends Panel {

	private static final String INTEGER_LABEL = "Integer value: "

	RomanNumeralPanel() {
		GridLayout layout = new GridLayout(2, 2)
		layout.with {
			setMargin(true)
			setSpacing(true)
		}

		setContent(layout)
		setSizeUndefined()
		
		final Label integerLabel = new Label(INTEGER_LABEL)
		final TextField romanField = new TextField("Roman numeral:")
		
		romanField.setConverter(new RomanToIntegerConverter())

		Button convertButton = new Button("convert")
		convertButton.with {
			setClickShortcut(KeyCode.ENTER, null)		
			addClickListener({ event ->
				String convertedValue
				try {
					Integer value = romanField.convertedValue
					convertedValue = value.toString()
				} catch (ConversionException e) {
					convertedValue = "n/a"
				}
				integerLabel.value = "${INTEGER_LABEL}${convertedValue}"
			} as ClickListener)
		}

		layout.with {
			addComponent(romanField)
			addComponent(convertButton)
			addComponent(integerLabel)
			
			setComponentAlignment(convertButton, Alignment.BOTTOM_LEFT)
		}
	}
}
