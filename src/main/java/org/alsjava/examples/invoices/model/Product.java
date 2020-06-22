package org.alsjava.examples.invoices.model;

import lombok.*;

import java.math.BigDecimal;

/**
 * Created by aluis on 6/21/20.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Product {

    @EqualsAndHashCode.Include
    private int item;

    private String name = "";
    private BigDecimal quantity = new BigDecimal(0);
    private BigDecimal amount = new BigDecimal(0);

    public Product(int item) {
        this.item = item;
    }

    public String toHTML() {
        return String.format("<tr>" +
                        "<td>%s</td>" +
                        "<td>%s</td>" +
                        "<td>%s</td>" +
                        "<td>%s</td>" +
                        "<td>%s</td>" +
                        "</tr>",
                item, name, quantity, amount, quantity.multiply(amount));
    }
}
