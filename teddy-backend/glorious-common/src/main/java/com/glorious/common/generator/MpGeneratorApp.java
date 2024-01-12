package com.glorious.common.generator;

import com.baomidou.mybatisplus.annotation.TableName;
import com.glorious.model.entity.base.Storehouse;
import com.glorious.model.entity.base.Supplier;
import com.glorious.model.entity.custom.Customer;
import com.glorious.model.entity.custom.CustomerLevel;
import com.glorious.model.entity.order.Order;
import com.glorious.model.entity.order.RefundedRecord;
import com.glorious.model.entity.product.*;
import com.glorious.model.entity.restock.Broken;
import com.glorious.model.entity.restock.Invoice;
import com.glorious.model.entity.restock.Restock;
import com.glorious.model.entity.sys.User;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class MpGeneratorApp {

    static List<Class> classes = Arrays.asList(
        // Supplier.class,
            // Storehouse.class,
            // Customer.class
            // CustomerLevel.class
            Order.class
            // RefundedRecord.class
            // BrokenRecord.class
            // Label.class
            // ProductOfVariationAndStorehouse.class
            // Variation.class
            // User.class
            // Broken.class
            // Invoice.class
            // Restock.class
    );

    static String output = "~/Desktop";

    public static void main(String[] args) {

        StringBuilder sb = new StringBuilder();
        classes.forEach(entity -> {
            Optional<String> ops = Optional.ofNullable((TableName) entity.getAnnotation(TableName.class)).map(TableName::value);

            String sql = QSqlGeneratorUtil.generatorSql(
                    entity,
                    ops.orElse(QSqlGeneratorUtil.DEF_TABLE_NAME),
                    "id",
                    "is_live",
                    "1");
            sb.append(sql);
            sb.append("\n");
        });
        System.out.println(sb.toString());
    }
}
