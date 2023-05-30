package com.itsgood.ru.old.repository.JDBCTemplateImpl.rowmapper;

import com.itsgood.ru.domain.Volume;
import com.itsgood.ru.old.domain.Item;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.itsgood.ru.old.repository.JDBCTemplateImpl.columns.ItemColumns.*;

@Component
public class ItemRowMapper implements RowMapper<Item> {

    @Override
    public Item mapRow(ResultSet rs, int rowNum) {
        Item item;
        try {
            item = new Item();
            item.setId(rs.getInt(ID));
            item.setTitle(rs.getString(TITLE));
            item.setPrice(rs.getInt(PRICE));
            item.setFirm(rs.getString(FIRM));
            item.setLink(rs.getString(LINK));
            item.setDescription(rs.getString(DESCRIPTION));
            item.setWeight(rs.getInt(WEIGHT));
            item.setVolume(Volume.valueOf(rs.getString(VOLUME)));
            item.setCreate_time(rs.getString(CREATE_TIME));
            item.setUpdate_time(rs.getString(UPDATE_TIME));
            item.setCategory_id(rs.getInt(CATEGORY_ID));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return item;
    }
}
