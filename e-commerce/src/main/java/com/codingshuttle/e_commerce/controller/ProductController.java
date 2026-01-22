package com.codingshuttle.e_commerce.controller;


import com.codingshuttle.e_commerce.Enum.ProductCategory;
import com.codingshuttle.e_commerce.dto.requestDTO.ProductReqDTO;
import com.codingshuttle.e_commerce.dto.responseDTO.ProductResDTO;
import com.codingshuttle.e_commerce.exception.SellerNotFoundException;
import com.codingshuttle.e_commerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tools.jackson.databind.annotation.JsonAppend;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    // add a product

    @PostMapping("/addProduct")
    public ResponseEntity addProduct(@RequestBody ProductReqDTO productReqDTO){

        try{
            ProductResDTO productResDTO = productService.addProduct(productReqDTO);
            return new ResponseEntity(productResDTO,HttpStatus.CREATED);
        }
        catch (SellerNotFoundException exp){

             return new ResponseEntity(exp.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/getByCategoryAndPrice")
    public ResponseEntity getProdByCategoryAndPriceGreaterThan(@RequestParam("price") int price, @RequestParam("category")ProductCategory productCategory){

        List<ProductResDTO>  productResDTOSList = productService.getProdByCategoryAndPriceGreaterThan(price,productCategory);

        return new ResponseEntity(productResDTOSList,HttpStatus.FOUND);

    }
}
