package com.itsgood.ru.repository.JDBCTemplateImpl.rowmapper;

import com.itsgood.ru.domain.Contract_item;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;

import static com.itsgood.ru.repository.JDBCTemplateImpl.columns.Contract_itemColumns.ID;
import static com.itsgood.ru.repository.JDBCTemplateImpl.columns.Contract_itemColumns.CONTRACT_ID;
import static com.itsgood.ru.repository.JDBCTemplateImpl.columns.Contract_itemColumns.ITEM_ID;
import static com.itsgood.ru.repository.JDBCTemplateImpl.columns.Contract_itemColumns.QUANTITY;
import static com.itsgood.ru.repository.JDBCTemplateImpl.columns.Contract_itemColumns.CREATE_TIME;
import static com.itsgood.ru.repository.JDBCTemplateImpl.columns.Contract_itemColumns.UPDATE_TIME;

@Component
public class Contract_itemRowMapper implements RowMapper<Contract_item> {
    @Override
    public Contract_item mapRow(ResultSet rs, int rowNum) {
        Contract_item contract_item;
        try {
            contract_item = new Contract_item();
            contract_item.setId(rs.getInt(ID));
            contract_item.setContract_id(rs.getInt(CONTRACT_ID));
            contract_item.setItem_id(rs.getInt(ITEM_ID));
            contract_item.setQuantity(rs.getInt(QUANTITY));
            contract_item.setCreate_time(rs.getString(CREATE_TIME));
            contract_item.setUpdate_time(rs.getString(UPDATE_TIME));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return contract_item;
    }
}
