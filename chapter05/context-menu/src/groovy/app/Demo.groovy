package app

import app.contextmenu.ContextMenuTextArea
import com.vaadin.server.VaadinRequest
import com.vaadin.ui.TextArea
import com.vaadin.ui.UI

@SuppressWarnings("serial")
public class Demo extends UI {
    
    @Override
    public void init(VaadinRequest request) {
        TextArea area = new ContextMenuTextArea()
        area.with {
            setWidth(200, Unit.PIXELS)
            setHeight(100, Unit.PIXELS)
        }
        setContent(area)
    }

}
