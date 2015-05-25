package app

import app.tabs.TabsURL
import com.vaadin.server.Page.UriFragmentChangedListener
import com.vaadin.server.VaadinRequest
import com.vaadin.ui.UI

@SuppressWarnings("serial")
public class Demo extends UI {
    @Override
    public void init(VaadinRequest request) {
        final TabsURL tabsURL = new TabsURL()
        setContent(tabsURL)    
        tabsURL.selectTab()    
                
        getPage().addUriFragmentChangedListener({ event ->
                tabsURL.selectTab()                
        } as UriFragmentChangedListener)
    }
}
