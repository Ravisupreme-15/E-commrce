package com.codingshuttle.e_commerce.service;

import com.codingshuttle.e_commerce.dto.requestDTO.CardReqDTO;
import com.codingshuttle.e_commerce.dto.responseDTO.CardResDTO;
import com.codingshuttle.e_commerce.exception.CustomerNotFoundException;
import com.codingshuttle.e_commerce.model.Card;
import com.codingshuttle.e_commerce.model.Customer;
import com.codingshuttle.e_commerce.repository.CustomerRepository;
import com.codingshuttle.e_commerce.transformers.CardTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardService {

    @Autowired
    CustomerRepository customerRepository;

    public CardResDTO addCard(CardReqDTO cardReqDTO) {

        // check the customer
        Customer customer =  customerRepository.findByMobile(cardReqDTO.getMobile());

        if(customer == null){

             throw new CustomerNotFoundException("Customer doesn't exists");
        }

        // create card entity
        Card card = CardTransformer.cardReqDtoToCardEntity(cardReqDTO);
        card.setCustomer(customer);
        customer.getCards().add(card);

        Customer savedCustomer = customerRepository.save(customer);

        // prepare card response dto
        Card latestCard = savedCustomer.getCards().get(savedCustomer.getCards().size()-1);

        CardResDTO cardResDTO = CardTransformer.cardEntityToCardResDto(latestCard);

        cardResDTO.setCardNo(getMaskedCardNo(latestCard.getCardNo()));

        return cardResDTO;
    }

    public String getMaskedCardNo(String cardNumber){

        cardNumber = cardNumber.replaceAll("\\s+", ""); // remove spaces
        int length = cardNumber.length();

        if (length < 4) {
            throw new IllegalArgumentException("Invalid card number");
        }

        return "XXXX XXXX XXXX " + cardNumber.substring(length - 4);

    }
}
