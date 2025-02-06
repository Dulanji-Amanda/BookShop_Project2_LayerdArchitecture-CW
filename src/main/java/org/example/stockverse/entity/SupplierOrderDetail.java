package org.example.stockverse.entity;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class SupplierOrderDetail {
    private LocalDate Date;
    private String Stock_Id;
    private String Sup_Id;
}
