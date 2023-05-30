package com.itsgood.ru.domain.hibernate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.springframework.cache.annotation.Cacheable;

import javax.persistence.*;
import java.math.BigInteger;
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
public class PaymentDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "status")
    private String status;
    @Column(name = "phone")
    private BigInteger phone;
    @Column(name = "card")
    private BigInteger card;
    @Column(name = "validity")
    private Date validity;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonBackReference
    private CustomerDTO customer;

    @OneToMany(mappedBy = "payment", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = false)
    @JsonManagedReference
    private Set<ContractDTO> contracts = Collections.emptySet();

}
