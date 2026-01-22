package com.codingshuttle.e_commerce.dto.requestDTO;


import com.codingshuttle.e_commerce.Enum.Gender;
import jakarta.persistence.Column;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SellerReqDTO {

    String name;

    String email;

    String panNo;
}
