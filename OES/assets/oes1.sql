CREATE DATABASE  IF NOT EXISTS `oes` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `oes`;
-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: oes
-- ------------------------------------------------------
-- Server version	8.0.31

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
-- Table structure for table `course_details`
--

DROP TABLE IF EXISTS `course_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course_details` (
  `course_name` varchar(20) NOT NULL,
  `time` time NOT NULL,
  `course_no` int unsigned NOT NULL AUTO_INCREMENT,
  `date_of_creation` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  `hide` smallint unsigned NOT NULL DEFAULT '0',
  `total_question` int unsigned NOT NULL DEFAULT '0',
  `question_mark` int unsigned NOT NULL DEFAULT '4',
  PRIMARY KEY (`course_name`),
  UNIQUE KEY `course_no` (`course_no`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_details`
--

LOCK TABLES `course_details` WRITE;
/*!40000 ALTER TABLE `course_details` DISABLE KEYS */;
INSERT INTO `course_details` VALUES ('Aptitude','00:10:00',40,'2023-01-24 07:32:31',0,0,1),('Java','00:10:00',39,'2023-01-30 07:10:42',0,11,1),('Math','00:10:00',41,'2023-01-27 10:50:51',0,2,1);
/*!40000 ALTER TABLE `course_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `questions`
--

DROP TABLE IF EXISTS `questions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `questions` (
  `q_id` varchar(5) NOT NULL,
  `q_statement` longtext NOT NULL,
  `option_1` varchar(150) NOT NULL,
  `option_2` varchar(150) NOT NULL,
  `option_3` varchar(150) NOT NULL,
  `option_4` varchar(150) NOT NULL,
  `correct_option` int NOT NULL,
  `course_name` varchar(20) NOT NULL,
  PRIMARY KEY (`q_id`),
  KEY `course_name` (`course_name`),
  CONSTRAINT `questions_ibfk_1` FOREIGN KEY (`course_name`) REFERENCES `course_details` (`course_name`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `questions`
--

LOCK TABLES `questions` WRITE;
/*!40000 ALTER TABLE `questions` DISABLE KEYS */;
INSERT INTO `questions` VALUES ('','','','','','',1,'Java'),('1','eyr87y																																																																												','euyriuyr','serjsd','edjgf','jfkdsj',1,'Math'),('1.','What is the full form of JVM ?','Java Virtual Machine','Java Visual Machine','Java Viral Machine ','Java Verified Machine',1,'Java'),('10','Select the valid statement','char[] ch = new char(5)','char[] ch = new char()','char[] ch = new char[5]','char[] ch = new char[]',3,'Java'),('2','What will be the output of the following Java code?\n  \nclass increment {       \n         public static void main(String args[]) {       \n               int g = 3;           \n                 System.out.print(++g * 8);    \n        }\n    }','33','32','24','25',2,'Java'),('3','Exception handling is targeted at','Logical error','Compile time error','Run time error','All of the above',3,'Java'),('4','Which of the following is not Java keywords ?','switch','instanceof','then','double ',3,'Java'),('5','Which component is used to compile, debug and execute the java programs?','JVM','JRE','JIT','JDK',4,'Java'),('6','Modulus operator (%) can be applied to which of these?','Integers','Floating - point numbers','Both A and B','None of the above',3,'Java'),('7','What is the size of float and double in java?','32 and 64','32 and 32','64 and 32','64 and 64',1,'Java'),('8','Find the output of the following code.  \nint Integer = 24;\nchar String  = ‘I’;\nSystem.out.print(Integer);\nSystem.out.print(String);','Exception','24 I','I','Compile time error',2,'Java'),('9','compareTo() returns','True ','False','An Int Value','None',3,'Java'),('m2','sdnfkjsf','dfi','dkfir','sdlf','ld,f',1,'Math');
/*!40000 ALTER TABLE `questions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `result`
--

DROP TABLE IF EXISTS `result`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `result` (
  `Username` varchar(15) NOT NULL,
  `course_name` varchar(20) NOT NULL,
  `time_taken` time NOT NULL DEFAULT '00:00:00',
  `total_questions` int NOT NULL,
  `wrong_question` int NOT NULL,
  `attempted_question` int NOT NULL,
  `obtained_marks` int NOT NULL,
  `total_marks` int NOT NULL,
  `percentage` float NOT NULL,
  `test_date` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  KEY `Username` (`Username`),
  KEY `course_name` (`course_name`),
  CONSTRAINT `result_ibfk_1` FOREIGN KEY (`Username`) REFERENCES `userdetails` (`Username`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `result_ibfk_2` FOREIGN KEY (`course_name`) REFERENCES `course_details` (`course_name`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `result`
--

LOCK TABLES `result` WRITE;
/*!40000 ALTER TABLE `result` DISABLE KEYS */;
INSERT INTO `result` VALUES ('Anita','Java','00:00:45',11,1,11,10,11,90.9091,'2023-01-31 04:53:53'),('Anita','Java','00:00:54',11,1,11,10,11,90.9091,'2023-01-31 06:30:24'),('Anita','Java','00:10:00',11,0,0,0,11,0,'2023-02-01 05:53:14'),('Anita','Java','00:00:07',11,0,1,1,11,9.09091,'2023-02-01 05:53:54'),('Anita','Java','00:10:00',11,0,0,0,11,0,'2023-02-01 05:59:36'),('Anita','Java','00:10:00',11,0,0,0,11,0,'2023-02-01 05:59:42');
/*!40000 ALTER TABLE `result` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userdetails`
--

DROP TABLE IF EXISTS `userdetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `userdetails` (
  `Username` varchar(15) NOT NULL,
  `Password` varchar(15) NOT NULL DEFAULT '12345',
  `FirstName` varchar(10) NOT NULL,
  `MiddleName` varchar(20) DEFAULT '',
  `LastName` varchar(10) DEFAULT '',
  `Gender` char(1) NOT NULL,
  `E-mail` varchar(30) NOT NULL,
  `Mobile` char(10) NOT NULL,
  `DOB` date NOT NULL,
  `RegNo` int unsigned NOT NULL AUTO_INCREMENT,
  `RegDate` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  `Address` longtext NOT NULL,
  `College` varchar(60) NOT NULL,
  `Verify` tinyint(1) NOT NULL DEFAULT '0',
  `LoginAttempts` int DEFAULT NULL,
  PRIMARY KEY (`RegNo`),
  UNIQUE KEY `Username` (`Username`),
  UNIQUE KEY `E-mail` (`E-mail`),
  UNIQUE KEY `Mobile` (`Mobile`)
) ENGINE=InnoDB AUTO_INCREMENT=77 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userdetails`
--

LOCK TABLES `userdetails` WRITE;
/*!40000 ALTER TABLE `userdetails` DISABLE KEYS */;
INSERT INTO `userdetails` VALUES ('Anita','12345','Anita','V.','Dukare','F','anita.dukare@vpel.in','9730700560','2001-03-20',76,'2023-02-01 05:53:47','Pride Icon, office no. 108. ','Virtuoso SoftTech',1,6);
/*!40000 ALTER TABLE `userdetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'oes'
--

--
-- Dumping routines for database 'oes'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-02-01 14:18:29
