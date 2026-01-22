package com.codingshuttle.e_commerce.controller;


import com.codingshuttle.e_commerce.dto.requestDTO.OrderReqDTO;
import com.codingshuttle.e_commerce.dto.responseDTO.OrderResDTO;
import com.codingshuttle.e_commerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/placeOrder")
    public ResponseEntity placeOrder(@RequestBody OrderReqDTO orderReqDTO){
       try {
            OrderResDTO orderResDTO = orderService.placeOrder(orderReqDTO);

            return new ResponseEntity(orderResDTO,HttpStatus.CREATED);

       }
       catch (Exception exp){

            return new ResponseEntity(exp.getMessage(), HttpStatus.BAD_REQUEST);
       }



    }
}
