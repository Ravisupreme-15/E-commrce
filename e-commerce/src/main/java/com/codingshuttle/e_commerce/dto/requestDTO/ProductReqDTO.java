package com.codingshuttle.e_commerce.dto.requestDTO;


import com.codingshuttle.e_commerce.Enum.ProductCategory;
import com.codingshuttle.e_commerce.model.Seller;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductReqDTO {


    String sellerEmail;

    String productName;

    int availableQuantity;

    int price;

    ProductCategory category;
}
