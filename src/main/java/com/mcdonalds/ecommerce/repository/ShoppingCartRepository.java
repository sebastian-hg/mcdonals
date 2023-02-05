package com.mcdonalds.ecommerce.repository;

import com.mcdonalds.ecommerce.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {

    @Transactional
    List<ShoppingCart> findAllByClientDocumentNationalAndCreationDateBetweenOrderByTotalPurchaseDesc
            (Integer documentNational, LocalDate init, LocalDate end);

    @Transactional
    List<ShoppingCart> findAllByClientDocumentNationalAndCreationDateBetweenOrderByCreationDateDescCreationTimeDesc
            (Integer documentNational, LocalDate init, LocalDate end);
}
