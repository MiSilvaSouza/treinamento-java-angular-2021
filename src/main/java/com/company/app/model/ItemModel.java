package com.company.app.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ItemModel {
    private Integer itemNumber;
    private BigDecimal quantity;
    private BigDecimal unitaryValue;
    private BigDecimal amountValue;
    private Integer productCode;
    private String productName;
}
