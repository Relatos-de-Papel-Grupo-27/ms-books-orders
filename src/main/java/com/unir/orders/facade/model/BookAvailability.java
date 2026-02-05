package com.unir.orders.facade.model;

import lombok.Data;

@Data
public class BookAvailability {

    private boolean available;
    private int stock;
}
