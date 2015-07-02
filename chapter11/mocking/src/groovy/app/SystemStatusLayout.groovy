package app

import com.vaadin.ui.HorizontalLayout
import com.vaadin.ui.Label

class SystemStatusLayout extends HorizontalLayout {

    Label lblSystemStatus
    SystemStatusService statusService = new SystemStatusService()

    public SystemStatusLayout() {
        String value = statusService.getValue()
        lblSystemStatus = new Label(value)
        addComponent(lblSystemStatus)
    }

}