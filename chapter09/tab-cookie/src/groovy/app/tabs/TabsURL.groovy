package app.tabs

import javax.servlet.http.Cookie

import com.vaadin.server.VaadinService
import com.vaadin.server.VaadinServletRequest
import com.vaadin.server.VaadinServletResponse
import com.vaadin.shared.ui.label.ContentMode
import com.vaadin.ui.Component
import com.vaadin.ui.Label
import com.vaadin.ui.TabSheet
import com.vaadin.ui.UI
import com.vaadin.ui.VerticalLayout

@SuppressWarnings("serial")
class TabsURL extends TabSheet{	
	private static final tabNames = ["Home", "Contractors", "Customers", "Employees", "Help"] as String[]
	private static final String LAST_TAB_COOKIE_NAME = "vaadin_last_tab_name"	
	private static final String PATH = "/tab-cookie"

	TabsURL() {
		createTabs(tabNames)
		addSelectedTabChangeListener({ event ->
			String selectedTabName = event.tabSheet.selectedTab.caption
			UI.current.page.uriFragment = selectedTabName
		} as TabSheet.SelectedTabChangeListener)
	}
	
	private void createTabs(tabNames){
		tabNames.each { tabName ->
			VerticalLayout layout = new VerticalLayout()
			layout.with {
				setCaption(tabName)
				addComponent(new Label("<h1>${tabName}</h1>", ContentMode.HTML))
				setHeight(400, Unit.PIXELS)
			}
			addComponent(layout)			
		}
	}
	
	public void selectTab(){		
		String fragment = UI.getCurrent().getPage().getUriFragment()		
		if (fragment == null) {
			fragment = getLastTabNameFromCookies()
		}
		Iterator<Component> iterator = getComponentIterator()
		while (iterator.hasNext()){
			Component tab = iterator.next()
			String name = tab.getCaption().toLowerCase()
			if (fragment.toLowerCase().equals(name)){				
				setSelectedTab(tab)
				setLastTabNameToCookies(name)
				return
			}
		}		
		setSelectedTab(0)
	}	

	private void setLastTabNameToCookies(String tabName) {
		Cookie lastTabCookie = new Cookie(LAST_TAB_COOKIE_NAME, tabName)
		lastTabCookie.setPath(PATH)
		VaadinServletResponse response = VaadinService.getCurrentResponse() as VaadinServletResponse
		response.addCookie(lastTabCookie)		
	}
	
	private String getLastTabNameFromCookies() {		
		Cookie[] cookies = (VaadinService.getCurrentRequest() as VaadinServletRequest).getCookies()
		for (Cookie cookie : cookies) {
			println "Cookie [name: ${cookie.name}, value: ${cookie.value}]"
			if (LAST_TAB_COOKIE_NAME.equals(cookie.getName())){
				return cookie.getValue()
			}
		}		
		return ""
	}

}
 