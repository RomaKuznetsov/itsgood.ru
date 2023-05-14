package com.itsgood.ru.repository.JDBCTemplateImpl.rowmapper;

import com.itsgood.ru.domain.Contract;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.itsgood.ru.repository.JDBCTemplateImpl.columns.ContractColumns.ID;
import static com.itsgood.ru.repository.JDBCTemplateImpl.columns.ContractColumns.CUSTOMER_ID;
import static com.itsgood.ru.repository.JDBCTemplateImpl.columns.ContractColumns.ADDRESS_ID;
import static com.itsgood.ru.repository.JDBCTemplateImpl.columns.ContractColumns.PAYMENT_ID;
import static com.itsgood.ru.repository.JDBCTemplateImpl.columns.ContractColumns.PAYMENT_TYPES;
import static com.itsgood.ru.repository.JDBCTemplateImpl.columns.ContractColumns.SUM_ORDER;
import static com.itsgood.ru.repository.JDBCTemplateImpl.columns.ContractColumns.CREATE_TIME;
import static com.itsgood.ru.repository.JDBCTemplateImpl.columns.ContractColumns.UPDATE_TIME;


@Component
public class ContractRowMapper implements RowMapper<Contract> {
    @Override
    public Contract mapRow(ResultSet rs, int rowNum) {
        Contract contract;
        try {
            contract = new Contract();
            contract.setId(rs.getInt(ID));
            contract.setCustomer_id(rs.getInt(CUSTOMER_ID));
            contract.setAddress_id(rs.getInt(ADDRESS_ID));
            contract.setPayment_id(rs.getInt(PAYMENT_ID));
            contract.setSum_order(rs.getInt(SUM_ORDER));
            contract.setPayment_types(rs.getString(PAYMENT_TYPES));
            contract.setCreate_time(rs.getString(CREATE_TIME));
            contract.setUpdate_time(rs.getString(UPDATE_TIME));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return contract;
    }
}
