/*
Navicat MySQL Data Transfer

Source Server         : Test
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : parsevip

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2018-06-14 18:40:15
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for announcement
-- ----------------------------
DROP TABLE IF EXISTS `announcement`;
CREATE TABLE `announcement` (
  `id` int(11) NOT NULL,
  `content` varchar(255) DEFAULT NULL,
  `title` varchar(255) NOT NULL,
  `create_time` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_1u2ubk1rhxer3qi2my019helb` (`title`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of announcement
-- ----------------------------
INSERT INTO `announcement` VALUES ('1', '如果感觉不错，请加入QQ群一起讨论', '如果感觉不错，请加入QQ群一起讨论吧', null);
INSERT INTO `announcement` VALUES ('2', '网站正式部署到服务器上', '网站正式部署到服务器上', null);
INSERT INTO `announcement` VALUES ('3', '请不要使用本站做任何盈利商业', '请不要使用本站做任何盈利商业', null);
INSERT INTO `announcement` VALUES ('4', '网站仅作为演示', '网站仅作为演示', null);

-- ----------------------------
-- Table structure for hibernate_sequence
-- ----------------------------
DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of hibernate_sequence
-- ----------------------------
INSERT INTO `hibernate_sequence` VALUES ('6');
INSERT INTO `hibernate_sequence` VALUES ('6');

-- ----------------------------
-- Table structure for system_friend_link
-- ----------------------------
DROP TABLE IF EXISTS `system_friend_link`;
CREATE TABLE `system_friend_link` (
  `id` bigint(20) NOT NULL,
  `create_time` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_dfxx2l3716kpvhdtp4dow1s3m` (`name`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of system_friend_link
-- ----------------------------
INSERT INTO `system_friend_link` VALUES ('1', '', 'JSON工具', 'http://www.jsons.cn/');
INSERT INTO `system_friend_link` VALUES ('2', null, '在线代码运行', 'https://tool.lu/coderunner/');

-- ----------------------------
-- Table structure for system_info
-- ----------------------------
DROP TABLE IF EXISTS `system_info`;
CREATE TABLE `system_info` (
  `id` bigint(20) NOT NULL,
  `system_author` varchar(255) DEFAULT NULL,
  `system_data_base_edition` varchar(255) DEFAULT NULL,
  `system_edition` varchar(255) NOT NULL,
  `system_environment` varchar(255) DEFAULT NULL,
  `system_index` varchar(255) DEFAULT NULL,
  `system_upload_size` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ccdevqfk4t3st011oass58sxy` (`system_edition`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of system_info
-- ----------------------------
INSERT INTO `system_info` VALUES ('1', 'java_派大星', '8.00.2039', 'v1.0.1', 'Linux', 'mxspvip.cn', '2M');

-- ----------------------------
-- Table structure for system_interface
-- ----------------------------
DROP TABLE IF EXISTS `system_interface`;
CREATE TABLE `system_interface` (
  `id` bigint(20) NOT NULL,
  `interface_name` varchar(255) NOT NULL,
  `interface_url` varchar(255) NOT NULL,
  `last_time` datetime DEFAULT NULL,
  `create_time` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_2gjk078h9kwbnc6g0p9f7ps7h` (`interface_name`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of system_interface
-- ----------------------------
INSERT INTO `system_interface` VALUES ('1', '无名小站', 'http://www.wmxz.wang/video.php?url=', null, null);
INSERT INTO `system_interface` VALUES ('4', '接口一', 'http://www.mxspvip.cn/api/url=', null, null);
INSERT INTO `system_interface` VALUES ('5', '接口二', 'http://www.yyy.com/api/url/?', '2018-05-12 15:25:00', '2018-05-12 15:25:00');

-- ----------------------------
-- Table structure for system_permission
-- ----------------------------
DROP TABLE IF EXISTS `system_permission`;
CREATE TABLE `system_permission` (
  `id` bigint(20) NOT NULL,
  `available` bit(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `parent_ids` varchar(255) DEFAULT NULL,
  `permission` varchar(255) DEFAULT NULL,
  `resource_type` enum('menu','button') DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of system_permission
-- ----------------------------
INSERT INTO `system_permission` VALUES ('1', '\0', '用户管理', '0', '0/', 'user:list', 'menu', 'user/list');
INSERT INTO `system_permission` VALUES ('2', '\0', '用户添加', '1', '0/1', 'user:add', 'button', 'user/add');
INSERT INTO `system_permission` VALUES ('3', '\0', '用户删除', '1', '0/1', 'user:delete', 'button', 'user/delete');
INSERT INTO `system_permission` VALUES ('4', '\0', '接口管理', '0', '0/', 'interface:list', 'menu', 'interface/list');
INSERT INTO `system_permission` VALUES ('5', '\0', '接口添加', '2', '0/2', 'interface:add', 'button', 'interface/add');
INSERT INTO `system_permission` VALUES ('6', '\0', '接口删除', '2', '0/2', 'interface:delete', 'button', 'interface/delete');

-- ----------------------------
-- Table structure for system_role
-- ----------------------------
DROP TABLE IF EXISTS `system_role`;
CREATE TABLE `system_role` (
  `id` bigint(20) NOT NULL,
  `available` bit(1) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of system_role
-- ----------------------------
INSERT INTO `system_role` VALUES ('1', '\0', '管理员', 'admin');
INSERT INTO `system_role` VALUES ('2', '\0', 'VIP会员', 'vip');
INSERT INTO `system_role` VALUES ('3', '\0', '普通会员', 'member');

-- ----------------------------
-- Table structure for system_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `system_role_permission`;
CREATE TABLE `system_role_permission` (
  `role_id` bigint(20) NOT NULL,
  `permission_id` bigint(20) NOT NULL,
  KEY `FK801uq3st7cvfp8blsb7vfl4fk` (`permission_id`),
  KEY `FKp1u97cwl4kh8wboa58dyippik` (`role_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of system_role_permission
-- ----------------------------
INSERT INTO `system_role_permission` VALUES ('1', '1');
INSERT INTO `system_role_permission` VALUES ('1', '1');
INSERT INTO `system_role_permission` VALUES ('1', '2');
INSERT INTO `system_role_permission` VALUES ('1', '3');
INSERT INTO `system_role_permission` VALUES ('1', '4');
INSERT INTO `system_role_permission` VALUES ('1', '5');
INSERT INTO `system_role_permission` VALUES ('1', '6');
INSERT INTO `system_role_permission` VALUES ('2', '4');
INSERT INTO `system_role_permission` VALUES ('2', '5');
INSERT INTO `system_role_permission` VALUES ('2', '6');

-- ----------------------------
-- Table structure for system_user
-- ----------------------------
DROP TABLE IF EXISTS `system_user`;
CREATE TABLE `system_user` (
  `id` bigint(20) NOT NULL,
  `age` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `email_code` varchar(255) DEFAULT NULL,
  `last_address` varchar(255) DEFAULT NULL,
  `last_ip` varchar(255) DEFAULT NULL,
  `last_time` datetime DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `signature` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_74y7xiqrvp39wycn0ron4xq4h` (`username`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of system_user
-- ----------------------------
INSERT INTO `system_user` VALUES ('0', null, null, '', null, null, null, null, 'cc09e9c68e32fcb0482347c8a4fadfc4', null, null, null, null, null, 'admin');
INSERT INTO `system_user` VALUES ('3', null, null, '123@qq.com', null, null, null, null, '123', '15936010174', '男', '', '0', null, 'test');

-- ----------------------------
-- Table structure for system_user_role
-- ----------------------------
DROP TABLE IF EXISTS `system_user_role`;
CREATE TABLE `system_user_role` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  KEY `FKnp61n3syn415rmbwvhnw87u3a` (`role_id`),
  KEY `FKkc6ik04bm9v9kldgbt3kkgfac` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of system_user_role
-- ----------------------------
INSERT INTO `system_user_role` VALUES ('0', '1');
INSERT INTO `system_user_role` VALUES ('3', '2');
