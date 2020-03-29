-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: localhost    Database: lost_found
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
-- Table structure for table `lost_item`
--

DROP TABLE IF EXISTS `lost_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lost_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL COMMENT '丢失物名称(非空)',
  `description` varchar(512) DEFAULT NULL COMMENT '丢失物描述',
  `picker_id` int(11) DEFAULT NULL COMMENT '拾取人信息',
  `pick_date` date NOT NULL COMMENT '拾取日期',
  `pick_address` varchar(64) DEFAULT NULL COMMENT '拾取地点',
  `image` varchar(64) DEFAULT NULL COMMENT '物品图片url',
  `status` int(11) DEFAULT '0' COMMENT '0->未归还, 1->归还',
  `owner_name` varchar(64) DEFAULT NULL COMMENT '失主姓名',
  `owner_telephone` varchar(32) DEFAULT NULL COMMENT '失主电话',
  `return_time` datetime DEFAULT NULL COMMENT '归还时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='丢失物信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lost_item`
--

LOCK TABLES `lost_item` WRITE;
/*!40000 ALTER TABLE `lost_item` DISABLE KEYS */;
INSERT INTO `lost_item` VALUES (1,'test','一个蓝牙耳机',0,'2020-03-27','花溪校区','http://localhost:8080/lostandfound/20200327_一次幸福的机会.jpg',1,NULL,NULL,NULL),(2,'test1','一个蓝牙耳机',0,'2020-03-28','花溪校区','http://localhost:8080/lostandfound/20200327_一次幸福的机会.jpg',0,NULL,NULL,NULL),(3,'test2','一个蓝牙耳机',0,'2020-03-28','花溪校区','http://localhost:8080/lostandfound/20200327_一次幸福的机会.jpg',0,NULL,NULL,NULL),(4,'test2','一个蓝牙耳机',0,'2020-03-28','花溪校区','http://localhost:8080/lostandfound/20200327_一次幸福的机会.jpg',0,NULL,NULL,NULL);
/*!40000 ALTER TABLE `lost_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `picker`
--

DROP TABLE IF EXISTS `picker`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `picker` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL COMMENT '姓名',
  `qq` varchar(16) DEFAULT NULL COMMENT 'qq号',
  `telephone` varchar(32) DEFAULT NULL COMMENT '电话',
  `open_id` varchar(64) NOT NULL COMMENT '微信用户的唯一标识符',
  PRIMARY KEY (`id`),
  UNIQUE KEY `picker_open_id_uindex` (`open_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='拾取者信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `picker`
--

LOCK TABLES `picker` WRITE;
/*!40000 ALTER TABLE `picker` DISABLE KEYS */;
INSERT INTO `picker` VALUES (1,NULL,NULL,NULL,'test');
/*!40000 ALTER TABLE `picker` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'lost_found'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-03-29 15:37:45
