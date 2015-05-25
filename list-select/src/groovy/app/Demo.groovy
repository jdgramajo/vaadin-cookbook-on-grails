package app

import app.list.Contact
import app.list.ContactViewer
import com.vaadin.data.util.BeanItemContainer
import com.vaadin.server.VaadinRequest
import com.vaadin.ui.UI

/**
 * Main UI class
 */
@SuppressWarnings("serial")
public class Demo extends UI {

    @Override
    protected void init(VaadinRequest request) {
        ContactViewer contactViewer = new ContactViewer(createContactContainer())
        setContent(contactViewer)
    }

    private BeanItemContainer<Contact> createContactContainer() {
        BeanItemContainer<Contact> contacts = new BeanItemContainer<>(Contact.class)
        contacts.with {
            addItem(new Contact(firstName: "Tom", lastName: "Smith", email: "tom.smith@gmail.com"))
            addItem(new Contact(firstName: "Bob", lastName: "Taylor", email: "bob.taylor@yahoo.com"))
            addItem(new Contact(firstName: "Jane", lastName: "White", email: "jane.white@outlook.com"))
            addItem(new Contact(firstName: "Suzan", lastName: "Lee", email: "suzan.lee@aol.com"))
        }
        return contacts
    }

}