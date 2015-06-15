package app.views

import com.vaadin.ui.VerticalLayout
import com.vaadin.navigator.View
import com.vaadin.navigator.ViewChangeListener
import com.vaadin.ui.Label
import com.vaadin.grails.Grails

import app.TagService
import app.forms.TagForm

class TagsView extends VerticalLayout implements View {

	static final String VIEW_NAME = "tags"

	TagForm tagForm = new TagForm()
	Label tagsLabel = new Label("| ")

	TagService tagService = Grails.get(TagService)

	@Override
	void enter(ViewChangeListener.ViewChangeEvent viewChangeListener) {

		margin = true

		addComponent(tagForm)
		tagForm.tagsLabel = this.tagsLabel
		addComponent(tagsLabel)

		def tags = tagService.getAllTags()
		tags.each { tag ->
			tagsLabel.value += "${tag.name} | "
		}
	}

}