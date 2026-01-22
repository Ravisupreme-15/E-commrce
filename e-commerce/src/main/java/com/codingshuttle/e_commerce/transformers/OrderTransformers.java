package com.codingshuttle.e_commerce.transformers;

import com.codingshuttle.e_commerce.dto.responseDTO.ItemResDTO;
import com.codingshuttle.e_commerce.dto.responseDTO.OrderResDTO;
import com.codingshuttle.e_commerce.model.Item;
import com.codingshuttle.e_commerce.model.OrderEntity;

import java.util.ArrayList;
import java.util.List;

public class OrderTransformers {


    public static OrderResDTO orderEntityToOrderResDto(OrderEntity order){

        List<ItemResDTO> itemResDTOList  = new ArrayList<>();

        for(Item item : order.getItemList()){
            itemResDTOList.add(ItemTransformer.itemEntityToItemResDto(item));
        }


        return  OrderResDTO.builder()
                .orderId(order.getOrderId())
                .orderDate(order.getOrderDate())
                .cardUsed(order.getCardUsed())
                .customerName(order.getCustomer().getName())
                .itemList(itemResDTOList)
                .orderTotal(order.getOrderTotal()).build();
    }
}
