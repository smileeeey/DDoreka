package com.eureka.order.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.Date;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserOrderListDTO {
    private int orderId;
    private String userId;
    private int productId;
    private String productName;
    private int optionId;
    private String optionName;
    private String quantity;
    private int price;
    private Date datetime;
    private String status;
    private String thumbnail;
}
