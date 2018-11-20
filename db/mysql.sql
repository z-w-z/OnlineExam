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

/*Table structure for table `exam_comment` */

DROP TABLE IF EXISTS `exam_comment`;

CREATE TABLE `exam_comment` (
  `id` int(8) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` int(8) DEFAULT NULL COMMENT '用户ID',
  `post_id` int(8) DEFAULT NULL COMMENT '帖子id',
  `content` text COLLATE utf8mb4_unicode_ci COMMENT '内容',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `exam_comment` */

/*Table structure for table `exam_contest` */

DROP TABLE IF EXISTS `exam_contest`;

CREATE TABLE `exam_contest` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '考试标题',
  `total_score` int(8) DEFAULT NULL COMMENT '考试总分',
  `subject_id` int(8) DEFAULT NULL COMMENT '学科ID',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `start_time` timestamp NULL DEFAULT NULL COMMENT '考试开始时间',
  `end_time` timestamp NULL DEFAULT NULL COMMENT '考试结束时间',
  `state` int(8) DEFAULT '0' COMMENT '进行状态:0表示未开始,1表示进行中,2表示考试已经结束,3表示该考试已经完成批卷',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `exam_contest` */

/*Table structure for table `exam_grade` */

DROP TABLE IF EXISTS `exam_grade`;

CREATE TABLE `exam_grade` (
  `id` int(8) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `student_id` int(8) DEFAULT NULL COMMENT '考生主键ID',
  `contest_id` int(8) DEFAULT NULL COMMENT '考试主键ID',
  `result` int(8) DEFAULT '0' COMMENT '最终分数',
  `auto_result` int(8) DEFAULT '0' COMMENT '电脑自动评判选择题分数',
  `manul_result` int(8) DEFAULT NULL COMMENT '人工手动评判分数',
  `answer_json` longtext COLLATE utf8mb4_unicode_ci COMMENT '考试作答答案json',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '考试交卷时间',
  `finish_time` timestamp NULL DEFAULT NULL COMMENT '改卷完成时间',
  `state` int(8) DEFAULT '0' COMMENT '0表示已交卷但是未评卷,1表示已交卷已评卷',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `exam_grade` */

/*Table structure for table `exam_post` */

DROP TABLE IF EXISTS `exam_post`;

CREATE TABLE `exam_post` (
  `id` int(8) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `author_id` int(8) DEFAULT NULL COMMENT '作者ID',
  `html_content` longtext COLLATE utf8mb4_unicode_ci COMMENT 'html源代码',
  `text_content` longtext COLLATE utf8mb4_unicode_ci COMMENT '文本内容',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发帖时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '帖子最后编辑时间',
  `last_reply_time` timestamp NULL DEFAULT NULL COMMENT '最后一次回复时间',
  `reply_num` int(8) DEFAULT '0' COMMENT '回复数',
  `title` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '标题',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `exam_post` */

insert  into `exam_post`(`id`,`author_id`,`html_content`,`text_content`,`create_time`,`update_time`,`last_reply_time`,`reply_num`,`title`) values (2,7,'<p>hellow</p>','hellow','2018-11-20 16:58:45','2018-11-20 16:58:45',NULL,0,'');

/*Table structure for table `exam_question` */

DROP TABLE IF EXISTS `exam_question`;

CREATE TABLE `exam_question` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '题目标题',
  `content` text COLLATE utf8mb4_unicode_ci COMMENT '题目内容',
  `question_type` int(8) DEFAULT NULL COMMENT '题目类型,0表示单项选择题,1表示多项选择题,2表示问答题,3表示编程题',
  `option_a` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '选项A',
  `option_b` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '选项B',
  `option_c` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '选项C',
  `option_d` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '选项D',
  `answer` text COLLATE utf8mb4_unicode_ci COMMENT '答案',
  `parse` text COLLATE utf8mb4_unicode_ci COMMENT '答案解析',
  `subject_id` int(8) DEFAULT NULL COMMENT '学科ID',
  `contest_id` int(8) DEFAULT NULL COMMENT '试卷ID',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `score` int(8) DEFAULT NULL COMMENT '题目分值',
  `difficulty` int(8) DEFAULT '1' COMMENT '题目难度',
  `state` int(8) DEFAULT '1' COMMENT '0表示未考试题目,1表示已考试题目',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `exam_question` */

insert  into `exam_question`(`id`,`title`,`content`,`question_type`,`option_a`,`option_b`,`option_c`,`option_d`,`answer`,`parse`,`subject_id`,`contest_id`,`create_time`,`update_time`,`score`,`difficulty`,`state`) values (1,'单选题','1、设、x、y、z和k都是int型变量，则执行表达式x=(y=4,z=16,k=32)后，x的值为（    ）',0,'4','16','32','52','C','答案是C，这里涉及到逗号表达式的问题。其形式为\n（表达式1，表达式2，……，表达式n）\n从左到右依次计算各表达式的值，逗号表达式的最终结果是表达式n的值。\n所以，x=32。',1,0,'2018-11-20 16:06:02','2018-11-20 16:51:14',5,1,1);

/*Table structure for table `exam_reply` */

DROP TABLE IF EXISTS `exam_reply`;

CREATE TABLE `exam_reply` (
  `id` int(8) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` int(8) DEFAULT NULL COMMENT '用户id',
  `atuser_id` int(8) DEFAULT NULL COMMENT '被回复用户id',
  `post_id` int(8) DEFAULT NULL COMMENT '帖子id',
  `comment_id` int(8) DEFAULT NULL COMMENT '评论id',
  `content` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '内容',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `exam_reply` */

/*Table structure for table `exam_subject` */

DROP TABLE IF EXISTS `exam_subject`;

CREATE TABLE `exam_subject` (
  `id` int(8) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '学科名称',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `question_num` int(11) DEFAULT '0' COMMENT '题目数量',
  `img_url` varchar(63) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '图片url',
  `state` int(4) DEFAULT '0' COMMENT '课程状态,0表示正常,1表示弃用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `exam_subject` */

insert  into `exam_subject`(`id`,`name`,`create_time`,`update_time`,`question_num`,`img_url`,`state`) values (1,'C语言测试','2018-11-20 16:03:00','2018-11-20 16:03:00',0,'problemset_default.jpg',0),(2,'数据库测试','2018-11-20 16:03:13','2018-11-20 16:03:13',0,'problemset_default.jpg',0),(3,'Java语言测试','2018-11-20 16:03:28','2018-11-20 16:03:28',0,'problemset_default.jpg',0);

/*Table structure for table `exam_user` */

DROP TABLE IF EXISTS `exam_user`;

CREATE TABLE `exam_user` (
  `id` int(8) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(63) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '姓名',
  `username` varchar(30) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '账号,一般为学号或者教工号',
  `password` varchar(60) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '密码',
  `qq` varchar(30) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'QQ',
  `phone` varchar(30) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '手机号',
  `email` varchar(30) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '邮箱',
  `description` varchar(63) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '个人描述',
  `avatar_img_url` varchar(63) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '头像',
  `state` int(8) DEFAULT '0' COMMENT '当前账号状态,0表示正常,1表示禁用',
  `level` int(8) DEFAULT '0' COMMENT '0表示学生,1表示教师,2表示管理员',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `exam_user` */

insert  into `exam_user`(`id`,`name`,`username`,`password`,`qq`,`phone`,`email`,`description`,`avatar_img_url`,`state`,`level`,`create_time`,`update_time`) values (7,'郭洪奎','admin','378A37076476EE08AD3A914AB912E837','1468584274','15709620338','1468584274@qq.com','管理员',NULL,0,2,'2018-11-20 09:27:31','2018-11-20 15:54:50'),(9,'郭洪奎','40115208','62BD1C79F9F6CA8BA03BF1456AED3865','1468584274','15709*620338','hungkuei@163.com','','headimg_placeholder.png',0,0,'2018-11-20 16:15:31','2018-11-20 16:15:31');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
