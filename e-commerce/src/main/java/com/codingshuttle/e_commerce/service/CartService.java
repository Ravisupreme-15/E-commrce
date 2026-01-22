package com.codingshuttle.e_commerce.service;

import com.codingshuttle.e_commerce.dto.requestDTO.CheckoutCartReqDTO;
import com.codingshuttle.e_commerce.dto.requestDTO.ItemReqDTO;
import com.codingshuttle.e_commerce.dto.responseDTO.CartResDTO;
import com.codingshuttle.e_commerce.dto.responseDTO.OrderResDTO;
import com.codingshuttle.e_commerce.exception.CustomerNotFoundException;
import com.codingshuttle.e_commerce.exception.EmptyCartException;
import com.codingshuttle.e_commerce.exception.InvalidCardException;
import com.codingshuttle.e_commerce.model.*;
import com.codingshuttle.e_commerce.repository.*;
import com.codingshuttle.e_commerce.transformers.CartTransformer;
import com.codingshuttle.e_commerce.transformers.OrderTransformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@Service
public class CartService {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrderService orderService;


    @Autowired
    CardRepository cardRepository;

    @Autowired
    OrderEntityRepository orderEntityRepository;

    public CartResDTO addItemToCart(ItemReqDTO itemReqDTO, Item item) {

        // get the customer
        Customer customer  = customerRepository.findByEmail(itemReqDTO.getCustomerEmail());

        // get cart
        Cart cart = customer.getCart();
        Product product = productRepository.findById(itemReqDTO.getProductId()).get();
        int price = product.getPrice()*item.getRequiredQuantity();
        cart.setCartTotal(cart.getCartTotal()+price);

        // set cart and product
        item.setCart(cart);
        item.setProduct(product);

        // save the item repo
        Item savedItem = itemRepository.save(item);
        cart.getItems().add(savedItem);    //  to avoid the duplicacy for the item saving twice
        product.getItemList().add(savedItem);

        Cart savedCart = cartRepository.save(cart);
        productRepository.save(product);

        // cart Response DTO

        return CartTransformer.cartEntityToCartResDto(savedCart);
    }

    public OrderResDTO checkOutCart(CheckoutCartReqDTO cartCheckOutReqDTO) {

        Customer customer = customerRepository.findByEmail(cartCheckOutReqDTO.getCustomerEmail());

        if(customer==null) {
            throw new CustomerNotFoundException("customer doesn't exist");
        }


        Card card = cardRepository.findByCardNo(cartCheckOutReqDTO.getCardUsed());
        Date currentDate = new Date();
        if(card==null || card.getCvv()!= cartCheckOutReqDTO.getCvv() || currentDate.after(card.getValidTill()) ){

            throw new InvalidCardException("Card is inValid, Please check the cardNo or cvv or validTill");

        }

        Cart cart = customer.getCart();

        if(cart.getItems().size()==0){

            throw new EmptyCartException("Cart is Empty");
        }

        // place Order


        OrderEntity orderEntity = orderService.placeOrder(cart,card);

        resetCart(cart);

        OrderEntity savedOrder =  orderEntityRepository.save(orderEntity);

        //  return order to orderResDTO

        return OrderTransformers.orderEntityToOrderResDto(savedOrder);
    }

    public void resetCart(Cart cart){

         cart.setCartTotal(0);
         for(Item item : cart.getItems()){

              item.setCart(null);
         }
         cart.setItems(new ArrayList<>());
    }
}
