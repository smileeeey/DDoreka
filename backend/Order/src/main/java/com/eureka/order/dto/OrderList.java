package com.eureka.order.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderList {
    private String cartId;
    private String quantity;
    private String productId;
    private String optionId;
}
