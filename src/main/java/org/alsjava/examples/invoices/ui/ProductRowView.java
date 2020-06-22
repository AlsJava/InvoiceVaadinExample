package org.alsjava.examples.invoices.ui;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.BigDecimalField;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import org.alsjava.examples.invoices.model.Product;

import java.math.BigDecimal;

/**
 * Created by aluis on 6/21/20.
 */
public class ProductRowView extends HorizontalLayout {

    private final IntegerField ifItem = new IntegerField("Item");

    private TextField tfName;
    private BigDecimalField bdfQuantity;
    private BigDecimalField bdfAmount;

    private final TextField tfTotal = new TextField("Total");

    private final Product product;

    public ProductRowView(Product product) {
        this.product = product;
        prepareView();
    }

    private void prepareView() {
        tfName = new TextField("Name");
        tfName.addValueChangeListener(event -> product.setName(event.getValue()));
        bdfQuantity = new BigDecimalField("Quantity");
        bdfQuantity.addValueChangeListener(event -> {
            product.setQuantity(event.getValue());
            calculate();
        });
        bdfAmount = new BigDecimalField("Amount");
        bdfAmount.addValueChangeListener(event -> {
            product.setAmount(event.getValue());
            calculate();
        });
        add(ifItem, tfName, bdfQuantity, bdfAmount, tfTotal);
        ifItem.setValue(product.getItem());
        tfName.setValue(product.getName());
        bdfQuantity.setValue(product.getQuantity());
        bdfAmount.setValue(product.getAmount());
        ifItem.setReadOnly(true);
        tfTotal.setReadOnly(true);
    }

    private void calculate() {
        BigDecimal quantity = bdfQuantity.getValue();
        BigDecimal amount = bdfAmount.getValue();
        if (quantity == null || amount == null) {
            tfTotal.setValue("None");
        } else {
            tfTotal.setValue(quantity.multiply(amount).toEngineeringString());
        }
    }

}
