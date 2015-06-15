package app.forms

import com.vaadin.data.fieldgroup.BeanFieldGroup
import com.vaadin.data.fieldgroup.FieldGroup
import com.vaadin.data.fieldgroup.PropertyId
import com.vaadin.data.util.BeanItem
import com.vaadin.ui.FormLayout
import com.vaadin.ui.TextField
import com.vaadin.ui.Button
import com.vaadin.ui.Label
import com.vaadin.grails.Grails

import app.Tag
import app.TagService

class TagForm extends FormLayout {
	
	private TextField name = new TextField("Tag name:")
	
	Button saveButton = new Button("Save")

	TagService tagService = Grails.get(TagService)
	Label tagsLabel

	TagForm() {
		setSizeUndefined()
		margin = true

		FieldGroup fieldGroup = new BeanFieldGroup<Tag>(Tag.class)
		fieldGroup.with {
			setItemDataSource(new Tag(name: ""))
			bindMemberFields(this)
		}
		saveButton.addClickListener({ event ->
			fieldGroup.commit()
			BeanItem<Tag> tagBean = fieldGroup.getItemDataSource() as BeanItem<Tag>
			if(tagService.saveTag(tagBean.bean) && tagsLabel) {
				tagsLabel.value += "${tagBean.bean.name} | "
				fieldGroup.setItemDataSource(new Tag(name: ""))
			} else {
				println "Error saving ${tagBean.bean.name}"
			}
		} as Button.ClickListener)

		addComponent(name)
		addComponent(saveButton)
	}

}