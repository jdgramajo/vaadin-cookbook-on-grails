package app

import spock.lang.Specification

class SystemStatusLayoutSpec extends Specification {

	void "mock returns Online status"() {
		given:
			def service = Mock(SystemStatusService)
			def layout = new SystemStatusLayout()

		and:
			layout.systemStatusService = service
			
		when:
			layout.init()

		then:
			service.value >> "Online"
			layout.lblSystemStatus.value.equals("Online")
	}

}