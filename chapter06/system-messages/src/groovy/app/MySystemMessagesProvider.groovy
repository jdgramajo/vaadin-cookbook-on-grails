package app

import com.vaadin.server.CustomizedSystemMessages
import com.vaadin.server.SystemMessages
import com.vaadin.server.SystemMessagesInfo
import com.vaadin.server.SystemMessagesProvider

public class MySystemMessagesProvider implements SystemMessagesProvider {

    @Override
    public SystemMessages getSystemMessages(SystemMessagesInfo systemMessagesInfo) {
        CustomizedSystemMessages messages = new CustomizedSystemMessages()
        messages.with {
            setCommunicationErrorCaption("The server is down")
            setInternalErrorCaption("Internal error")
            setSessionExpiredCaption("Session has expired")
            setOutOfSyncCaption("Out of synchronization")
            setCookiesDisabledCaption("Cookies disabled")
            setAuthenticationErrorCaption("Authentication error")
        }
        return messages
    }
}
