package com.itsgood.ru.hibernate.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.springframework.cache.annotation.Cacheable;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString(exclude = {"category", "contracts", "contracts_items"})
@Entity
@EqualsAndHashCode(exclude = {"category", "contracts", "contracts_items"})
@Table(name = "Item")
@Cacheable("item")
public class HibernateItem {
    //ctrl+p в аннотации
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "title")
    private String title;
    @Column(name = "price")
    private int price;
    @Column(name = "firm")
    private String firm;
    @Column(name = "link")
    private String link;
    @Column(name = "description")
    private String description;
    @Column(name = "weight")
    private int weight;
    @Column(name = "volume")
    private String volume;
    @Column(name = "create_time")
    private Timestamp create_time;
    @Column(name = "update_time")
    private Timestamp update_time;

    @ManyToMany
    @JoinTable(name = "Contract_item", joinColumns = @JoinColumn(name = "item_id"),
    inverseJoinColumns = @JoinColumn(name = "contract_id"))
    @JsonIgnoreProperties("items")
    private Set<HibernateContract> contracts = Collections.emptySet();

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonBackReference
    private HibernateCategory category;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = false)
    @JsonManagedReference
    private Set<HibernateContract_item> contracts_items = Collections.emptySet();

}
