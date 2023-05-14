package com.itsgood.ru.controller.springDataRepository;

import com.itsgood.ru.controller.dto.request.itemDTO.ItemRequestSearch;
import com.itsgood.ru.hibernate.domain.HibernateContract_item;
import com.itsgood.ru.hibernate.domain.HibernateItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.awt.desktop.OpenFilesEvent;
import java.util.Optional;
import java.util.Set;

public interface ItemDataRepository extends JpaRepository<HibernateItem, Integer>,
        PagingAndSortingRepository<HibernateItem, Integer>,
        CrudRepository<HibernateItem, Integer> {

    Optional<HibernateItem> findHibernateItemByIdOrTitle(ItemRequestSearch request);


}
