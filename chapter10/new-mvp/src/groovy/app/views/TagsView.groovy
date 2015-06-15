package app.views

import com.vaadin.data.util.BeanItemContainer
import com.vaadin.ui.VerticalLayout
import com.vaadin.navigator.View
import com.vaadin.navigator.ViewChangeListener
import com.vaadin.ui.ListSelect
import com.vaadin.grails.Grails

import app.Tag
import app.TagService
import app.forms.TagForm

class TagsView extends VerticalLayout implements View {

	static final String VIEW_NAME = "tags"

	TagForm tagForm = new TagForm()
	ListSelect tagsList = new ListSelect()

	TagService tagService = Grails.get(TagService)

	@Override
	void enter(ViewChangeListener.ViewChangeEvent viewChangeListener) {

		margin = true
		
		tagForm.tagsList = this.tagsList
		tagsList.setItemCaptionPropertyId("name")
		tagsList.setContainerDataSource(new BeanItemContainer<Tag>(Tag.class, tagService.getAllTags()))

		addComponent(tagForm)
		addComponent(tagsList)
	}

}