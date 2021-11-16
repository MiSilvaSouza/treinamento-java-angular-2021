package com.company.app.services;

import com.company.app.model.OrderModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
public class ServiceReadFiles {

    public List<OrderModel> execute(String caminhoArquivo) {
        List<String> rows;
        try {
            File arquivo = new File(caminhoArquivo);
            rows = FileUtils.readLines(arquivo, Charset.defaultCharset());

            if (rows.isEmpty()) {
                return Collections.emptyList();
            }
        } catch (IOException e) {
            log.error("Falha ao ler arquivo [{}]", caminhoArquivo, e);
            return Collections.emptyList();
        }

        return createOrders(rows);
    }

    public List<OrderModel> createOrders(List<String> rows) {

        OrderModel currentOrder = null;

       List<OrderModel> orders = new ArrayList<>();
       for (String row : rows) {
           String[] field = StringUtils.split(row, ";");
           if (field.length == 0) {
               continue;
           }

           if (field[0].equals("C")) {
               currentOrder = createOrder(field);
               orders.add(currentOrder);
               continue;
           }
       }
        return orders;
    }

    private OrderModel createOrder(String...field) {
        int index = 1;
        OrderModel order = OrderModel.builder().code(Integer.valueOf(field[index++])).build();
        return order;
    }
}
