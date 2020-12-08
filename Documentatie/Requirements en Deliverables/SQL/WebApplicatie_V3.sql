-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema WebApplicatie_DROVN_Bank
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema WebApplicatie_DROVN_Bank
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `WebApplicatie_DROVN_Bank` DEFAULT CHARACTER SET utf8 ;
USE `WebApplicatie_DROVN_Bank` ;

-- -----------------------------------------------------
-- Table `WebApplicatie_DROVN_Bank`.`Customer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `WebApplicatie_DROVN_Bank`.`Customer` (
  `customerID` INT NOT NULL AUTO_INCREMENT,
  `userName` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`customerID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `WebApplicatie_DROVN_Bank`.`NaturalPerson`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `WebApplicatie_DROVN_Bank`.`NaturalPerson` (
  `customerID` INT NOT NULL,
  `initials` VARCHAR(10) NOT NULL,
  `firstName` VARCHAR(15) NOT NULL,
  `preposition` VARCHAR(15) NULL,
  `surName` VARCHAR(45) NOT NULL,
  `dateOfBirth` DATE NOT NULL,
  `socialSecurityNumber` INT NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `phone` INT NOT NULL,
  `postCode` VARCHAR(6) NOT NULL,
  `homeNumber` INT NOT NULL,
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
-- Table `WebApplicatie_DROVN_Bank`.`LegalPerson`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `WebApplicatie_DROVN_Bank`.`LegalPerson` (
  `companyID` INT NOT NULL,
  `companyName` VARCHAR(45) NOT NULL,
  `kvkNumber` INT NOT NULL,
  `sector` VARCHAR(45) NOT NULL,
  `VATNumber` VARCHAR(20) NOT NULL,
  `postCode` VARCHAR(6) NOT NULL,
  `homeNumber` INT NOT NULL,
  `street` VARCHAR(45) NOT NULL,
  `residence` VARCHAR(45) NOT NULL,
  `accountmanagerID` INT NOT NULL,
  PRIMARY KEY (`companyID`),
  INDEX `fk_Rechtspersoon_Klant2_idx` (`accountmanagerID` ASC) VISIBLE,
  CONSTRAINT `fk_Rechtspersoon_Klant1`
    FOREIGN KEY (`companyID`)
    REFERENCES `WebApplicatie_DROVN_Bank`.`Customer` (`customerID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Rechtspersoon_Klant2`
    FOREIGN KEY (`accountmanagerID`)
    REFERENCES `WebApplicatie_DROVN_Bank`.`Customer` (`customerID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `WebApplicatie_DROVN_Bank`.`Medewerker`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `WebApplicatie_DROVN_Bank`.`Medewerker` (
  `employeeID` INT NOT NULL,
  `role` VARCHAR(45) NOT NULL,
  INDEX `fk_Medewerker_NatuurlijkPersoon1_idx` (`employeeID` ASC) VISIBLE,
  PRIMARY KEY (`employeeID`),
  CONSTRAINT `fk_Medewerker_NatuurlijkPersoon1`
    FOREIGN KEY (`employeeID`)
    REFERENCES `WebApplicatie_DROVN_Bank`.`NaturalPerson` (`customerID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `WebApplicatie_DROVN_Bank`.`Account`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `WebApplicatie_DROVN_Bank`.`Account` (
  `accountID` INT NOT NULL AUTO_INCREMENT,
  `customerID` INT NOT NULL,
  `IBAN` VARCHAR(45) NOT NULL,
  `balance` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`accountID`),
  INDEX `fk_Rekening_Klant1_idx` (`customerID` ASC) VISIBLE,
  CONSTRAINT `fk_Rekening_Klant1`
    FOREIGN KEY (`customerID`)
    REFERENCES `WebApplicatie_DROVN_Bank`.`Customer` (`customerID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `WebApplicatie_DROVN_Bank`.`BusinessAccount`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `WebApplicatie_DROVN_Bank`.`BusinessAccount` (
  `accountID` INT NOT NULL,
  `contactPerson` INT NOT NULL,
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


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
