package com.codingshuttle.e_commerce.controller;


import com.codingshuttle.e_commerce.dto.requestDTO.CheckoutCartReqDTO;
import com.codingshuttle.e_commerce.dto.requestDTO.ItemReqDTO;
import com.codingshuttle.e_commerce.dto.responseDTO.CartResDTO;
import com.codingshuttle.e_commerce.dto.responseDTO.OrderResDTO;
import com.codingshuttle.e_commerce.model.Item;
import com.codingshuttle.e_commerce.service.CartService;
import com.codingshuttle.e_commerce.service.ItemService;
import com.codingshuttle.e_commerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cart")
public class CartController {


    @Autowired
    ItemService itemService;

    @Autowired
    CartService cartService;

    @Autowired
    OrderService orderService;



    // add to cart

    @PostMapping("/addItemToCart")
    public ResponseEntity addToCart(@RequestBody ItemReqDTO itemReqDTO){

       try { // create an item
            Item item = itemService.createItem(itemReqDTO);

            // Add Item to cart
            CartResDTO cartResDTO = cartService.addItemToCart(itemReqDTO, item);

            return new ResponseEntity(cartResDTO, HttpStatus.CREATED);
        }
       catch (Exception exp){

           return new ResponseEntity(exp.getMessage(),HttpStatus.NOT_FOUND);
       }
    }


    // check-out cart
  @PostMapping("/checkoutCart")
    public ResponseEntity checkOutCart(@RequestBody CheckoutCartReqDTO cartCheckOutReqDTO){


       try {
            OrderResDTO orderResDTO = cartService.checkOutCart(cartCheckOutReqDTO);

            return new ResponseEntity(orderResDTO,HttpStatus.CREATED);

        }
       catch (Exception exp){
           return new ResponseEntity(exp.getMessage(),HttpStatus.BAD_REQUEST);
       }

    }
}
