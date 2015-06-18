package app

import com.google.gwt.core.ext.typeinfo.JClassType
import com.vaadin.server.widgetsetutils.ConnectorBundleLoaderFactory
import com.vaadin.shared.ui.Connect
import com.vaadin.ui.*

public class OptimizedConnectorBundleLoaderFactory extends ConnectorBundleLoaderFactory {
    
    def eagerConnectors = []

    {
        com.vaadin.client.ui.with {
            eagerConnectors << orderedlayout.VerticalLayoutConnector.class.name
            eagerConnectors << button.ButtonConnector.class.name
            eagerConnectors << ui.UIConnector.class.name
        }
    }

    @Override
    protected Connect.LoadStyle getLoadStyle(JClassType connectorType) {
        if (eagerConnectors.contains(connectorType.getQualifiedBinaryName())) {
            return Connect.LoadStyle.EAGER
        } else {
            // Loads all other connectors immediately after the initial view has
            // been rendered
            return Connect.LoadStyle.DEFERRED
        }
    }
}