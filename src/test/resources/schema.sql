SET MODE MYSQL;

CREATE SCHEMA IF NOT EXISTS `testdb`; /*DEFAULT CHARACTER SET utf8;*/
USE `testdb`;

--
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
CREATE TABLE `account`
(
    `accountID` bigint         NOT NULL AUTO_INCREMENT,
    `iban`      varchar(45)    NOT NULL,
    `balance`   decimal(64, 2) NOT NULL,
    PRIMARY KEY (`accountID`)
) ENGINE = InnoDB;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer`
(
    `customerID`   bigint      NOT NULL AUTO_INCREMENT,
    `userName`     varchar(20) NOT NULL,
    `password`     varchar(20) NOT NULL,
    `customerType` varchar(25) NOT NULL,
    PRIMARY KEY (`customerID`)
) ENGINE = InnoDB;

--
-- Table structure for table `customer_has_account`
--

DROP TABLE IF EXISTS `customer_has_account`;
CREATE TABLE `customer_has_account`
(
    `customerID` bigint NOT NULL,
    `accountID`  bigint NOT NULL,
    PRIMARY KEY (`accountID`, `customerID`),
    KEY `fk_Customer_has_Account_Account1_idx` (`accountID`),
    KEY `fk_Customer_has_Account_Customer1_idx` (`customerID`),
    CONSTRAINT `fk_Customer_has_Account_Account1` FOREIGN KEY (`accountID`) REFERENCES `account` (`accountID`) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `fk_Customer_has_Account_Customer1` FOREIGN KEY (`customerID`) REFERENCES `customer` (`customerID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
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
) ENGINE = InnoDB;

--
-- Table structure for table `legalperson`
--

DROP TABLE IF EXISTS `legalperson`;
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
) ENGINE = InnoDB;

--
-- Table structure for table `naturalperson`
--

DROP TABLE IF EXISTS `naturalperson`;
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
) ENGINE = InnoDB;

DROP TABLE IF EXISTS `spring_session`;
DROP TABLE IF EXISTS `spring_session_attributes`;

--
-- Table structure for table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
CREATE TABLE `transaction`
(
    `transactionID` bigint         NOT NULL AUTO_INCREMENT,
    `amount`        decimal(64, 2) NOT NULL,
    `description`   varchar(45)    NOT NULL,
    `dateTime`      datetime       NOT NULL,
    `creditAccount` varchar(45)    NOT NULL,
    `debitAccount`  varchar(45)    NOT NULL,
    PRIMARY KEY (`transactionID`)
) ENGINE = InnoDB;

--
-- Table structure for table `transaction_has_account`
--

DROP TABLE IF EXISTS `transaction_has_account`;
CREATE TABLE `transaction_has_account`
(
    `accountID`     bigint NOT NULL,
    `transactionID` bigint NOT NULL,
    PRIMARY KEY (`accountID`, `transactionID`),
    KEY `fk_transaction_has_account_account_idx` (`accountID`),
    KEY `fk_transaction_has_account_transaction_idx` (`transactionID`),
    CONSTRAINT `fk_account_has_transaction` FOREIGN KEY (`transactionID`) REFERENCES `transaction` (`transactionID`),
    CONSTRAINT `fk_transaction_has_account` FOREIGN KEY (`accountID`) REFERENCES `account` (`accountID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB;
