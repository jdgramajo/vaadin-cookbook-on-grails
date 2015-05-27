package app

import com.vaadin.annotations.Theme
import com.vaadin.server.VaadinRequest
import com.vaadin.ui.Table
import com.vaadin.ui.UI

@SuppressWarnings("serial")
@Theme("mytheme")
public class Demo extends UI {
    @Override
    protected void init(VaadinRequest request) {
        
        Table table = new Table()
        table.with {
            setStyleName("myTable")
            addContainerProperty("First name", String.class, null)
            addContainerProperty("Second name", String.class, null)
            addItem(["Tom", "Smith"] as Object[], 0)
            addItem(["Bob", "Taylor"] as Object[], 1)
            addItem(["Jane", "White"] as Object[], 2)
            addItem(["Suzan", "Lee"] as Object[], 3)     
            setPageLength(size())
        }
        setContent(table)
    }
}