package app.slider

import com.vaadin.data.Property
import com.vaadin.data.Property.ValueChangeListener
import com.vaadin.server.ThemeResource
import com.vaadin.shared.ui.slider.SliderOrientation
import com.vaadin.ui.HorizontalLayout
import com.vaadin.ui.Image
import com.vaadin.ui.Panel
import com.vaadin.ui.Slider

@SuppressWarnings("serial")
public class Zooming extends HorizontalLayout {	
	private static final int DEFAULT_ZOOM = 60

	public Zooming() {	
		setSpacing(true)
		setMargin(true)
		
		Panel zoomPanel = new Panel()	
		
		final Image image = new Image("", new ThemeResource("../runo/icons/64/globe.png"))
		image.setWidth(DEFAULT_ZOOM, Unit.PERCENTAGE)
		
		zoomPanel.with {		
			setContent(image)		
			setWidth(300, Unit.PIXELS)
			setHeight(300, Unit.PIXELS)
		}
		
		final Slider slider = new Slider()
		slider.with {
			setSizeFull()
			setImmediate(true)
			setOrientation(SliderOrientation.VERTICAL)
			setValue(DEFAULT_ZOOM as double)
			addValueChangeListener({ event ->
				double value = slider.getValue()
				image.setWidth(value as float, Unit.PERCENTAGE)				
			} as ValueChangeListener)
		}
	
		addComponent(zoomPanel)
		addComponent(slider)
	}

}
