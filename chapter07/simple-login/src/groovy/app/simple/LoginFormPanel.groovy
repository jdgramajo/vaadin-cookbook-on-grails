package app.simple

import com.vaadin.event.ShortcutAction.KeyCode
import com.vaadin.ui.Button
import com.vaadin.ui.Button.ClickEvent
import com.vaadin.ui.Button.ClickListener
import com.vaadin.ui.FormLayout
import com.vaadin.ui.Notification
import com.vaadin.ui.Panel
import com.vaadin.ui.PasswordField
import com.vaadin.ui.TextField

@SuppressWarnings("serial")
public class LoginFormPanel extends Panel {

	public LoginFormPanel() {
		super("Login")
		setSizeUndefined()

		FormLayout layout = new FormLayout()
		layout.margin = true
		setContent(layout)

		final TextField nameField = new TextField("Name:")
		nameField.focus()
		layout.addComponent(nameField)

		PasswordField passwordField = new PasswordField("Password:")
		layout.addComponent(passwordField)

		Button okButton = new Button("OK")		
		okButton.with {
			setClickShortcut(KeyCode.ENTER, null)
			addClickListener({
				Notification.show("Login user: ${nameField.value}")
			} as ClickListener)
		}
		layout.addComponent(okButton)
	}

}
