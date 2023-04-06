# Host: 127.0.0.1  (Version: 5.7.24-log)
# Date: 2022-06-18 19:33:36
# Generator: MySQL-Front 5.3  (Build 4.234)

/*!40101 SET NAMES utf8 */;

#
# Structure for table "admin"
#

DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `password` varchar(32) DEFAULT NULL,
  `id` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "admin"
#

INSERT INTO `admin` VALUES ('8a30ec6807f71bc69d096d8e4d501ade','root');

#
# Structure for table "classes"
#

DROP TABLE IF EXISTS `classes`;
CREATE TABLE `classes` (
  `class_id` varchar(10) DEFAULT NULL,
  `class_name` varchar(20) DEFAULT NULL,
  `school` varchar(20) DEFAULT NULL,
  `pk` varchar(32) DEFAULT NULL,
  `sk` varchar(32) DEFAULT NULL,
  UNIQUE KEY `classes_class_id_uindex` (`class_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "classes"
#

INSERT INTO `classes` VALUES ('2019110101','AAA1901','XXxx学校','2269051271','1134477972'),('2019110102','BBB1901','B学校','1790534423','895224324'),('2019110103','CCC1901','XX学校','2143487743','357231960'),('2019110104','DDD班级','XXxx学校','1825008013','304153338');

#
# Structure for table "exams"
#

DROP TABLE IF EXISTS `exams`;
CREATE TABLE `exams` (
  `id` char(19) NOT NULL,
  `exam_name` varchar(20) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `description` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `exams_exam_name_uindex` (`exam_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "exams"
#

INSERT INTO `exams` VALUES ('1538084406273626113','模拟1','2022-07-30',1,'模拟考试1'),('1538085036367138817','模拟2','2022-07-06',1,'模拟考试2模拟考试2'),('1538085122392313858','模拟3','2022-07-23',1,'模拟考试3'),('1538087962720456705','模拟4','2022-08-01',1,'模拟考试4');

#
# Structure for table "tb_1538084406273626113"
#

DROP TABLE IF EXISTS `tb_1538084406273626113`;
CREATE TABLE `tb_1538084406273626113` (
  `class_id` varchar(20) DEFAULT NULL,
  `class_name` varchar(20) DEFAULT NULL,
  `count` int(11) DEFAULT '0',
  `score` varchar(32) DEFAULT '1',
  `total` int(11) DEFAULT NULL,
  UNIQUE KEY `class_id` (`class_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "tb_1538084406273626113"
#

INSERT INTO `tb_1538084406273626113` VALUES ('2019110101','AAA1901',2,'150.0',2),('2019110104','DDD班级',0,'1',4),('2019110102','BBB1901',0,'1',2),('2019110103','CCC1901',0,'1',2);

#
# Structure for table "tb_1538085036367138817"
#

DROP TABLE IF EXISTS `tb_1538085036367138817`;
CREATE TABLE `tb_1538085036367138817` (
  `class_id` varchar(20) DEFAULT NULL,
  `class_name` varchar(20) DEFAULT NULL,
  `count` int(11) DEFAULT '0',
  `score` varchar(32) DEFAULT '1',
  `total` int(11) DEFAULT NULL,
  UNIQUE KEY `class_id` (`class_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "tb_1538085036367138817"
#

INSERT INTO `tb_1538085036367138817` VALUES ('2019110101','AAA1901',2,'150.0',2),('2019110103','CCC1901',0,'1',2);

#
# Structure for table "tb_1538085122392313858"
#

DROP TABLE IF EXISTS `tb_1538085122392313858`;
CREATE TABLE `tb_1538085122392313858` (
  `class_id` varchar(20) DEFAULT NULL,
  `class_name` varchar(20) DEFAULT NULL,
  `count` int(11) DEFAULT '0',
  `score` varchar(32) DEFAULT '1',
  `total` int(11) DEFAULT NULL,
  UNIQUE KEY `class_id` (`class_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "tb_1538085122392313858"
#

INSERT INTO `tb_1538085122392313858` VALUES ('2019110101','AAA1901',2,'150.0',2),('2019110103','CCC1901',0,'1',2);

#
# Structure for table "tb_1538087962720456705"
#

DROP TABLE IF EXISTS `tb_1538087962720456705`;
CREATE TABLE `tb_1538087962720456705` (
  `class_id` varchar(20) DEFAULT NULL,
  `class_name` varchar(20) DEFAULT NULL,
  `count` int(11) DEFAULT '0',
  `score` varchar(32) DEFAULT '1',
  `total` int(11) DEFAULT NULL,
  UNIQUE KEY `class_id` (`class_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "tb_1538087962720456705"
#


#
# Structure for table "tb_user"
#

DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` char(19) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(32) DEFAULT '4297f44b13955235245b2497399d7a93',
  `name` varchar(20) DEFAULT NULL,
  `class_name` varchar(20) DEFAULT NULL,
  `class_id` varchar(20) DEFAULT NULL,
  `exam` varchar(380) DEFAULT NULL,
  `school` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `tb_user_username_uindex` (`username`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "tb_user"
#

INSERT INTO `tb_user` VALUES ('1536184455603970049','201911010239','4297f44b13955235245b2497399d7a93','Meow1','AAA1901','2019110101','153808440627362611315380851223923138581538085036367138817','XXxx学校'),('1536184511933472770','201911010415','4297f44b13955235245b2497399d7a93','Meow2','AAA1901','2019110101','153808440627362611315380850363671388171538085122392313858','XXxx学校'),('1536184581995126785','201911010322','4297f44b13955235245b2497399d7a93','Meow3','BBB1901','2019110102','','B学校'),('1536184692292739074','201910010102','4297f44b13955235245b2497399d7a93','Meow4','BBB1901','2019110102','','B学校'),('1538026323706945538','201911010101','4297f44b13955235245b2497399d7a93','Meow5','CCC1901','2019110103',NULL,'XX学校'),('1538026323706946539','201911010102','4297f44b13955235245b2497399d7a93','Meow6','CCC1901','2019110103',NULL,'XX学校'),('1538078985391558657','201911010501','4297f44b13955235245b2497399d7a93','meow7','DDD班级','2019110104',NULL,'XXxx学校'),('1538079929596497922','201911010504','4297f44b13955235245b2497399d7a93','meow10','DDD班级','2019110104','','XX学校'),('1538080006876549122','201911010505','4297f44b13955235245b2497399d7a93','meow11','DDD班级','2019110104','','XXxx学校'),('1538080136975470594','201911010506','4297f44b13955235245b2497399d7a93','meow12','DDD班级','2019110104','','XXxx学校');
