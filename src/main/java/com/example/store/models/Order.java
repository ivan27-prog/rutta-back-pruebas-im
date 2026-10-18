package com.example.store.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idorder")
    private Long id;

    @Column(name = "order_date", nullable = false)
    private LocalDate orderDate;

    @Column(name = "order_status", length = 45, nullable = false)
    private String orderStatus;

    @Column(name = "order_amount", nullable = false)
    private BigDecimal orderAmount;

    //@ManyToOne
    //@JoinColumn(name = "user_idusers", nullable = false)
    //@JsonIgnore
    //private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderDetails> orderDetails;
}