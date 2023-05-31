package com.itsgood.ru.old.repository.JDBCTemplateImpl.rowmapper;

import com.itsgood.ru.old.domain.Bucket;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;

import static com.itsgood.ru.old.repository.JDBCTemplateImpl.columns.Contract_itemColumns.ID;
import static com.itsgood.ru.old.repository.JDBCTemplateImpl.columns.Contract_itemColumns.CONTRACT_ID;
import static com.itsgood.ru.old.repository.JDBCTemplateImpl.columns.Contract_itemColumns.ITEM_ID;
import static com.itsgood.ru.old.repository.JDBCTemplateImpl.columns.Contract_itemColumns.QUANTITY;
import static com.itsgood.ru.old.repository.JDBCTemplateImpl.columns.Contract_itemColumns.CREATE_TIME;
import static com.itsgood.ru.old.repository.JDBCTemplateImpl.columns.Contract_itemColumns.UPDATE_TIME;

@Component
public class Contract_itemRowMapper implements RowMapper<Bucket> {
    @Override
    public Bucket mapRow(ResultSet rs, int rowNum) {
        Bucket bucket;
        try {
            bucket = new Bucket();
            bucket.setId(rs.getInt(ID));
            bucket.setContract_id(rs.getInt(CONTRACT_ID));
            bucket.setItem_id(rs.getInt(ITEM_ID));
            bucket.setQuantity(rs.getInt(QUANTITY));
            bucket.setCreate_time(rs.getString(CREATE_TIME));
            bucket.setUpdate_time(rs.getString(UPDATE_TIME));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return bucket;
    }
}
