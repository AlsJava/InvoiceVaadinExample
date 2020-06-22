package org.alsjava.examples.invoices.ui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.ironlist.IronList;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.Route;
import org.alsjava.examples.invoices.PrintTool;
import org.alsjava.examples.invoices.model.Product;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by aluis on 6/14/20.
 */
@Route("")
public class App extends VerticalLayout {

    private static final Product PRODUCT = new Product();

    private TextField tfInvoiceNumber;
    private DatePicker dpCreationDate;
    private DatePicker dpDueDate;

    private final List<Product> products = new ArrayList<>();

    private final IronList<Product> productIronList = new IronList<>();
    private ListDataProvider<Product> dataProvider;

    public App() {
        products.add(new Product(1));
        Button btnLocalPrint = new Button("Local Print");
        btnLocalPrint.addClickListener(event -> getUI().ifPresent(ui -> PrintTool.printFromClient(ui, tfInvoiceNumber.getValue(), prepareTemplate())));
        Button btnServerPrint = new Button("Server Print");
        btnServerPrint.addClickListener(event -> {
            ReportView reportView = new ReportView(PrintTool.printFromServer(prepareTemplate()));
            reportView.open();
        });
        add(new HorizontalLayout(btnLocalPrint, btnServerPrint));
        add(header());
        add(body());
    }

    private HorizontalLayout header() {
        tfInvoiceNumber = new TextField("Invoice #:");
        tfInvoiceNumber.setValue(UUID.randomUUID().toString().replaceAll("-", ""));
        dpCreationDate = new DatePicker("Created:");
        dpCreationDate.setValue(LocalDate.now());
        dpDueDate = new DatePicker("Due:");
        dpDueDate.setValue(LocalDate.now());
        return new HorizontalLayout(tfInvoiceNumber, dpCreationDate, dpDueDate);
    }

    private VerticalLayout body() {
        dataProvider = DataProvider.ofCollection(products);
        productIronList.setDataProvider(dataProvider);
        productIronList.setPlaceholderItem(PRODUCT);
        productIronList.setRenderer(new ComponentRenderer<>(ProductRowView::new));
        Button btnAdd = new Button("Add");
        btnAdd.addClickListener(event -> {
            products.add(new Product(products.size() + 1));
            dataProvider.refreshAll();
        });
        return new VerticalLayout(productIronList, btnAdd);
    }

    private String prepareTemplate() {
        StringBuilder productConvert = new StringBuilder();
        for (Product product : products) {
            productConvert.append(product.toString());
        }
        return String.format(loadInvoiceTemplate(), tfInvoiceNumber.getValue(), dpCreationDate.getValue(), dpDueDate.getValue(), productConvert.toString());
    }

    private String loadInvoiceTemplate() {
        InputStream inputStream = getClass().getResourceAsStream("/invoice_template.html");
        return new BufferedReader(new InputStreamReader(inputStream)).lines().collect(Collectors.joining());
    }
}
