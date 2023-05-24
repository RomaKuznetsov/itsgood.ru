package com.itsgood.ru.old.service.JDBCTemplate.impl;

import com.itsgood.ru.old.domain.Role;
import com.itsgood.ru.old.repository.RoleRepository;
import com.itsgood.ru.old.service.JDBCTemplate.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {


    private final RoleRepository roleRepositoryJDBCTemplateImpl;

    @Override
    public List<Role> findListRoleOneCustomer(Integer id) {
        List<Role> listRoleOneCustomer;
        try {
            listRoleOneCustomer = roleRepositoryJDBCTemplateImpl.findOneCustomer(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listRoleOneCustomer;
    }
}
