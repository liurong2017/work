/*
Navicat MySQL Data Transfer

Source Server         : work
Source Server Version : 50639
Source Host           : localhost:3306
Source Database       : work

Target Server Type    : MYSQL
Target Server Version : 50639
File Encoding         : 65001

Date: 2018-03-12 14:51:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for custom
-- ----------------------------
DROP TABLE IF EXISTS `custom`;
CREATE TABLE `custom` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `wechat_name` varchar(100) DEFAULT NULL,
  `wechat_code` varchar(100) DEFAULT NULL,
  `mobile` varchar(30) DEFAULT NULL,
  `source` varchar(50) DEFAULT NULL,
  `sex` varchar(10) DEFAULT NULL,
  `level` int(10) DEFAULT NULL,
  `add_time` datetime DEFAULT NULL,
  `address1` varchar(200) DEFAULT NULL,
  `address2` varchar(200) DEFAULT NULL,
  `remark` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4;
