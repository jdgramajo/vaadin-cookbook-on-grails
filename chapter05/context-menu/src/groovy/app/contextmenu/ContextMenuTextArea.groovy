package app.contextmenu

import java.text.SimpleDateFormat
import java.util.Date

import org.vaadin.peter.contextmenu.ContextMenu
import org.vaadin.peter.contextmenu.ContextMenu.ContextMenuItem
import org.vaadin.peter.contextmenu.ContextMenu.ContextMenuItemClickListener

import com.vaadin.ui.TextArea

@SuppressWarnings("serial")
public class ContextMenuTextArea extends TextArea {

	private ContextMenu menu = new ContextMenu()
	private ContextMenuItem insertItem = menu.addItem("Insert")
	private ContextMenuItem cleanItem = menu.addItem("Clean")
	private ContextMenuItem dateItem = insertItem.addItem("Date")
	private ContextMenuItem nameItem = insertItem.addItem("Name")	
	private static final String NAME = "Vaadin"

	ContextMenuTextArea() {
		addExtension(menu)
		addListeners()
	}
	
	private void addListeners(){
		
		dateItem.addItemClickListener({
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd")	
			insertText(format.format(new Date()))				
		} as ContextMenuItemClickListener)
		
		nameItem.addItemClickListener({
			insertText(NAME)
		} as ContextMenuItemClickListener)		

		cleanItem.addItemClickListener({
			setValue("")
			focus()
		} as ContextMenuItemClickListener)
	}
	
	private void insertText(String text) {
		int position = getCursorPosition()
		String value = getValue()
		setValue(value.substring(0, position) + text + value.substring(position))
		focus()
	}
	
	

}
