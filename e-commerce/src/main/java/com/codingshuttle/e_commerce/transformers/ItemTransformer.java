package com.codingshuttle.e_commerce.transformers;

import com.codingshuttle.e_commerce.dto.responseDTO.ItemResDTO;
import com.codingshuttle.e_commerce.model.Item;


public class ItemTransformer {

    public static Item ItemReqDtoToItemEntity(int requiredQuantity) {

        return Item.builder()
                .requiredQuantity(requiredQuantity).build();
    }


    public static ItemResDTO itemEntityToItemResDto(Item item){

           return ItemResDTO.builder()
                   .quantityAdded(item.getRequiredQuantity())
                   .itemName(item.getProduct().getProductName())
                   .itemPrice(item.getProduct().getPrice())
                   .productCategory(item.getProduct().getCategory().toString()).build();
    }
}