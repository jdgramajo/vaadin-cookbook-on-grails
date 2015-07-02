package app

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(SystemStatusService)
class SystemStatusServiceSpec extends Specification {

    void "value is Offline always"() {
    	expect:
    		service.value.equals("Offline")
    }
}
