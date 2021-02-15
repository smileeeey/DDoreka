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

    OrderStatus(int i, String 환불신청) {
    }

    public static OrderStatus valueOf(int matchNum) {
        OrderStatus val = null;
        switch (matchNum) {
            case 0:
                val = OrderStatus.PAY;
                break;
            case 1:
                val = OrderStatus.SHIPPING;
                break;
            case 2:
                val = OrderStatus.DONE;
                break;
            case 3:
                val = OrderStatus.REFUND;
                break;
            default:
                break;
        }
        return val;
    }
}
