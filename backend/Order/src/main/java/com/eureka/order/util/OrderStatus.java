package com.eureka.order.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatus {
    PAY(0,"결제완료"),
    SHIPPING(1,"배송중"),
    DONE(2,"배송완료"),
    REFUND(3,"환불신청");

    OrderStatus(int i, String status) {

    }
}
