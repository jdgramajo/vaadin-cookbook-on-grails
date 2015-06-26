package app

import com.vaadin.ui.HorizontalLayout
import com.vaadin.ui.Label

class SystemStatusLayout extends HorizontalLayout {

    Label lblSystemStatus

    public SystemStatusLayout() {
        String value = SystemStatusService.value
        lblSystemStatus = new Label(value)
        addComponent(lblSystemStatus)
    }

}