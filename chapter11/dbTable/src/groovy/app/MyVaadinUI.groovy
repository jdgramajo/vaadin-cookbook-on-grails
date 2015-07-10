package app 

import com.vaadin.server.VaadinRequest 
import com.vaadin.ui.UI 

@SuppressWarnings("serial")
public class MyVaadinUI extends UI {

    @Override
    protected void init(VaadinRequest request) {
        TableLayout tableLayout = new TableLayout() 
        tableLayout.init()
        setContent(tableLayout) 
    }
}