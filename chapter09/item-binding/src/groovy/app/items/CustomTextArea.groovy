package app.items

import com.vaadin.data.Item
import com.vaadin.data.util.ObjectProperty
import com.vaadin.ui.TextArea

@SuppressWarnings("serial")
class CustomTextArea extends TextArea {

	static final String WIDTH = "width"
	static final String HEIGHT = "height"

	CustomTextArea(Item itemDataSource) {		
		setItemDataSource(itemDataSource)
	}

	public void setItemDataSource(Item itemDataSource) {
		ObjectProperty<?> widthProperty = itemDataSource.getItemProperty(WIDTH) as ObjectProperty<?>
		widthProperty.addValueChangeListener({ event ->
			Integer width = event.property.value as Integer
			setWidth(width, Unit.PIXELS)				
		} as ValueChangeListener)

		ObjectProperty<?> heightProperty = (ObjectProperty<?>) itemDataSource.getItemProperty(HEIGHT)
		heightProperty.addValueChangeListener({ event ->
			Integer height = event.property.value as Integer
			setHeight(height, Unit.PIXELS)				
		} as ValueChangeListener)

	}

}
