package app

import com.vaadin.event.ShortcutAction
import com.vaadin.event.ShortcutListener
import com.vaadin.ui.*
import com.vaadin.ui.Button.ClickListener

public class ConfirmWindow extends Window {

    private Decision decision
    private Button btnYes = new Button()
    private Button btnNo = new Button()

    private VerticalLayout layout = new VerticalLayout()
    private HorizontalLayout buttonsLayout = new HorizontalLayout()

    public ConfirmWindow(String caption, String question, String yes, String no) {
        setCaption(caption)
        btnYes.setCaption(yes)
        btnYes.focus()
        btnNo.setCaption(no)
        setModal(true)
        center()

        buttonsLayout.with {
            addComponent(btnYes)
            setComponentAlignment(btnYes, Alignment.MIDDLE_CENTER)
            addComponent(btnNo)
            setComponentAlignment(btnNo, Alignment.MIDDLE_CENTER)
        }
        layout.with {
            addComponent(new Label(question))
            addComponent(buttonsLayout)
        }
        setContent(layout)

        layout.setMargin(true)
        buttonsLayout.setMargin(true)
        buttonsLayout.setWidth("100%")
        setWidth("300px")
        setHeight("160px")
        setResizable(false)

        btnYes.addClickListener({ event ->
            decision.yes(event)
            close()
        } as ClickListener)

        btnNo.addClickListener({ event ->
            decision.no(event)
            close()
        } as ClickListener)

        addShortcutListener(new ShortcutListener("Close", ShortcutAction.KeyCode.ESCAPE, null) {
            @Override
            public void handleAction(Object sender, Object target) {
                close()
            }
        })

        UI.getCurrent().addWindow(this)
    }

    void setDecision(Decision decision) {
        this.decision = decision
    }
}
