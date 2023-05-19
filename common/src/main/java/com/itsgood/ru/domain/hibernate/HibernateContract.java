package com.itsgood.ru.domain.hibernate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.springframework.cache.annotation.Cacheable;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString(exclude = {"items", "customer", "address", "payment", "contracts_items"})
@Entity
@EqualsAndHashCode(exclude = {"id", "items", "customer", "address", "payment", "contracts_items"})
@Table(name = "Contract")
@Cacheable("contract")
public class HibernateContract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "sum_order")
    private int sum_order;
    @Column(name = "payment_types")
    private String payment_types;
    @Column(name = "relevance")
    private String relevance;
    @Column(name = "create_time")
    private Timestamp create_time;
    @Column(name = "update_time")
    private Timestamp update_time;

    @ManyToMany(mappedBy = "contracts", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnoreProperties("contracts")
    private List<HibernateItem> items = Collections.emptyList();

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonBackReference
    private HibernateCustomer customer;

    @ManyToOne
    @JoinColumn(name = "address_id")
    @JsonBackReference
    private HibernateAddress address;

    @ManyToOne
    @JoinColumn(name = "payment_id")
    @JsonBackReference
    private HibernatePayment payment;

    @OneToMany(mappedBy = "contract", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = false)
    @JsonManagedReference
    private Set<HibernateContract_item> contracts_items = Collections.emptySet();
}
