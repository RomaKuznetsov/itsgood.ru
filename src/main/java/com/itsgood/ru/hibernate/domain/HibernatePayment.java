package com.itsgood.ru.hibernate.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.springframework.cache.annotation.Cacheable;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collections;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString(exclude = {"customer", "contracts"})
@Entity
@EqualsAndHashCode(exclude = {"id", "customer", "contracts"})
@Table(name = "Payment")
@Cacheable("payment")
public class HibernatePayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "status")
    private String status;
    @Column(name = "phone")
    private int phone;
    @Column(name = "card")
    private String card;
    @Column(name = "validity")
    private String validity;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonBackReference
    private HibernateCustomer customer;

    @OneToMany(mappedBy = "payment", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = false)
    @JsonManagedReference
    private Set<HibernateContract> contracts = Collections.emptySet();

}
