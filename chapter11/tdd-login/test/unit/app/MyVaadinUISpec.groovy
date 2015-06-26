package app

import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
class MyVaadinUISpec extends Specification {

    void "UI initialization"() {
        when: "UI instance is initialized"
            def ui = new MyVaadinUI()
            ui.init(null)

        then: "UI has content of type LoginLayout"
            ui.content instanceof LoginLayout
    }

}
