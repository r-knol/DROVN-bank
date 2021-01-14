CREATE SCHEMA IF NOT EXISTS `WebApplicatie_DROVN_Bank` DEFAULT CHARACTER SET utf8;
USE `WebApplicatie_DROVN_Bank`;

CREATE USER IF NOT EXISTS 'userWebApplicatie_DROVN_Bank'@'localhost'
    IDENTIFIED WITH caching_sha2_password
        BY 'pwWebApplicatie_DROVN_Bank' PASSWORD EXPIRE NEVER;
GRANT ALL ON WebApplicatie_DROVN_Bank.* TO 'userWebApplicatie_DROVN_Bank'@'localhost';

DROP TABLE IF EXISTS `Customer`;
CREATE TABLE `Customer`
(
    `customerID`   BIGINT      NOT NULL AUTO_INCREMENT,
    `userName`     VARCHAR(20) NOT NULL,
    `password`     VARCHAR(20) NOT NULL,
    `customerType` VARCHAR(25) NOT NULL,
    PRIMARY KEY (`customerID`)
) ENGINE = InnoDB;

DROP TABLE IF EXISTS `NaturalPerson`;
CREATE TABLE `NaturalPerson`
(
    `customerID`           BIGINT      NOT NULL AUTO_INCREMENT,
    `initials`             VARCHAR(15) NOT NULL,
    `firstName`            VARCHAR(25) NOT NULL,
    `preposition`          VARCHAR(15) NULL,
    `surName`              VARCHAR(45) NOT NULL,
    `dateOfBirth`          VARCHAR(10) NOT NULL,
    `socialSecurityNumber` VARCHAR(10) NOT NULL,
    `email`                VARCHAR(45) NOT NULL,
    `phone`                VARCHAR(15) NOT NULL,
    `postalCode`           VARCHAR(7)  NOT NULL,
    `homeNumber`           VARCHAR(10) NOT NULL,
    `street`               VARCHAR(45) NOT NULL,
    `residence`            VARCHAR(45) NOT NULL,
    PRIMARY KEY (`customerID`),
    INDEX `fk_NaturalPerson_Customer_idx` (`customerID` ASC) VISIBLE,
    UNIQUE INDEX `socialSecurityNumber_UNIQUE` (`socialSecurityNumber` ASC) VISIBLE,
    CONSTRAINT `fk_NaturalPerson_Customer` FOREIGN KEY (`customerID`) REFERENCES `Customer` (`customerID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB;

DROP TABLE IF EXISTS `Employee`;
CREATE TABLE `Employee`
(
    `employeeID`  BIGINT      NOT NULL AUTO_INCREMENT,
    `userName`    VARCHAR(17) NOT NULL,
    `password`    varchar(17) NOT NULL,
    `firstName`   varchar(25) NOT NULL,
    `preposition` varchar(15) DEFAULT NULL,
    `surName`     varchar(45) NOT NULL,
    `role`        varchar(25) NOT NULL,
    PRIMARY KEY (`employeeID`)
) ENGINE = InnoDB;

DROP TABLE IF EXISTS `LegalPerson`;
CREATE TABLE `LegalPerson`
(
    `companyID`        BIGINT      NOT NULL AUTO_INCREMENT,
    `companyName`      VARCHAR(45) NOT NULL,
    `kvkNumber`        BIGINT(8)   NOT NULL,
    `sector`           VARCHAR(45) NULL,
    `vatNumber`        VARCHAR(16) NOT NULL,
    `postalCode`       VARCHAR(7)  NOT NULL,
    `homeNumber`       VARCHAR(10) NOT NULL,
    `street`           VARCHAR(45) NOT NULL,
    `residence`        VARCHAR(45) NOT NULL,
    `accountmanagerID` BIGINT      NOT NULL,
    PRIMARY KEY (`companyID`),
    INDEX `fk_LegalPerson_Customer_idx` (`companyID`),
    INDEX `fk_LegalPerson_Employee_idx` (`accountmanagerID`),
    CONSTRAINT `fk_LegalPerson_Customer` FOREIGN KEY (`companyID`) REFERENCES `Customer` (`customerID`),
    CONSTRAINT `fk_LegalPerson_Employee` FOREIGN KEY (`accountmanagerID`) REFERENCES `Employee` (`employeeID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB;

DROP TABLE IF EXISTS `Account`;
CREATE TABLE `Account`
(
    `accountID` BIGINT         NOT NULL AUTO_INCREMENT,
    `iban`      VARCHAR(45)    NOT NULL,
    `balance`   DECIMAL(64, 2) NOT NULL,
    PRIMARY KEY (`accountID`)
) ENGINE = InnoDB;

DROP TABLE IF EXISTS `BusinessAccount`;
CREATE TABLE `BusinessAccount`
(
    `accountID`     BIGINT NOT NULL AUTO_INCREMENT,
    `contactPerson` BIGINT NOT NULL,
    PRIMARY KEY (`accountID`, `contactPerson`),
    INDEX `fk_BusinessAccount_NaturalPerson_idx` (`contactPerson`),
    CONSTRAINT `fk_BusinessAccount_Account` FOREIGN KEY (`accountID`) REFERENCES `Account` (`accountID`),
    CONSTRAINT `fk_BusinessAccount_NaturalPerson` FOREIGN KEY (`contactPerson`) REFERENCES `NaturalPerson` (`customerID`)
) ENGINE = InnoDB;

DROP TABLE IF EXISTS `customer_has_account`;
CREATE TABLE `customer_has_account`
(
    `customerID` BIGINT NOT NULL,
    `accountID`  BIGINT NOT NULL,
    PRIMARY KEY (`accountID`, `customerID`),
    KEY `fk_Customer_has_Account_Account1_idx` (`accountID`),
    KEY `fk_Customer_has_Account_Customer1_idx` (`customerID`),
    CONSTRAINT `fk_Customer_has_Account_Account1` FOREIGN KEY (`accountID`) REFERENCES `account` (`accountID`) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `fk_Customer_has_Account_Customer1` FOREIGN KEY (`customerID`) REFERENCES `customer` (`customerID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB;

DROP TABLE IF EXISTS `Transaction`;
CREATE TABLE `Transaction`
(
    `transactionID` BIGINT         NOT NULL AUTO_INCREMENT,
    `amount`        DECIMAL(64, 2) NOT NULL,
    `description`   VARCHAR(45)    NOT NULL,
    `dateTime`      DATETIME       NOT NULL,
    `creditAccount` VARCHAR(45)    NOT NULL,
    `debitAccount`  VARCHAR(45)    NOT NULL,
    PRIMARY KEY (`transactionID`)
) ENGINE = InnoDB;


DROP TABLE IF EXISTS `transaction_has_account`;
CREATE TABLE `transaction_has_account`
(
    `accountID`     BIGINT NOT NULL,
    `transactionID` BIGINT NOT NULL,
    PRIMARY KEY (`accountID`, `transactionID`),
    KEY `fk_transaction_has_account_account_idx` (`accountID`),
    KEY `fk_transaction_has_account_transaction_idx` (`transactionID`),
    CONSTRAINT `fk_account_has_transaction` FOREIGN KEY (`transactionID`) REFERENCES `transaction` (`transactionID`),
    CONSTRAINT `fk_transaction_has_account` FOREIGN KEY (`accountID`) REFERENCES `account` (`accountID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB;