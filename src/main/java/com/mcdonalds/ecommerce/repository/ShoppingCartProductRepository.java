package com.mcdonalds.ecommerce.repository;

import com.mcdonalds.ecommerce.model.ShoppingCartProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface ShoppingCartProductRepository extends JpaRepository<ShoppingCartProduct, Long> {
    Optional<ShoppingCartProduct> findByShoppingCartIdAndProductId(Long shoppingCardId, Long productId);

    @Transactional
    void deleteByShoppingCartIdAndProductId(Long shoppingCardId, Long productId);

    @Transactional
    void deleteAllByShoppingCartId(Long shoppingCartId);

    @Transactional
    Boolean existsByShoppingCartIdAndProductId(Long shoppingCardId, Long productId);

    @Transactional
    List<ShoppingCartProduct> findAllByShoppingCartId(Long ShoppingCartId);

}
