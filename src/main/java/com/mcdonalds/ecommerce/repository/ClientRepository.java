package com.mcdonalds.ecommerce.repository;

import com.mcdonalds.ecommerce.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    @Transactional
    Optional<Client> findByDocumentNational(Integer documentNational);
    @Transactional
    Boolean existsByDocumentNational(Integer documentNational);

}
