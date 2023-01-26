CREATE DATABASE  IF NOT EXISTS `practice-db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `practice-db`;
-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: practice-db
-- ------------------------------------------------------
-- Server version	8.0.29

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
-- Table structure for table `employee_db`
--

DROP TABLE IF EXISTS `employee_db`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee_db` (
  `email_id` varchar(100) NOT NULL,
  `password` varchar(20) NOT NULL,
  `age` int DEFAULT NULL,
  `name` varchar(50) NOT NULL,
  `Employee_id` varchar(45) NOT NULL,
  `address` varchar(500) DEFAULT NULL,
  `department` varchar(45) DEFAULT NULL,
  `designation` varchar(45) DEFAULT NULL,
  `basic_salary` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`email_id`,`Employee_id`),
  UNIQUE KEY `Employee_id_UNIQUE` (`Employee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_db`
--

LOCK TABLES `employee_db` WRITE;
/*!40000 ALTER TABLE `employee_db` DISABLE KEYS */;
INSERT INTO `employee_db` VALUES ('akash@gmail.com','akash1234',21,'Akash','e1','Asansol','Production','Clerk','24000'),('arindam@gmail.com','arindam1234',21,'Arindam','e2','Asansol','Production','Manager','54000'),('arnav@gmail.com','arnav1234',34,'Arnav','e3','Asansol','Sales','Clerk','14000'),('ayan@gmail.com','ayan1234',24,'Ayan','e4','Durgapur','Sales','Supervisior','44000'),('bappa@gmail.com','bappa1234',35,'Bappa','e5','Kolkata','Production','Supervisor','56000'),('bikram@gmail.com','bikram1234',21,'Bikram','e6','Barddhaman','Admin','Manager','45000'),('Bittu@gmail.com','bittu1234',25,'Bittu','e7','Asansol','Production','Manager','41000'),('pratik@gmail.com','pratik1234',27,'Pratik','e8','Kolkata','Production','Clerk','22000'),('sasuke@gmail.com','sasuke1234',25,'Sasuke','e9','Asansol','Production','Supervisior','35000'),('shuvam@gmail.com','shuvam1234',24,'Shuvam','e10','Barddhaman','Admin','Supervisor','63000'),('sourav@gmail.com','sourav1234',29,'Sourav','e11','Kolkata','Sales','Manager','54000'),('souvik@gmail.com','souvik1234',23,'Souvik','e12','Asansol','Sales','Manager','29000'),('sudip@gmail.com','sudip1234',39,'Sudip','e13','Durgapur','Sales','Supervisor','35000'),('suvo@gmail.com','suvo1234',19,'Suvo','e14','Durgapur','Sales','Clerk','19000');
/*!40000 ALTER TABLE `employee_db` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-08-01  8:18:17
