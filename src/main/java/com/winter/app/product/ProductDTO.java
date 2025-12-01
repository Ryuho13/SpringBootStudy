package com.winter.app.product;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductDTO {
		
    private Long productNum;
    private String productTitle;
    private String productName;
    private String productCategory;
    private BigDecimal productRate;
    private Boolean productSale;
	}

