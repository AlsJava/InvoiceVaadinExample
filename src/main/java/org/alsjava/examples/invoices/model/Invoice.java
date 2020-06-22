package org.alsjava.examples.invoices.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by aluis on 6/21/20.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Invoice {

    private String invoiceNumber;
    private LocalDate creationDate;
    private LocalDate dueDate;

    private List<Product> products;
}
