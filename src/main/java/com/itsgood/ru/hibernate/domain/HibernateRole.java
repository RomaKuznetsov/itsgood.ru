package com.itsgood.ru.hibernate.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString(exclude = {"customer"})
@Entity
@EqualsAndHashCode(exclude = {"customer"})
@Table(name = "Role")
@Cacheable("role")
public class HibernateRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "role")
    private String role;
    @Column(name = "create_time")
    private Timestamp create_time;
    @Column(name = "update_time")
    private Timestamp update_time;
    @Column(name = "validity")
    private Date validity;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonBackReference
    private HibernateCustomer customer;

}
