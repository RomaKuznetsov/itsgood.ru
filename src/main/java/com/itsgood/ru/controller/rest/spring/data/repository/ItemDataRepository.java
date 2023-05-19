package com.itsgood.ru.controller.rest.spring.data.repository;

import com.itsgood.ru.controller.dto.request.itemDTO.ItemRequestSearch;
import com.itsgood.ru.hibernate.domain.HibernateContract_item;
import com.itsgood.ru.hibernate.domain.HibernateItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.awt.desktop.OpenFilesEvent;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ItemDataRepository extends JpaRepository<HibernateItem, Integer>,
        PagingAndSortingRepository<HibernateItem, Integer>,
        CrudRepository<HibernateItem, Integer> {

    Optional<HibernateItem> findHibernateItemByIdOrTitle(Integer id, String title);
    Optional<List<HibernateItem>> findHibernateItemByTitleAndPriceAfterOrFirm(String title, int price, String firm);
    Optional<List<HibernateItem>> findHibernateItemByTitleAndPriceBeforeOrFirm(String title, int price, String firm);
    Optional<List<HibernateItem>> findHibernateItemByTitleAndDescription(String title, String description);

}
