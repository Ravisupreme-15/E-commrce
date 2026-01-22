package com.codingshuttle.e_commerce.dto.requestDTO;


import com.codingshuttle.e_commerce.Enum.CARDTYPE;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CardReqDTO {

    String mobile;

    String cardNo;

    int  cvv;

    Date validTill;

    CARDTYPE cardtype;
}
