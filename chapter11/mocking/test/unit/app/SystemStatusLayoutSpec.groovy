package app

import spock.lang.Specification

class SystemStatusLayoutSpec extends Specification {

	def service
	SystemStatusLayout layout

	void "mock returns Online status"() {
		given:
			service = GroovySpy(SystemStatusService)
			
		when:
			layout = new SystemStatusLayout()

		then:
			service.value >> "Online"
			layout.lblSystemStatus.value.equals("Online")
	}

}