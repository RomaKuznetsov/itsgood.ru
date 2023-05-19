package com.itsgood.ru.old.repository.JDBCTemplateImpl.rowmapper;

import com.itsgood.ru.domain.Contract;
import com.itsgood.ru.old.repository.JDBCTemplateImpl.columns.ContractColumns;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;


@Component
public class ContractRowMapper implements RowMapper<Contract> {
    @Override
    public Contract mapRow(ResultSet rs, int rowNum) {
        Contract contract;
        try {
            contract = new Contract();
            contract.setId(rs.getInt(ContractColumns.ID));
            contract.setCustomer_id(rs.getInt(ContractColumns.CUSTOMER_ID));
            contract.setAddress_id(rs.getInt(ContractColumns.ADDRESS_ID));
            contract.setPayment_id(rs.getInt(ContractColumns.PAYMENT_ID));
            contract.setSum_order(rs.getInt(ContractColumns.SUM_ORDER));
            contract.setPayment_types(rs.getString(ContractColumns.PAYMENT_TYPES));
            contract.setCreate_time(rs.getString(ContractColumns.CREATE_TIME));
            contract.setUpdate_time(rs.getString(ContractColumns.UPDATE_TIME));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return contract;
    }
}
