package app

import spock.lang.Specification

class MyVaadinUISpec extends Specification {

	void "MyVaadinUI has content instanceof SystemStatusLayout after init"() {
		given:
			def ui = new MyVaadinUI()
			ui.init(null)

		expect:
			ui.content instanceof SystemStatusLayout
	}

}