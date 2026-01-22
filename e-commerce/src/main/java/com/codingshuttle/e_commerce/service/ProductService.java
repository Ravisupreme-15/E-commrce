package com.codingshuttle.e_commerce.service;


import com.codingshuttle.e_commerce.Enum.ProductCategory;
import com.codingshuttle.e_commerce.dto.requestDTO.ProductReqDTO;
import com.codingshuttle.e_commerce.dto.responseDTO.ProductResDTO;
import com.codingshuttle.e_commerce.exception.SellerNotFoundException;
import com.codingshuttle.e_commerce.model.Product;
import com.codingshuttle.e_commerce.model.Seller;
import com.codingshuttle.e_commerce.repository.ProductRepository;
import com.codingshuttle.e_commerce.repository.SellerRepository;
import com.codingshuttle.e_commerce.transformers.ProductTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    SellerRepository sellerRepository;

    @Autowired
    ProductRepository productRepository;

    public ProductResDTO addProduct(ProductReqDTO productReqDTO) {

        // chech wheather seller exist or not (is valid)

        Seller seller = sellerRepository.findByEmail(productReqDTO.getSellerEmail());

        if (seller == null) {

            throw new SellerNotFoundException("Email doesn't exists");

        }
        // reqDto -> Entity
        Product product = ProductTransformer.productReqDtoToProductEntity(productReqDTO);
        product.setSeller(seller);
        seller.getProductList().add(product);

        Seller savedSeller = sellerRepository.save(seller);  // save both seller and product

        List<Product> productList = savedSeller.getProductList();

        Product latestProduct = productList.get(productList.size() - 1);

        return ProductTransformer.productEntityToProductResDto(latestProduct);
    }

    public List<ProductResDTO> getProdByCategoryAndPriceGreaterThan(int price, ProductCategory productCategory) {

        List<Product> productList = productRepository.findByCategoryAndPriceGreaterThan(productCategory, price);

        // prepare the list response DTO

        List<ProductResDTO> productResDTOS = new ArrayList<>();

        for (Product product : productList) {

            productResDTOS.add(ProductTransformer.productEntityToProductResDto(product));
        }

        return productResDTOS;
    }
}
