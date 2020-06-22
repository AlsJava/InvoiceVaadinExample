package org.alsjava.examples.invoices.ui.pdf;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasSize;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.server.StreamResource;

/**
 * Created by aluis on 6/21/20.
 */
@Tag("object")
public class PDFObjectViewer extends Component implements HasSize {

    public PDFObjectViewer(StreamResource resource) {
        this();
        getElement().setAttribute("data", resource);
    }

    public PDFObjectViewer() {
        getElement().setAttribute("type", "application/pdf");
        setSizeFull();
    }
}
