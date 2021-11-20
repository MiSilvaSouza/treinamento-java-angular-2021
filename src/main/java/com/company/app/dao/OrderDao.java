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

    public Optional<OrderModel> insert(OrderModel order) throws SQLException {

        // TODO Se o pedido existir chamar método update(OrderModel order)

//        int newOrder = order.getCode();
//        int searchCode = this.searchByCode(newOrder).get().getCode();
//
//        if (newOrder == searchCode) {
//            this.update(order);
//        }


        StringBuilder sb = new StringBuilder();
        sb.append("insert into orders ");
        sb.append("     ( code ");
        sb.append("     , client_code ");
        sb.append("     , amount ");
        sb.append("     , cpf ");
        sb.append("     , date )");
        sb.append("values ");
        sb.append("     ( ? ");
        sb.append("     , ? ");
        sb.append("     , ? ");
        sb.append("     , ? ");
        sb.append("     , ? )");

        int i = 1;
        PreparedStatement pst = connection.prepareStatement(sb.toString());
        pst.setInt(i++, order.getCode());
        pst.setInt(i++, order.getClient().getCode());
        pst.setBigDecimal(i++, order.getAmount());
        pst.setString(i++, order.getCpf());
        pst.setDate(i, (java.sql.Date)order.getDate());

        int qtdRows = pst.executeUpdate();
        if (qtdRows == 0) {
            throw new SQLException("Nenhum registro inserido para o pedido []" + order);
        }

        return Optional.of(order);

    }

    public Optional <OrderModel> update(OrderModel order) throws SQLException {

        return Optional.of(order);
    }

    public Optional <OrderModel> searchByCode(Integer idQuery) throws SQLException {

        // TODO Criar tabela cliente e relacionar com pedido
        StringBuilder sb = new StringBuilder();
        sb.append("select code ");
        sb.append("     , client_code ");
        sb.append("     , amount ");
        sb.append("     , cpf ");
        sb.append("     , date ");
        sb.append(" from orders ");
        sb.append(" where code = ?");

        int i = 1;
        PreparedStatement pst = connection.prepareStatement(sb.toString());
        pst.setInt(i, idQuery);
        ResultSet rs = pst.executeQuery();

        if (!rs.next()) {
            return Optional.empty();
        }

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
