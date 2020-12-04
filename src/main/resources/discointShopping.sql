/*
SQLyog Ultimate
MySQL - 5.7.31-0ubuntu0.18.04.1 : Database - DiscountShopping
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
/*Table structure for table `t_commodity_category` */

DROP TABLE IF EXISTS `t_commodity_category`;

CREATE TABLE `t_commodity_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(50) DEFAULT NULL COMMENT '一级商品名称',
  `type_id` bigint(20) DEFAULT NULL,
  `category_order` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Table structure for table `t_commodity_item` */

DROP TABLE IF EXISTS `t_commodity_item`;

CREATE TABLE `t_commodity_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `item_name` varchar(50) DEFAULT NULL COMMENT '二级商品名称',
  `category_id` bigint(20) DEFAULT NULL,
  `item_order` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

/*Table structure for table `t_commodity_type` */

DROP TABLE IF EXISTS `t_commodity_type`;

CREATE TABLE `t_commodity_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type_name` varchar(20) DEFAULT NULL COMMENT '商品根类',
  `type_order` bigint(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Table structure for table `t_commodity_wares` */

DROP TABLE IF EXISTS `t_commodity_wares`;

CREATE TABLE `t_commodity_wares` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `wares_name` varchar(256) DEFAULT NULL COMMENT '商品名称',
  `wares_tagprice` bigint(20) DEFAULT NULL COMMENT '商品吊牌价格',
  `wares_offprice` bigint(20) DEFAULT NULL COMMENT '商品官方价格',
  `wares_price` bigint(5) DEFAULT NULL COMMENT '商品价格',
  `offi_state` tinyint(2) DEFAULT NULL COMMENT '官方价格展示状态0显示1不显示',
  `wares_remarks` varchar(512) DEFAULT NULL COMMENT '商品备注',
  `wares_img` varchar(512) DEFAULT NULL COMMENT '商品图片地址',
  `wares_state` tinyint(2) DEFAULT NULL COMMENT '商品状态0不上架1热销2无货',
  `type_id` bigint(20) DEFAULT NULL,
  `category_id` bigint(20) DEFAULT NULL,
  `item_id` bigint(20) DEFAULT NULL,
  `wares_exp` varchar(512) DEFAULT NULL COMMENT '商品说明',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=82 DEFAULT CHARSET=utf8;

/*Table structure for table `t_logger_action` */

DROP TABLE IF EXISTS `t_logger_action`;

CREATE TABLE `t_logger_action` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `host_ip` varchar(20) DEFAULT NULL,
  `action_time` varchar(20) DEFAULT NULL,
  `action_name` varchar(20) DEFAULT NULL,
  `action_function` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9584 DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
