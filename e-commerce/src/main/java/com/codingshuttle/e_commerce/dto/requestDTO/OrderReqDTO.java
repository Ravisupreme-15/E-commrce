package com.codingshuttle.e_commerce.dto.requestDTO;

import com.codingshuttle.e_commerce.Enum.CARDTYPE;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderReqDTO {


    String customerEmail;

    int productId;

    String  cardUsed;

    int cvv;

    int requiredQty;


}
