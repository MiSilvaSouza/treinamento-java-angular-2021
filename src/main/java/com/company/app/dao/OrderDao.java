package com.company.app.dao;

import com.company.app.model.ClientModel;
import com.company.app.model.OrderModel;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.sql.*;
import java.util.Date;
import java.util.Optional;
import java.util.Properties;

@Slf4j
public class OrderDao {

    private Connection connection;

    public OrderDao() throws SQLException{
        connection = createConnection();
        log.info("Conexão executada com sucesso!");
    }

    private Connection createConnection() throws SQLException {
        String url = "jdbc:postgresql://localhost/postgres";
        Properties props = new Properties();
        props.setProperty("user","postgres");
        props.setProperty("password","postgres");
        //props.setProperty("ssl","true");
        return DriverManager.getConnection(url, props);
    }

    public OrderModel insert(OrderModel order) {

        return order;
    }

    public OrderModel update(OrderModel order) {

        return order;
    }

    public Optional <OrderModel> searchByCode(Integer idQuery) throws SQLException {

        StringBuilder sb = new StringBuilder();
        sb.append("select code ");
        sb.append("     , client_code ");
        sb.append("     , amount ");
        sb.append("     , cpf ");
        sb.append("     , date ");
        sb.append(" from orders ");
        sb.append(" where code = " + idQuery);

        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(sb.toString());

        if (!rs.next()) {
            return Optional.empty();
        }

        int i = 1;
        Integer code = rs.getInt(i++);
        Integer client_code = rs.getInt(i++);
        BigDecimal amount = rs.getBigDecimal(i++);
        String cpf = rs.getString(i++);
        Date date = rs.getDate(i);

        ClientModel client = ClientModel.builder()
                .code(client_code)
                .build();

        OrderModel order = OrderModel.builder()
                .code(code)
                .client(client)
                .amount(amount)
                .cpf(cpf)
                .date(date)
                .build();

        return Optional.of(order);
    }
}
