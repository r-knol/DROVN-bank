-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0;
SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0;
SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE =
        'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema WebApplicatie_DROVN_Bank
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `WebApplicatie_DROVN_Bank` DEFAULT CHARACTER SET utf8;
USE `WebApplicatie_DROVN_Bank`;

-- -----------------------------------------------------
-- Database user for Schema WebApplicatie_DROVN_Bank
-- -----------------------------------------------------
CREATE USER IF NOT EXISTS 'userWebApplicatie_DROVN_Bank'@'localhost'
    IDENTIFIED WITH caching_sha2_password
        BY 'pwWebApplicatie_DROVN_Bank' PASSWORD EXPIRE NEVER;
GRANT ALL ON WebApplicatie_DROVN_Bank.* TO 'userWebApplicatie_DROVN_Bank'@'localhost';

-- -----------------------------------------------------
-- Table `WebApplicatie_DROVN_Bank`.`Customer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `WebApplicatie_DROVN_Bank`.`Customer`
(
    `customerID`   BIGINT(10)  NOT NULL AUTO_INCREMENT,
    `userName`     VARCHAR(16) NOT NULL,
    `password`     VARCHAR(16) NOT NULL,
    `customerType` VARCHAR(10) NOT NULL,
    PRIMARY KEY (`customerID`)
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `WebApplicatie_DROVN_Bank`.`NaturalPerson`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `WebApplicatie_DROVN_Bank`.`NaturalPerson`
(
    `ID`                   BIGINT(10)  NOT NULL AUTO_INCREMENT,
    `initials`             VARCHAR(10) NOT NULL,
    `firstName`            VARCHAR(15) NOT NULL,
    `preposition`          VARCHAR(15) NULL,
    `surName`              VARCHAR(45) NOT NULL,
    `dateOfBirth`          DATE        NOT NULL,
    `socialSecurityNumber` VARCHAR(9)  NOT NULL,
    `email`                VARCHAR(45) NOT NULL,
    `phone`                VARCHAR(14) NOT NULL,
    `postalCode`           VARCHAR(7)  NOT NULL,
    `homeNumber`           VARCHAR(10) NOT NULL,
    `street`               VARCHAR(45) NOT NULL,
    `residence`            VARCHAR(45) NOT NULL,
    PRIMARY KEY (`ID`),
    INDEX `fk_NaturalPerson_Customer_idx` (`ID` ASC) VISIBLE,
    UNIQUE INDEX `socialSecurityNumber_UNIQUE` (`socialSecurityNumber` ASC) VISIBLE,
    CONSTRAINT `fk_NaturalPerson_Customer`
        FOREIGN KEY (`ID`)
            REFERENCES `WebApplicatie_DROVN_Bank`.`Customer` (`customerID`)
            ON DELETE CASCADE
            ON UPDATE CASCADE
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `WebApplicatie_DROVN_Bank`.`LegalPerson`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `WebApplicatie_DROVN_Bank`.`LegalPerson`
(
    `companyID`        BIGINT(10)  NOT NULL AUTO_INCREMENT,
    `companyName`      VARCHAR(45) NOT NULL,
    `kvkNumber`        BIGINT(8)   NOT NULL,
    `sector`           VARCHAR(45) NULL,
    `vatNumber`        VARCHAR(20) NOT NULL,
    `postalCode`       VARCHAR(7)  NOT NULL,
    `homeNumber`       VARCHAR(10) NOT NULL,
    `street`           VARCHAR(45) NOT NULL,
    `residence`        VARCHAR(45) NOT NULL,
    `accountmanagerID` BIGINT(10)  NULL,
    PRIMARY KEY (`companyID`),
    INDEX `fk_LegalPerson_Customer_idx` (`companyID` ASC) VISIBLE,
    /* INDEX `fk_LegalPerson_Employee_idx` (`accountmanagerID` ASC) VISIBLE, */
    CONSTRAINT `fk_LegalPerson_Customer`
        FOREIGN KEY (`companyID`)
            REFERENCES `WebApplicatie_DROVN_Bank`.`Customer` (`customerID`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION/*,
  CONSTRAINT `fk_LegalPerson_Employee`
    FOREIGN KEY (`accountmanagerID`)
    REFERENCES `WebApplicatie_DROVN_Bank`.`Employee` (`employeeID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE*/)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `WebApplicatie_DROVN_Bank`.`Employee`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `WebApplicatie_DROVN_Bank`.`Employee`
(
    `employeeID` BIGINT(10)  NOT NULL,
    `role`       VARCHAR(45) NOT NULL,
    PRIMARY KEY (`employeeID`),
    INDEX `fk_Employee_NaturalPerson_idx` (`employeeID` ASC) VISIBLE,
    CONSTRAINT `fk_Employee_NaturalPerson`
        FOREIGN KEY (`employeeID`)
            REFERENCES `WebApplicatie_DROVN_Bank`.`NaturalPerson` (`ID`)
            ON DELETE CASCADE
            ON UPDATE CASCADE
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `WebApplicatie_DROVN_Bank`.`Account`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `WebApplicatie_DROVN_Bank`.`Account`
(
    `accountID` BIGINT(10)     NOT NULL,
    `iban`      VARCHAR(45)    NOT NULL,
    `balance`   DECIMAL(64, 2) NOT NULL,
    PRIMARY KEY (`accountID`)
)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `WebApplicatie_DROVN_Bank`.`BusinessAccount`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `WebApplicatie_DROVN_Bank`.`BusinessAccount` (
  `accountID` BIGINT(10) NOT NULL,
  `contactPerson` BIGINT(10) NOT NULL,
  PRIMARY KEY (`accountID`),
  INDEX `fk_BusinessAccount_NaturalPerson1_idx` (`contactPerson` ASC) VISIBLE,
  CONSTRAINT `fk_BusinessAccount_Account1`
    FOREIGN KEY (`accountID`)
    REFERENCES `WebApplicatie_DROVN_Bank`.`Account` (`accountID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_BusinessAccount_NaturalPerson1`
    FOREIGN KEY (`contactPerson`)
        REFERENCES `WebApplicatie_DROVN_Bank`.`NaturalPerson` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `WebApplicatie_DROVN_Bank`.`Customer_has_Account`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `WebApplicatie_DROVN_Bank`.`Customer_has_Account`
(
    `customerID` BIGINT(10) NOT NULL,
    `accountID`  BIGINT(10) NOT NULL,
    PRIMARY KEY (`accountID`, `customerID`),
    INDEX `fk_Customer_has_Account_Account1_idx` (`accountID` ASC) VISIBLE,
    INDEX `fk_Customer_has_Account_Customer1_idx` (`customerID` ASC) VISIBLE,
    CONSTRAINT `fk_Customer_has_Account_Customer1`
        FOREIGN KEY (`customerID`)
            REFERENCES `WebApplicatie_DROVN_Bank`.`Customer` (`customerID`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_Customer_has_Account_Account1`
        FOREIGN KEY (`accountID`)
            REFERENCES `WebApplicatie_DROVN_Bank`.`Account` (`accountID`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;

INSERT INTO `WebApplicatie_DROVN_Bank`.`Customer` (`customerID`, `userName`, `password`, `customerType`)
VALUES ('1', 'loo', 'loo', 'NATURAL');
INSERT INTO `WebApplicatie_DROVN_Bank`.`Customer` (`customerID`, `userName`, `password`, `customerType`)
VALUES ('2', 'rknol', 'rknol', 'NATURAL');
INSERT INTO `WebApplicatie_DROVN_Bank`.`Customer` (`customerID`, `userName`, `password`, `customerType`)
VALUES ('3', 'hva', 'hva', 'LEGAL');

INSERT INTO `WebApplicatie_DROVN_Bank`.`NaturalPerson` (`ID`, `initials`, `firstName`, `preposition`, `surName`,
                                                        `dateOfBirth`, `socialSecurityNumber`, `email`, `phone`,
                                                        `postalCode`, `homeNumber`, `street`, `residence`)
VALUES ('1', 'N.', 'Nina', 'van', 'Loo', '1987-02-07', '159398289', 'ninavanloo@gmail.com', '0610087058', '1056 AC',
        '2-H', 'James Rosskade', 'Amsterdam');
INSERT INTO `WebApplicatie_DROVN_Bank`.`NaturalPerson` (`ID`, `initials`, `firstName`, `surName`, `dateOfBirth`,
                                                        `socialSecurityNumber`, `email`, `phone`, `postalCode`,
                                                        `homeNumber`, `street`, `residence`)
VALUES ('2', 'R.W.', 'Richard', 'Knol', '1990-05-11', '243535345', 'rknol@gmail.com', '0612345678', '1234 AQ', '345',
        'Oudenoord', 'Utrecht');
INSERT INTO `WebApplicatie_DROVN_Bank`.`LegalPerson` (`companyID`, `companyName`, `kvkNumber`, `sector`, `vatNumber`,
                                                      `postalCode`, `homeNumber`, `street`, `residence`)
VALUES ('3', 'Hogeschool van Amsterdam', '12345676', 'EDUCATION', '1234567', '1000 AB', '1', 'Wibautstraat',
        'Amsterdam');

INSERT INTO `WebApplicatie_DROVN_Bank`.`Account` (`accountID`, `iban`, `balance`)
VALUES ('1', '84DROVN1234567', '10.00');
INSERT INTO `WebApplicatie_DROVN_Bank`.`Account` (`accountID`, `iban`, `balance`)
VALUES ('2', '65DROVN6543217', '1234.99');
INSERT INTO `WebApplicatie_DROVN_Bank`.`Account` (`accountID`, `iban`, `balance`)
VALUES ('3', '54DROVN5432576', '10987.87');

INSERT INTO `WebApplicatie_DROVN_Bank`.`Customer_has_Account` (`customerID`, `accountID`)
VALUES ('1', '1');
INSERT INTO `WebApplicatie_DROVN_Bank`.`Customer_has_Account` (`customerID`, `accountID`)
VALUES ('2', '1');
INSERT INTO `WebApplicatie_DROVN_Bank`.`Customer_has_Account` (`customerID`, `accountID`)
VALUES ('2', '2');
INSERT INTO `WebApplicatie_DROVN_Bank`.`Customer_has_Account` (`customerID`, `accountID`)
VALUES ('3', '3');

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;


INSERT INTO `WebApplicatie_DROVN_Bank`.`Customer` (`customerID`, `userName`, `password`, `customerType`) VALUES ('1', 'loo', 'loo', 'NATURAL');
INSERT INTO `WebApplicatie_DROVN_Bank`.`Customer` (`customerID`, `userName`, `password`, `customerType`) VALUES ('2', 'rknol', 'rknol', 'NATURAL');
INSERT INTO `WebApplicatie_DROVN_Bank`.`Customer` (`customerID`, `userName`, `password`, `customerType`) VALUES ('3', 'hva', 'hva', 'LEGAL');
INSERT INTO `WebApplicatie_DROVN_Bank`.`Customer` (`customerID`, `userName`, `password`, `customerType`) VALUES ('4', 'omkaaij', 'omkaaij', 'NATURAL');

INSERT INTO `WebApplicatie_DROVN_Bank`.`NaturalPerson` (`customerID`, `initials`, `firstName`, `preposition`, `surName`, `dateOfBirth`, `socialSecurityNumber`, `email`, `phone`, `postalCode`, `homeNumber`, `street`, `residence`) VALUES ('1', 'N.', 'Nina', 'van', 'Loo', '1987-02-07', '159398289', 'ninavanloo@gmail.com', '0610087058', '1056AC', '2-H', 'James Rosskade', 'Amsterdam');
INSERT INTO `WebApplicatie_DROVN_Bank`.`NaturalPerson` (`customerID`, `initials`, `firstName`, `surName`, `dateOfBirth`, `socialSecurityNumber`, `email`, `phone`, `postalCode`, `homeNumber`, `street`, `residence`) VALUES ('2', 'R.W.', 'Richard', 'Knol', '1990-05-11', '243535345', 'rknol@gmail.com', '0612345678', '1234AQ', '345', 'Oudenoord', 'Utrecht');
INSERT INTO `WebApplicatie_DROVN_Bank`.`LegalPerson` (`companyID`, `companyName`, `kvkNumber`, `sector`, `vatNumber`, `postalCode`, `homeNumber`, `street`, `residence`) VALUES ('3', 'Hogeschool van Amsterdam', '12345676', 'onderwijs', '1234567', '1000AB', '1', 'Wibautstraat', 'Amsterdam');
INSERT INTO `WebApplicatie_DROVN_Bank`.`NaturalPerson` (`customerID`, `initials`, `firstName`, `preposition`,`surName`, `dateOfBirth`, `socialSecurityNumber`, `email`, `phone`, `postalCode`, `homeNumber`, `street`, `residence`) VALUES ('4', 'O.M.', 'Olaf', 'van der', 'Kaaij', '1976-12-25', '175347896', 'omkaaij@gmail.com', '0612345678', '1234AQ', '298', 'Van Eysingalaan', 'Utrecht');

INSERT INTO `WebApplicatie_DROVN_Bank`.`Account` (`accountID`, `iban`, `balance`) VALUES ('1', 'NL84DRVN1234567124', '10.00');
INSERT INTO `WebApplicatie_DROVN_Bank`.`Account` (`accountID`, `iban`, `balance`) VALUES ('2', 'NL65DRVN6543217874', '1234.99');
INSERT INTO `WebApplicatie_DROVN_Bank`.`Account` (`accountID`, `iban`, `balance`) VALUES ('3', 'NL54DRVN5432576135', '10987.87');
INSERT INTO `WebApplicatie_DROVN_Bank`.`Account` (`accountID`, `iban`, `balance`) VALUES ('4', 'NL14DRVN0478712478', '22452.19');
INSERT INTO `WebApplicatie_DROVN_Bank`.`Account` (`accountID`, `iban`, `balance`) VALUES ('5', 'NL37DRVN0478515462', '987.87');

INSERT INTO `WebApplicatie_DROVN_Bank`.`Customer_has_Account` (`customerID`, `accountID`) VALUES ('1', '1');
INSERT INTO `WebApplicatie_DROVN_Bank`.`Customer_has_Account` (`customerID`, `accountID`) VALUES ('2', '1');
INSERT INTO `WebApplicatie_DROVN_Bank`.`Customer_has_Account` (`customerID`, `accountID`) VALUES ('2', '2');
INSERT INTO `WebApplicatie_DROVN_Bank`.`Customer_has_Account` (`customerID`, `accountID`) VALUES ('3', '3');
INSERT INTO `WebApplicatie_DROVN_Bank`.`Customer_has_Account` (`customerID`, `accountID`) VALUES ('4', '4');
INSERT INTO `WebApplicatie_DROVN_Bank`.`Customer_has_Account` (`customerID`, `accountID`) VALUES ('4', '5');

INSERT INTO `WebApplicatie_DROVN_Bank`.`Employee` (`employeeID`, `userName`, `password`, `firstName`, `preposition`, `surName`, `role`) VALUES (1,'zack','zack','zack','van','zack','ACCOUNTMANAGER'),
                                                                                                                                               (2,'jan','jan','jan','de','jan','HEAD_LEGAL'),
                                                                                                                                               (3,'piet','piet','piet','','piet','HEAD_PRIVATE');




SET SQL_MODE = @OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS;