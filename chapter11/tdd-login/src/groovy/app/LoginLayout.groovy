package app

//import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion
import com.vaadin.ui.Button
import com.vaadin.ui.TextField
import com.vaadin.ui.UI
import com.vaadin.ui.VerticalLayout

class LoginLayout extends VerticalLayout {

    Button btnLogin = new Button("Login")
    TextField txtUsername = new TextField("Username:")
    TextField txtPassword = new TextField("Password:")

    UserService userService = new UserService()

    LoginLayout() {
        this.init()
    }

    void init() {
        setMargin(true)
        addComponent(txtUsername)
        addComponent(txtPassword)
        addComponent(btnLogin)
        btnLogin.addClickListener({
                String username = txtUsername.value
                String password = txtPassword.value
                User user = userService.loginUser(username, password)
                UI.current.data = user
        } as Button.ClickListener)
    }

}
