package com.codingshuttle.e_commerce.transformers;

import com.codingshuttle.e_commerce.dto.responseDTO.CartResDTO;
import com.codingshuttle.e_commerce.dto.responseDTO.ItemResDTO;
import com.codingshuttle.e_commerce.model.Cart;
import com.codingshuttle.e_commerce.model.Item;

import java.util.ArrayList;
import java.util.List;

public class CartTransformer {


    public static CartResDTO cartEntityToCartResDto(Cart cart){

        List<ItemResDTO> itemResDTOList  = new ArrayList<>();

        for(Item item : cart.getItems()){
            itemResDTOList.add(ItemTransformer.itemEntityToItemResDto(item));
        }
        return CartResDTO.builder()
                .cartTotal(cart.getCartTotal())
                .customerName(cart.getCustomer().getName())
                .customerEmail(cart.getCustomer().getEmail())
                .itemResDTOList(itemResDTOList).build();
    }
}
