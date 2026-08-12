-- MySQL dump 10.13  Distrib 8.0.46, for Win64 (x86_64)
--
-- Host: localhost    Database: token_display_db
-- ------------------------------------------------------
-- Server version	8.0.46

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `token_counter`
--

DROP TABLE IF EXISTS `token_counter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `token_counter` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `counter_code` varchar(255) NOT NULL,
  `active` bit(1) NOT NULL,
  `display_name` varchar(255) NOT NULL,
  `counter_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK5r5oijp5lo8frljhs8s48lcst` (`counter_code`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `token_counter`
--

LOCK TABLES `token_counter` WRITE;
/*!40000 ALTER TABLE `token_counter` DISABLE KEYS */;
INSERT INTO `token_counter` VALUES (1,'2026-07-30 18:01:24.000000',NULL,'c1',_binary '','Display 1','counter 1'),(2,'2026-07-30 18:01:24.000000',NULL,'c2',_binary '','Display  2','counter  2'),(3,'2026-07-30 18:01:24.000000',NULL,'c3',_binary '','Display  3','counter  3');
/*!40000 ALTER TABLE `token_counter` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `token_queue`
--

DROP TABLE IF EXISTS `token_queue`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `token_queue` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `called_time` datetime(6) DEFAULT NULL,
  `status` enum('CALLED','COMPLETED','WAITING') NOT NULL,
  `token_number` int NOT NULL,
  `counter_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKixn4dwqpy0te08tbqv114pneb` (`counter_id`),
  CONSTRAINT `FKixn4dwqpy0te08tbqv114pneb` FOREIGN KEY (`counter_id`) REFERENCES `token_counter` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `token_queue`
--

LOCK TABLES `token_queue` WRITE;
/*!40000 ALTER TABLE `token_queue` DISABLE KEYS */;
INSERT INTO `token_queue` VALUES (1,'2026-08-11 15:09:00.625132','2026-08-11 15:09:35.988045','2026-08-11 15:09:20.536817','COMPLETED',1,1),(2,'2026-08-11 15:09:02.956367','2026-08-11 15:09:24.654175','2026-08-11 15:09:24.654175','CALLED',2,2),(3,'2026-08-11 15:09:10.007377','2026-08-11 15:09:27.198620','2026-08-11 15:09:27.198620','CALLED',3,3),(4,'2026-08-11 15:09:12.128034','2026-08-11 15:09:12.128034',NULL,'WAITING',4,NULL),(5,'2026-08-11 15:10:40.031861','2026-08-11 15:10:40.031861',NULL,'WAITING',5,NULL);
/*!40000 ALTER TABLE `token_queue` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-08-11 15:45:04
