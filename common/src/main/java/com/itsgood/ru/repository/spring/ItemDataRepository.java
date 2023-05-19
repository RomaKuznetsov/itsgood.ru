package com.itsgood.ru.repository.spring;

import com.itsgood.ru.domain.hibernate.HibernateItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface ItemDataRepository extends JpaRepository<HibernateItem, Integer>,
        PagingAndSortingRepository<HibernateItem, Integer>,
        CrudRepository<HibernateItem, Integer> {

    Optional<HibernateItem> findHibernateItemByIdOrTitle(Integer id, String title);
    Optional<List<HibernateItem>> findHibernateItemByTitleAndPriceAfterOrFirm(String title, int price, String firm);
    Optional<List<HibernateItem>> findHibernateItemByTitleAndPriceBeforeOrFirm(String title, int price, String firm);
    Optional<List<HibernateItem>> findHibernateItemByTitleAndDescription(String title, String description);

}
