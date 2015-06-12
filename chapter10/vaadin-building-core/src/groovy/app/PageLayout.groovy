package app

import com.vaadin.ui.VerticalLayout

public class PageLayout extends VerticalLayout {

    HeaderLayout headerLayout
    BodyLayout bodyLayout
    FooterLayout footerLayout

    public PageLayout() {
        margin = true

        headerLayout = new HeaderLayout()
        addComponent(headerLayout)

        bodyLayout = new BodyLayout()
        addComponent(bodyLayout)

        footerLayout = new FooterLayout()
        addComponent(footerLayout)
    }

}
