package com.itsgood.ru.hibernate.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.springframework.cache.annotation.Cacheable;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString(exclude = {"address", "payments", "roles", "contracts"})
@Entity
@EqualsAndHashCode(exclude = {"address", "payments", "roles", "contracts"})
@Table(name = "Customer")
//@Cacheable("customer")
public class HibernateCustomer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "firstname")
    private String firstname;
    @Column(name = "lastname")
    private String lastname;
    @Column(name = "mail")
    private String mail;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "username", column = @Column(name = "username")),
            @AttributeOverride(name = "password", column = @Column(name = "password")),
    })
    private AuthenticationInfo authenticationInfo;

    @Column(name = "phone")
    private int phone;
    @Column(name = "birthday")
    private Date birthday;
    @Column(name = "gender")
    private String gender;
    //  @JsonView
    @Column(name = "create_time")
    private String create_time;
    @Column(name = "update_time")
    private String update_time;


    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = false)
    @JsonManagedReference
    private Set<HibernateAddress> address = Collections.emptySet();

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = false)
    @JsonManagedReference
    private Set<HibernatePayment> payments = Collections.emptySet();

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = false)
    @JsonManagedReference
    private Set<HibernateRole> roles = Collections.emptySet();

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = false)
    @JsonManagedReference
    private Set<HibernateContract> contracts = Collections.emptySet();

}
