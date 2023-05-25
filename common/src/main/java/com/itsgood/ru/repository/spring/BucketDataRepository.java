package com.itsgood.ru.repository.spring;

import com.itsgood.ru.domain.BucketDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BucketDataRepository extends JpaRepository<BucketDTO, Integer>,
        PagingAndSortingRepository<BucketDTO, Integer>,
        CrudRepository<BucketDTO, Integer> {
}
