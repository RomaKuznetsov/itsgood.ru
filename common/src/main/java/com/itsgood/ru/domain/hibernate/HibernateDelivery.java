package com.itsgood.ru.domain.hibernate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.springframework.cache.annotation.Cacheable;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString(exclude = {"address", "contracts_items"})
@Entity
@EqualsAndHashCode(exclude = {"address", "contracts_items"})
@Table(name = "Delivery")
@Cacheable("delivery")
public class HibernateDelivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "firstname")
    private String firstname;
    @Column(name = "lastname")
    private String lastname;
    @Column(name = "phone")
    private int phone;
    @Column(name = "loading_time")
    private Timestamp loading_time;
    @Column(name = "shipment_time")
    private Timestamp shipment_time;
    @Column(name = "stock_index")
    private int stock_index;
    @Column(name = "distance")
    private String distance;
    @Column(name = "price")
    private int price;
    @Column(name = "validity")
    private Date validity;

    @ManyToOne
    @JoinColumn(name = "address_id")
    @JsonBackReference
    private HibernateAddress address;

    @OneToMany(mappedBy = "delivery", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = false)
    @JsonManagedReference
    private Set<HibernateContract_item> contracts_items = Collections.emptySet();

}
