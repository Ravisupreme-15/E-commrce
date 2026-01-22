package com.codingshuttle.e_commerce.dto.requestDTO;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CheckoutCartReqDTO {


    String customerEmail;

    String cardUsed;

    int cvv;


}
