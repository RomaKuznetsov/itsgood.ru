package com.itsgood.ru.JDBCTemplate.service;

import com.itsgood.ru.old.domain.Role;

import java.sql.SQLException;
import java.util.List;

public interface RoleService {

    List<Role> findListRoleOneCustomer(Integer id) throws SQLException;
}
