package app

import com.vaadin.server.VaadinRequest
import com.vaadin.ui.UI

@SuppressWarnings("serial")
public class MyVaadinUI extends UI {

    PageLayout pageLayout

    @Override
    protected void init(VaadinRequest request) {
        pageLayout = new PageLayout()
        setContent(pageLayout)
    }

    public static MyVaadinUI getCurrent() {
        return UI.current as MyVaadinUI
    }
}
