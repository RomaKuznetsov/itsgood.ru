package com.itsgood.ru.domain.hibernate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.springframework.cache.annotation.Cacheable;

import javax.persistence.*;
import java.util.Collections;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString(exclude = {"customer", "contracts", "deliveries"})
@Entity
@EqualsAndHashCode(exclude = {"id", "customer", "contracts", "deliveries"})
@Table(name = "Address")
@Cacheable("address")
public class HibernateAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "code")
    private String code;
    @Column(name = "country")
    private String country;
    @Column(name = "region")
    private String region;
    @Column(name = "city")
    private String city;
    @Column(name = "street")
    private String street;
    @Column(name = "house")
    private int house;
    @Column(name = "frame")
    private String frame;
    @Column(name = "apartment")
    private int apartment;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonBackReference
    private HibernateCustomer customer;

    @OneToMany(mappedBy = "address", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = false)
    @JsonManagedReference
    private Set<HibernateContract> contracts = Collections.emptySet();

    @OneToMany(mappedBy = "address", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = false)
    @JsonManagedReference
    private Set<HibernateDelivery> deliveries = Collections.emptySet();


}
