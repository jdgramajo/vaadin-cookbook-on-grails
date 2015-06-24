package app

//import com.jensjansson.pagedtable.ControlsLayout
import com.jensjansson.pagedtable.PagedTable
import com.vaadin.data.util.BeanContainer
import com.vaadin.data.util.BeanItem
import com.vaadin.server.VaadinRequest
import com.vaadin.ui.UI
import com.vaadin.ui.VerticalLayout

@SuppressWarnings("serial")
class MyVaadinUI extends UI {

    @Override
    protected void init(VaadinRequest request) {
        final VerticalLayout layout = new VerticalLayout()
        layout.setMargin(true)
        setContent(layout)

        PagedTable table = new PagedTable("Paged lazy loaded table")
        table.setWidth("500px")

        LazyLoadedContainer container = new LazyLoadedContainer(User.class)
        table.setContainerDataSource(container)
        layout.addComponent(table)

        def controls = table.createControls()
        //controls.setWidth("500px")
        layout.addComponent(controls)
    }
}

class LazyLoadedContainer extends BeanContainer {

    UserService userService = new UserService()
    boolean dirty
    int lastSize

    public LazyLoadedContainer(Class type) {
        super(type)
    }

    @Override
    public int size() {
        if (lastSize == 0 || dirty) {
            lastSize = userService.size()
        }
        lastSize
    }

    @Override
    public BeanItem getItem(Object itemId) {
        new BeanItem(itemId as User)
    }

    @Override
    public List getItemIds(int startIndex, int numberOfIds) {
        int endIndex = startIndex + numberOfIds
        println "startIndex: ${startIndex}, endIndex: ${endIndex}"
        userService.list(startIndex, endIndex)
    }

}

