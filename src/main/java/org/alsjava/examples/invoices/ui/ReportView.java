package org.alsjava.examples.invoices.ui;

import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.server.StreamResource;

import java.io.ByteArrayInputStream;

/**
 * Created by aluis on 6/21/20.
 */
public class ReportView extends Dialog {

    public ReportView(byte[] pdfContent ) {
        setWidth("800px");
        setHeight("600px");
        StreamResource pdf = new StreamResource("pdf", () -> new ByteArrayInputStream(pdfContent));
        pdf.setContentType("application/pdf");
        pdf.setCacheTime(0);
        add(new PDFViewer(pdf));
    }
}
