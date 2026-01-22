package com.codingshuttle.e_commerce.service;

import ch.qos.logback.core.model.processor.ProcessorException;
import com.codingshuttle.e_commerce.dto.requestDTO.ItemReqDTO;
import com.codingshuttle.e_commerce.exception.CustomerNotFoundException;
import com.codingshuttle.e_commerce.exception.InsufficientQuantityException;
import com.codingshuttle.e_commerce.exception.ProductNotFoundException;
import com.codingshuttle.e_commerce.model.Customer;
import com.codingshuttle.e_commerce.model.Item;
import com.codingshuttle.e_commerce.model.Product;
import com.codingshuttle.e_commerce.repository.CustomerRepository;
import com.codingshuttle.e_commerce.repository.ProductRepository;
import com.codingshuttle.e_commerce.transformers.ItemTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;


    public Item createItem(ItemReqDTO itemReqDTO) {

        // check the customer
        Customer customer =  customerRepository.findByEmail(itemReqDTO.getCustomerEmail());

        if(customer==null){

            throw new CustomerNotFoundException("Customer doesn't Exist");
        }
        // check the product

        Optional<Product> productOptional = productRepository.findById(itemReqDTO.getProductId());

        if(productOptional.isEmpty()){

            throw new ProductNotFoundException("Product doesn't exist");
        }
        // get the product
        Product product = productOptional.get();

        // check the available Qty
        if(product.getAvailableQuantity()<itemReqDTO.getRequiredQuantity()){
            throw new InsufficientQuantityException("Product Quantity is in sufficient");
        }

        // Create an Item
        Item item = ItemTransformer.ItemReqDtoToItemEntity(itemReqDTO.getRequiredQuantity());
        item.setProduct(product);

        return item;
    }
}
