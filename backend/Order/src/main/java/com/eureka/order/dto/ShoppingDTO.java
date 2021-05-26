package com.eureka.order.dto;

import lombok.*;

@Data
@Builder
public class ShoppingDTO {
    private int cartId;
    private String quantity; //얘를 통해서 계산해야대
    private String productId;
    private String optionId;
    private String sellerId;
    //price만들던지 말던지
}
