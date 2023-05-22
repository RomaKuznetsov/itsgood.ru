package com.itsgood.ru.repository.spring;

import com.itsgood.ru.domain.hibernate.ItemDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface ItemDataRepository extends JpaRepository<ItemDTO, Integer>,
        PagingAndSortingRepository<ItemDTO, Integer>,
        CrudRepository<ItemDTO, Integer> {

    Optional<ItemDTO> findItemByIdOrTitle(Integer id, String title);
    Optional<List<ItemDTO>> findHibernateItemByTitleAndPriceAfterOrFirm(String title, int price, String firm);
    Optional<List<ItemDTO>> findHibernateItemByTitleAndPriceBeforeOrFirm(String title, int price, String firm);
    Optional<List<ItemDTO>> findHibernateItemByTitleAndDescription(String title, String description);

}
