package com.codingshuttle.e_commerce.controller;

import com.codingshuttle.e_commerce.dto.requestDTO.CustomerReqDTO;
import com.codingshuttle.e_commerce.dto.responseDTO.CustomerResDTO;
import com.codingshuttle.e_commerce.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    // add customer - new customer
    @PostMapping("/add")
    public ResponseEntity addCustomer(@RequestBody CustomerReqDTO customerReqDTO){

        CustomerResDTO customerResDTO = customerService.addCustomer(customerReqDTO);

        return new ResponseEntity(customerResDTO, HttpStatus.CREATED);
    }


}
