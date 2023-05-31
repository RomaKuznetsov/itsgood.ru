package com.itsgood.ru.domain.hibernate;

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
@ToString(exclude = "items")
@Entity
@EqualsAndHashCode(exclude = {"id", "description", "items"})
@Table(name = "Category")
@Cacheable("category")
public class CategoryDTO {
// @Id
//    @GeneratedValue(strategy = GenerationType.UUID)
//    private UUID id;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = false)
    @JsonManagedReference
    private Set<ItemDTO> items = Collections.emptySet();


}
