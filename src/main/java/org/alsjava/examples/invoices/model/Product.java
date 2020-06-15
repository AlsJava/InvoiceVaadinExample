package org.alsjava.examples.invoices.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Created by aluis on 6/14/20.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product {

    private int item;

    private String name;
    private BigDecimal quantity;
    private BigDecimal amount;
}
