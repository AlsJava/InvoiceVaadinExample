package org.alsjava.examples.invoices.ui;

import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

/**
 * Created by aluis on 6/14/20.
 */
@Route("")
public class App extends VerticalLayout {

    private static final int size = 800;

    private Div body = new Div();

    public App() {
        setWidth(size + "px");
        setHeightFull();
        add(createHeader());
        add(createBody());
        add(createFooter());
    }

    private VerticalLayout createHeader() {
        VerticalLayout vlHeader = new VerticalLayout();
        vlHeader.setPadding(false);
        vlHeader.setClassName("header");
        vlHeader.setWidthFull();

        Image imageLogo = new Image();
        imageLogo.setWidth((size / 2) + "px");

        VerticalLayout vlInfo = new VerticalLayout();
        vlInfo.setAlignItems(Alignment.END);
        vlInfo.setWidth((size / 2) + "px");

        TextField tfInvoiceNumber = new TextField("Invoice #:");
        DatePicker dpCreationDate = new DatePicker("Created:");
        DatePicker dpDueDate = new DatePicker("Due:");

        vlInfo.add(tfInvoiceNumber, dpCreationDate, dpDueDate);

        HorizontalLayout hlUp = new HorizontalLayout();
        hlUp.setPadding(false);
        hlUp.setClassName("up-part");
        hlUp.setWidthFull();
        hlUp.add(imageLogo, vlInfo);

        HorizontalLayout hlDown = new HorizontalLayout();
        hlDown.setPadding(false);
        hlDown.setClassName("down-part");
        hlDown.setWidthFull();

        TextArea taData1 = new TextArea();
        taData1.setWidthFull();
        taData1.setPlaceholder("Write here ...");
        TextArea taData2 = new TextArea();
        taData2.setPlaceholder("Write here ...");
        taData2.setWidthFull();

        hlDown.add(taData1, taData2);

        vlHeader.add(hlUp);
        vlHeader.add(hlDown);

        return vlHeader;
    }

    private Div createBody() {
        body.setWidthFull();



        // Editor de items

        return body;
    }

    private HorizontalLayout createFooter() {
        HorizontalLayout hlFooter = new HorizontalLayout();
        hlFooter.setWidthFull();
        TextArea taFooter = new TextArea();
        taFooter.setWidthFull();
        hlFooter.add(taFooter);
        return hlFooter;
    }
}
