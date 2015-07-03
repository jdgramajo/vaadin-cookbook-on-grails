package app

import com.vaadin.ui.HorizontalLayout
import com.vaadin.ui.Label

class SystemStatusLayout extends HorizontalLayout {

    Label lblSystemStatus
    SystemStatusService systemStatusService = new SystemStatusService()

    void init() {
        String value = systemStatusService.getValue()
        lblSystemStatus = new Label(value)
        addComponent(lblSystemStatus)
    }

}