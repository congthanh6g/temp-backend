package com.projectsem4.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "payments")
public class Payment extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int amount;
    private int type;

    @Column(name = "payment_date")
    private LocalDateTime paymentDate;

    @Column(name = "expiration_date")
    private LocalDateTime expirationDate;

    @Column(name = "customer_id")
    private int customerId;

    @ManyToOne
    @JoinColumn(name = "customer_id", insertable = false, updatable = false)
    private Customer customer;
}
