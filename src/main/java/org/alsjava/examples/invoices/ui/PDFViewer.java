package org.alsjava.examples.invoices.ui;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasSize;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.server.StreamResource;

/**
 * Created by aluis on 6/21/20.
 */
@Tag("object")
public class PDFViewer  extends Component implements HasSize {

    public PDFViewer(StreamResource resource) {
        this();
        getElement().setAttribute("data", resource);
    }

    public PDFViewer(String url) {
        this();
        getElement().setAttribute("data", url);
    }

    private PDFViewer() {
        getElement().setAttribute("type", "application/pdf");
        setSizeFull();
    }
}
