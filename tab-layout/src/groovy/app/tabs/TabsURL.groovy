package app.tabs

import java.util.Iterator

import com.vaadin.shared.ui.label.ContentMode
import com.vaadin.ui.Component
import com.vaadin.ui.Label
import com.vaadin.ui.TabSheet
import com.vaadin.ui.TabSheet.SelectedTabChangeListener
import com.vaadin.ui.UI
import com.vaadin.ui.VerticalLayout

@SuppressWarnings("serial")
public class TabsURL extends TabSheet {	
	private static final tabNames = ["Home", "Contractors", "Customers", "Employees", "Help", "My new tab"]

	TabsURL() {
		createTabs(tabNames)		
		addSelectedTabChangeListener ({ event ->
				String selectedTabName = event.getTabSheet().getSelectedTab().getCaption()
				UI.getCurrent().getPage().setUriFragment(selectedTabName)	
		} as SelectedTabChangeListener)
	}
	
	private void createTabs(tabNames){
		tabNames.each { tabName ->
			VerticalLayout layout = new VerticalLayout()
			layout.with {
				setCaption(tabName)
				addComponent(new Label("<h1>" + tabName + "</h1>", ContentMode.HTML))
				setHeight(400, Unit.PIXELS)
			}
			addComponent(layout)			
		}
	}
	
	void selectTab(){		
		String fragment = UI.getCurrent().getPage().getUriFragment()		
		if (!fragment) {
			setSelectedTab(0)
			return
		}
		Iterator<Component> iterator = getComponentIterator()
		while (iterator.hasNext()){
			Component tab = iterator.next()
			String name = tab.getCaption().toLowerCase()
			if (fragment.toLowerCase().equals(name)){
				setSelectedTab(tab)
				return
			}
		}		
		setSelectedTab(0)
	}	


}
