package com.codingshuttle.e_commerce.controller;

import com.codingshuttle.e_commerce.dto.requestDTO.CardReqDTO;
import com.codingshuttle.e_commerce.dto.responseDTO.CardResDTO;
import com.codingshuttle.e_commerce.exception.CustomerNotFoundException;
import com.codingshuttle.e_commerce.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/card")
public class CardController {

    @Autowired
    CardService cardService;

    @PostMapping("/addCard")
    public ResponseEntity addCard(@RequestBody CardReqDTO cardReqDTO){


        try{
            CardResDTO cardResDTO = cardService.addCard(cardReqDTO);
            return new ResponseEntity(cardResDTO, HttpStatus.CREATED);
        }
        catch (CustomerNotFoundException exp){

            return new ResponseEntity(exp.getMessage(),HttpStatus.NOT_FOUND);
        }
 }

}
