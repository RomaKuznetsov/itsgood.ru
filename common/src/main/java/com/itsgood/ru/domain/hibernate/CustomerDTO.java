package com.itsgood.ru.domain.hibernate;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

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
@ToString(exclude = {"address", "payments", "roles", "contract"})
@Entity
@EqualsAndHashCode(exclude = {"address", "payments", "roles", "contract"})
@Table(name = "Customer")
//@Cacheable("customer")
public class CustomerDTO {
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
    private Timestamp create_time;
    @Column(name = "update_time")
    private Timestamp update_time;


    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = false)
    @JsonManagedReference
    private Set<AddressDTO> address = Collections.emptySet();

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = false)
    @JsonManagedReference
    private Set<PaymentDTO> payments = Collections.emptySet();

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = false)
    @JsonManagedReference
    private Set<RoleDTO> roles = Collections.emptySet();

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = false)
    @JsonManagedReference
    private ContractDTO contract;

}
