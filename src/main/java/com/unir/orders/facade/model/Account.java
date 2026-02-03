package com.unir.orders.facade.model;

import lombok.Data;

@Data
public class Account {

    private Long id;
    private String names;
    private String lastNames;
    private String documentType;
}