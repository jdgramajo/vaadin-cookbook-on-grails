package app

import com.vaadin.server.VaadinRequest
import com.vaadin.ui.*
import com.vaadin.ui.Button.ClickEvent

/**
 * The Application's "main" class
 */
@SuppressWarnings("serial")
public class MyVaadinUI extends UI {

    class PriceCalculation implements Runnable {

        private long calculated = 0
        private final ProgressIndicator indicator
        private final Label label

        public PriceCalculation(ProgressIndicator indicator, Label label) {
            this.indicator = indicator
            this.label = label
        }

        public void addResult(int result) {
            calculated += result
        }

        @Override
        public void run() {
            // TODO: fetch data from DB
            int data = 200

            // perform calculation
            data.times { i ->
                data.times { j ->
                    addResult(i + j)
                    println "[i: ${i}, j: ${j}, calculated: ${calculated}]"
                }
            }

            getSession().lockInstance.lock()
            try {
                // inform UI about result
                label.value = "Result is: ${calculated}"
                indicator.visible = false
            } finally {
                getSession().lockInstance.unlock()
            }
        }
    }

    public class StartCalculationListener implements Button.ClickListener {

        private final ProgressIndicator indicator
        private final Label label

        public StartCalculationListener(ProgressIndicator indicator, Label label) {
            this.indicator = indicator
            this.label = label
        }

        @Override
        public void buttonClick(ClickEvent event) {
            indicator.visible = true
            Thread thread = new Thread(new PriceCalculation(indicator, label))
            thread.start()
        }
    }

    @Override
    protected void init(VaadinRequest request) {
        final VerticalLayout layout = new VerticalLayout()
        layout.margin = true
        setContent(layout)

        ProgressIndicator indicator = new ProgressIndicator()
        Label label = new Label()

        Button button = new Button("Start calculation")
        layout.addComponent(button)

        indicator.indeterminate = true
        indicator.visible = false
        layout.addComponent(indicator)

        layout.addComponent(label)

        button.addClickListener(new StartCalculationListener(indicator, label))
    }

}
