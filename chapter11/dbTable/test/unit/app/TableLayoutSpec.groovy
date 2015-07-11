package app

import spock.lang.Specification
import spock.lang.Shared

import com.vaadin.ui.Table

class TableLayoutSpec extends Specification {

	UserService userService = Mock()
	@Shared tableLayout = new TableLayout()

	void setup() {
		tableLayout.userService = userService
	}

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
		given:"defining a correct mock return value"
			List<User> fakeUsers = []
			fakeUsers << new User(name: "Wayne Gretzky")
			fakeUsers << new User(name: "Jaromir Jagr")
			fakeUsers << new User(name: "Sidney Crosby")
			userService.getAll() >> fakeUsers

		when:"TableLayout init"
			tableLayout.init()

		then:"I can set the table contents"
			tableLayout.table.itemIds.size() == 3

		and:"all contents are Users"
			tableLayout.table.itemIds.each { it instanceof User }			
	}

}