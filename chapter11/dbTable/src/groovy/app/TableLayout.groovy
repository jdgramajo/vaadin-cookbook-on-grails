package app 

import com.vaadin.data.util.BeanItemContainer 
import com.vaadin.ui.*

public class TableLayout extends VerticalLayout {

    Table table = new Table() 
    UserService userService = new UserService() 

    void init() {
        setMargin(true) 

        BeanItemContainer<User> container = getContainer() 
        table.setContainerDataSource(container)
        table.visibleColumns = ["name"] as String[]

        addComponent(table) 
    }

    // container that is connected to the database, we need to avoid the connection to database in unit tests
    BeanItemContainer<User> getContainer() {
        BeanItemContainer<User> container = new BeanItemContainer<User>(User.class) 

        userService.findAll().each { user ->
            container.addBean(user) 
        }

        return container 
    }

}
