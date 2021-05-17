package com.eureka.product.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OptionPriceDTO {
    private String optionId;
    private int price;
}
