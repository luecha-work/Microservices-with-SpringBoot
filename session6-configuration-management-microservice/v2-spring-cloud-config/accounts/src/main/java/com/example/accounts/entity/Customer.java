package com.example.accounts.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
//@Table(name = "customers")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Customer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
//    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "customer_id")
    private Long customerId;

    @Column
    private String name;

    @Column
    private String email;

    @Column(name = "mobile_number")
    private String mobileNumber;

}
