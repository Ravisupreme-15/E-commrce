package com.codingshuttle.e_commerce.transformers;

import com.codingshuttle.e_commerce.Enum.ProductStatus;
import com.codingshuttle.e_commerce.dto.requestDTO.ProductReqDTO;
import com.codingshuttle.e_commerce.dto.responseDTO.ProductResDTO;
import com.codingshuttle.e_commerce.model.Product;
import jakarta.persistence.EnumType;

public class ProductTransformer {


    public static Product productReqDtoToProductEntity(ProductReqDTO productReqDTO){


         return Product.builder()
                 .productName(productReqDTO.getProductName())
                 .availableQuantity(productReqDTO.getAvailableQuantity())
                 .productStatus(ProductStatus.AVAILABLE)
                 .price(productReqDTO.getPrice())
                 .category(productReqDTO.getCategory())
                 .build();
    }

    public static ProductResDTO productEntityToProductResDto(Product product){

        return ProductResDTO.builder()
                .sellerName(product.getSeller().getName())
                .productName(product.getProductName())
                .availableQuantity(product.getAvailableQuantity())
                .price(product.getPrice())
                .category(product.getCategory())
                .productStatus(product.getProductStatus())
                .build();
    }
}
