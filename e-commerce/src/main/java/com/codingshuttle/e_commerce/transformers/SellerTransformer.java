package com.codingshuttle.e_commerce.transformers;


import com.codingshuttle.e_commerce.dto.requestDTO.SellerReqDTO;
import com.codingshuttle.e_commerce.dto.responseDTO.SellerResDTO;
import com.codingshuttle.e_commerce.model.Seller;
import lombok.experimental.UtilityClass;

@UtilityClass
public class SellerTransformer {

    public  static Seller sellerReqDtoToSellerEntity(SellerReqDTO sellerReqDTO){

           return  Seller.builder()
                   .name(sellerReqDTO.getName())
                   .email(sellerReqDTO.getEmail())
                   .panNo(sellerReqDTO.getPanNo()).build();
    }

    public  static SellerResDTO sellerEntityToSellerResDto(Seller seller){

         return  SellerResDTO.builder()
                 .email(seller.getEmail())
                 .name(seller.getName())
                 .build();
    }
}
