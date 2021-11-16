package com.company.app.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClientModel {

    private Integer code;
    private String name;

}
