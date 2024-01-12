package com.glorious.common.generator;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Optional;

public class QSqlGeneratorUtil {

    public final static String DEF_TABLE_NAME = "undefined_table";

    static Boolean isShortStringField(Field field) {
        String name = field.getName().toLowerCase();
        return name.contains("phone") || name.contains("discount");
    }
    static Boolean isJsonField(Field field) {
        String name = field.getName().toLowerCase();
        return name.contains("json");
    }

    static HashMap<Class<?>, String> getTypeMap() {
        HashMap<Class<?>, String> res = new HashMap<>();

        res.put(Boolean.class, "tinyint(2)");
        res.put(Short.class, "tinyint(4)");

        res.put(Long.class, "bigint(20)");
        res.put(Integer.class, "int(9)");

        res.put(Date.class, "timestamp");
        res.put(BigDecimal.class, "decimal(11, 2)");

        res.put(String.class, "varchar(250)");
        res.put(Object.class, "varchar(250)");

        return res;
    }

    public static String dropTable(String tabName) {
        return "DROP TABLE IF EXISTS " + tabName + ";";
    }

    public static String createTable(String tabName) {
        return "CREATE TABLE " + tabName;
    }

    public static String javaTypeToMysqlType(Field field) {
        Class<?> clazz = field.getType();
        String res;
        if (isShortStringField(field)) {
            res = "varchar(60)";
        }
        else if (isJsonField(field)) {
            res = "text";
        }
        else {
            HashMap<Class<?>, String> src = getTypeMap();
            Optional<Class<?>> ops = src.keySet().stream().filter(s -> ((s.equals(clazz) || s.getName().equals(clazz.getName())))).findFirst();
            res = src.get(ops.orElse(Object.class));
        }
        return res != null ? res : "varchar(250)";
    }

    public static String generatorSql(Class<?> clazz, String tabName, String idName, String logicTrashName, String logicTrashValue) {

        if (tabName == null) tabName = DEF_TABLE_NAME;
        Field[] fields = clazz.getDeclaredFields();
        StringBuilder sb = new StringBuilder();

        sb.append(dropTable(tabName)).append("\r\n");
        sb.append(createTable(tabName)).append(" (\r\n");

        for (int i= 0; i< fields.length; i++ ) {
            Field field = fields[i];
            // 去掉 id
            if (!field.getName().equalsIgnoreCase(idName)) {
                sb.append("\t");
                sb.append(field.getName()).append(" ").append(javaTypeToMysqlType(field));
                sb.append(",\n");
            }
        }

        // 加入 ID
        sb.append("\tid bigint(20) PRIMARY KEY NOT NULL AUTO_INCREMENT");
        // 加入 逻辑删除
        if (logicTrashName != null) sb.append(",\n\t").append(logicTrashName).append(" tinyint(3) DEFAULT ").append(logicTrashValue);

        sb.append("\n) ");
        sb.append(createTableSqlSuffix());

        return sb.toString();
    }

    static String createTableSqlSuffix() {
        return "ENGINE = INNODB DEFAULT CHARSET = utf8mb4";
    }
}
