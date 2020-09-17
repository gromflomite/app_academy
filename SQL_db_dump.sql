-- MariaDB dump 10.17  Distrib 10.5.5-MariaDB, for Win64 (AMD64)
--
-- Host: localhost    Database: app_academy
-- ------------------------------------------------------
-- Server version	10.4.11-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `courses`
--

DROP TABLE IF EXISTS `courses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `courses` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `identifier` varchar(10) NOT NULL,
  `hours` int(11) NOT NULL,
  `id_professor` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `courses_un` (`name`),
  UNIQUE KEY `courses_un_identifier` (`identifier`),
  KEY `FK_courses_need_professor` (`id_professor`),
  CONSTRAINT `FK_courses_need_professor` FOREIGN KEY (`id_professor`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COMMENT='Table of courses.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `courses`
--

LOCK TABLES `courses` WRITE;
/*!40000 ALTER TABLE `courses` DISABLE KEYS */;
INSERT INTO `courses` VALUES (1,'Exiting VIM','I001',50,1),(2,'Develop your web with COBOL','I002',630,2),(3,'Mastering Stack Overflow','I003',510,3),(25,'Python in one afternoon','I004',5,2),(26,'Assembly for dummies','I005',6,2),(27,'Don\'t understanding JavaScript','I006',17500,2),(29,'Fight to the death against CSS','I007',15000,2),(30,'Test course','T001',15,2),(32,'Test course II','T002',25,2),(35,'Test course III','T003',55,2);
/*!40000 ALTER TABLE `courses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `students_course`
--

DROP TABLE IF EXISTS `students_course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `students_course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_student` int(11) NOT NULL,
  `id_course` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `students_course_FK` (`id_student`),
  KEY `students_course_FK_1` (`id_course`),
  CONSTRAINT `FK_course_needs_student` FOREIGN KEY (`id_student`) REFERENCES `users` (`id`),
  CONSTRAINT `FK_studenst_needs_course` FOREIGN KEY (`id_course`) REFERENCES `courses` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COMMENT='Table of students and their courses.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `students_course`
--

LOCK TABLES `students_course` WRITE;
/*!40000 ALTER TABLE `students_course` DISABLE KEYS */;
INSERT INTO `students_course` VALUES (1,4,1),(2,4,2),(3,5,1),(4,5,3),(5,6,2),(6,6,3),(7,7,1),(8,7,3),(19,4,32),(21,4,30),(22,4,26),(23,4,35);
/*!40000 ALTER TABLE `students_course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `surname` varchar(100) NOT NULL,
  `role` int(11) NOT NULL COMMENT '1 -> Student - 2 -> Professor',
  `password` varchar(150) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `users_un` (`surname`,`name`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COMMENT='Table of users.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Alain','O\'Sullivan',2,'e10adc3949ba59abbe56e057f20f883e'),(2,'Ander ','McCarthy',2,'e10adc3949ba59abbe56e057f20f883e'),(3,'Manolo','Davies',2,'e10adc3949ba59abbe56e057f20f883e'),(4,'Diego','Velázquez',1,'e10adc3949ba59abbe56e057f20f883e'),(5,'Francisco','Goya',1,'e10adc3949ba59abbe56e057f20f883e'),(6,'Joan','Miró',1,'e10adc3949ba59abbe56e057f20f883e'),(7,'Salvador','Dalí',1,'e10adc3949ba59abbe56e057f20f883e'),(8,'Pablo','Picasso',1,'e10adc3949ba59abbe56e057f20f883e');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'app_academy'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-09-17 20:12:17
