/*
Navicat MySQL Data Transfer

Source Server         : work
Source Server Version : 50639
Source Host           : localhost:3306
Source Database       : work

Target Server Type    : MYSQL
Target Server Version : 50639
File Encoding         : 65001

Date: 2018-03-12 14:51:22
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for order_form
-- ----------------------------
DROP TABLE IF EXISTS `order_form`;
CREATE TABLE `order_form` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `type` varchar(10) NOT NULL DEFAULT '' COMMENT '+ 收入；- 支出',
  `cid` int(10) DEFAULT NULL,
  `cname` varchar(255) DEFAULT NULL,
  `address` varchar(200) DEFAULT '',
  `source` varchar(20) DEFAULT NULL,
  `add_time` datetime DEFAULT NULL,
  `status` varchar(20) DEFAULT '1' COMMENT '1 已确认；2 已发货',
  `express` varchar(255) DEFAULT '' COMMENT '快递',
  `remark` varchar(500) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4;
