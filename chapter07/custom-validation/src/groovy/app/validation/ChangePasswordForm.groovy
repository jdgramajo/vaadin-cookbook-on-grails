package app.validation

import com.vaadin.data.Validator
import com.vaadin.ui.Button
import com.vaadin.ui.Button.ClickListener
import com.vaadin.ui.FormLayout
import com.vaadin.ui.Notification
import com.vaadin.ui.Notification.Type
import com.vaadin.ui.PasswordField

@SuppressWarnings("serial")
public class ChangePasswordForm extends FormLayout {

	private PasswordField newPasswordField = new PasswordField("New password:")
	private PasswordField confirmPasswordField = new PasswordField("Confirm new password:")
	private Button okButton = new Button("OK")
	private static final String CONFIRM_MESSAGE = "Passwords are the same"
	private static final String ERROR_MESSAGE = "Passwords must match"

	public ChangePasswordForm() {
		setSizeUndefined()
		margin = true		

		confirmPasswordField.addValidator({ value ->
			String password = value as String
			if (!password.equals(newPasswordField.getValue())) {
				throw new Validator.InvalidValueException(ERROR_MESSAGE)
			}
		} as Validator)

		okButton.addClickListener({
			try {
				confirmPasswordField.validate()		
				Notification.show(CONFIRM_MESSAGE)
			} catch (Exception e) {
				Notification.show(ERROR_MESSAGE, Type.ERROR_MESSAGE)
			}
		} as ClickListener)

		addComponent(newPasswordField)
		addComponent(confirmPasswordField)
		addComponent(okButton)
	}

}
