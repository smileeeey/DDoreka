package com.eureka.order.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OptionPriceDTO {
    String optionId;
    int price;
}
