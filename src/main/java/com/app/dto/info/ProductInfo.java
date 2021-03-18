package com.app.dto.info;

import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductInfo {

    private String productName;
    private BigDecimal price;
    private String categoryName;
    private String producentName;
    private String countryName;

    @Override
    public String toString() {
        return productName + " " + price + " " + categoryName + " " + producentName + " " + countryName;
    }
}
