package com.codingshuttle.e_commerce.dto.responseDTO;


import com.codingshuttle.e_commerce.Enum.CARDTYPE;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CardResDTO {

    String customerName;

    String cardNo;

    Date validTill;

    CARDTYPE cardtype;

}
