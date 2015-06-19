package app

import com.vaadin.data.Container
import com.vaadin.data.Item
import com.vaadin.data.Property
import com.vaadin.data.util.*
import com.vaadin.server.VaadinRequest
import com.vaadin.ui.*
import com.vaadin.ui.Button.ClickListener

import java.lang.ref.SoftReference
import java.util.ArrayList
import java.util.Collection
import java.util.Collections
import java.util.List

@SuppressWarnings("serial")
class MyVaadinUI extends UI {

    @Override
    protected void init(VaadinRequest request) {
        final VerticalLayout layout = new VerticalLayout()
        layout.setMargin(true)
        setContent(layout)

        Table table = new Table("Lazy loaded table")
        table.setWidth("200px")

        //table.setCacheRate(1)

        LazyLoadedContainer container = new LazyLoadedContainer(User.class)
        table.with {
            setContainerDataSource(container)
            visibleColumns = ["name"] as String[]
        }
        layout.addComponent(table)
    }

}

class LazyLoadedContainer extends BeanContainer {

    private UserService userService = new UserService()

    public LazyLoadedContainer(Class type) {
        super(type)
    }

    @Override
    public int size() {
        return userService.size()
    }

    @Override
    public BeanItem getItem(Object itemId) {
        new BeanItem(itemId as User)
    }

    @Override
    public List getItemIds(int startIndex, int numberOfIds) {
        int endIndex = startIndex + numberOfIds
        println "startIndex: ${startIndex}, endIndex: ${endIndex}"
        def list = userService.list(startIndex, endIndex)
        return list
    }
}
