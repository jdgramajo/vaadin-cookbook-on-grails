package app

import spock.lang.Specification
import spock.lang.Shared

import com.vaadin.ui.Table

class TableLayoutSpec extends Specification {

	@Shared tableLayout = new TableLayout()

	void "layout has a table"() {
		when:"TableLayout init"
			tableLayout.init()

		then:"layout has a table"
			tableLayout.getComponent(0) instanceof Table
	}

	void "only name column is visible"() {
		when:"TableLayout init"
			tableLayout.init()

		then:"only name column is visible"
			tableLayout.table.visibleColumns[0].equals("name")
	}

	void "able to set table contents"() {
		given:
			UserService userService = Mock()
			List<User> fakeUsers = []
			fakeUsers << new User(name: "Wayne Gretzky")
			fakeUsers << new User(name: "Jaromir Jagr")
			fakeUsers << new User(name: "Sidney Crosby")

		when:"userService mocked is set and init is called"
			tableLayout.userService = userService
			tableLayout.init()

		then:"I can set the table contents"
			userService.findAll() >> fakeUsers
			tableLayout.table.itemIds.size() == 3

		and:"all contents are Users"
			tableLayout.table.itemIds.each { it instanceof User }			
	}

}