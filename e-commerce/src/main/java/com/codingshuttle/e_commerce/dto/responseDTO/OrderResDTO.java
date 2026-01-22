package com.codingshuttle.e_commerce.dto.responseDTO;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class OrderResDTO {

    String orderId;

    Date orderDate;

    String cardUsed;

    int orderTotal;

    String  customerName;

    List<ItemResDTO>  itemList;

}
