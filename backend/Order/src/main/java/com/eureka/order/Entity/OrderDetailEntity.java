package com.eureka.order.Entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "orderdetail")
public class OrderDetailEntity {

    @Id
    @Column(name = "order_id")
    private String orderId;
    @Column(name = "check_datetime")
    private String checkDatetime;
    @Column(name = "order_status")
    private String orderStatus;
    private String courier;
    @Column(name = "invoice_num")
    private String invoiceNum;
    @Column(name = "delivery_start_datatime")
    private String deliveryStartDatatime;
    @Column(name = "delivery_completion_datatime")
    private String deliveryCompletionDatatime;
    @Column(name = "cancel_msg")
    private String cancelMsg;

    @OneToOne(mappedBy = "orderDetailEntity")
    private OrderEntity orderEntity;
}
