/*
Navicat MySQL Data Transfer

Source Server         : work
Source Server Version : 50639
Source Host           : localhost:3306
Source Database       : work

Target Server Type    : MYSQL
Target Server Version : 50639
File Encoding         : 65001

Date: 2018-03-12 14:51:32
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for report
-- ----------------------------
DROP TABLE IF EXISTS `report`;
CREATE TABLE `report` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `pid` int(10) DEFAULT NULL,
  `oid` int(10) DEFAULT NULL COMMENT '订单id',
  `content` varchar(255) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT '0.00',
  `type` varchar(10) DEFAULT NULL COMMENT '1收入，0 支出',
  `time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `flag` int(2) DEFAULT '1' COMMENT '0 不计入计算，1 计入计算（退货的订单为0，有效支出和收入为1）',
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb4;
