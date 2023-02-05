package com.mcdonalds.ecommerce.repository;

import com.mcdonalds.ecommerce.model.ShoppingCartDiscount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ShoppingCartDiscountRepository extends JpaRepository<ShoppingCartDiscount, Long> {
    @Transactional
    void deleteAllByShoppingCartId(Long shoppingCartId);
    @Transactional
    List<ShoppingCartDiscount>findAllByShoppingCartId(Long shoppingCartId);

}
