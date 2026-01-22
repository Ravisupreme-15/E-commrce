package com.codingshuttle.e_commerce.controller;

import com.codingshuttle.e_commerce.dto.requestDTO.SellerReqDTO;
import com.codingshuttle.e_commerce.dto.responseDTO.SellerResDTO;
import com.codingshuttle.e_commerce.service.SellerService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    SellerService sellerService;

    // add new seller
    @PostMapping("/addSeller")
    public ResponseEntity addSeller(@RequestBody SellerReqDTO sellerReqDTO){

        SellerResDTO sellerResDTO = sellerService.addSeller(sellerReqDTO);
        return new ResponseEntity(sellerReqDTO, HttpStatus.CREATED);

    }
}
