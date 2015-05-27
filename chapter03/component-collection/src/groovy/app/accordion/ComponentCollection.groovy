package app.accordion

import com.vaadin.server.Resource
import com.vaadin.server.ThemeResource
import com.vaadin.ui.Accordion
import com.vaadin.ui.Component
import com.vaadin.ui.CssLayout
import com.vaadin.ui.Image
import com.vaadin.ui.DragAndDropWrapper
import com.vaadin.ui.DragAndDropWrapper.DragStartMode

@SuppressWarnings("serial")
public class ComponentCollection extends Accordion {	
	private sizes = ["16", "32", "64"]
	private icons = ["cancel.png", "calendar.png", "document.png", "email.png", "globe.png",
		"help.png", "note.png", "ok.png", "trash.png", "user.png"]

	public ComponentCollection() {
		setSizeFull()
		createTabs(sizes)		
	}

	private void createTabs(sizes) {
		sizes.each { size ->
			CssLayout layout = new CssLayout(){
				@Override
				protected String getCss(Component c) {					
					return "display: inline-block"
				}
			}			
			layout.setCaption("Icons " + size + "x" + size)
			addComponent(layout)
			icons.each { icon ->
				Resource imageResource = new ThemeResource("../runo/icons/" + size + "/" + icon)
				Image image = new Image(null, imageResource)

				DragAndDropWrapper imageWrap = new DragAndDropWrapper()
				imageWrap.setDragStartMode(DragStartMode.COMPONENT)
				imageWrap.setSizeUndefined()

				layout.addComponent(image)
			}			
		}
	}

}
