package com.codingshuttle.e_commerce.dto.responseDTO;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ItemResDTO {

    int quantityAdded;

    String itemName;

    int itemPrice;

    String productCategory;
}
