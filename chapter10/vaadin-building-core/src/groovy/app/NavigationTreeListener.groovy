package app

import com.vaadin.ui.Layout
import com.vaadin.event.ItemClickEvent

public class NavigationTreeListener implements ItemClickEvent.ItemClickListener {

    @Override
    public void itemClick(ItemClickEvent event) {

        Object value = event.itemId
        MyVaadinUI current = MyVaadinUI.current

        // we should do this in a bit more elegant way (not just use labels from the tree)
        // we can add e.g. object structure fetched from database to the tree
        ContentLayout contentLayout = current.pageLayout.bodyLayout.contentLayout
        contentLayout.removeAllComponents()

        Layout layout = null

        switch(value) {
            case NavigationTree.REPORTS_LABEL:
                layout = new ReportsLayout()
                break
            case NavigationTree.MY_REPORTS_LABEL:
                layout = new MyReportsLayout()
                break
            default:
                break
        }

        contentLayout.addComponent(layout)

    }

}
