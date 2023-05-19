package com.itsgood.ru.repository;

import com.itsgood.ru.domain.Role;

import java.sql.SQLException;
import java.util.List;

public interface RoleRepository extends CRUDRepository<Integer, Role> {
    List<Role> findOneCustomer(Integer id) throws SQLException;

    Role findMaxIdRole() throws SQLException;

    Role findMinIdRole() throws SQLException;

}
