/*
SQLyog Ultimate v10.00 Beta1
MySQL - 5.7.24 : Database - online_exam
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`online_exam` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `online_exam`;

/*Table structure for table `permission` */

DROP TABLE IF EXISTS `permission`;

CREATE TABLE `permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `permission_id` varchar(20) NOT NULL COMMENT '权限id',
  `name` varchar(100) NOT NULL COMMENT '权限名称',
  `description` varchar(255) DEFAULT NULL COMMENT '权限描述',
  `url` varchar(255) DEFAULT NULL COMMENT '权限访问路径',
  `perms` varchar(255) DEFAULT NULL COMMENT '权限标识',
  `parent_id` int(11) DEFAULT NULL COMMENT '父级权限id',
  `type` int(1) DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `order_num` int(3) DEFAULT '0' COMMENT '排序',
  `icon` varchar(50) DEFAULT NULL COMMENT '图标',
  `status` int(1) NOT NULL COMMENT '状态：1有效；2删除',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

/*Data for the table `permission` */

insert  into `permission`(`id`,`permission_id`,`name`,`description`,`url`,`perms`,`parent_id`,`type`,`order_num`,`icon`,`status`,`create_time`,`update_time`) values (1,'1','工作台','工作台','/workdest','workdest',0,1,1,'fa fa-home',1,'2017-09-27 21:22:02','2018-02-27 10:53:14'),(2,'2','权限管理','权限管理','',NULL,0,0,2,'fa fa-th-list',1,'2017-07-13 15:04:42','2018-02-27 10:53:14'),(3,'201','用户管理','用户管理','/users','users',2,1,1,'fa fa-circle-o',1,'2017-07-13 15:05:47','2018-02-27 10:53:14'),(4,'20101','列表查询','用户列表查询','/user/list','user:list',3,2,0,NULL,1,'2017-07-13 15:09:24','2017-10-09 05:38:29'),(5,'20102','新增','新增用户','/user/add','user:add',3,2,0,NULL,1,'2017-07-13 15:06:50','2018-02-28 17:58:46'),(6,'20103','编辑','编辑用户','/user/edit','user:edit',3,2,0,NULL,1,'2017-07-13 15:08:03','2018-02-27 10:53:14'),(7,'20104','删除','删除用户','/user/delete','user:delete',3,2,0,NULL,1,'2017-07-13 15:08:42','2018-02-27 10:53:14'),(8,'20105','批量删除','批量删除用户','/user/batch/delete','user:batchDelete',3,2,0,'',1,'2018-07-11 01:53:09','2018-07-11 01:53:09'),(9,'20106','分配角色','分配角色','/user/assign/role','user:assignRole',3,2,0,NULL,1,'2017-07-13 15:09:24','2017-10-09 05:38:29'),(10,'202','角色管理','角色管理','/roles','roles',2,1,2,'fa fa-circle-o',1,'2017-07-17 14:39:09','2018-02-27 10:53:14'),(11,'20201','列表查询','角色列表查询','/role/list','role:list',10,2,0,NULL,1,'2017-10-10 15:31:36','2018-02-27 10:53:14'),(12,'20202','新增','新增角色','/role/add','role:add',10,2,0,NULL,1,'2017-07-17 14:39:46','2018-02-27 10:53:14'),(13,'20203','编辑','编辑角色','/role/edit','role:edit',10,2,0,NULL,1,'2017-07-17 14:40:15','2018-02-27 10:53:14'),(14,'20204','删除','删除角色','/role/delete','role:delete',10,2,0,NULL,1,'2017-07-17 14:40:57','2018-02-27 10:53:14'),(15,'20205','批量删除','批量删除角色','/role/batch/delete','role:batchDelete',10,2,0,'',1,'2018-07-10 22:20:43','2018-07-10 22:20:43'),(16,'20206','分配权限','分配权限','/role/assign/permission','role:assignPerms',10,2,0,NULL,1,'2017-09-26 07:33:05','2018-02-27 10:53:14'),(17,'203','资源管理','资源管理','/permissions','permissions',2,1,3,'fa fa-circle-o',1,'2017-09-26 07:33:51','2018-02-27 10:53:14'),(18,'20301','列表查询','资源列表','/permission/list','permission:list',17,2,0,NULL,1,'2018-07-12 16:25:28','2018-07-12 16:25:33'),(19,'20302','新增','新增资源','/permission/add','permission:add',17,2,0,NULL,1,'2017-09-26 08:06:58','2018-02-27 10:53:14'),(20,'20303','编辑','编辑资源','/permission/edit','permission:edit',17,2,0,NULL,1,'2017-09-27 21:29:04','2018-02-27 10:53:14'),(21,'20304','删除','删除资源','/permission/delete','permission:delete',17,2,0,NULL,1,'2017-09-27 21:29:50','2018-02-27 10:53:14'),(22,'3','运维管理','运维管理','',NULL,0,0,3,'fa fa-th-list',1,'2018-07-06 15:19:26','2018-07-06 15:19:26'),(23,'301','数据监控','数据监控','/database/monitoring','database',22,1,1,'fa fa-circle-o',1,'2018-07-06 15:19:55','2018-09-12 13:14:48'),(24,'4','系统工具','系统工具','',NULL,0,0,4,'fa fa-th-list',1,'2018-07-06 15:20:38','2018-07-06 15:20:38'),(25,'401','图标工具','图标工具','/icons','icons',24,1,1,'fa fa-circle-o',1,'2018-07-06 15:21:00','2018-07-06 15:21:00'),(28,'1000000884924014','在线用户','在线用户','/online/users','onlineUsers',2,1,4,'fa fa-circle-o',1,'2018-07-18 21:00:38','2018-07-19 12:47:42'),(29,'1000000433323073','在线用户查询','在线用户查询','/online/user/list','onlineUser:list',28,2,0,NULL,1,'2018-07-18 21:01:25','2018-07-19 12:48:04'),(30,'1000000903407910','踢出用户','踢出用户','/online/user/kickout','onlineUser:kickout',28,2,0,NULL,1,'2018-07-18 21:41:54','2018-07-19 12:48:25'),(31,'1000000851815490','批量踢出','批量踢出','/online/user/batch/kickout','onlineUser:batchKickout',28,2,0,'',1,'2018-07-19 12:49:30','2018-07-19 12:49:30');

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` varchar(20) NOT NULL COMMENT '角色id',
  `name` varchar(50) NOT NULL COMMENT '角色名称',
  `description` varchar(255) DEFAULT NULL COMMENT '角色描述',
  `status` int(1) NOT NULL COMMENT '状态：1有效；2删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Data for the table `role` */

insert  into `role`(`id`,`role_id`,`name`,`description`,`status`,`create_time`,`update_time`) values (1,'1','超级管理员','超级管理员',1,'2017-06-28 20:30:05','2017-06-28 20:30:10'),(2,'2','老师','老师',1,'2017-06-30 23:35:19','2019-01-11 14:32:54'),(3,'3','学生','学生',1,'2017-06-30 23:35:44','2019-01-11 14:33:08'),(4,'4','数据库管理员','数据库管理员',1,'2017-07-12 11:50:22','2017-10-09 17:38:02');

/*Table structure for table `role_permission` */

DROP TABLE IF EXISTS `role_permission`;

CREATE TABLE `role_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` varchar(20) NOT NULL COMMENT '角色id',
  `permission_id` varchar(20) NOT NULL COMMENT '权限id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=923 DEFAULT CHARSET=utf8;

/*Data for the table `role_permission` */

insert  into `role_permission`(`id`,`role_id`,`permission_id`) values (840,'1','1'),(841,'1','2'),(842,'1','201'),(843,'1','20101'),(844,'1','20102'),(845,'1','20103'),(846,'1','20104'),(847,'1','20105'),(848,'1','20106'),(849,'1','202'),(850,'1','20201'),(851,'1','20202'),(852,'1','20203'),(853,'1','20204'),(854,'1','20205'),(855,'1','20206'),(856,'1','203'),(857,'1','20301'),(858,'1','20302'),(859,'1','20303'),(860,'1','20304'),(861,'1','1000000884924014'),(862,'1','1000000433323073'),(863,'1','1000000903407910'),(864,'1','1000000851815490'),(865,'1','3'),(866,'1','301'),(867,'1','4'),(868,'1','401'),(892,'2','1'),(909,'3','1'),(910,'3','2'),(911,'3','201'),(912,'3','20101'),(913,'3','202'),(914,'3','20201'),(915,'3','203'),(916,'3','20301'),(917,'3','1000000884924014'),(918,'3','1000000433323073'),(919,'3','3'),(920,'3','301'),(921,'3','4'),(922,'3','401');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(20) NOT NULL COMMENT '用户id',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(50) NOT NULL,
  `salt` varchar(128) DEFAULT NULL COMMENT '加密盐值',
  `nickname` varchar(50) DEFAULT NULL COMMENT '昵称',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(50) DEFAULT NULL COMMENT '联系方式',
  `sex` int(255) DEFAULT NULL COMMENT '年龄：1男2女',
  `age` int(3) DEFAULT NULL COMMENT '年龄',
  `img` varchar(255) DEFAULT NULL COMMENT '头像地址',
  `status` int(1) NOT NULL COMMENT '用户状态：1有效; 2删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  PRIMARY KEY (`id`,`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`user_id`,`username`,`password`,`salt`,`nickname`,`email`,`phone`,`sex`,`age`,`img`,`status`,`create_time`,`update_time`,`last_login_time`) values (2,'1000001032968610','郭洪奎','c74efd68fce353d1c52e4e0dc9bfd047','4aba7dd9d17cf5a083b2edc52ccac16b',NULL,'hungkuei@163.com','15709620338',1,23,NULL,1,'2019-01-11 14:34:49','2019-01-11 14:34:49','2019-01-11 14:37:51');

/*Table structure for table `user_role` */

DROP TABLE IF EXISTS `user_role`;

CREATE TABLE `user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(20) NOT NULL COMMENT '用户id',
  `role_id` varchar(20) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `user_role` */

insert  into `user_role`(`id`,`user_id`,`role_id`) values (1,'1','1'),(2,'1000001032968610','3');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
