package com.company.app.dao;

import com.company.app.model.OrderModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class OrderDao {

    private Connection connection;

    public OrderDao() throws SQLException{
        connection = createConnection();
    }

    private Connection createConnection() throws SQLException {
        String url = "jdbc:postgresql://localhost/test";
        Properties props = new Properties();
        props.setProperty("user","fred");
        props.setProperty("password","secret");
        props.setProperty("ssl","true");
        return DriverManager.getConnection(url, props);
    }

    public OrderModel insert(OrderModel order) {
        return order;
    }

    public OrderModel update(OrderModel order) {
        return order;
    }

    public OrderModel searchByCode(Integer code) {
        return null;
    }
}
