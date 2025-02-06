package org.example.stockverse.entity;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class OrderDetail {
    private String orderId;
    private String itemId;
    private LocalDate date;
    private double amount;
}
