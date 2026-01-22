package com.codingshuttle.e_commerce.transformers;

import com.codingshuttle.e_commerce.dto.requestDTO.CardReqDTO;
import com.codingshuttle.e_commerce.dto.responseDTO.CardResDTO;
import com.codingshuttle.e_commerce.model.Card;

public class CardTransformer {

    public static Card  cardReqDtoToCardEntity(CardReqDTO cardReqDTO){


        return Card.builder()
                .cardNo(cardReqDTO.getCardNo())
                .cardtype(cardReqDTO.getCardtype())
                .validTill(cardReqDTO.getValidTill())
                .cvv(cardReqDTO.getCvv()).build();

    }

    public static CardResDTO cardEntityToCardResDto(Card card){

        return CardResDTO.builder()
                .validTill(card.getValidTill())
                .cardtype(card.getCardtype())
                .customerName(card.getCustomer().getName())
                .build();
    }
}
