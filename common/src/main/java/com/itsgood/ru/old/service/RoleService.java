package com.itsgood.ru.old.service;

import com.itsgood.ru.domain.Role;

import java.sql.SQLException;
import java.util.List;

public interface RoleService {

    List<Role> findListRoleOneCustomer(Integer id) throws SQLException;
}
