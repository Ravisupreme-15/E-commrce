package com.codingshuttle.e_commerce.dto.responseDTO;


import com.codingshuttle.e_commerce.Enum.ProductCategory;
import com.codingshuttle.e_commerce.Enum.ProductStatus;
import com.codingshuttle.e_commerce.model.Seller;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ProductResDTO {

    String  sellerName;

    String productName;

    int availableQuantity;

    int price;

    ProductCategory category;

    ProductStatus productStatus;


}
