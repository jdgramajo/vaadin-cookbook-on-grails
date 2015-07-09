package app

import spock.lang.Specification

class MyVaadinUISpec extends Specification {

    void "UI contents"() {
        when: "UI instance is initialized"
            def ui = new MyVaadinUI()
            ui.init(null)

        then: "UI has content of type TableLayout"
            ui.content instanceof TableLayout
    }

}
