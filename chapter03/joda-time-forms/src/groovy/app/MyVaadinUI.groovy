package app

import com.vaadin.data.fieldgroup.FieldGroup
import com.vaadin.data.fieldgroup.FieldGroupFieldFactory
import com.vaadin.data.util.BeanItem
import com.vaadin.server.VaadinRequest
import com.vaadin.ui.*
import org.joda.time.DateTime

@SuppressWarnings("serial")
public class MyVaadinUI extends UI {

    @Override
    protected void init(VaadinRequest request) {

        Post post = new Post(text: "I really need that stuff.", created: DateTime.now())
        
        BeanItem<Post> postBean = new BeanItem<Post>(post)
        FieldGroup fieldGroup = new FieldGroup(postBean)
        FieldGroupFieldFactory fieldFactory = new JodaFieldFactory()
        fieldGroup.setFieldFactory(fieldFactory)
        FormLayout formLayout = new FormLayout()

        formLayout.addComponent(fieldGroup.buildAndBind("text"))
        formLayout.addComponent(fieldGroup.buildAndBind("created"))

        VerticalLayout layout = new VerticalLayout()
        layout.setMargin(true)
        setContent(layout)
        layout.addComponent(formLayout)
    }

}
