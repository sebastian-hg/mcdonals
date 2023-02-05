package com.mcdonalds.ecommerce.service.impl;

import com.mcdonalds.ecommerce.model.ShoppingCart;
import com.mcdonalds.ecommerce.repository.ShoppingCartRepository;
import com.mcdonalds.ecommerce.service.GetShoppingCartByFilterService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Log4j2
@Service
public class GetShoppingCartByFilterServiceImpl implements GetShoppingCartByFilterService {

    private final ShoppingCartRepository repository;


    @Override
    public Mono<List<ShoppingCart>> execute(Integer documentId, LocalDate dateStar, LocalDate dateEnd, String typeOrder) {
        String amount= "amount";
        if (Objects.equals(typeOrder, amount)){
            return Mono.just(repository.findAllByClientDocumentNationalAndCreationDateBetweenOrderByTotalPurchaseDesc
                    (documentId, dateStar, dateEnd));
        }
        else {
            return Mono.just(repository.findAllByClientDocumentNationalAndCreationDateBetweenOrderByCreationDateDescCreationTimeDesc
                    (documentId,dateStar,dateEnd));
        }
    }
}
