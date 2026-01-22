package com.codingshuttle.e_commerce.dto.responseDTO;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CartResDTO {

    int cartTotal;

    String customerName;

    String customerEmail;

    List<ItemResDTO> itemResDTOList;


}
