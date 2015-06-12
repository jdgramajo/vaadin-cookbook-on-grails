package app

import com.vaadin.ui.HorizontalSplitPanel

public class BodyLayout extends HorizontalSplitPanel {

    NavigationTree navigationTree
    ContentLayout contentLayout

    public BodyLayout() {
        setHeight("500px")
        setSplitPosition(300, Unit.PIXELS)

        navigationTree = new NavigationTree()
        setFirstComponent(navigationTree)

        contentLayout = new ContentLayout()
        setSecondComponent(contentLayout)
    }

}
