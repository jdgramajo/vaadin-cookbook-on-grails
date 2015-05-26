package app.absolute

import com.vaadin.server.Resource
import com.vaadin.server.ThemeResource
import com.vaadin.ui.AbsoluteLayout
import com.vaadin.ui.Image

@SuppressWarnings("serial")
public class CircleLayoutDemo extends AbsoluteLayout {	
	private icons = ["cancel.png", "calendar.png", "document.png", "email.png", "globe.png", "help.png",
			"note.png", "ok.png", "trash.png", "user.png"]

	public CircleLayoutDemo() {		
		double step = 360.0 / icons.size()
		int radius = 70
		int i = 0
		int xMargin = 20
		int yMargin = 20

		icons.each { icon ->
			Resource imageResource = new ThemeResource("../runo/icons/32/" + icon)
			Image image = new Image(null, imageResource)
			double degrees = Math.toRadians(i * step)
			int x = (int) (Math.cos(degrees) * radius) + xMargin + radius
			int y = (int) (Math.sin(degrees) * radius) + yMargin + radius
			addComponent(image, "left: " + y + "px; top: " + x + "px;")
			i++
		}
		setWidth((xMargin * 4) + (radius * 2), Unit.PIXELS)
		setHeight((yMargin * 4) + (radius * 2), Unit.PIXELS)
	}
}
