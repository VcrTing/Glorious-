/*
 Navicat Premium Data Transfer

 Source Server         : 腾讯云
 Source Server Type    : MySQL
 Source Server Version : 80035 (8.0.35-0ubuntu0.22.04.1)
 Source Host           : 42.192.15.218:3306
 Source Schema         : glorious

 Target Server Type    : MySQL
 Target Server Version : 80035 (8.0.35-0ubuntu0.22.04.1)
 File Encoding         : 65001

 Date: 11/01/2024 20:47:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bas_storehouse
-- ----------------------------
DROP TABLE IF EXISTS `bas_storehouse`;
CREATE TABLE `bas_storehouse` (
  `name` varchar(250) DEFAULT NULL,
  `phone_no` varchar(60) DEFAULT NULL,
  `facebook` varchar(250) DEFAULT NULL,
  `contact_person` varchar(250) DEFAULT NULL,
  `address` varchar(250) DEFAULT NULL,
  `remark` varchar(250) DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `is_live` tinyint DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for bas_supplier
-- ----------------------------
DROP TABLE IF EXISTS `bas_supplier`;
CREATE TABLE `bas_supplier` (
  `supplier_id` varchar(250) DEFAULT NULL,
  `email` varchar(250) DEFAULT NULL,
  `name` varchar(250) DEFAULT NULL,
  `phone_no` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `remarks` varchar(250) DEFAULT NULL,
  `create_date` timestamp NULL DEFAULT NULL,
  `contact_person` varchar(250) DEFAULT NULL,
  `office_address` varchar(250) DEFAULT NULL,
  `factory_address` varchar(250) DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `is_live` tinyint DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for csm_custom_level
-- ----------------------------
DROP TABLE IF EXISTS `csm_custom_level`;
CREATE TABLE `csm_custom_level` (
  `name` varchar(250) DEFAULT NULL,
  `discount` varchar(60) DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `is_live` tinyint DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for csm_customer
-- ----------------------------
DROP TABLE IF EXISTS `csm_customer`;
CREATE TABLE `csm_customer` (
  `name` varchar(250) DEFAULT NULL,
  `email` varchar(250) DEFAULT NULL,
  `phone_no` varchar(60) DEFAULT NULL,
  `create_date` timestamp NULL DEFAULT NULL,
  `birthdate` timestamp NULL DEFAULT NULL,
  `sex` varchar(250) DEFAULT NULL,
  `member_id` varchar(250) DEFAULT NULL,
  `address` varchar(250) DEFAULT NULL,
  `remarks` varchar(250) DEFAULT NULL,
  `customer_level_sql_id` bigint DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `is_live` tinyint DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for odr_order
-- ----------------------------
DROP TABLE IF EXISTS `odr_order`;
CREATE TABLE `odr_order` (
  `order_id` varchar(250) DEFAULT NULL,
  `order_date` timestamp NULL DEFAULT NULL,
  `status` varchar(250) DEFAULT NULL,
  `total_price` decimal(11,2) DEFAULT NULL,
  `total_profit` decimal(11,2) DEFAULT NULL,
  `storehouse_sql_id` bigint DEFAULT NULL,
  `cashier_sql_id` bigint DEFAULT NULL,
  `customer_sql_id` bigint DEFAULT NULL,
  `customer_level_sql_id` bigint DEFAULT NULL,
  `discount` text,
  `payment_method` text,
  `ordered_product` text,
  `refunded_remarks` text,
  `ordered_product_after_refund` text,
  `total_price_after_refund` decimal(11,2) DEFAULT NULL,
  `total_profit_after_refund` decimal(11,2) DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `is_live` tinyint DEFAULT '1',
  `version` int DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for odr_refunded_record
-- ----------------------------
DROP TABLE IF EXISTS `odr_refunded_record`;
CREATE TABLE `odr_refunded_record` (
  `order_sql_id` bigint DEFAULT NULL,
  `profit_sql_id` bigint DEFAULT NULL,
  `storehouse_sql_id` bigint DEFAULT NULL,
  `refunded_remarks` text,
  `refunded_price` decimal(11,2) DEFAULT NULL,
  `refunded_quantity` int DEFAULT NULL,
  `refunded_info` text,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `is_live` tinyint DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for pod_label
-- ----------------------------
DROP TABLE IF EXISTS `pod_label`;
CREATE TABLE `pod_label` (
  `name` varchar(250) DEFAULT NULL,
  `is_show` int DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `is_live` tinyint DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for pod_product
-- ----------------------------
DROP TABLE IF EXISTS `pod_product`;
CREATE TABLE `pod_product` (
  `product_id` varchar(250) DEFAULT NULL,
  `create_date` timestamp NULL DEFAULT NULL,
  `name` varchar(250) DEFAULT NULL,
  `remarks` varchar(250) DEFAULT NULL,
  `new_restock_date` timestamp NULL DEFAULT NULL,
  `new_restock_price` decimal(11,2) DEFAULT NULL,
  `new_selling_price` decimal(11,2) DEFAULT NULL,
  `new_lowest_price` decimal(11,2) DEFAULT NULL,
  `new_supplier_sql_id` bigint DEFAULT NULL,
  `new_supplier` varchar(250) DEFAULT NULL,
  `labels` text,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `is_live` tinyint DEFAULT '1',
  `average_restock_price` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for pod_product_of_variation_and_storehouse
-- ----------------------------
DROP TABLE IF EXISTS `pod_product_of_variation_and_storehouse`;
CREATE TABLE `pod_product_of_variation_and_storehouse` (
  `quantity` int DEFAULT NULL,
  `product_sql_id` bigint DEFAULT NULL,
  `variation_sql_id` bigint DEFAULT NULL,
  `storehouse_sql_id` bigint DEFAULT NULL,
  `version` int DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `is_live` tinyint DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for pod_variation
-- ----------------------------
DROP TABLE IF EXISTS `pod_variation`;
CREATE TABLE `pod_variation` (
  `name` varchar(250) DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `is_live` tinyint DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for sok_broken
-- ----------------------------
DROP TABLE IF EXISTS `sok_broken`;
CREATE TABLE `sok_broken` (
  `date` timestamp NULL DEFAULT NULL,
  `quantity` tinyint DEFAULT NULL,
  `remarks` varchar(250) DEFAULT NULL,
  `storehouse_sql_id` bigint DEFAULT NULL,
  `product_sql_id` bigint DEFAULT NULL,
  `variation_sql_id` bigint DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `is_live` tinyint DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for sok_invoice
-- ----------------------------
DROP TABLE IF EXISTS `sok_invoice`;
CREATE TABLE `sok_invoice` (
  `total_price` decimal(11,2) DEFAULT NULL,
  `total_quantity` int DEFAULT NULL,
  `supplier_sql_id` bigint DEFAULT NULL,
  `storehouse_sql_id` bigint DEFAULT NULL,
  `date` timestamp NULL DEFAULT NULL,
  `invoice_id` varchar(250) DEFAULT NULL,
  `invoice_address` varchar(250) DEFAULT NULL,
  `delivery_address` varchar(250) DEFAULT NULL,
  `restocks` text,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `is_live` tinyint DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for sok_restock
-- ----------------------------
DROP TABLE IF EXISTS `sok_restock`;
CREATE TABLE `sok_restock` (
  `restock_date` timestamp NULL DEFAULT NULL,
  `restock_price` decimal(11,2) DEFAULT NULL,
  `lowest_price` decimal(11,2) DEFAULT NULL,
  `selling_price` decimal(11,2) DEFAULT NULL,
  `invoice_sql_id` bigint DEFAULT NULL,
  `product_sql_id` bigint DEFAULT NULL,
  `supplier_sql_id` bigint DEFAULT NULL,
  `storehouse_sql_id` bigint DEFAULT NULL,
  `total_quantity` int DEFAULT NULL,
  `total_amount` decimal(11,2) DEFAULT NULL,
  `restock_distribute` text,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `is_live` tinyint DEFAULT '1',
  `average_restock_price` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `username` varchar(250) DEFAULT NULL,
  `email` varchar(250) DEFAULT NULL,
  `password` varchar(250) DEFAULT NULL,
  `name` varchar(250) DEFAULT NULL,
  `phone_no` varchar(60) DEFAULT NULL,
  `storehouse_sql_id` bigint DEFAULT NULL,
  `is_admin` tinyint DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `is_live` tinyint DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

SET FOREIGN_KEY_CHECKS = 1;
