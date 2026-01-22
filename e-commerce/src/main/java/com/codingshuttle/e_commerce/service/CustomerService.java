package com.codingshuttle.e_commerce.service;

import com.codingshuttle.e_commerce.dto.requestDTO.CustomerReqDTO;
import com.codingshuttle.e_commerce.dto.responseDTO.CustomerResDTO;
import com.codingshuttle.e_commerce.model.Cart;
import com.codingshuttle.e_commerce.model.Customer;
import com.codingshuttle.e_commerce.repository.CustomerRepository;
import com.codingshuttle.e_commerce.transformers.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public CustomerResDTO addCustomer(CustomerReqDTO customerReqDTO) {

         // reqDto -> Entity
         Customer  customer = CustomerTransformer.customerReqDtoToCustomerEntity(customerReqDTO);

         Cart  cart = new Cart();
         cart.setCartTotal(0);
         cart.setCustomer(customer);
         customer.setCart(cart);

         Customer savedCustomer =   customerRepository.save(customer);

         // Entity -> resDto
         return CustomerTransformer.customerEntityToCustomerResDto(savedCustomer);
    }
}
