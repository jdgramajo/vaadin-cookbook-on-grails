package app

import com.vaadin.server.Page
import com.vaadin.server.VaadinRequest
import com.vaadin.server.WebBrowser
import com.vaadin.ui.Button
import com.vaadin.ui.Button.ClickEvent
import com.vaadin.ui.Label
import com.vaadin.ui.UI
import com.vaadin.ui.VerticalLayout
import com.vaadin.ui.themes.Runo

@SuppressWarnings("serial")
public class MyVaadinUI extends UI {

    @Override
    protected void init(VaadinRequest request) {
        final VerticalLayout layout = new VerticalLayout()
        layout.setMargin(true)
        setContent(layout)

        Label label = new Label("Browser details")
        label.addStyleName(Runo.LABEL_H2)
        layout.addComponent(label)

        Page page = Page.current

        int browserWindowHeight = page.browserWindowHeight
        layout.addComponent(new Label("browserWindowHeight: ${browserWindowHeight}"))

        int browserWindowWidth = page.browserWindowWidth
        layout.addComponent(new Label("browserWindowWidth: ${browserWindowWidth}"))

        WebBrowser webBrowser = page.webBrowser

        boolean secureConnection = webBrowser.secureConnection
        layout.addComponent(new Label("secureConnection: ${secureConnection}"))

        boolean touchDevice = webBrowser.touchDevice
        layout.addComponent(new Label("touchDevice: ${touchDevice}"))

        boolean ie = webBrowser.IE
        boolean firefox = webBrowser.firefox
        boolean safari = webBrowser.safari
        boolean chrome = webBrowser.chrome
        boolean chromeFrame = webBrowser.chromeFrame
        boolean opera = webBrowser.opera
        boolean macOSX = webBrowser.macOSX
        boolean linux = webBrowser.linux
        boolean windows = webBrowser.windows

        String address = webBrowser.address
        layout.addComponent(new Label("address: ${address}"))

        String browserApplication = webBrowser.browserApplication
        layout.addComponent(new Label("browserApplication: ${browserApplication}"))

        int browserMajorVersion = webBrowser.browserMajorVersion
        layout.addComponent(new Label("browserMajorVersion: ${browserMajorVersion}"))

        int browserMinorVersion = webBrowser.browserMinorVersion
        layout.addComponent(new Label("browserMinorVersion: ${browserMinorVersion}"))

        Date currentDate = webBrowser.currentDate
        layout.addComponent(new Label("currentDate: ${currentDate}"))

        int dstSavings = webBrowser.DSTSavings
        layout.addComponent(new Label("dstSavings: ${dstSavings}"))

        Locale locale = webBrowser.locale
        layout.addComponent(new Label("locale: ${locale}"))

        Integer rawTimezoneOffset = webBrowser.rawTimezoneOffset
        layout.addComponent(new Label("rawTimezoneOffset: ${rawTimezoneOffset}"))

        int screenHeight = webBrowser.screenHeight
        layout.addComponent(new Label("screenHeight: ${screenHeight}"))

        int screenWidth = webBrowser.screenWidth
        layout.addComponent(new Label("screenWidth: ${screenWidth}"))

        Integer timezoneOffset = webBrowser.timezoneOffset
        layout.addComponent(new Label("timezoneOffset: ${timezoneOffset}"))
    }

}
