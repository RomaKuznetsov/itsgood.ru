package com.itsgood.ru.domain.hibernate;

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
@ToString(exclude = {"category", "contracts", "buckets"})
@Entity
@EqualsAndHashCode(exclude = {"category", "contracts", "buckets"})
@Table(name = "Item")
@Cacheable("item")
public class ItemDTO {
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
    @JoinTable(name = "Bucket", joinColumns = @JoinColumn(name = "item_id"),
    inverseJoinColumns = @JoinColumn(name = "contract_id"))
    @JsonIgnoreProperties("items")
    private Set<ContractDTO> contracts = Collections.emptySet();

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonBackReference
    private CategoryDTO category;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = false)
    @JsonManagedReference
    private Set<BucketDTO> buckets = Collections.emptySet();

}
