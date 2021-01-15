/*!40101 SET @OLD_CHARACTER_SET_CLIENT = @@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS = @@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION = @@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE = @@TIME_ZONE */;
/*!40103 SET TIME_ZONE = '+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0 */;
/*!40101 SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES = @@SQL_NOTES, SQL_NOTES = 0 */;

CREATE SCHEMA IF NOT EXISTS `drovn` DEFAULT CHARACTER SET utf8;
USE `drovn`;

--
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account`
(
    `accountID` bigint         NOT NULL AUTO_INCREMENT,
    `iban`      varchar(45)    NOT NULL,
    `balance`   decimal(64, 2) NOT NULL,
    PRIMARY KEY (`accountID`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 7001
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer`
(
    `customerID`   bigint      NOT NULL AUTO_INCREMENT,
    `userName`     varchar(20) NOT NULL,
    `password`     varchar(20) NOT NULL,
    `customerType` varchar(25) NOT NULL,
    PRIMARY KEY (`customerID`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 5001
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `customer_has_account`
--

DROP TABLE IF EXISTS `customer_has_account`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer_has_account`
(
    `customerID` bigint NOT NULL,
    `accountID`  bigint NOT NULL,
    PRIMARY KEY (`accountID`, `customerID`),
    KEY `fk_Customer_has_Account_Account1_idx` (`accountID`),
    KEY `fk_Customer_has_Account_Customer1_idx` (`customerID`),
    CONSTRAINT `fk_Customer_has_Account_Account1` FOREIGN KEY (`accountID`) REFERENCES `account` (`accountID`) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `fk_Customer_has_Account_Customer1` FOREIGN KEY (`customerID`) REFERENCES `customer` (`customerID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee`
(
    `employeeID`  bigint      NOT NULL AUTO_INCREMENT,
    `userName`    varchar(17) NOT NULL,
    `password`    varchar(17) NOT NULL,
    `firstName`   varchar(25) NOT NULL,
    `preposition` varchar(15) DEFAULT NULL,
    `surName`     varchar(45) NOT NULL,
    `role`        varchar(25) NOT NULL,
    PRIMARY KEY (`employeeID`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 8
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `legalperson`
--

DROP TABLE IF EXISTS `legalperson`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `legalperson`
(
    `companyID`        bigint      NOT NULL AUTO_INCREMENT,
    `companyName`      varchar(45) NOT NULL,
    `kvkNumber`        bigint      NOT NULL,
    `sector`           varchar(45) DEFAULT NULL,
    `vatNumber`        varchar(16) NOT NULL,
    `postalCode`       varchar(7)  NOT NULL,
    `homeNumber`       varchar(10) NOT NULL,
    `street`           varchar(45) NOT NULL,
    `residence`        varchar(45) NOT NULL,
    `accountmanagerID` bigint      NOT NULL,
    PRIMARY KEY (`companyID`),
    KEY `fk_LegalPerson_Customer_idx` (`companyID`),
    KEY `fk_LegalPerson_Employee_idx` (`accountmanagerID`),
    CONSTRAINT `fk_LegalPerson_Customer` FOREIGN KEY (`companyID`) REFERENCES `customer` (`customerID`),
    CONSTRAINT `fk_LegalPerson_Employee` FOREIGN KEY (`accountmanagerID`) REFERENCES `employee` (`employeeID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB
  AUTO_INCREMENT = 5001
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `naturalperson`
--

DROP TABLE IF EXISTS `naturalperson`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `naturalperson`
(
    `customerID`           bigint      NOT NULL AUTO_INCREMENT,
    `initials`             varchar(15) NOT NULL,
    `firstName`            varchar(25) NOT NULL,
    `preposition`          varchar(15) DEFAULT NULL,
    `surName`              varchar(45) NOT NULL,
    `dateOfBirth`          varchar(10) NOT NULL,
    `socialSecurityNumber` varchar(10) NOT NULL,
    `email`                varchar(45) NOT NULL,
    `phone`                varchar(45) NOT NULL,
    `postalCode`           varchar(7)  NOT NULL,
    `homeNumber`           varchar(10) NOT NULL,
    `street`               varchar(45) NOT NULL,
    `residence`            varchar(45) NOT NULL,
    PRIMARY KEY (`customerID`),
    UNIQUE KEY `socialSecurityNumber_UNIQUE` (`socialSecurityNumber`),
    KEY `fk_NaturalPerson_Customer_idx` (`customerID`),
    CONSTRAINT `fk_NaturalPerson_Customer` FOREIGN KEY (`customerID`) REFERENCES `customer` (`customerID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB
  AUTO_INCREMENT = 4001
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `spring_session`
--

DROP TABLE IF EXISTS `spring_session`;

--
-- Table structure for table `spring_session_attributes`
--

DROP TABLE IF EXISTS `spring_session_attributes`;

--
-- Table structure for table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transaction`
(
    `transactionID` bigint         NOT NULL AUTO_INCREMENT,
    `amount`        decimal(64, 2) NOT NULL,
    `description`   varchar(45)    NOT NULL,
    `dateTime`      datetime       NOT NULL,
    `creditAccount` varchar(45)    NOT NULL,
    `debitAccount`  varchar(45)    NOT NULL,
    PRIMARY KEY (`transactionID`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 10001
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `transaction_has_account`
--

DROP TABLE IF EXISTS `transaction_has_account`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transaction_has_account`
(
    `accountID`     bigint NOT NULL,
    `transactionID` bigint NOT NULL,
    PRIMARY KEY (`accountID`, `transactionID`),
    KEY `fk_transaction_has_account_account_idx` (`accountID`),
    KEY `fk_transaction_has_account_transaction_idx` (`transactionID`),
    CONSTRAINT `fk_account_has_transaction` FOREIGN KEY (`transactionID`) REFERENCES `transaction` (`transactionID`),
    CONSTRAINT `fk_transaction_has_account` FOREIGN KEY (`accountID`) REFERENCES `account` (`accountID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

/*!40101 SET SQL_MODE = @OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT = @OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS = @OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION = @OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES = @OLD_SQL_NOTES */;
