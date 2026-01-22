package com.codingshuttle.e_commerce.service;

import com.codingshuttle.e_commerce.dto.requestDTO.SellerReqDTO;
import com.codingshuttle.e_commerce.dto.responseDTO.SellerResDTO;
import com.codingshuttle.e_commerce.model.Seller;
import com.codingshuttle.e_commerce.repository.SellerRepository;
import com.codingshuttle.e_commerce.transformers.SellerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerService {

    @Autowired
    SellerRepository sellerRepository;

    public SellerResDTO addSeller(SellerReqDTO sellerReqDTO) {

         Seller seller = SellerTransformer.sellerReqDtoToSellerEntity(sellerReqDTO);

         Seller savedSeller = sellerRepository.save(seller);

         return SellerTransformer.sellerEntityToSellerResDto(savedSeller);

    }
}
