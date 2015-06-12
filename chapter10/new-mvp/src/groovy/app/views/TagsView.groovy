package app.views

import com.vaadin.ui.VerticalLayout
import com.vaadin.navigator.View
import com.vaadin.navigator.ViewChangeListener
import com.vaadin.ui.Label
import com.vaadin.grails.Grails

import app.TagService

class TagsView extends VerticalLayout implements View {

	static final String VIEW_NAME = "tags"

	TagService tagService = Grails.get(TagService)

	@Override
	void enter(ViewChangeListener.ViewChangeEvent viewChangeListener) {
		def tags = tagService.getAllTags()
		tags.each { tag ->
			addComponent(new Label(tag.name))
		}
	}

}