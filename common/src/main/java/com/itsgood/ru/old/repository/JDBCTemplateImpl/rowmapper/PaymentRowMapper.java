package com.itsgood.ru.old.repository.JDBCTemplateImpl.rowmapper;

import com.itsgood.ru.old.domain.Payment;
import com.itsgood.ru.old.repository.JDBCTemplateImpl.columns.PaymentColumns;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class PaymentRowMapper implements RowMapper<Payment> {
    @Override
    public Payment mapRow(ResultSet rs, int rowNum) {
        Payment payment;

        try {
            payment = new Payment();
            payment.setId(rs.getInt(PaymentColumns.ID));
            payment.setStatus(rs.getString(PaymentColumns.STATUS));
            payment.setPhone(rs.getInt(PaymentColumns.CUSTOMER_ID));
            payment.setPhone(rs.getInt(PaymentColumns.PHONE));
            payment.setCard(rs.getString(PaymentColumns.CARD));
            payment.setValidity(rs.getString(PaymentColumns.VALIDITY));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return payment;
    }
}
