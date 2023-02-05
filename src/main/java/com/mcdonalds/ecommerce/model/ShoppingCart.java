package com.mcdonalds.ecommerce.model;

import com.mcdonalds.ecommerce.helper.IResponseSuccess;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
@Getter
@Setter
@Entity
@Table(name = "shopping_cart")
public class ShoppingCart implements IResponseSuccess {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Builder.Default
    private Long id = 0L;
    @ManyToOne(optional = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Client client;
    @Builder.Default
    private LocalDate creationDate = LocalDate.now();
    @Builder.Default
    private LocalTime creationTime = LocalTime.now();
    private Boolean isVip;
    @Builder.Default
    private Integer numberProducts = 0;
    @Builder.Default
    private BigDecimal totalPurchase = BigDecimal.ZERO;
}
