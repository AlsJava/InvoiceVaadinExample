package org.alsjava.examples.invoices.model;

import lombok.*;

import java.math.BigDecimal;

/**
 * Created by aluis on 6/14/20.
 */
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
public class Product {

    @EqualsAndHashCode.Include
    private int item;

    private String name = "";
    private BigDecimal quantity = new BigDecimal(0);
    private BigDecimal amount = new BigDecimal(0);

    public Product(int item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return String.format("<tr>" +
                        "<td>%s</td>" +
                        "<td>%s</td>" +
                        "<td>%s</td>" +
                        "<td>%s</td>" +
                        "<tr>",
                item, name, quantity, amount);
    }
}
