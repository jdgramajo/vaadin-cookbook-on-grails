package app.shortcut

import com.vaadin.event.Action
import com.vaadin.event.Action.Handler
import com.vaadin.event.ShortcutAction
import com.vaadin.event.ShortcutAction.KeyCode
import com.vaadin.event.ShortcutAction.ModifierKey
import com.vaadin.ui.Button
import com.vaadin.ui.Button.ClickListener
import com.vaadin.ui.Label
import com.vaadin.ui.Notification
import com.vaadin.ui.Panel
import com.vaadin.ui.TextArea
import com.vaadin.ui.UI
import com.vaadin.ui.VerticalLayout
import com.vaadin.ui.Window

@SuppressWarnings("serial")
public class ShortcutPanel extends Panel implements Handler {

	private Window helpWindow = createHelpWindow()
	private final static Action ACTION_HELP = new ShortcutAction("Help", KeyCode.F1, null)

	ShortcutPanel() {
		VerticalLayout layout = new VerticalLayout()
		layout.setMargin(true)		
		setContent(layout)
		
		TextArea textArea = new TextArea()
		Button saveButton = new Button("Save")
		saveButton.with {
			setClickShortcut(KeyCode.S, ModifierKey.CTRL)
			addClickListener({ Notification.show("Saved") } as ClickListener)
		}

		setSizeUndefined()
		layout.with { 
			addComponent(textArea)
			addComponent(saveButton)
		}
		addActionHandler(this)
	}

	@Override
	public Action[] getActions(Object target, Object sender) {
		return [ ACTION_HELP ] as Action[]
	}

	@Override
	public void handleAction(Action action, Object sender, Object target) {
		if (action == ACTION_HELP) {
			UI.getCurrent().addWindow(helpWindow)			
		}		
	}

	private Window createHelpWindow() {
		Window window = new Window("Help")
		VerticalLayout layout = new VerticalLayout()
		layout.with {		
			addComponent(new Label("Save: Ctrl+S"))
			addComponent(new Label("Help: F1"))
		}
		window.with {
			setContent(layout)
			center()
		}
		return window
	}

}
