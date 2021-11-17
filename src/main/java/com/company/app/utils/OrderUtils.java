package com.company.app.utils;

import com.company.app.model.ClientModel;
import com.company.app.model.OrderModel;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Optional;

@Slf4j
public final class OrderUtils {

    public static final int LENGTH_FIELD_ORDER = 7;
    private static final SimpleDateFormat SDF;

    static {
        SDF = new SimpleDateFormat("ddMMyyyy");
    }

    private OrderUtils() {

    }

    public static Optional<OrderModel> createOrder(String...field) {
        if (field.length != LENGTH_FIELD_ORDER) {
            return Optional.empty();
        }
        try {
            Index index = Index.create();
            OrderModel order = OrderModel.builder()
                    .code(Integer.valueOf(field[index.inc()]))
                    .client(createClient(index, field).orElse(null))
                    .amount(new BigDecimal(field[index.inc()]))
                    .cpf(field[index.inc()])
                    .date(SDF.parse(field[index.inc()]))
                    .build();
            return Optional.of(order);

        } catch (Exception e) {
            log.error("Falha ao criar pedido - Campos: {}", field, e);
            return Optional.empty();
        }
    }

    public static Optional<ClientModel> createClient(Index index, String...field) {

        try {
            ClientModel client = ClientModel.builder()
                    .code(Integer.valueOf(field[index.inc()]))
                    .name(field[index.inc()])
                    .build();
            return Optional.of(client);

        } catch (Exception e) {
            log.error("Falha ao criar cliente - Campos: {}", field, e);
            return Optional.empty();
        }
    }

    static class Index {
        private int index;

        public static Index create() {
            return new Index();
        }

        Index() {
            index = 1;
        }

        public int inc() {
            return index++;
        }
    }
}
