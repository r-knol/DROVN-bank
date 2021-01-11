CREATE DATABASE  IF NOT EXISTS `WebApplicatie_DROVN_Bank` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `WebApplicatie_DROVN_Bank`;
-- MySQL dump 10.13  Distrib 8.0.22, for macos10.15 (x86_64)
--
-- Host: 127.0.0.1    Database: WebApplicatie_DROVN_Bank
-- ------------------------------------------------------
-- Server version	8.0.22

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
-- Table structure for table `Account`
--

DROP TABLE IF EXISTS `Account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Account` (
  `accountID` bigint NOT NULL,
  `iban` varchar(45) NOT NULL,
  `balance` decimal(64,2) NOT NULL,
  PRIMARY KEY (`accountID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Account`
--

LOCK TABLES `Account` WRITE;
/*!40000 ALTER TABLE `Account` DISABLE KEYS */;
INSERT INTO `Account` VALUES (1,'NL87DRVN0981245744',10.00),(2,'NL19DRVN0478579866',1234.99),(3,'NL25DRVN0354785412',10987.87),(4,'NL14DRVN0478712478',22154.48),(5,'NL77DRVN0451247363',899.25),(6,'NL31DRVN0124574999',845147.45),(7,'NL87DRVN0981245744',45112.41),(8,'NL52DRVN0214785124',2874125.74),(9,'NL71DRVN0541263213',89878.77);
/*!40000 ALTER TABLE `Account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `BusinessAccount`
--

DROP TABLE IF EXISTS `BusinessAccount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `BusinessAccount` (
  `accountID` bigint NOT NULL,
  `contactPerson` bigint NOT NULL,
  PRIMARY KEY (`accountID`),
  KEY `fk_BusinessAccount_NaturalPerson1_idx` (`contactPerson`),
  CONSTRAINT `fk_BusinessAccount_Account1` FOREIGN KEY (`accountID`) REFERENCES `Account` (`accountID`),
  CONSTRAINT `fk_BusinessAccount_NaturalPerson1` FOREIGN KEY (`contactPerson`) REFERENCES `NaturalPerson` (`customerID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BusinessAccount`
--

LOCK TABLES `BusinessAccount` WRITE;
/*!40000 ALTER TABLE `BusinessAccount` DISABLE KEYS */;
/*!40000 ALTER TABLE `BusinessAccount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Customer`
--

DROP TABLE IF EXISTS `Customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Customer` (
  `customerID` bigint NOT NULL AUTO_INCREMENT,
  `userName` varchar(16) NOT NULL,
  `password` varchar(16) NOT NULL,
  `customerType` varchar(10) NOT NULL,
  PRIMARY KEY (`customerID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Customer`
--

LOCK TABLES `Customer` WRITE;
/*!40000 ALTER TABLE `Customer` DISABLE KEYS */;
INSERT INTO `Customer` VALUES (1,'loo','loo','NATURAL'),(2,'rknol','rknol','NATURAL'),(3,'hva','hva','LEGAL'),(4,'omkaaij','omkaaij','NATURAL'),(5,'shell','shell','LEGAL'),(6,'ah','ah','LEGAL'),(7,'bol','bol','LEGAL');
/*!40000 ALTER TABLE `Customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Customer_has_Account`
--

DROP TABLE IF EXISTS `Customer_has_Account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Customer_has_Account` (
  `customerID` bigint NOT NULL,
  `accountID` bigint NOT NULL,
  PRIMARY KEY (`accountID`,`customerID`),
  KEY `fk_Customer_has_Account_Account1_idx` (`accountID`),
  KEY `fk_Customer_has_Account_Customer1_idx` (`customerID`),
  CONSTRAINT `fk_Customer_has_Account_Account1` FOREIGN KEY (`accountID`) REFERENCES `Account` (`accountID`),
  CONSTRAINT `fk_Customer_has_Account_Customer1` FOREIGN KEY (`customerID`) REFERENCES `Customer` (`customerID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Customer_has_Account`
--

LOCK TABLES `Customer_has_Account` WRITE;
/*!40000 ALTER TABLE `Customer_has_Account` DISABLE KEYS */;
INSERT INTO `Customer_has_Account` VALUES (1,1),(2,1),(2,2),(3,3),(4,4),(4,5),(5,6),(6,7),(7,8);
/*!40000 ALTER TABLE `Customer_has_Account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Employee`
--

DROP TABLE IF EXISTS `Employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Employee` (
  `employeeID` bigint NOT NULL,
  `role` varchar(45) NOT NULL,
  PRIMARY KEY (`employeeID`),
  KEY `fk_Employee_NaturalPerson_idx` (`employeeID`),
  CONSTRAINT `fk_Employee_NaturalPerson` FOREIGN KEY (`employeeID`) REFERENCES `NaturalPerson` (`customerID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Employee`
--

LOCK TABLES `Employee` WRITE;
/*!40000 ALTER TABLE `Employee` DISABLE KEYS */;
/*!40000 ALTER TABLE `Employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `LegalPerson`
--

DROP TABLE IF EXISTS `LegalPerson`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `LegalPerson` (
  `companyID` bigint NOT NULL AUTO_INCREMENT,
  `companyName` varchar(45) NOT NULL,
  `kvkNumber` bigint NOT NULL,
  `sector` varchar(45) DEFAULT NULL,
  `vatNumber` varchar(20) NOT NULL,
  `postalCode` varchar(7) NOT NULL,
  `homeNumber` varchar(10) NOT NULL,
  `street` varchar(45) NOT NULL,
  `residence` varchar(45) NOT NULL,
  `accountmanagerID` bigint DEFAULT NULL,
  PRIMARY KEY (`companyID`),
  KEY `fk_LegalPerson_Customer_idx` (`companyID`),
  CONSTRAINT `fk_LegalPerson_Customer` FOREIGN KEY (`companyID`) REFERENCES `Customer` (`customerID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `LegalPerson`
--

LOCK TABLES `LegalPerson` WRITE;
/*!40000 ALTER TABLE `LegalPerson` DISABLE KEYS */;
INSERT INTO `LegalPerson` VALUES (3,'Hogeschool van Amsterdam',12345676,'EDUCATION','1234567','1000 AB','1','Wibautstraat','Amsterdam',NULL),(5,'Shell Nederland B.V.',21784524,'ENERGIE','7654321','2596 JM','2','Oostduinlaan','Den Haag',NULL),(6,'AH Kanaleneiland',47851353,'RETAIL','6521476','3527 HE','64','Hammarskjoldhof','Utrecht',NULL),(7,'BOL.com',74444666,'RETAIL','4155463','3528 BJ','100','Papendorpseweg','Utrecht',NULL);
/*!40000 ALTER TABLE `LegalPerson` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `NaturalPerson`
--

DROP TABLE IF EXISTS `NaturalPerson`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `NaturalPerson` (
  `customerID` bigint NOT NULL AUTO_INCREMENT,
  `initials` varchar(10) NOT NULL,
  `firstName` varchar(15) NOT NULL,
  `preposition` varchar(15) DEFAULT NULL,
  `surName` varchar(45) NOT NULL,
  `dateOfBirth` date NOT NULL,
  `socialSecurityNumber` varchar(9) NOT NULL,
  `email` varchar(45) NOT NULL,
  `phone` varchar(14) NOT NULL,
  `postalCode` varchar(7) NOT NULL,
  `homeNumber` varchar(10) NOT NULL,
  `street` varchar(45) NOT NULL,
  `residence` varchar(45) NOT NULL,
  PRIMARY KEY (`customerID`),
  UNIQUE KEY `socialSecurityNumber_UNIQUE` (`socialSecurityNumber`),
  KEY `fk_NaturalPerson_Customer_idx` (`customerID`),
  CONSTRAINT `fk_NaturalPerson_Customer` FOREIGN KEY (`customerID`) REFERENCES `Customer` (`customerID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `NaturalPerson`
--

LOCK TABLES `NaturalPerson` WRITE;
/*!40000 ALTER TABLE `NaturalPerson` DISABLE KEYS */;
INSERT INTO `NaturalPerson` VALUES (1,'N.','Nina','van','Loo','1987-02-07','159398289','ninavanloo@gmail.com','0610087058','1056 AC','2-H','James Rosskade','Amsterdam'),(2,'R.W.','Richard',NULL,'Knol','1990-05-11','243535345','rknol@gmail.com','0612345678','1234 AQ','345','Oudenoord','Utrecht'),(4,'O.M.','Olaf','van der','Kaaij','1976-12-25','178514235','omkaaij@gmail.com','06-87654321','1234 VT','298','Dorpsweg','Utrecht');
/*!40000 ALTER TABLE `NaturalPerson` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SPRING_SESSION`
--

DROP TABLE IF EXISTS `SPRING_SESSION`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `SPRING_SESSION` (
  `PRIMARY_ID` char(36) NOT NULL,
  `SESSION_ID` char(36) NOT NULL,
  `CREATION_TIME` bigint NOT NULL,
  `LAST_ACCESS_TIME` bigint NOT NULL,
  `MAX_INACTIVE_INTERVAL` int NOT NULL,
  `EXPIRY_TIME` bigint NOT NULL,
  `PRINCIPAL_NAME` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`PRIMARY_ID`),
  UNIQUE KEY `SPRING_SESSION_IX1` (`SESSION_ID`),
  KEY `SPRING_SESSION_IX2` (`EXPIRY_TIME`),
  KEY `SPRING_SESSION_IX3` (`PRINCIPAL_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SPRING_SESSION`
--

LOCK TABLES `SPRING_SESSION` WRITE;
/*!40000 ALTER TABLE `SPRING_SESSION` DISABLE KEYS */;
INSERT INTO `SPRING_SESSION` VALUES ('423b4c38-96b8-485c-b633-be1b783b6fa6','9c5d0433-785f-41d0-b3a8-5fa0f2a81e79',1610384991194,1610385069263,1800,1610386869263,NULL);
/*!40000 ALTER TABLE `SPRING_SESSION` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SPRING_SESSION_ATTRIBUTES`
--

DROP TABLE IF EXISTS `SPRING_SESSION_ATTRIBUTES`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `SPRING_SESSION_ATTRIBUTES` (
  `SESSION_PRIMARY_ID` char(36) NOT NULL,
  `ATTRIBUTE_NAME` varchar(200) NOT NULL,
  `ATTRIBUTE_BYTES` blob NOT NULL,
  PRIMARY KEY (`SESSION_PRIMARY_ID`,`ATTRIBUTE_NAME`),
  CONSTRAINT `SPRING_SESSION_ATTRIBUTES_FK` FOREIGN KEY (`SESSION_PRIMARY_ID`) REFERENCES `SPRING_SESSION` (`PRIMARY_ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SPRING_SESSION_ATTRIBUTES`
--

LOCK TABLES `SPRING_SESSION_ATTRIBUTES` WRITE;
/*!40000 ALTER TABLE `SPRING_SESSION_ATTRIBUTES` DISABLE KEYS */;
INSERT INTO `SPRING_SESSION_ATTRIBUTES` VALUES ('423b4c38-96b8-485c-b633-be1b783b6fa6','customer',_binary '¨\Ì\0sr\0.nl.hva.miw.internetbanking.model.NaturalPerson ñ\Ï¯\ÕZ\r\0L\0dateOfBirtht\0Ljava/lang/String;L\0emailq\0~\0L\0	firstNameq\0~\0L\0\nhomeNumberq\0~\0L\0initialsq\0~\0L\0phoneq\0~\0L\0\npostalCodeq\0~\0L\0prepositionq\0~\0L\0	residenceq\0~\0L\0socialSecurityNumberq\0~\0L\0streetq\0~\0L\0surNameq\0~\0xr\0)nl.hva.miw.internetbanking.model.Customer^Gu\œ\0cçí\0J\0\ncustomerIDL\0accountst\0Ljava/util/List;L\0customerTypet\0/Lnl/hva/miw/internetbanking/model/CustomerType;L\0passwordq\0~\0L\0userNameq\0~\0xp\0\0\0\0\0\0\0sr\0java.util.ArrayListxÅ\“ô\«aù\0I\0sizexp\0\0\0w\0\0\0sr\0(nl.hva.miw.internetbanking.model.Account{\“˛á\’x/\0J\0	accountIDD\0balanceL\0accountHolderNamesq\0~\0L\0accountHoldersq\0~\0L\0ibanq\0~\0L\0transactiont\0.Lnl/hva/miw/internetbanking/model/Transaction;L\0transactionsq\0~\0xp\0\0\0\0\0\0\0@’¢û∏Q\ÎÖsq\0~\0\0\0\0w\0\0\0t\0Olaf van der Kaaijxsq\0~\0\0\0\0w\0\0\0sq\0~\0\0\0\0\0\0\0\0sq\0~\0\0\0\0\0w\0\0\0\0x~r\0-nl.hva.miw.internetbanking.model.CustomerType\0\0\0\0\0\0\0\0\0\0xr\0java.lang.Enum\0\0\0\0\0\0\0\0\0\0xpt\0NATURALt\0omkaaijt\0omkaaijxt\0NL14DRVN0478712478psq\0~\0\0\0\0\0w\0\0\0\0xsq\0~\0\0\0\0\0\0\0\0@å\Z\0\0\0\0\0sq\0~\0\0\0\0w\0\0\0t\0Olaf van der Kaaijxsq\0~\0\0\0\0w\0\0\0sq\0~\0\0\0\0\0\0\0\0sq\0~\0\0\0\0\0w\0\0\0\0xq\0~\0t\0omkaaijt\0omkaaijxt\0NL77DRVN0451247363psq\0~\0\0\0\0\0w\0\0\0\0xxq\0~\0t\0omkaaijt\0omkaaijt\0\n1976-12-25t\0omkaaij@gmail.comt\0Olaft\0298t\0O.M.t\006-87654321t\01234 VTt\0van dert\0Utrechtt\0	178514235t\0Dorpswegt\0Kaaij'),('423b4c38-96b8-485c-b633-be1b783b6fa6','nameCurrentCus',_binary '¨\Ì\0t\0Olaf van der Kaaij');
/*!40000 ALTER TABLE `SPRING_SESSION_ATTRIBUTES` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Transaction`
--

DROP TABLE IF EXISTS `Transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Transaction` (
  `transactionID` bigint NOT NULL,
  `amount` decimal(64,2) NOT NULL,
  `description` varchar(45) NOT NULL,
  `dateTime` datetime NOT NULL,
  `creditAccount` varchar(45) NOT NULL,
  `debitAccount` varchar(45) NOT NULL,
  PRIMARY KEY (`transactionID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Transaction`
--

LOCK TABLES `Transaction` WRITE;
/*!40000 ALTER TABLE `Transaction` DISABLE KEYS */;
INSERT INTO `Transaction` VALUES (1,45.00,'transactie','2020-12-30 12:25:59','NL87DRVN0981245744','NL14DRVN0478712478'),(2,1252.50,'transactie','2020-12-29 08:15:00','NL25DRVN0354785412','NL14DRVN0478712478'),(3,2514.78,'transactie','2020-12-27 15:32:14','NL14DRVN0478712478','NL31DRVN0124574999'),(4,99.99,'transactie','2020-12-16 14:47:18','NL19DRVN0478579866','NL14DRVN0478712478'),(5,78.56,'transactie','2020-12-15 16:47:33','NL19DRVN0478579866','NL14DRVN0478712478'),(6,24.99,'pinbetaling','2020-12-14 10:03:15','NL78DRVN0254778965','NL14DRVN0478712478'),(7,39.25,'pinbetaling','2020-12-12 08:58:56','NL78DRVN0254778965','NL14DRVN0478712478'),(8,8.00,'pinbetaling','2020-12-11 17:12:35','NL78DRVN0254778965','NL14DRVN0478712478'),(9,79.99,'iDeal betaling','2020-12-11 11:33:26','NL52DRVN0214785124','NL14DRVN0478712478'),(10,19.99,'iDeal betaling','2020-12-10 23:14:59','NL52DRVN0214785124','NL14DRVN0478712478'),(11,185.23,'Incasso','2020-12-06 00:00:00','NL71DRVN0541263213','NL14DRVN0478712478');
/*!40000 ALTER TABLE `Transaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Transaction_has_account`
--

DROP TABLE IF EXISTS `Transaction_has_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Transaction_has_account` (
  `accountID` bigint NOT NULL,
  `transactionID` bigint NOT NULL,
  PRIMARY KEY (`accountID`,`transactionID`),
  KEY `fk_table1_Transaction1_idx` (`transactionID`),
  CONSTRAINT `fk_table1_Account1` FOREIGN KEY (`accountID`) REFERENCES `Account` (`accountID`),
  CONSTRAINT `fk_table1_Transaction1` FOREIGN KEY (`transactionID`) REFERENCES `Transaction` (`transactionID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Transaction_has_account`
--

LOCK TABLES `Transaction_has_account` WRITE;
/*!40000 ALTER TABLE `Transaction_has_account` DISABLE KEYS */;
INSERT INTO `Transaction_has_account` VALUES (1,1),(4,1),(3,2),(4,2),(3,3),(4,3),(2,4),(4,4),(2,5),(4,5),(4,6),(7,6),(4,7),(7,7),(4,8),(7,8),(4,9),(8,9),(4,10),(8,10),(4,11),(6,11);
/*!40000 ALTER TABLE `Transaction_has_account` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-01-11 18:27:23
