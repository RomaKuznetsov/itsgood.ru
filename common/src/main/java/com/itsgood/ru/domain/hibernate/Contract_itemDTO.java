package com.itsgood.ru.domain.hibernate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString(exclude = {"item", "contract"})
@Entity
@EqualsAndHashCode(exclude = {"item", "contract"})
@Table(name = "Contract_item")
//@Cacheable("contract_item")
public class Contract_itemDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "create_time")
    private Timestamp create_time;
    @Column(name = "update_time")
    private Timestamp update_time;

    @ManyToOne
    @JoinColumn(name = "contract_id")
    @JsonBackReference
    private ContractDTO contract;

    @ManyToOne
    @JoinColumn(name = "item_id")
    @JsonBackReference
    private ItemDTO item;

    @ManyToOne
    @JoinColumn(name = "delivery_id")
    @JsonBackReference
    private DeliveryDTO delivery;

}
