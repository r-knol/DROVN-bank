-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema WebApplicatie_DROVN_Bank
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `WebApplicatie_DROVN_Bank` DEFAULT CHARACTER SET utf8 ;
USE `WebApplicatie_DROVN_Bank` ;

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
DROP TABLE IF EXISTS `WebApplicatie_DROVN_Bank`.`Customer` ;

CREATE TABLE IF NOT EXISTS `WebApplicatie_DROVN_Bank`.`Customer` (
  `customerID` BIGINT(10) NOT NULL AUTO_INCREMENT,
  `userName` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `customerType` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`customerID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `WebApplicatie_DROVN_Bank`.`NaturalPerson`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `WebApplicatie_DROVN_Bank`.`NaturalPerson` ;

CREATE TABLE IF NOT EXISTS `WebApplicatie_DROVN_Bank`.`NaturalPerson` (
  `customerID` BIGINT(10) NOT NULL,
  `initials` VARCHAR(10) NOT NULL,
  `firstName` VARCHAR(15) NOT NULL,
  `preposition` VARCHAR(15) NULL,
  `surName` VARCHAR(45) NOT NULL,
  `dateOfBirth` VARCHAR(45) NOT NULL,
  `socialSecurityNumber` BIGINT(10) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `phone` VARCHAR(45) NOT NULL,
  `postalCode` VARCHAR(6) NOT NULL,
  `homeNumber` VARCHAR(10) NOT NULL,
  `street` VARCHAR(45) NOT NULL,
  `residence` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`customerID`),
  CONSTRAINT `fk_NatuurlijkPersoon_Klant`
    FOREIGN KEY (`customerID`)
    REFERENCES `WebApplicatie_DROVN_Bank`.`Customer` (`customerID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `WebApplicatie_DROVN_Bank`.`Employee`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `WebApplicatie_DROVN_Bank`.`Employee` ;

CREATE TABLE IF NOT EXISTS `WebApplicatie_DROVN_Bank`.`Employee` (
  `employeeID` BIGINT(10) NOT NULL,
  `userName` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `firstName` VARCHAR(15) NOT NULL,
  `preposition` VARCHAR(15) NOT NULL,
  `surName` VARCHAR(45) NOT NULL,
  `role` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`employeeID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `WebApplicatie_DROVN_Bank`.`LegalPerson`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `WebApplicatie_DROVN_Bank`.`LegalPerson` ;

CREATE TABLE IF NOT EXISTS `WebApplicatie_DROVN_Bank`.`LegalPerson` (
  `companyID` BIGINT(10) NOT NULL,
  `companyName` VARCHAR(45) NOT NULL,
  `kvkNumber` BIGINT(8) NOT NULL,
  `sector` VARCHAR(45) NOT NULL,
  `vatNumber` VARCHAR(20) NOT NULL,
  `postalCode` VARCHAR(6) NOT NULL,
  `homeNumber` VARCHAR(10) NOT NULL,
  `street` VARCHAR(45) NOT NULL,
  `residence` VARCHAR(45) NOT NULL,
  `employeeID` BIGINT(10),
  PRIMARY KEY (`companyID`),
  INDEX `fk_LegalPerson_Employee1_idx` (`employeeID` ASC) VISIBLE,
  CONSTRAINT `fk_Rechtspersoon_Klant1`
    FOREIGN KEY (`companyID`)
    REFERENCES `WebApplicatie_DROVN_Bank`.`Customer` (`customerID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_LegalPerson_Employee1`
    FOREIGN KEY (`employeeID`)
    REFERENCES `WebApplicatie_DROVN_Bank`.`Employee` (`employeeID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `WebApplicatie_DROVN_Bank`.`Account`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `WebApplicatie_DROVN_Bank`.`Account` ;

CREATE TABLE IF NOT EXISTS `WebApplicatie_DROVN_Bank`.`Account` (
  `accountID` BIGINT(10) NOT NULL,
  `iban` VARCHAR(45) NOT NULL,
  `balance` DECIMAL(64,2) NOT NULL,
  PRIMARY KEY (`accountID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `WebApplicatie_DROVN_Bank`.`BusinessAccount`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `WebApplicatie_DROVN_Bank`.`BusinessAccount` ;

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
    REFERENCES `WebApplicatie_DROVN_Bank`.`NaturalPerson` (`customerID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `WebApplicatie_DROVN_Bank`.`Customer_has_Account`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `WebApplicatie_DROVN_Bank`.`Customer_has_Account` ;

CREATE TABLE IF NOT EXISTS `WebApplicatie_DROVN_Bank`.`Customer_has_Account` (
  `customerID` BIGINT(10) NOT NULL,
  `accountID` BIGINT(10) NOT NULL,
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
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `WebApplicatie_DROVN_Bank`.`Transaction`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `WebApplicatie_DROVN_Bank`.`Transaction` ;

CREATE TABLE IF NOT EXISTS `WebApplicatie_DROVN_Bank`.`Transaction` (
  `transactionID` BIGINT NOT NULL,
  `debitAccount` VARCHAR(45) NOT NULL,
  `creditAccount` VARCHAR(45) NOT NULL,
  `amount` DECIMAL(64,2) NOT NULL,
  `description` VARCHAR(45) NULL,
  `date` DATE NOT NULL,
  PRIMARY KEY (`transactionID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `WebApplicatie_DROVN_Bank`.`Transaction_has_account`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `WebApplicatie_DROVN_Bank`.`Transaction_has_account` ;

CREATE TABLE IF NOT EXISTS `WebApplicatie_DROVN_Bank`.`Transaction_has_account` (
  `accountID` BIGINT(10) NOT NULL,
  `transactionID` BIGINT NOT NULL,
  PRIMARY KEY (`accountID`, `transactionID`),
  INDEX `fk_table1_Transaction1_idx` (`transactionID` ASC) VISIBLE,
  CONSTRAINT `fk_table1_Account1`
    FOREIGN KEY (`accountID`)
    REFERENCES `WebApplicatie_DROVN_Bank`.`Account` (`accountID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_table1_Transaction1`
    FOREIGN KEY (`transactionID`)
    REFERENCES `WebApplicatie_DROVN_Bank`.`Transaction` (`transactionID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;


INSERT INTO `WebApplicatie_DROVN_Bank`.`Customer` (`customerID`, `userName`, `password`, `customerType`) VALUES ('1', 'loo', 'loo', 'NATURAL');
INSERT INTO `WebApplicatie_DROVN_Bank`.`Customer` (`customerID`, `userName`, `password`, `customerType`) VALUES ('2', 'rknol', 'rknol', 'NATURAL');
INSERT INTO `WebApplicatie_DROVN_Bank`.`Customer` (`customerID`, `userName`, `password`, `customerType`) VALUES ('3', 'hva', 'hva', 'LEGAL');

INSERT INTO `WebApplicatie_DROVN_Bank`.`NaturalPerson` (`customerID`, `initials`, `firstName`, `preposition`, `surName`, `dateOfBirth`, `socialSecurityNumber`, `email`, `phone`, `postalCode`, `homeNumber`, `street`, `residence`) VALUES ('1', 'N.', 'Nina', 'van', 'Loo', '1987-02-07', '159398289', 'ninavanloo@gmail.com', '0610087058', '1056AC', '2-H', 'James Rosskade', 'Amsterdam');
INSERT INTO `WebApplicatie_DROVN_Bank`.`NaturalPerson` (`customerID`, `initials`, `firstName`, `surName`, `dateOfBirth`, `socialSecurityNumber`, `email`, `phone`, `postalCode`, `homeNumber`, `street`, `residence`) VALUES ('2', 'R.W.', 'Richard', 'Knol', '1990-05-11', '243535345', 'rknol@gmail.com', '0612345678', '1234AQ', '345', 'Oudenoord', 'Utrecht');
INSERT INTO `WebApplicatie_DROVN_Bank`.`LegalPerson` (`companyID`, `companyName`, `kvkNumber`, `sector`, `vatNumber`, `postalCode`, `homeNumber`, `street`, `residence`) VALUES ('3', 'Hogeschool van Amsterdam', '12345676', 'onderwijs', '1234567', '1000AB', '1', 'Wibautstraat', 'Amsterdam');

INSERT INTO `WebApplicatie_DROVN_Bank`.`Account` (`accountID`, `iban`, `balance`) VALUES ('1', '84DROVN1234567', '10.00');
INSERT INTO `WebApplicatie_DROVN_Bank`.`Account` (`accountID`, `iban`, `balance`) VALUES ('2', '65DROVN6543217', '1234.99');
INSERT INTO `WebApplicatie_DROVN_Bank`.`Account` (`accountID`, `iban`, `balance`) VALUES ('3', '54DROVN5432576', '10987.87');

INSERT INTO `WebApplicatie_DROVN_Bank`.`Customer_has_Account` (`customerID`, `accountID`) VALUES ('1', '1');
INSERT INTO `WebApplicatie_DROVN_Bank`.`Customer_has_Account` (`customerID`, `accountID`) VALUES ('2', '1');
INSERT INTO `WebApplicatie_DROVN_Bank`.`Customer_has_Account` (`customerID`, `accountID`) VALUES ('2', '2');
INSERT INTO `WebApplicatie_DROVN_Bank`.`Customer_has_Account` (`customerID`, `accountID`) VALUES ('3', '3');

INSERT INTO `WebApplicatie_DROVN_Bank`.`Employee` (`employeeID`, `userName`, `password`, `firstName`, `preposition`, `surName`, `role`) VALUES (1,'zack','zack','zack','van','zack','ACCOUNTMANAGER'),
                                                                                                                                               (2,'jan','jan','jan','de','jan','HEAD_LEGAL'),
                                                                                                                                               (3,'piet','piet','piet','','piet','HEAD_PRIVATE');



