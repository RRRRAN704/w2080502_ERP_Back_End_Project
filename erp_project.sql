-- MySQL dump 10.13  Distrib 9.2.0, for Win64 (x86_64)
--
-- Host: localhost    Database: erp_project
-- ------------------------------------------------------
-- Server version	9.2.0

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
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `client` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `employee_id` bigint NOT NULL COMMENT 'employee id, foreign key to employee table',
  `department_id` bigint NOT NULL COMMENT 'department id, foreign key to department table',
  `company_name` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `contact_person` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `contact_phone` varchar(11) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `contact_email` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `address` varchar(256) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `is_archived` int NOT NULL DEFAULT '0' COMMENT 'status 0:in use, 1:archived',
  `create_time` datetime DEFAULT NULL,
  `create_user` bigint DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_user` bigint DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT 'client remark',
  PRIMARY KEY (`id`),
  KEY `c_eid_fk` (`employee_id`),
  KEY `c_did_fk` (`department_id`),
  CONSTRAINT `c_did_fk` FOREIGN KEY (`department_id`) REFERENCES `department` (`id`),
  CONSTRAINT `c_eid_fk` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='client info';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client`
--

LOCK TABLES `client` WRITE;
/*!40000 ALTER TABLE `client` DISABLE KEYS */;
INSERT INTO `client` VALUES (1,2,2,'TechNova Ltd','Alice Johnson','02079461234','alice.johnson@technova.co.uk','10 Downing Street, London, SW1A 2AA',0,'2025-07-11 14:06:14',2,'2025-08-31 17:28:57',3,NULL),(2,2,2,'Global Solutions PLC','John Smith','01615557890','john.smith@globalsolutions.com','100 Deansgate, Manchester, M3 2NW',0,'2025-07-12 16:08:03',2,'2025-08-31 17:28:56',3,NULL),(4,3,2,'Bright Future Consulting','Emma Brown','01213334567','emma.brown@brightfuture.org','55 Colmore Row, Birmingham, B3 2AA',0,'2025-07-14 16:09:24',3,'2025-08-31 16:15:40',3,NULL),(5,2,2,'NextGen Retail Ltd','Michael Green','01132469876','michael.green@nextgenretail.com','1 City Square, Leeds, LS1 2ES',0,'2025-07-17 21:59:39',2,'2025-08-31 16:15:40',3,''),(6,2,2,'GreenTech Solutions','Sophia Davis','01179654321','sophia.davis@greentech.co.uk','Temple Quay, Bristol, BS1 6EA',0,'2025-07-23 17:49:58',2,'2025-08-31 16:15:39',3,''),(7,3,2,'Skyline Ventures','William Martin','01417781234','william.martin@skylineventures.com','George Square, Glasgow, G2 1DU',0,'2025-07-23 17:54:16',3,'2025-08-31 17:17:42',3,''),(9,6,2,'FutureVision PLC','Charlotte Evans','07234567891','charlotte.evans@futurevision.com','1 St Peter\'s Square, Manchester, M2 3DE',0,'2025-08-31 17:16:59',3,'2025-08-31 17:17:32',3,'');
/*!40000 ALTER TABLE `client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contract`
--

DROP TABLE IF EXISTS `contract`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contract` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `contract_name` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `contract_number` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `employee_id` bigint NOT NULL COMMENT 'employee id, foreign key to employee table',
  `client_id` bigint NOT NULL COMMENT 'client id, foreign key to client table',
  `department_id` bigint NOT NULL COMMENT 'department id, foreign key to department table',
  `amount` int NOT NULL,
  `start_date` date NOT NULL COMMENT 'contract start date',
  `end_date` date NOT NULL COMMENT 'contract end date',
  `is_archived` int NOT NULL DEFAULT '0' COMMENT 'status 0:in use, 1:archived',
  `file_url` varchar(256) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT 'URL to the contract file (PDF)',
  `create_time` datetime DEFAULT NULL,
  `create_user` bigint DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_user` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `co_eid_fk` (`employee_id`),
  KEY `c_cid_fk` (`client_id`),
  KEY `co_did_fk` (`department_id`),
  CONSTRAINT `c_cid_fk` FOREIGN KEY (`client_id`) REFERENCES `client` (`id`),
  CONSTRAINT `co_did_fk` FOREIGN KEY (`department_id`) REFERENCES `department` (`id`),
  CONSTRAINT `co_eid_fk` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='contract info';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contract`
--

LOCK TABLES `contract` WRITE;
/*!40000 ALTER TABLE `contract` DISABLE KEYS */;
INSERT INTO `contract` VALUES (1,'Service Agreement A','EN-2025-001',2,1,2,1000,'2024-12-31','2025-01-01',0,'','2025-07-11 22:57:58',2,'2025-07-23 19:50:05',3),(2,'Software License Contract','EN-2025-002',3,2,2,2000,'2024-12-31','2024-01-01',0,'','2025-07-11 22:59:21',2,'2025-07-23 19:49:40',3),(6,'Maintenance Contract','EN-2025-003',3,4,2,2000,'2024-12-31','2024-01-01',0,'','2025-08-25 23:02:03',2,'2025-08-31 17:05:28',3),(8,'Consulting Agreement','EN-2025-004',2,1,2,5000,'2025-08-01','2025-08-01',0,'','2025-08-25 20:53:21',3,'2025-08-31 16:15:27',3),(9,'Partnership Contract','EN-2025-005',2,5,2,5000,'2025-07-21','2025-07-31',0,'','2025-08-21 20:58:42',3,'2025-08-31 16:15:26',3),(10,'Outsourcing Agreement','EN-2025-006',3,1,1,5000,'2025-07-29','2025-07-25',0,'','2025-08-27 21:00:11',3,'2025-07-23 21:00:11',3),(11,'Non-Disclosure Agreement','EN-2025-007',3,1,1,5000,'2025-07-06','2025-07-24',0,'','2025-08-23 21:05:03',3,'2025-07-23 21:05:03',3),(12,'Procurement Contract','EN-2025-008',2,1,2,5000,'2025-07-28','2025-08-03',0,'https://erp-project0702.oss-cn-beijing.aliyuncs.com/59287147-4147-40eb-99e7-d013eb96eb9d.pdf','2025-08-23 21:32:04',2,'2025-08-31 16:15:26',3),(13,'Test Contract','EN-2025-009',3,1,2,6001,'2025-07-27','2025-08-26',0,'https://erp-project0702.oss-cn-beijing.aliyuncs.com/3176feca-3368-42f3-bb52-6d9ed34b1499.pdf','2025-08-25 20:27:38',2,'2025-08-31 16:15:25',3),(14,'IT Support Service','EN-2025-010',3,2,2,10000,'2025-08-04','2026-08-13',0,'','2025-08-31 17:04:28',3,'2025-08-31 17:10:25',3);
/*!40000 ALTER TABLE `contract` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `department` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `department_name` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `create_user` bigint DEFAULT NULL,
  `update_user` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='department info';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES (1,'IT','2022-02-15 15:51:20','2022-02-17 09:16:20',1,1),(2,'Consultation','2025-07-06 21:02:42','2025-07-06 21:02:42',1,1);
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `username` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `position_id` bigint NOT NULL COMMENT 'position id, foreign key to position table',
  `department_id` bigint NOT NULL COMMENT 'department id, foreign key to department table',
  `password` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `phone` varchar(11) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `sex` varchar(2) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '1:male, 2:female',
  `status` int NOT NULL DEFAULT '1' COMMENT 'status 0:disabled 1:enabled',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `create_user` bigint DEFAULT NULL,
  `update_user` bigint DEFAULT NULL,
  `email` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  KEY `e_did_fk` (`department_id`),
  KEY `e_pid_fk` (`position_id`),
  CONSTRAINT `e_did_fk` FOREIGN KEY (`department_id`) REFERENCES `department` (`id`),
  CONSTRAINT `e_pid_fk` FOREIGN KEY (`position_id`) REFERENCES `position` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='employee info';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,'admin','admin',1,1,'e10adc3949ba59abbe56e057f20f883e','07123456789','1',1,'2022-02-15 15:51:20','2025-08-07 17:51:07',1,1,'candiceran@126.com'),(2,'Yiran Wang','yrwang',3,2,'e10adc3949ba59abbe56e057f20f883e','07234567890','2',1,'2025-07-09 19:45:51','2025-08-31 16:14:26',1,1,'candiceran808@gmail.com'),(3,'Manager','manager',2,2,'e10adc3949ba59abbe56e057f20f883e','07345678901','2',1,'2025-07-10 21:52:46','2025-08-25 22:31:53',1,1,'marywyr@qq.com'),(6,'Adam Smith','adam',3,2,'e10adc3949ba59abbe56e057f20f883e','07456789012','1',1,'2025-08-29 13:17:11','2025-08-31 16:09:19',1,1,'123456@gmail.com');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `company_name` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `contact_person` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `contact_phone` varchar(11) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `contact_email` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `employee_id` bigint DEFAULT NULL COMMENT 'employee id, foreign key to employee table',
  `remark` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT 'client remark',
  `is_archived` int NOT NULL DEFAULT '0' COMMENT 'status 0: in use,  1: archived',
  `create_time` datetime DEFAULT NULL,
  `assign_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_user` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `o_eid_fk` (`employee_id`),
  CONSTRAINT `o_eid_fk` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='order info';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` VALUES (1,'TechNova Ltd','Alice Wang','07512345678','alice.wang@technova.com',2,NULL,0,'2025-07-20 15:51:20','2025-07-26 17:15:41','2025-07-26 17:15:41',3),(2,'Global Solutions Inc','John Smith','07499887766','john.smith@gsi.com',2,NULL,0,'2025-07-01 15:51:20','2025-07-10 23:01:50','2025-07-25 17:02:05',3),(3,'FinEdge Consulting','Michael Johnson','07311122233','m.johnson@finedge.co.uk',2,NULL,0,'2025-07-03 15:51:20','2025-07-26 17:14:51','2025-07-26 17:14:51',3),(4,'Bright Future Ltd','Emma Brown','07844455566','emma.brown@brightfuture.com',3,'',0,'2025-07-24 16:17:09','2025-07-25 17:02:50','2025-08-31 16:26:35',3),(5,'NextGen Retail','Sophia Davis','07977788899','sophia@nextgenretail.com',2,'',0,'2025-07-25 17:03:07','2025-07-26 16:56:54','2025-08-31 16:26:34',3),(6,'GreenTech Corp','Daniel Lee','07123456789','daniel.lee@greentech.org',6,'',0,'2025-07-26 15:44:09','2025-08-31 16:40:43','2025-08-31 16:40:43',3),(7,'Urban Design Studio','Olivia Harris','07788899900','olivia@uds.com',3,'',0,'2025-08-01 15:27:25','2025-08-25 20:02:52','2025-08-25 20:03:01',3),(8,'SmartBuild Ltd','James Wilson','07233344455','james.w@smartbuild.co.uk',6,'',0,'2025-08-25 21:58:31','2025-08-31 16:26:14','2025-08-31 16:26:14',3),(9,'Skyline Ventures','Chloe Taylor','07566677788','chloe.taylor@skyline.com',6,'Test request',0,'2025-08-29 13:10:53','2025-08-29 13:27:12','2025-08-29 13:27:12',3);
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `position`
--

DROP TABLE IF EXISTS `position`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `position` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `position_name` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `department_id` bigint NOT NULL COMMENT 'department id, foreign key to department table',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `create_user` bigint DEFAULT NULL,
  `update_user` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `p_did_fk` (`department_id`),
  CONSTRAINT `p_did_fk` FOREIGN KEY (`department_id`) REFERENCES `department` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='position info';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `position`
--

LOCK TABLES `position` WRITE;
/*!40000 ALTER TABLE `position` DISABLE KEYS */;
INSERT INTO `position` VALUES (1,'System Admin',1,'2022-02-15 15:51:20','2022-02-17 09:16:20',1,1),(2,'Manager',2,'2025-07-09 17:05:09','2025-07-09 17:05:09',1,1),(3,'Consultant',2,'2025-07-09 17:05:43','2025-07-09 17:05:43',1,1);
/*!40000 ALTER TABLE `position` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project`
--

DROP TABLE IF EXISTS `project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `project` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `project_name` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `employee_id` bigint NOT NULL COMMENT 'employee id, foreign key to employee table',
  `client_id` bigint NOT NULL COMMENT 'client id, foreign key to client table',
  `department_id` bigint NOT NULL COMMENT 'department id, foreign key to department table',
  `contract_id` bigint NOT NULL COMMENT 'contract id, foreign key to contract table',
  `description` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT 'project description',
  `start_date` date NOT NULL COMMENT 'contract start date',
  `end_date` date NOT NULL COMMENT 'contract end date',
  `status` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL DEFAULT 'In Progress',
  `is_archived` int NOT NULL DEFAULT '0' COMMENT 'status 0:in use, 1:archived',
  `create_time` datetime DEFAULT NULL,
  `create_user` bigint DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_user` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `p_eid_fk` (`employee_id`),
  KEY `p_cid_fk` (`client_id`),
  KEY `pro_did_fk` (`department_id`),
  KEY `p_con_fk` (`contract_id`),
  CONSTRAINT `p_cid_fk` FOREIGN KEY (`client_id`) REFERENCES `client` (`id`),
  CONSTRAINT `p_con_fk` FOREIGN KEY (`contract_id`) REFERENCES `contract` (`id`),
  CONSTRAINT `p_eid_fk` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`),
  CONSTRAINT `pro_did_fk` FOREIGN KEY (`department_id`) REFERENCES `department` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='project info';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project`
--

LOCK TABLES `project` WRITE;
/*!40000 ALTER TABLE `project` DISABLE KEYS */;
INSERT INTO `project` VALUES (1,'Project Name Edit',2,2,2,2,'Test Project 1','2023-01-01','2024-01-01','Project established',0,'2025-07-12 17:02:24',3,'2025-07-12 17:05:30',3),(4,'Project2',2,2,2,1,'Test Project 3','2023-01-01','2024-01-01','Project established',0,'2025-07-12 17:03:43',3,'2025-07-12 17:05:30',3),(5,'Project2',3,2,2,1,'Test Project 4','2023-01-01','2024-01-01','Project established',1,'2025-07-12 17:03:58',3,'2025-07-12 17:05:31',3);
/*!40000 ALTER TABLE `project` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-09-06 20:44:12
