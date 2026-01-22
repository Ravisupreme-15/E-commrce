package com.codingshuttle.e_commerce.dto.responseDTO;

import com.codingshuttle.e_commerce.Enum.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CustomerResDTO {

    String name;

    String email;

    String mobile;

    Gender gender;
}
