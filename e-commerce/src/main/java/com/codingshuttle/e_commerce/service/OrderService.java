package com.codingshuttle.e_commerce.service;


import com.codingshuttle.e_commerce.Enum.ProductStatus;
import com.codingshuttle.e_commerce.dto.requestDTO.OrderReqDTO;
import com.codingshuttle.e_commerce.dto.responseDTO.OrderResDTO;
import com.codingshuttle.e_commerce.exception.CustomerNotFoundException;
import com.codingshuttle.e_commerce.exception.InsufficientQuantityException;
import com.codingshuttle.e_commerce.exception.InvalidCardException;
import com.codingshuttle.e_commerce.exception.ProductNotFoundException;
import com.codingshuttle.e_commerce.model.*;
import com.codingshuttle.e_commerce.repository.*;
import com.codingshuttle.e_commerce.transformers.ItemTransformer;
import com.codingshuttle.e_commerce.transformers.OrderTransformers;
import org.aspectj.weaver.ast.Or;
import org.hibernate.query.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    CardService cardService;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    OrderEntityRepository orderEntityRepository;

    public OrderResDTO placeOrder(OrderReqDTO orderReqDTO) {

         // check the customer

        Customer customer = customerRepository.findByEmail(orderReqDTO.getCustomerEmail());

        if(customer==null) {
             throw new CustomerNotFoundException("customer doesn't exist");
        }
        Optional<Product> productOptional = productRepository.findById(orderReqDTO.getProductId());

        if(productOptional.isEmpty()){

            throw new ProductNotFoundException("Product doesn't exist");
        }


        // check for card and its cvv

        Card card = cardRepository.findByCardNo(orderReqDTO.getCardUsed());

        Date currentDate = new Date();
        if(card==null || card.getCvv()!= orderReqDTO.getCvv() || currentDate.after(card.getValidTill()) ){

            throw new InvalidCardException("Card is inValid, Please check the cardNo or cvv or validTill");

        }

        // get the product
        Product product = productOptional.get();

        // check the available Qty
        if(product.getAvailableQuantity()<orderReqDTO.getRequiredQty()){
            throw new InsufficientQuantityException("Product Quantity is in sufficient");
        }

        int newPrdQty = product.getAvailableQuantity() - orderReqDTO.getRequiredQty();

        product.setAvailableQuantity(newPrdQty);

        if(newPrdQty==0){
             product.setProductStatus(ProductStatus.OUT_OF_STOCK);
        }

       // now need to prepare the order entity
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderId(String.valueOf(UUID.randomUUID()));
        orderEntity.setCardUsed(cardService.getMaskedCardNo(orderReqDTO.getCardUsed()));
        orderEntity.setOrderTotal(product.getPrice()*orderReqDTO.getRequiredQty());

        // create item
        Item item = ItemTransformer.ItemReqDtoToItemEntity(orderReqDTO.getRequiredQty());
        item.setOrderEntity(orderEntity);
        item.setProduct(product);

        // set item list
        orderEntity.getItemList().add(item);
        orderEntity.setCustomer(customer);

        OrderEntity savedOrderEntity = orderEntityRepository.save(orderEntity);

        // set the item to product
        product.getItemList().add(savedOrderEntity.getItemList().get(0));
        customer.getOrderEntities().add(savedOrderEntity);

        productRepository.save(product);
        customerRepository.save(customer);

        // response OrderResDTO
        return OrderTransformers.orderEntityToOrderResDto(savedOrderEntity);

    }

    public OrderEntity placeOrder(Cart cart,Card card){

         OrderEntity orderEntity = new OrderEntity();
         orderEntity.setOrderId(String.valueOf(UUID.randomUUID()));
         orderEntity.setCardUsed(cardService.getMaskedCardNo(card.getCardNo()));


         int orderTotal = 0;

         for(Item item : cart.getItems()){

              Product product = item.getProduct();

              if(product.getAvailableQuantity()< item.getRequiredQuantity()){

                  throw new InsufficientQuantityException("Sorry!, product: "+product.getProductName()+" quantity is Insufficient");

              }

              int newQty = product.getAvailableQuantity() - item.getRequiredQuantity();

              product.setAvailableQuantity(newQty);

              if(newQty==0){
                   product.setProductStatus(ProductStatus.OUT_OF_STOCK);
              }

              orderTotal+=(product.getPrice()*item.getRequiredQuantity());

              item.setOrderEntity(orderEntity);

         }

         orderEntity.setOrderTotal(orderTotal);
         orderEntity.setItemList(cart.getItems());
         orderEntity.setCustomer(cart.getCustomer());

         return orderEntity;


    }
}
