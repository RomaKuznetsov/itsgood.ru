package com.itsgood.ru.repository.JDBCTemplateImpl.rowmapper;

import com.itsgood.ru.domain.Payment;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.itsgood.ru.repository.JDBCTemplateImpl.columns.PaymentColumns.*;

@Component
public class PaymentRowMapper implements RowMapper<Payment> {
    @Override
    public Payment mapRow(ResultSet rs, int rowNum) {
        Payment payment;

        try {
            payment = new Payment();
            payment.setId(rs.getInt(ID));
            payment.setStatus(rs.getString(STATUS));
            payment.setPhone(rs.getInt(CUSTOMER_ID));
            payment.setPhone(rs.getInt(PHONE));
            payment.setCard(rs.getString(CARD));
            payment.setValidity(rs.getString(VALIDITY));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return payment;
    }
}
