package com.mcdonalds.ecommerce;

import com.mcdonalds.ecommerce.model.Client;
import com.mcdonalds.ecommerce.model.Gender;
import com.mcdonalds.ecommerce.model.Product;
import com.mcdonalds.ecommerce.repository.ClientRepository;
import com.mcdonalds.ecommerce.repository.ProductRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.ArrayList;

@SpringBootApplication
@ConfigurationPropertiesScan(basePackages = {"com.mcdonalds.ecommerce.configuration"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    ApplicationListener<ApplicationReadyEvent> setUpDataBase(ClientRepository clientRepository, ProductRepository productRepository) {
        return event -> {
            var listClients = new ArrayList<Client>();
            listClients.add(Client.builder()
                    .gender(Gender.MALE)
                    .nameCompleted("Sebastian Hernandez")
                    .documentNational(95764679)
                    .build());
            listClients.add(Client.builder()
                    .nameCompleted("Cindy Villa")
                    .gender(Gender.FEMALE)
                    .documentNational(958851100)
                    .build());
            listClients.add(Client.builder()
                    .nameCompleted("Jose Perez")
                    .gender(Gender.MALE)
                    .documentNational(95837374)
                    .build());

            var listProducts = new ArrayList<Product>();
            listProducts.add(Product.builder()
                    .description("french fries'")
                    .price(BigDecimal.valueOf(100))
                    .isActive(true)
                    .build());
            listProducts.add(Product.builder()
                    .description("Soda")
                    .isActive(true)
                    .price(BigDecimal.valueOf(50))
                    .build());
            listProducts.add(Product.builder()
                    .description("Burger")
                    .price(BigDecimal.valueOf(150))
                    .isActive(Boolean.TRUE)
                    .build());


            var clients = Flux.fromIterable(listClients)
                    .map(clientRepository::save).subscribe();

            var product = Flux.fromIterable(listProducts)
                    .map(productRepository::save).subscribe();
        };
    }

}
