package com.codingshuttle.e_commerce.transformers;

import com.codingshuttle.e_commerce.dto.requestDTO.CustomerReqDTO;
import com.codingshuttle.e_commerce.dto.responseDTO.CustomerResDTO;
import com.codingshuttle.e_commerce.model.Customer;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CustomerTransformer {


    public static Customer customerReqDtoToCustomerEntity(CustomerReqDTO customerReqDTO){

        return  Customer.builder().name(customerReqDTO.getName())
                .email(customerReqDTO.getEmail())
                .gender(customerReqDTO.getGender())
                .mobile(customerReqDTO.getMobile())
                .build();
    }

    public static CustomerResDTO  customerEntityToCustomerResDto(Customer customer){

         return  CustomerResDTO.builder().
                 name(customer.getName()).
                 email(customer.getEmail()).
                 gender(customer.getGender()).
                 mobile(customer.getMobile()).build();
    }


}
