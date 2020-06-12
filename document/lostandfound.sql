-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: lost_found
-- ------------------------------------------------------
-- Server version	8.0.18

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(64) DEFAULT NULL,
  `password` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='管理员';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (1,'why','$2a$10$MWI/bH8BkdkRl00OBOOUC.E7prn1fe7/NP3QU.HjLFnn2qOZ5lTt.');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `app_user`
--

DROP TABLE IF EXISTS `app_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `app_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(64) NOT NULL,
  `password` varchar(128) NOT NULL,
  `icon` varchar(64) DEFAULT NULL COMMENT '头像url',
  `department` varchar(64) DEFAULT NULL COMMENT '学院',
  `email` varchar(64) DEFAULT NULL COMMENT '用户邮箱',
  `student_id` varchar(32) DEFAULT NULL COMMENT '用户学号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `app_user_password_uindex` (`password`),
  UNIQUE KEY `app_user_username_uindex` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `app_user`
--

LOCK TABLES `app_user` WRITE;
/*!40000 ALTER TABLE `app_user` DISABLE KEYS */;
INSERT INTO `app_user` VALUES (1,'1684523860@qq.com','$2a$10$ii9zTLzNH6RACFLv0GzweuqbRV3.CsQhCImZIdrSq2YnWQ7LRNgCa',NULL,NULL,'1684523860@qq.com',NULL);
/*!40000 ALTER TABLE `app_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `apply_manager_msg`
--

DROP TABLE IF EXISTS `apply_manager_msg`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `apply_manager_msg` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `region_id` int(11) NOT NULL,
  `wx_user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='申请管理员信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `apply_manager_msg`
--

LOCK TABLES `apply_manager_msg` WRITE;
/*!40000 ALTER TABLE `apply_manager_msg` DISABLE KEYS */;
/*!40000 ALTER TABLE `apply_manager_msg` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `apply_region_msg`
--

DROP TABLE IF EXISTS `apply_region_msg`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `apply_region_msg` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `region_id` int(11) DEFAULT NULL COMMENT '区域id',
  `wx_user_id` int(11) DEFAULT NULL COMMENT '申请微信用户id',
  `apply_time` datetime DEFAULT NULL COMMENT '申请时间',
  `status` int(11) DEFAULT '0' COMMENT '0->未审核，1->同意,2->不同意',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `apply_region_msg`
--

LOCK TABLES `apply_region_msg` WRITE;
/*!40000 ALTER TABLE `apply_region_msg` DISABLE KEYS */;
INSERT INTO `apply_region_msg` VALUES (1,1,1,'2020-04-13 17:16:16',0),(2,1,1,'2020-04-13 17:22:49',0),(3,1,1,'2020-04-13 17:23:46',0),(4,1,1,'2020-04-13 17:43:23',0),(5,1,1,'2020-04-13 17:44:46',0),(6,1,1,'2020-04-14 16:42:57',0),(7,1,1,'2020-04-14 16:44:34',0);
/*!40000 ALTER TABLE `apply_region_msg` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lost_item`
--

DROP TABLE IF EXISTS `lost_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lost_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL COMMENT '丢失物名称(非空)',
  `description` varchar(512) DEFAULT NULL COMMENT '丢失物描述',
  `picker` varchar(64) DEFAULT NULL COMMENT '拾取人信息 对应wx_user的open_id和app_user的username',
  `pick_date` date NOT NULL COMMENT '拾取日期',
  `pick_address` varchar(64) DEFAULT NULL COMMENT '拾取地点',
  `image` varchar(64) DEFAULT NULL COMMENT '物品图片url',
  `status` int(11) DEFAULT '0' COMMENT '0->未归还, 1->归还',
  `owner_name` varchar(64) DEFAULT NULL COMMENT '失主姓名',
  `owner_telephone` varchar(32) DEFAULT NULL COMMENT '失主电话',
  `return_time` datetime DEFAULT NULL COMMENT '归还时间',
  `region_id` int(11) DEFAULT NULL COMMENT '区域id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='丢失物信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lost_item`
--

LOCK TABLES `lost_item` WRITE;
/*!40000 ALTER TABLE `lost_item` DISABLE KEYS */;
INSERT INTO `lost_item` VALUES (1,'test','一个蓝牙耳机','0','2020-03-27','花溪校区','http://localhost:8080/lostandfound/20200327_一次幸福的机会.jpg',1,NULL,NULL,NULL,NULL),(2,'test1','一个蓝牙耳机','0','2020-03-28','花溪校区','http://localhost:8080/lostandfound/20200327_一次幸福的机会.jpg',0,NULL,NULL,NULL,NULL),(3,'test2','一个蓝牙耳机','0','2020-03-28','花溪校区','http://localhost:8080/lostandfound/20200327_一次幸福的机会.jpg',0,NULL,NULL,NULL,NULL),(4,'test2','一个蓝牙耳机','0','2020-03-28','花溪校区','http://localhost:8080/lostandfound/20200327_一次幸福的机会.jpg',0,NULL,NULL,NULL,NULL),(5,'test2','一个蓝牙耳机','0','2020-03-29','花溪校区','http://localhost:8080/lostandfound/20200327_一次幸福的机会.jpg',0,NULL,NULL,NULL,NULL),(6,'test2','一个蓝牙耳机','0','2020-04-06','花溪校区','http://localhost:8080/lostandfound/20200327_一次幸福的机会.jpg',0,NULL,NULL,NULL,NULL),(7,'test2','一个蓝牙耳机','0','2020-04-06','花溪校区','http://localhost:8080/lostandfound/20200327_一次幸福的机会.jpg',0,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `lost_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `region`
--

DROP TABLE IF EXISTS `region`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `region` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL COMMENT '区域名称',
  `status` int(11) DEFAULT '1' COMMENT '0->已被创建，1->未被创建',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='区域';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `region`
--

LOCK TABLES `region` WRITE;
/*!40000 ALTER TABLE `region` DISABLE KEYS */;
INSERT INTO `region` VALUES (1,'重庆理工大学花溪校区',0);
/*!40000 ALTER TABLE `region` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `region_create_relation`
--

DROP TABLE IF EXISTS `region_create_relation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `region_create_relation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `region_id` int(11) DEFAULT NULL COMMENT '区域id',
  `wx_user_id` int(11) DEFAULT NULL COMMENT '微信用户id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='区域创建者与用户的关系表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `region_create_relation`
--

LOCK TABLES `region_create_relation` WRITE;
/*!40000 ALTER TABLE `region_create_relation` DISABLE KEYS */;
INSERT INTO `region_create_relation` VALUES (1,1,1);
/*!40000 ALTER TABLE `region_create_relation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `region_manager_relation`
--

DROP TABLE IF EXISTS `region_manager_relation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `region_manager_relation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `region_id` int(11) DEFAULT NULL COMMENT '区域id',
  `wx_user_id` int(11) DEFAULT NULL COMMENT '微信用户id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='区域管理员与用户的联系表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `region_manager_relation`
--

LOCK TABLES `region_manager_relation` WRITE;
/*!40000 ALTER TABLE `region_manager_relation` DISABLE KEYS */;
/*!40000 ALTER TABLE `region_manager_relation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wx_user`
--

DROP TABLE IF EXISTS `wx_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `wx_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `open_id` varchar(64) NOT NULL COMMENT '微信用户唯一标识',
  `session_key` varchar(64) DEFAULT NULL COMMENT '会话密匙，解密隐私数据',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wx_user`
--

LOCK TABLES `wx_user` WRITE;
/*!40000 ALTER TABLE `wx_user` DISABLE KEYS */;
INSERT INTO `wx_user` VALUES (1,'abcd','abcd','2020-04-06 22:27:16','2020-04-06 22:27:07'),(2,'test','test','2020-04-13 20:59:11','2020-04-13 20:59:14');
/*!40000 ALTER TABLE `wx_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-04-21  0:40:53
