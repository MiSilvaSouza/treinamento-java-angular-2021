package com.company.app.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@Builder
public class OrderModel {

    private Integer code;
    private ClientModel client;
    private BigDecimal amount;
    private Date date;
    private List<ItemModel> items;

}
