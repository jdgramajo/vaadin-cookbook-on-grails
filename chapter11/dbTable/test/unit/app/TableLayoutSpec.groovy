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
			table.visibleColumns[0] == "name"
	}

}