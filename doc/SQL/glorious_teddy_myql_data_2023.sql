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

 Date: 11/01/2024 20:48:13
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
-- Records of bas_storehouse
-- ----------------------------
BEGIN;
INSERT INTO `bas_storehouse` (`name`, `phone_no`, `facebook`, `contact_person`, `address`, `remark`, `id`, `is_live`) VALUES ('天虹 1', '44443333', 'BBBB2222', '掌權者', '這裡是倉庫地址', '這裡是備註 / 條款', 1, 1);
INSERT INTO `bas_storehouse` (`name`, `phone_no`, `facebook`, `contact_person`, `address`, `remark`, `id`, `is_live`) VALUES ('沃尔玛', '33334444', '12312', '丽莲', '撒打算打算大大说的', '请问请问请问', 2, 1);
COMMIT;

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
-- Records of bas_supplier
-- ----------------------------
BEGIN;
INSERT INTO `bas_supplier` (`supplier_id`, `email`, `name`, `phone_no`, `remarks`, `create_date`, `contact_person`, `office_address`, `factory_address`, `id`, `is_live`) VALUES ('HK0001', 'vcrting@123.com', '香港公司 2', '13576639988', '這裡是香港公司香港公司香港公司 x2', '2023-11-10 08:00:00', '流星 x2', '這裡是供應商公司的地址 x2', '這裡是供應商工廠的地址 x2', 1, 1);
INSERT INTO `bas_supplier` (`supplier_id`, `email`, `name`, `phone_no`, `remarks`, `create_date`, `contact_person`, `office_address`, `factory_address`, `id`, `is_live`) VALUES ('PD0001', 'vcrting@123.com', '澳门公司', '13577669988', '這裡是香港公司香港公司香港公司', '2023-11-30 08:00:00', '流星', '這裡是供應商公司的地址', '這裡是供應商工廠的地址', 2, 1);
COMMIT;

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
-- Records of csm_custom_level
-- ----------------------------
BEGIN;
INSERT INTO `csm_custom_level` (`name`, `discount`, `id`, `is_live`) VALUES ('一級會員', '0.92', 1, 1);
INSERT INTO `csm_custom_level` (`name`, `discount`, `id`, `is_live`) VALUES ('普通會員', '1', 2, 1);
INSERT INTO `csm_custom_level` (`name`, `discount`, `id`, `is_live`) VALUES ('耳机会员 2', '0.3', 3, 0);
COMMIT;

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
-- Records of csm_customer
-- ----------------------------
BEGIN;
INSERT INTO `csm_customer` (`name`, `email`, `phone_no`, `create_date`, `birthdate`, `sex`, `member_id`, `address`, `remarks`, `customer_level_sql_id`, `id`, `is_live`) VALUES ('穹天使 x2', 'qiong@163.com', '111111', '2023-11-01 08:00:00', '2000-01-12 08:00:00', 'F', '8860000001', 'XXXXXXXXXXXX', '', 1, 1, 1);
INSERT INTO `csm_customer` (`name`, `email`, `phone_no`, `create_date`, `birthdate`, `sex`, `member_id`, `address`, `remarks`, `customer_level_sql_id`, `id`, `is_live`) VALUES ('劉漪琳', 'lucy@163.com', '998883', '2023-11-10 08:00:00', '2000-01-01 08:00:00', 'F', '8860000002', 'AAAAA', 'XXXXXXXXXXXX', 2, 2, 1);
INSERT INTO `csm_customer` (`name`, `email`, `phone_no`, `create_date`, `birthdate`, `sex`, `member_id`, `address`, `remarks`, `customer_level_sql_id`, `id`, `is_live`) VALUES ('劉漪琳', 'qiong@163.com', '998883', '2023-11-10 08:00:00', '2000-01-01 08:00:00', 'F', '8860000003', 'AAAAA', 'XXXXXXXXXXXX', 1, 3, 1);
COMMIT;

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
-- Records of odr_order
-- ----------------------------
BEGIN;
INSERT INTO `odr_order` (`order_id`, `order_date`, `status`, `total_price`, `total_profit`, `storehouse_sql_id`, `cashier_sql_id`, `customer_sql_id`, `customer_level_sql_id`, `discount`, `payment_method`, `ordered_product`, `refunded_remarks`, `ordered_product_after_refund`, `total_price_after_refund`, `total_profit_after_refund`, `id`, `is_live`, `version`) VALUES ('XD2024010211378', '2024-01-02 11:37:03', 'refunded', 40.00, 20.00, 2, 1, NULL, NULL, '[]', '[{\"name\":\"微信支付\",\"price\":40}]', '[{\"product_sql_id\":2,\"variation_sql_id\":2,\"average_price\":10,\"selling_price\":20,\"quantity\":1,\"discount_deduction\":0,\"total_profit\":10,\"total_price\":20},{\"product_sql_id\":2,\"variation_sql_id\":3,\"average_price\":10,\"selling_price\":20,\"quantity\":1,\"discount_deduction\":0,\"total_profit\":10,\"total_price\":20}]', '[{\"refund_total_price\":80,\"refund_total_quantity\":4,\"refund_time\":\"2024-01-02 17:18\"}]', NULL, 40.00, 20.00, 1, 1, 1);
INSERT INTO `odr_order` (`order_id`, `order_date`, `status`, `total_price`, `total_profit`, `storehouse_sql_id`, `cashier_sql_id`, `customer_sql_id`, `customer_level_sql_id`, `discount`, `payment_method`, `ordered_product`, `refunded_remarks`, `ordered_product_after_refund`, `total_price_after_refund`, `total_profit_after_refund`, `id`, `is_live`, `version`) VALUES ('XD20240102142962', '2024-01-02 14:29:02', 'refunded', 32.00, 12.00, 2, 1, 3, 1, '[{\"type\":\"會員優惠\",\"discount_deduction\":3.2},{\"type\":\"全單折扣\",\"discount_deduction\":3.68},{\"type\":\"全單減價\",\"discount_deduction\":1.12}]', '[{\"name\":\"支付寶\",\"price\":20},{\"name\":\"微信支付\",\"price\":12}]', '[{\"product_sql_id\":2,\"variation_sql_id\":2,\"average_price\":10,\"selling_price\":20,\"quantity\":1,\"discount_deduction\":0,\"total_profit\":10,\"total_price\":20},{\"product_sql_id\":2,\"variation_sql_id\":3,\"average_price\":10,\"selling_price\":20,\"quantity\":1,\"discount_deduction\":0,\"total_profit\":10,\"total_price\":20}]', '[{\"refund_total_price\":40,\"refund_total_quantity\":2,\"refund_time\":\"2024-01-02 18:20\"}]', NULL, 32.00, 12.00, 2, 1, 1);
INSERT INTO `odr_order` (`order_id`, `order_date`, `status`, `total_price`, `total_profit`, `storehouse_sql_id`, `cashier_sql_id`, `customer_sql_id`, `customer_level_sql_id`, `discount`, `payment_method`, `ordered_product`, `refunded_remarks`, `ordered_product_after_refund`, `total_price_after_refund`, `total_profit_after_refund`, `id`, `is_live`, `version`) VALUES ('XD20240102180384', '2024-01-02 18:03:30', 'refunded', 20.00, 10.00, 2, 1, NULL, NULL, '[]', '[{\"name\":\"銀聯\",\"price\":20}]', '[{\"product_sql_id\":2,\"variation_sql_id\":2,\"average_price\":10,\"selling_price\":20,\"quantity\":1,\"discount_deduction\":0,\"total_profit\":10,\"total_price\":20}]', '[{\"refund_total_price\":20,\"refund_total_quantity\":1,\"refund_time\":\"2024-01-02 18:19\"}]', NULL, 20.00, 10.00, 3, 1, 1);
INSERT INTO `odr_order` (`order_id`, `order_date`, `status`, `total_price`, `total_profit`, `storehouse_sql_id`, `cashier_sql_id`, `customer_sql_id`, `customer_level_sql_id`, `discount`, `payment_method`, `ordered_product`, `refunded_remarks`, `ordered_product_after_refund`, `total_price_after_refund`, `total_profit_after_refund`, `id`, `is_live`, `version`) VALUES ('XD20240102180542', '2024-01-02 18:05:34', 'refunded', 200.00, -10.00, 2, 1, NULL, NULL, '[{\"type\":\"全單減價\",\"discount_deduction\":20}]', '[{\"name\":\"微信支付\",\"price\":200}]', '[{\"product_sql_id\":4,\"variation_sql_id\":6,\"average_price\":100,\"selling_price\":100,\"quantity\":2,\"discount_deduction\":0,\"total_profit\":0,\"total_price\":200},{\"product_sql_id\":2,\"variation_sql_id\":2,\"average_price\":10,\"selling_price\":20,\"quantity\":1,\"discount_deduction\":0,\"total_profit\":10,\"total_price\":20}]', '[{\"refund_total_price\":440,\"refund_total_quantity\":6,\"refund_time\":\"2024-01-02 18:20\"}]', NULL, 200.00, -10.00, 4, 1, 1);
INSERT INTO `odr_order` (`order_id`, `order_date`, `status`, `total_price`, `total_profit`, `storehouse_sql_id`, `cashier_sql_id`, `customer_sql_id`, `customer_level_sql_id`, `discount`, `payment_method`, `ordered_product`, `refunded_remarks`, `ordered_product_after_refund`, `total_price_after_refund`, `total_profit_after_refund`, `id`, `is_live`, `version`) VALUES ('XD20240102180674', '2024-01-02 18:06:17', 'paid', 100.00, 0.00, 2, 1, NULL, NULL, '[]', '[{\"name\":\"其他付款方式\",\"price\":100}]', '[{\"product_sql_id\":4,\"variation_sql_id\":7,\"average_price\":100,\"selling_price\":100,\"quantity\":1,\"discount_deduction\":0,\"total_profit\":0,\"total_price\":100}]', NULL, NULL, 100.00, 0.00, 5, 1, 1);
INSERT INTO `odr_order` (`order_id`, `order_date`, `status`, `total_price`, `total_profit`, `storehouse_sql_id`, `cashier_sql_id`, `customer_sql_id`, `customer_level_sql_id`, `discount`, `payment_method`, `ordered_product`, `refunded_remarks`, `ordered_product_after_refund`, `total_price_after_refund`, `total_profit_after_refund`, `id`, `is_live`, `version`) VALUES ('XD20240102180614', '2024-01-02 18:06:40', 'paid', 184.00, -16.00, 2, 1, 1, 1, '[{\"type\":\"會員優惠\",\"discount_deduction\":16}]', '[{\"name\":\"EPS 支付\",\"price\":184}]', '[{\"product_sql_id\":4,\"variation_sql_id\":7,\"average_price\":100,\"selling_price\":100,\"quantity\":2,\"discount_deduction\":0,\"total_profit\":0,\"total_price\":200}]', NULL, NULL, 184.00, -16.00, 6, 1, 1);
INSERT INTO `odr_order` (`order_id`, `order_date`, `status`, `total_price`, `total_profit`, `storehouse_sql_id`, `cashier_sql_id`, `customer_sql_id`, `customer_level_sql_id`, `discount`, `payment_method`, `ordered_product`, `refunded_remarks`, `ordered_product_after_refund`, `total_price_after_refund`, `total_profit_after_refund`, `id`, `is_live`, `version`) VALUES ('XD20240102180781', '2024-01-02 18:07:36', 'paid', 200.00, 0.00, 2, 1, NULL, NULL, '[]', '[{\"name\":\"PayMe支付\",\"price\":100},{\"name\":\"現金支付\",\"price\":100}]', '[{\"product_sql_id\":4,\"variation_sql_id\":6,\"average_price\":100,\"selling_price\":100,\"quantity\":2,\"discount_deduction\":0,\"total_profit\":0,\"total_price\":200}]', NULL, NULL, 200.00, 0.00, 7, 1, 1);
INSERT INTO `odr_order` (`order_id`, `order_date`, `status`, `total_price`, `total_profit`, `storehouse_sql_id`, `cashier_sql_id`, `customer_sql_id`, `customer_level_sql_id`, `discount`, `payment_method`, `ordered_product`, `refunded_remarks`, `ordered_product_after_refund`, `total_price_after_refund`, `total_profit_after_refund`, `id`, `is_live`, `version`) VALUES ('XD20240102182967', '2024-01-02 18:29:02', 'refunded', 200.00, 0.00, 2, 1, NULL, NULL, '[]', '[{\"name\":\"銀聯\",\"price\":200}]', '[{\"product_sql_id\":4,\"variation_sql_id\":6,\"average_price\":100,\"selling_price\":100,\"quantity\":1,\"discount_deduction\":0,\"total_profit\":0,\"total_price\":100},{\"product_sql_id\":4,\"variation_sql_id\":7,\"average_price\":100,\"selling_price\":100,\"quantity\":1,\"discount_deduction\":0,\"total_profit\":0,\"total_price\":100}]', '[{\"refund_total_price\":400,\"refund_total_quantity\":4,\"refund_time\":\"2024-01-02 18:29\"}]', NULL, 200.00, 0.00, 8, 1, 1);
INSERT INTO `odr_order` (`order_id`, `order_date`, `status`, `total_price`, `total_profit`, `storehouse_sql_id`, `cashier_sql_id`, `customer_sql_id`, `customer_level_sql_id`, `discount`, `payment_method`, `ordered_product`, `refunded_remarks`, `ordered_product_after_refund`, `total_price_after_refund`, `total_profit_after_refund`, `id`, `is_live`, `version`) VALUES ('XD20240102183060', '2024-01-02 18:30:12', 'refunded', 120.00, 10.00, 2, 1, NULL, NULL, '[]', '[{\"name\":\"銀聯\",\"price\":120}]', '[{\"product_sql_id\":4,\"variation_sql_id\":7,\"average_price\":100,\"selling_price\":100,\"quantity\":1,\"discount_deduction\":0,\"total_profit\":0,\"total_price\":100},{\"product_sql_id\":2,\"variation_sql_id\":2,\"average_price\":10,\"selling_price\":20,\"quantity\":1,\"discount_deduction\":0,\"total_profit\":10,\"total_price\":20}]', '[{\"refund_total_price\":240,\"refund_total_quantity\":4,\"refund_time\":\"2024-01-02 18:31\"}]', NULL, 120.00, 10.00, 9, 1, 1);
INSERT INTO `odr_order` (`order_id`, `order_date`, `status`, `total_price`, `total_profit`, `storehouse_sql_id`, `cashier_sql_id`, `customer_sql_id`, `customer_level_sql_id`, `discount`, `payment_method`, `ordered_product`, `refunded_remarks`, `ordered_product_after_refund`, `total_price_after_refund`, `total_profit_after_refund`, `id`, `is_live`, `version`) VALUES ('XD2024010218326', '2024-01-02 18:32:57', 'refunded', 120.00, 10.00, 2, 1, NULL, NULL, '[]', '[{\"name\":\"EPS 支付\",\"price\":120}]', '[{\"product_sql_id\":4,\"variation_sql_id\":7,\"average_price\":100,\"selling_price\":100,\"quantity\":1,\"discount_deduction\":0,\"total_profit\":0,\"total_price\":100},{\"product_sql_id\":2,\"variation_sql_id\":2,\"average_price\":10,\"selling_price\":20,\"quantity\":1,\"discount_deduction\":0,\"total_profit\":10,\"total_price\":20}]', '[{\"refund_total_price\":240,\"refund_total_quantity\":4,\"refund_time\":\"2024-01-02 18:37\"}]', NULL, 120.00, 10.00, 10, 1, 1);
INSERT INTO `odr_order` (`order_id`, `order_date`, `status`, `total_price`, `total_profit`, `storehouse_sql_id`, `cashier_sql_id`, `customer_sql_id`, `customer_level_sql_id`, `discount`, `payment_method`, `ordered_product`, `refunded_remarks`, `ordered_product_after_refund`, `total_price_after_refund`, `total_profit_after_refund`, `id`, `is_live`, `version`) VALUES ('XD20240104142518', '2024-01-04 14:25:39', 'paid', 30.00, 10.00, 1, 2, NULL, NULL, '[{\"type\":\"全單折扣\",\"discount_deduction\":2},{\"type\":\"全單減價\",\"discount_deduction\":8}]', '[{\"name\":\"EPS 支付\",\"price\":30}]', '[{\"product_sql_id\":2,\"variation_sql_id\":2,\"average_price\":10,\"selling_price\":20,\"quantity\":2,\"discount_deduction\":0,\"total_profit\":20,\"total_price\":40}]', NULL, NULL, 30.00, 10.00, 11, 1, 1);
COMMIT;

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
-- Records of odr_refunded_record
-- ----------------------------
BEGIN;
INSERT INTO `odr_refunded_record` (`order_sql_id`, `profit_sql_id`, `storehouse_sql_id`, `refunded_remarks`, `refunded_price`, `refunded_quantity`, `refunded_info`, `id`, `is_live`) VALUES (1, NULL, 2, '[{\"refund_total_price\":80,\"refund_total_quantity\":4,\"refund_time\":\"2024-01-02 15:46\"}]', 80.00, 4, '[{\"product\":2,\"variation\":2,\"refunded_quantity\":1,\"refunded_price\":20},{\"product\":2,\"variation\":2,\"refunded_quantity\":1,\"refunded_price\":20},{\"product\":2,\"variation\":3,\"refunded_quantity\":1,\"refunded_price\":20},{\"product\":2,\"variation\":3,\"refunded_quantity\":1,\"refunded_price\":20}]', 1, 1);
INSERT INTO `odr_refunded_record` (`order_sql_id`, `profit_sql_id`, `storehouse_sql_id`, `refunded_remarks`, `refunded_price`, `refunded_quantity`, `refunded_info`, `id`, `is_live`) VALUES (1, NULL, 2, '[{\"refund_total_price\":80,\"refund_total_quantity\":4,\"refund_time\":\"2024-01-02 17:18\"}]', 80.00, 4, '[{\"product\":2,\"variation\":2,\"refunded_quantity\":1,\"refunded_price\":20},{\"product\":2,\"variation\":2,\"refunded_quantity\":1,\"refunded_price\":20},{\"product\":2,\"variation\":3,\"refunded_quantity\":1,\"refunded_price\":20},{\"product\":2,\"variation\":3,\"refunded_quantity\":1,\"refunded_price\":20}]', 2, 1);
INSERT INTO `odr_refunded_record` (`order_sql_id`, `profit_sql_id`, `storehouse_sql_id`, `refunded_remarks`, `refunded_price`, `refunded_quantity`, `refunded_info`, `id`, `is_live`) VALUES (3, NULL, 2, '[{\"refund_total_price\":20,\"refund_total_quantity\":1,\"refund_time\":\"2024-01-02 18:08\"}]', 20.00, 1, '[{\"product\":2,\"variation\":2,\"refunded_quantity\":1,\"refunded_price\":20}]', 3, 1);
INSERT INTO `odr_refunded_record` (`order_sql_id`, `profit_sql_id`, `storehouse_sql_id`, `refunded_remarks`, `refunded_price`, `refunded_quantity`, `refunded_info`, `id`, `is_live`) VALUES (3, NULL, 2, '[{\"refund_total_price\":20,\"refund_total_quantity\":1,\"refund_time\":\"2024-01-02 18:19\"}]', 20.00, 1, '[{\"product\":2,\"variation\":2,\"refunded_quantity\":1,\"refunded_price\":20}]', 4, 1);
INSERT INTO `odr_refunded_record` (`order_sql_id`, `profit_sql_id`, `storehouse_sql_id`, `refunded_remarks`, `refunded_price`, `refunded_quantity`, `refunded_info`, `id`, `is_live`) VALUES (4, NULL, 2, '[{\"refund_total_price\":440,\"refund_total_quantity\":6,\"refund_time\":\"2024-01-02 18:20\"}]', 440.00, 6, '[{\"product\":4,\"variation\":6,\"refunded_quantity\":2,\"refunded_price\":200},{\"product\":4,\"variation\":6,\"refunded_quantity\":2,\"refunded_price\":200},{\"product\":2,\"variation\":2,\"refunded_quantity\":1,\"refunded_price\":20},{\"product\":2,\"variation\":2,\"refunded_quantity\":1,\"refunded_price\":20}]', 5, 1);
INSERT INTO `odr_refunded_record` (`order_sql_id`, `profit_sql_id`, `storehouse_sql_id`, `refunded_remarks`, `refunded_price`, `refunded_quantity`, `refunded_info`, `id`, `is_live`) VALUES (2, NULL, 2, '[{\"refund_total_price\":40,\"refund_total_quantity\":2,\"refund_time\":\"2024-01-02 18:20\"}]', 40.00, 2, '[{\"product\":2,\"variation\":2,\"refunded_quantity\":1,\"refunded_price\":20},{\"product\":2,\"variation\":3,\"refunded_quantity\":1,\"refunded_price\":20}]', 6, 1);
INSERT INTO `odr_refunded_record` (`order_sql_id`, `profit_sql_id`, `storehouse_sql_id`, `refunded_remarks`, `refunded_price`, `refunded_quantity`, `refunded_info`, `id`, `is_live`) VALUES (8, NULL, 2, '[{\"refund_total_price\":400,\"refund_total_quantity\":4,\"refund_time\":\"2024-01-02 18:29\"}]', 400.00, 4, '[{\"product\":4,\"variation\":6,\"refunded_quantity\":1,\"refunded_price\":100},{\"product\":4,\"variation\":6,\"refunded_quantity\":1,\"refunded_price\":100},{\"product\":4,\"variation\":7,\"refunded_quantity\":1,\"refunded_price\":100},{\"product\":4,\"variation\":7,\"refunded_quantity\":1,\"refunded_price\":100}]', 7, 1);
INSERT INTO `odr_refunded_record` (`order_sql_id`, `profit_sql_id`, `storehouse_sql_id`, `refunded_remarks`, `refunded_price`, `refunded_quantity`, `refunded_info`, `id`, `is_live`) VALUES (9, NULL, 2, '[{\"refund_total_price\":240,\"refund_total_quantity\":4,\"refund_time\":\"2024-01-02 18:31\"}]', 240.00, 4, '[{\"product\":4,\"variation\":7,\"refunded_quantity\":1,\"refunded_price\":100},{\"product\":4,\"variation\":7,\"refunded_quantity\":1,\"refunded_price\":100},{\"product\":2,\"variation\":2,\"refunded_quantity\":1,\"refunded_price\":20},{\"product\":2,\"variation\":2,\"refunded_quantity\":1,\"refunded_price\":20}]', 8, 1);
INSERT INTO `odr_refunded_record` (`order_sql_id`, `profit_sql_id`, `storehouse_sql_id`, `refunded_remarks`, `refunded_price`, `refunded_quantity`, `refunded_info`, `id`, `is_live`) VALUES (10, NULL, 2, '[{\"refund_total_price\":240,\"refund_total_quantity\":4,\"refund_time\":\"2024-01-02 18:37\"}]', 240.00, 4, '[{\"product\":4,\"variation\":7,\"refunded_quantity\":1,\"refunded_price\":100},{\"product\":4,\"variation\":7,\"refunded_quantity\":1,\"refunded_price\":100},{\"product\":2,\"variation\":2,\"refunded_quantity\":1,\"refunded_price\":20},{\"product\":2,\"variation\":2,\"refunded_quantity\":1,\"refunded_price\":20}]', 9, 1);
COMMIT;

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
-- Records of pod_label
-- ----------------------------
BEGIN;
INSERT INTO `pod_label` (`name`, `is_show`, `id`, `is_live`) VALUES ('兔子', 1, 1, 1);
INSERT INTO `pod_label` (`name`, `is_show`, `id`, `is_live`) VALUES ('猫咪', 1, 2, 1);
COMMIT;

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
-- Records of pod_product
-- ----------------------------
BEGIN;
INSERT INTO `pod_product` (`product_id`, `create_date`, `name`, `remarks`, `new_restock_date`, `new_restock_price`, `new_selling_price`, `new_lowest_price`, `new_supplier_sql_id`, `new_supplier`, `labels`, `id`, `is_live`, `average_restock_price`) VALUES ('PD-001', '2023-12-02 08:00:00', '老鼠叉子x2', '[{\"content\":\"产品备注\"}]', '2024-01-01 19:15:51', 10.00, 20.00, 1.00, 1, '香港公司 2', '[\"ID_2_\",\"ID_1_\"]', 2, 1, NULL);
INSERT INTO `pod_product` (`product_id`, `create_date`, `name`, `remarks`, `new_restock_date`, `new_restock_price`, `new_selling_price`, `new_lowest_price`, `new_supplier_sql_id`, `new_supplier`, `labels`, `id`, `is_live`, `average_restock_price`) VALUES ('PO-001', '2023-12-02 08:00:00', '老鹰', '[]', '2024-01-07 16:47:42', 15.00, 20.00, 10.00, 2, '澳门公司', '[\"ID_2_\"]', 3, 1, 15.00);
INSERT INTO `pod_product` (`product_id`, `create_date`, `name`, `remarks`, `new_restock_date`, `new_restock_price`, `new_selling_price`, `new_lowest_price`, `new_supplier_sql_id`, `new_supplier`, `labels`, `id`, `is_live`, `average_restock_price`) VALUES ('LK-001', '2024-01-05 08:00:00', '飞机', '[]', '2024-01-02 18:05:03', 100.00, 100.00, 90.00, 2, '澳门公司', '[\"ID_2_\",\"ID_1_\"]', 4, 1, NULL);
COMMIT;

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
-- Records of pod_product_of_variation_and_storehouse
-- ----------------------------
BEGIN;
INSERT INTO `pod_product_of_variation_and_storehouse` (`quantity`, `product_sql_id`, `variation_sql_id`, `storehouse_sql_id`, `version`, `id`, `is_live`) VALUES (0, 1, 1, 1, 1, 1, 1);
INSERT INTO `pod_product_of_variation_and_storehouse` (`quantity`, `product_sql_id`, `variation_sql_id`, `storehouse_sql_id`, `version`, `id`, `is_live`) VALUES (28, 2, 2, 1, 1, 2, 1);
INSERT INTO `pod_product_of_variation_and_storehouse` (`quantity`, `product_sql_id`, `variation_sql_id`, `storehouse_sql_id`, `version`, `id`, `is_live`) VALUES (0, 2, 3, 1, 1, 3, 1);
INSERT INTO `pod_product_of_variation_and_storehouse` (`quantity`, `product_sql_id`, `variation_sql_id`, `storehouse_sql_id`, `version`, `id`, `is_live`) VALUES (0, 3, 4, 1, 1, 4, 1);
INSERT INTO `pod_product_of_variation_and_storehouse` (`quantity`, `product_sql_id`, `variation_sql_id`, `storehouse_sql_id`, `version`, `id`, `is_live`) VALUES (0, 3, 4, 2, 1, 5, 1);
INSERT INTO `pod_product_of_variation_and_storehouse` (`quantity`, `product_sql_id`, `variation_sql_id`, `storehouse_sql_id`, `version`, `id`, `is_live`) VALUES (10, 3, 5, 1, 1, 6, 1);
INSERT INTO `pod_product_of_variation_and_storehouse` (`quantity`, `product_sql_id`, `variation_sql_id`, `storehouse_sql_id`, `version`, `id`, `is_live`) VALUES (0, 3, 5, 2, 1, 7, 1);
INSERT INTO `pod_product_of_variation_and_storehouse` (`quantity`, `product_sql_id`, `variation_sql_id`, `storehouse_sql_id`, `version`, `id`, `is_live`) VALUES (11, 2, 2, 2, NULL, 8, 1);
INSERT INTO `pod_product_of_variation_and_storehouse` (`quantity`, `product_sql_id`, `variation_sql_id`, `storehouse_sql_id`, `version`, `id`, `is_live`) VALUES (6, 2, 3, 2, NULL, 9, 1);
INSERT INTO `pod_product_of_variation_and_storehouse` (`quantity`, `product_sql_id`, `variation_sql_id`, `storehouse_sql_id`, `version`, `id`, `is_live`) VALUES (0, 4, 6, 1, 1, 10, 1);
INSERT INTO `pod_product_of_variation_and_storehouse` (`quantity`, `product_sql_id`, `variation_sql_id`, `storehouse_sql_id`, `version`, `id`, `is_live`) VALUES (97, 4, 6, 2, 1, 11, 1);
INSERT INTO `pod_product_of_variation_and_storehouse` (`quantity`, `product_sql_id`, `variation_sql_id`, `storehouse_sql_id`, `version`, `id`, `is_live`) VALUES (0, 4, 7, 1, 1, 12, 1);
INSERT INTO `pod_product_of_variation_and_storehouse` (`quantity`, `product_sql_id`, `variation_sql_id`, `storehouse_sql_id`, `version`, `id`, `is_live`) VALUES (16, 4, 7, 2, 1, 13, 1);
COMMIT;

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
-- Records of pod_variation
-- ----------------------------
BEGIN;
INSERT INTO `pod_variation` (`name`, `id`, `is_live`) VALUES ('origin', 1, 1);
INSERT INTO `pod_variation` (`name`, `id`, `is_live`) VALUES ('GO', 2, 1);
INSERT INTO `pod_variation` (`name`, `id`, `is_live`) VALUES ('PINK', 3, 1);
INSERT INTO `pod_variation` (`name`, `id`, `is_live`) VALUES ('origin', 4, 1);
INSERT INTO `pod_variation` (`name`, `id`, `is_live`) VALUES ('BLUE', 5, 1);
INSERT INTO `pod_variation` (`name`, `id`, `is_live`) VALUES ('origin', 6, 1);
INSERT INTO `pod_variation` (`name`, `id`, `is_live`) VALUES ('BIG', 7, 1);
COMMIT;

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
-- Records of sok_broken
-- ----------------------------
BEGIN;
INSERT INTO `sok_broken` (`date`, `quantity`, `remarks`, `storehouse_sql_id`, `product_sql_id`, `variation_sql_id`, `id`, `is_live`) VALUES ('2023-12-01 08:00:00', 1, 'A', 1, 2, 2, 1, 1);
INSERT INTO `sok_broken` (`date`, `quantity`, `remarks`, `storehouse_sql_id`, `product_sql_id`, `variation_sql_id`, `id`, `is_live`) VALUES ('2023-12-02 08:00:00', 1, '', 1, 2, 2, 2, 0);
INSERT INTO `sok_broken` (`date`, `quantity`, `remarks`, `storehouse_sql_id`, `product_sql_id`, `variation_sql_id`, `id`, `is_live`) VALUES ('2024-01-03 08:00:00', 1, '', 2, 4, 6, 3, 1);
INSERT INTO `sok_broken` (`date`, `quantity`, `remarks`, `storehouse_sql_id`, `product_sql_id`, `variation_sql_id`, `id`, `is_live`) VALUES ('2024-01-05 08:00:00', 1, '', 2, 4, 7, 4, 1);
COMMIT;

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
-- Records of sok_invoice
-- ----------------------------
BEGIN;
INSERT INTO `sok_invoice` (`total_price`, `total_quantity`, `supplier_sql_id`, `storehouse_sql_id`, `date`, `invoice_id`, `invoice_address`, `delivery_address`, `restocks`, `id`, `is_live`) VALUES (1.00, 1, 2, 1, '2023-12-01 08:00:00', 'HK111', '', '', '[{\"name\":\"老鼠叉子\",\"product\":2,\"discount\":\"0\",\"lowest_price\":1,\"selling_price\":1,\"unit_price\":1,\"net_price\":1,\"total_quantity\":1,\"total_amount\":1,\"variations\":[{\"variation\":2,\"quantity\":1}]}]', 1, 1);
INSERT INTO `sok_invoice` (`total_price`, `total_quantity`, `supplier_sql_id`, `storehouse_sql_id`, `date`, `invoice_id`, `invoice_address`, `delivery_address`, `restocks`, `id`, `is_live`) VALUES (20.00, 10, 2, 1, '2023-12-02 08:00:00', 'KK1111', 'ADDR', 'ADDR2', '[{\"name\":\"老鼠叉子x2\",\"product\":2,\"discount\":\"1\",\"lowest_price\":1,\"selling_price\":2,\"unit_price\":2,\"net_price\":2,\"total_quantity\":10,\"total_amount\":20,\"variations\":[{\"variation\":2,\"quantity\":10}]}]', 2, 1);
INSERT INTO `sok_invoice` (`total_price`, `total_quantity`, `supplier_sql_id`, `storehouse_sql_id`, `date`, `invoice_id`, `invoice_address`, `delivery_address`, `restocks`, `id`, `is_live`) VALUES (9990.00, 10, 1, 2, '2023-12-02 08:00:00', 'KK2222', '', '', '[{\"name\":\"兔子2\",\"product\":1,\"discount\":\"0\",\"lowest_price\":12,\"selling_price\":9999,\"unit_price\":999,\"net_price\":999,\"total_quantity\":10,\"total_amount\":9990,\"variations\":[{\"variation\":1,\"quantity\":10}]}]', 3, 1);
INSERT INTO `sok_invoice` (`total_price`, `total_quantity`, `supplier_sql_id`, `storehouse_sql_id`, `date`, `invoice_id`, `invoice_address`, `delivery_address`, `restocks`, `id`, `is_live`) VALUES (100.00, 10, 2, 1, '2024-01-06 08:00:00', 'JK0011', '', '', '[{\"name\":\"老鼠叉子x2\",\"product\":2,\"discount\":\"0\",\"lowest_price\":1,\"selling_price\":20,\"unit_price\":10,\"net_price\":10,\"total_quantity\":10,\"total_amount\":100,\"variations\":[{\"variation\":2,\"quantity\":10}]}]', 4, 1);
INSERT INTO `sok_invoice` (`total_price`, `total_quantity`, `supplier_sql_id`, `storehouse_sql_id`, `date`, `invoice_id`, `invoice_address`, `delivery_address`, `restocks`, `id`, `is_live`) VALUES (47.50, 5, 2, 2, '2024-01-10 08:00:00', 'JK002', '', '', '[{\"name\":\"老鼠叉子x2\",\"product\":2,\"discount\":\"0.95\",\"lowest_price\":1,\"selling_price\":20,\"unit_price\":10,\"net_price\":9.5,\"total_quantity\":5,\"total_amount\":47.5,\"variations\":[{\"variation\":2,\"quantity\":5}]}]', 5, 1);
INSERT INTO `sok_invoice` (`total_price`, `total_quantity`, `supplier_sql_id`, `storehouse_sql_id`, `date`, `invoice_id`, `invoice_address`, `delivery_address`, `restocks`, `id`, `is_live`) VALUES (100.00, 10, 2, 1, '2024-01-06 08:00:00', 'JK003', '', '', '[{\"name\":\"老鼠叉子x2\",\"product\":2,\"discount\":\"0\",\"lowest_price\":1,\"selling_price\":20,\"unit_price\":10,\"net_price\":10,\"total_quantity\":10,\"total_amount\":100,\"variations\":[{\"variation\":2,\"quantity\":10}]}]', 6, 1);
INSERT INTO `sok_invoice` (`total_price`, `total_quantity`, `supplier_sql_id`, `storehouse_sql_id`, `date`, `invoice_id`, `invoice_address`, `delivery_address`, `restocks`, `id`, `is_live`) VALUES (100.00, 10, 1, 2, '2024-01-10 08:00:00', 'JK004', '', '', '[{\"name\":\"老鼠叉子x2\",\"product\":2,\"discount\":\"0\",\"lowest_price\":1,\"selling_price\":20,\"unit_price\":10,\"net_price\":10,\"total_quantity\":10,\"total_amount\":100,\"variations\":[{\"variation\":3,\"quantity\":9},{\"variation\":2,\"quantity\":1}]}]', 7, 1);
INSERT INTO `sok_invoice` (`total_price`, `total_quantity`, `supplier_sql_id`, `storehouse_sql_id`, `date`, `invoice_id`, `invoice_address`, `delivery_address`, `restocks`, `id`, `is_live`) VALUES (12000.00, 120, 2, 2, '2024-01-06 08:00:00', 'LK0001', '', '', '[{\"name\":\"飞机\",\"product\":4,\"discount\":\"0\",\"lowest_price\":90,\"selling_price\":100,\"unit_price\":100,\"net_price\":100,\"total_quantity\":120,\"total_amount\":12000,\"variations\":[{\"variation\":6,\"quantity\":100},{\"variation\":7,\"quantity\":20}]}]', 8, 1);
INSERT INTO `sok_invoice` (`total_price`, `total_quantity`, `supplier_sql_id`, `storehouse_sql_id`, `date`, `invoice_id`, `invoice_address`, `delivery_address`, `restocks`, `id`, `is_live`) VALUES (150.00, 10, 2, 1, '2024-01-06 08:00:00', 'JK004', '', '', '[{\"name\":\"老鹰\",\"product\":3,\"discount\":\"0\",\"lowest_price\":10,\"selling_price\":20,\"unit_price\":15,\"net_price\":15,\"total_quantity\":10,\"total_amount\":150,\"variations\":[{\"variation\":5,\"quantity\":10}]}]', 11, 1);
COMMIT;

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
-- Records of sok_restock
-- ----------------------------
BEGIN;
INSERT INTO `sok_restock` (`restock_date`, `restock_price`, `lowest_price`, `selling_price`, `invoice_sql_id`, `product_sql_id`, `supplier_sql_id`, `storehouse_sql_id`, `total_quantity`, `total_amount`, `restock_distribute`, `id`, `is_live`, `average_restock_price`) VALUES ('2023-12-29 16:05:54', 1.00, 1.00, 1.00, 1, 2, 2, 1, 1, 1.00, '[{\"variation\":2,\"quantity\":1}]', 1, 1, NULL);
INSERT INTO `sok_restock` (`restock_date`, `restock_price`, `lowest_price`, `selling_price`, `invoice_sql_id`, `product_sql_id`, `supplier_sql_id`, `storehouse_sql_id`, `total_quantity`, `total_amount`, `restock_distribute`, `id`, `is_live`, `average_restock_price`) VALUES ('2023-12-29 18:50:37', 2.00, 1.00, 2.00, 2, 2, 2, 1, 10, 20.00, '[{\"variation\":2,\"quantity\":10}]', 2, 1, NULL);
INSERT INTO `sok_restock` (`restock_date`, `restock_price`, `lowest_price`, `selling_price`, `invoice_sql_id`, `product_sql_id`, `supplier_sql_id`, `storehouse_sql_id`, `total_quantity`, `total_amount`, `restock_distribute`, `id`, `is_live`, `average_restock_price`) VALUES ('2024-01-01 17:29:01', 10.00, 1.00, 20.00, 4, 2, 2, 1, 10, 100.00, '[{\"variation\":2,\"quantity\":10}]', 3, 1, NULL);
INSERT INTO `sok_restock` (`restock_date`, `restock_price`, `lowest_price`, `selling_price`, `invoice_sql_id`, `product_sql_id`, `supplier_sql_id`, `storehouse_sql_id`, `total_quantity`, `total_amount`, `restock_distribute`, `id`, `is_live`, `average_restock_price`) VALUES ('2024-01-01 17:29:47', 9.50, 1.00, 20.00, 5, 2, 2, 2, 5, 47.50, '[{\"variation\":2,\"quantity\":5}]', 4, 1, NULL);
INSERT INTO `sok_restock` (`restock_date`, `restock_price`, `lowest_price`, `selling_price`, `invoice_sql_id`, `product_sql_id`, `supplier_sql_id`, `storehouse_sql_id`, `total_quantity`, `total_amount`, `restock_distribute`, `id`, `is_live`, `average_restock_price`) VALUES ('2024-01-01 19:14:25', 10.00, 1.00, 20.00, 6, 2, 2, 1, 10, 100.00, '[{\"variation\":2,\"quantity\":10}]', 5, 1, NULL);
INSERT INTO `sok_restock` (`restock_date`, `restock_price`, `lowest_price`, `selling_price`, `invoice_sql_id`, `product_sql_id`, `supplier_sql_id`, `storehouse_sql_id`, `total_quantity`, `total_amount`, `restock_distribute`, `id`, `is_live`, `average_restock_price`) VALUES ('2024-01-01 19:15:51', 10.00, 1.00, 20.00, 7, 2, 1, 2, 10, 100.00, '[{\"variation\":3,\"quantity\":9},{\"variation\":2,\"quantity\":1}]', 6, 1, NULL);
INSERT INTO `sok_restock` (`restock_date`, `restock_price`, `lowest_price`, `selling_price`, `invoice_sql_id`, `product_sql_id`, `supplier_sql_id`, `storehouse_sql_id`, `total_quantity`, `total_amount`, `restock_distribute`, `id`, `is_live`, `average_restock_price`) VALUES ('2024-01-02 18:05:03', 100.00, 90.00, 100.00, 8, 4, 2, 2, 120, 12000.00, '[{\"variation\":6,\"quantity\":100},{\"variation\":7,\"quantity\":20}]', 7, 1, NULL);
INSERT INTO `sok_restock` (`restock_date`, `restock_price`, `lowest_price`, `selling_price`, `invoice_sql_id`, `product_sql_id`, `supplier_sql_id`, `storehouse_sql_id`, `total_quantity`, `total_amount`, `restock_distribute`, `id`, `is_live`, `average_restock_price`) VALUES ('2024-01-07 16:47:42', 15.00, 10.00, 20.00, 11, 3, 2, 1, 10, 150.00, '[{\"variation\":5,\"quantity\":10}]', 9, 1, NULL);
COMMIT;

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

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` (`username`, `email`, `password`, `name`, `phone_no`, `storehouse_sql_id`, `is_admin`, `id`, `is_live`) VALUES ('qiong@163.com', 'qiong@163.com', '$2a$10$k/GalVIsEvWnqGLNKuWEJ.ko.rgxxm5xOWa62gvvAuGiDcnGCqpYC', '沙', '22334455', 2, 1, 1, 1);
INSERT INTO `sys_user` (`username`, `email`, `password`, `name`, `phone_no`, `storehouse_sql_id`, `is_admin`, `id`, `is_live`) VALUES ('lucy@163.com', 'lucy@163.com', '$2a$10$k/GalVIsEvWnqGLNKuWEJ.ko.rgxxm5xOWa62gvvAuGiDcnGCqpYC', 'Lucy', '12334433', 1, 0, 2, 1);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
