package com.mcdonalds.ecommerce.model;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
@Getter
@Setter
@Entity
@Table(name = "client")
public class Client {
    @Id
/*    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Builder.Default*/
    @GeneratedValue(generator="id")
    @SequenceGenerator(name="id",sequenceName="id", allocationSize=1)
    private Long id;
    private Integer documentNational;
    private String nameCompleted;
    @Enumerated(value = EnumType.STRING)
    private Gender gender;
}
