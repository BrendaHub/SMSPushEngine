/*
Navicat MySQL Data Transfer

Source Server         : 101.200.189.24
Source Server Version : 50627
Source Host           : 101.200.189.24:3306
Source Database       : binggou_cloud

Target Server Type    : MYSQL
Target Server Version : 50627
File Encoding         : 65001

Date: 2019-12-24 16:53:01
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for bg_task_push_r2
-- ----------------------------
DROP TABLE IF EXISTS `bg_task_push_r2`;
CREATE TABLE `bg_task_push_r2` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(50) NOT NULL COMMENT '推送标题内容',
  `content` varchar(100) NOT NULL COMMENT '推送内容， 部分内容。',
  `ext_param` varchar(2000) DEFAULT NULL,
  `clientId` varchar(255) NOT NULL DEFAULT '' COMMENT '个推的手机唯一标识',
  `clientId_type` varchar(10) NOT NULL COMMENT '推送设备号类型， getui， 个推； jpush，极光；xinge, 信鸽；......',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '推送任务状态， 0： 未处理；2：正在处理；5：处理成功； 6：处理失败',
  `err_info` varchar(45) DEFAULT NULL COMMENT '处理结果描述',
  `create_time` bigint(20) NOT NULL COMMENT '任务创建时间',
  `push_time` bigint(20) DEFAULT NULL COMMENT '任务处理时间',
  `callback_id` varchar(45) DEFAULT NULL COMMENT '推送后返回的编号，',
  `push_type` tinyint(4) NOT NULL COMMENT '推送方式 0：通知 / 1：透传\n说明： 默认为andorid通道类， \n       ios 没有通道和透传这个标识，即这个标识能IOS不起作用',
  `push_os` varchar(10) NOT NULL COMMENT '推送平台 0：ios/ 1：android',
  `prepush_time` bigint(20) NOT NULL COMMENT '预推送时间，用来设计计划推送任务。',
  `badge_num` int(11) DEFAULT '0' COMMENT '推送角标',
  `priority` tinyint(4) NOT NULL DEFAULT '5' COMMENT '记录处理优先级， 3，5，8 三个等级， 3，最低， 5 正常， 8 最优',
  `batch_number` varchar(100) DEFAULT NULL COMMENT '批次编号',
  PRIMARY KEY (`id`),
  KEY `index2` (`priority`)
) ENGINE=InnoDB AUTO_INCREMENT=100997 DEFAULT CHARSET=utf8;
SET FOREIGN_KEY_CHECKS=1;
