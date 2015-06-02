package app

import com.vaadin.data.Item
import com.vaadin.data.Property
import com.vaadin.server.VaadinRequest
import com.vaadin.ui.*
import com.vaadin.ui.Button.ClickEvent

import java.util.Collection

/**
 * The Application's "main" class
 */
@SuppressWarnings("serial")
public class MyVaadinUI extends UI {

    public class PeopleTableListener implements Property.ValueChangeListener {

        private final Table table

        PeopleTableListener(Table table) {
            this.table = table
        }

        @Override
        void valueChange(Property.ValueChangeEvent valueChangeEvent) {
            valueChangeEvent.getProperty().getValue()
            Object itemId = table.getValue()
            Item item = table.getItem(itemId)
            Collection<String> columns = item.getItemPropertyIds() as Collection<String>
            String column = columns.toArray()[0] as java.lang.String
            Property<?> itemProperty = item.getItemProperty(column)
            Object value = itemProperty.getValue()

            ConfirmWindow window = new ConfirmWindow("Question", "Do you want to fetch ${value}'s details?", "Yes", "No")
            window.setDecision(new Decision() {
                @Override
                public void yes(ClickEvent event) {
                    System.out.println("Yes")
                }

                @Override
                public void no(ClickEvent event) {
                    System.out.println("No")
                }
            })
        }
    }

    @Override
    protected void init(VaadinRequest request) {
        final VerticalLayout layout = new VerticalLayout()
        layout.setMargin(true)
        setContent(layout)

        Table table = new Table("This is my Table")

        table.with {
            addContainerProperty("First Name", String.class, null)
            addContainerProperty("Last Name", String.class, null)
            addContainerProperty("Credit", Integer.class, null)
    
            addItem(["John" , "Feleti" , 3000] as Object[], 1)
            addItem(["Jim" , "Gerades" , 10000] as Object[], 2)
            addItem(["Elias" , "Faid" , 800] as Object[], 3)
    
            setSelectable(true)
            setImmediate(true)
    
            addValueChangeListener(new PeopleTableListener(table))
        }

        layout.addComponent(table)
    }
}
