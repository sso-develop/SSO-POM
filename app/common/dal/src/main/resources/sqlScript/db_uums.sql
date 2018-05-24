/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50153
Source Host           : localhost:3306
Source Database       : db_uums

Target Server Type    : MYSQL
Target Server Version : 50153
File Encoding         : 65001

Date: 2017-12-28 22:31:43
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for uums_sys_app
-- ----------------------------
DROP TABLE IF EXISTS `uums_sys_app`;
CREATE TABLE `uums_sys_app` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL COMMENT '名称',
  `sort` int(11) NOT NULL COMMENT '排序',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `is_enable` bit(1) NOT NULL COMMENT '是否启用',
  `code` varchar(16) NOT NULL COMMENT '编码',
  `modify_date` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_uums_sys_app_code` (`code`) USING HASH
) ENGINE=InnoDB AUTO_INCREMENT=84 DEFAULT CHARSET=utf8 COMMENT='应用表';

-- ----------------------------
-- Records of uums_sys_app
-- ----------------------------
INSERT INTO `uums_sys_app` VALUES ('83', '用户统一登录平台', '0', '2017-12-12 23:44:26', '', 'SSO_LOGIN', '2017-12-15 21:54:23');

-- ----------------------------
-- Table structure for uums_sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `uums_sys_permission`;
CREATE TABLE `uums_sys_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `level` varchar(255) DEFAULT NULL,
  `des` varchar(255) DEFAULT NULL COMMENT '描述',
  `is_enable` bit(1) NOT NULL,
  `app_id` bigint(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_uums_per_code_app_id` (`code`,`app_id`) USING HASH
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of uums_sys_permission
-- ----------------------------
INSERT INTO `uums_sys_permission` VALUES ('1', '2017-12-17 23:16:53', '2017-12-17 23:16:56', '登录权限', 'SSO_LOGIN_PERMISSION', 'a', '统一登录平台管理登录权限', '', '83');

-- ----------------------------
-- Table structure for uums_user_info
-- ----------------------------
DROP TABLE IF EXISTS `uums_user_info`;
CREATE TABLE `uums_user_info` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `create_date` datetime DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  `operator_name` varchar(255) NOT NULL COMMENT '登录名',
  `real_name` varchar(255) NOT NULL COMMENT '真实姓名，可能为空，且不唯一',
  `nick_name` varchar(255) NOT NULL COMMENT '昵称',
  `password` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) NOT NULL,
  `staff_no` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `operator_name` (`operator_name`) USING HASH,
  UNIQUE KEY `mobile` (`mobile`) USING HASH,
  UNIQUE KEY `staff_no` (`staff_no`) USING HASH
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of uums_user_info
-- ----------------------------
INSERT INTO `uums_user_info` VALUES ('1', '2017-11-17 20:50:38', '2017-11-17 20:50:41', 'admin', '林泽宽', '林泽宽', 'admin', '123456', '123456');

-- ----------------------------
-- Table structure for uums_user_permission_relation
-- ----------------------------
DROP TABLE IF EXISTS `uums_user_permission_relation`;
CREATE TABLE `uums_user_permission_relation` (
  `user_id` bigint(20) NOT NULL,
  `permission_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of uums_user_permission_relation
-- ----------------------------
INSERT INTO `uums_user_permission_relation` VALUES ('1', '1');
