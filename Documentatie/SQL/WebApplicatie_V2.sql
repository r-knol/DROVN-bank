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
-- Table `WebApplicatie_DROVN_Bank`.`Klant`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `WebApplicatie_DROVN_Bank`.`Klant` (
  `klantID` INT NOT NULL AUTO_INCREMENT,
  `gebruikersnaam` VARCHAR(45) NOT NULL,
  `wachtwoord` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`klantID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `WebApplicatie_DROVN_Bank`.`NatuurlijkPersoon`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `WebApplicatie_DROVN_Bank`.`NatuurlijkPersoon` (
  `klantID` INT NOT NULL,
  `initialen` VARCHAR(10) NOT NULL,
  `voornaam` VARCHAR(15) NOT NULL,
  `tussenvoegsel` VARCHAR(15) NULL,
  `achternaam` VARCHAR(45) NOT NULL,
  `geboortedatum` DATE NOT NULL,
  `BSN` INT NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `telefoonnummer` INT NOT NULL,
  `postcode` VARCHAR(6) NOT NULL,
  `huisnummer` INT NOT NULL,
  `straatnaam` VARCHAR(45) NOT NULL,
  `woonplaats` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`klantID`),
  CONSTRAINT `fk_NatuurlijkPersoon_Klant`
    FOREIGN KEY (`klantID`)
    REFERENCES `WebApplicatie_DROVN_Bank`.`Klant` (`klantID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `WebApplicatie_DROVN_Bank`.`Rechtspersoon`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `WebApplicatie_DROVN_Bank`.`Rechtspersoon` (
  `klantID` INT NOT NULL,
  `bedrijfsnaam` VARCHAR(45) NOT NULL,
  `kvknummer` INT NOT NULL,
  `sector` VARCHAR(45) NOT NULL,
  `btwnummer` VARCHAR(20) NOT NULL,
  `postcode` VARCHAR(6) NOT NULL,
  `huisnummer` INT NOT NULL,
  `straatnaam` VARCHAR(45) NOT NULL,
  `woonplaats` VARCHAR(45) NOT NULL,
  `accountmanagerID` INT NOT NULL,
  `contactpersoonID` INT NOT NULL,
  PRIMARY KEY (`klantID`),
  INDEX `fk_Rechtspersoon_Klant2_idx` (`accountmanagerID` ASC) VISIBLE,
  INDEX `fk_Rechtspersoon_Klant3_idx` (`contactpersoonID` ASC) VISIBLE,
  CONSTRAINT `fk_Rechtspersoon_Klant1`
    FOREIGN KEY (`klantID`)
    REFERENCES `WebApplicatie_DROVN_Bank`.`Klant` (`klantID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Rechtspersoon_Klant2`
    FOREIGN KEY (`accountmanagerID`)
    REFERENCES `WebApplicatie_DROVN_Bank`.`Klant` (`klantID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Rechtspersoon_Klant3`
    FOREIGN KEY (`contactpersoonID`)
    REFERENCES `WebApplicatie_DROVN_Bank`.`Klant` (`klantID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `WebApplicatie_DROVN_Bank`.`Medewerker`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `WebApplicatie_DROVN_Bank`.`Medewerker` (
  `klantID` INT NOT NULL,
  `rol` VARCHAR(45) NOT NULL,
  INDEX `fk_Medewerker_NatuurlijkPersoon1_idx` (`klantID` ASC) VISIBLE,
  PRIMARY KEY (`klantID`),
  CONSTRAINT `fk_Medewerker_NatuurlijkPersoon1`
    FOREIGN KEY (`klantID`)
    REFERENCES `WebApplicatie_DROVN_Bank`.`NatuurlijkPersoon` (`klantID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `WebApplicatie_DROVN_Bank`.`Rekening`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `WebApplicatie_DROVN_Bank`.`Rekening` (
  `rekeningID` INT NOT NULL AUTO_INCREMENT,
  `klantID` INT NOT NULL,
  `IBAN` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`rekeningID`),
  INDEX `fk_Rekening_Klant1_idx` (`klantID` ASC) VISIBLE,
  CONSTRAINT `fk_Rekening_Klant1`
    FOREIGN KEY (`klantID`)
    REFERENCES `WebApplicatie_DROVN_Bank`.`Klant` (`klantID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
