-- MySQL Workbench Forward Engineering for PROD

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;

-- -----------------------------------------------------
-- Schema heroes
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `heroes` ;

-- -----------------------------------------------------
-- Schema heroes
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `heroes` DEFAULT CHARACTER SET utf8 ;
USE `heroes` ;

-- -----------------------------------------------------
-- Table `heroes`.`heroes`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `heroes`.`heroes` ;

CREATE TABLE IF NOT EXISTS `heroes`.`heroes` (
  `HeroID` INT NOT NULL AUTO_INCREMENT,
  `HeroName` VARCHAR(45) NOT NULL,
  `HeroDescription` VARCHAR(128) NOT NULL,
  `HeroSuperPower` VARCHAR(128) NOT NULL,
  PRIMARY KEY (`HeroID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `heroes`.`locations`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `heroes`.`locations` ;

CREATE TABLE IF NOT EXISTS `heroes`.`locations` (
  `LocationID` INT NOT NULL AUTO_INCREMENT,
  `LocationName` VARCHAR(45) NOT NULL,
  `LocationDescription` VARCHAR(128) NOT NULL,
  `Street` VARCHAR(128) NOT NULL,
  `City` VARCHAR(128) NOT NULL,
  `Lat` DECIMAL(9,6) NOT NULL,
  `Longitude` DECIMAL(9,6) NOT NULL,
  PRIMARY KEY (`LocationID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `heroes`.`orgs`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `heroes`.`orgs` ;

CREATE TABLE IF NOT EXISTS `heroes`.`orgs` (
  `OrgID` INT NOT NULL AUTO_INCREMENT,
  `OrgName` VARCHAR(45) NOT NULL,
  `OrgDescription` VARCHAR(128) NOT NULL,
  `LocationID` INT NOT NULL,
  PRIMARY KEY (`OrgID`),
  INDEX `fk_locations_locationid_idx` (`LocationID` ASC),
  CONSTRAINT `fk_locations_locationid_idx`
    FOREIGN KEY (`LocationID`)
    REFERENCES `heroes`.`locations` (`LocationID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `heroes`.`heroesorgbridge`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `heroes`.`heroesorgbridge` ;

CREATE TABLE IF NOT EXISTS `heroes`.`heroesorgbridge` (
  `HeroID` INT NOT NULL,
  `OrgID` INT NOT NULL,
  PRIMARY KEY (`HeroID`, `OrgID`),
  INDEX `fk_orgs_orgsid_idx` (`OrgID` ASC),
  INDEX `fk_heroes_heroesid_idx` (`HeroID` ASC),
  CONSTRAINT `fk_heroes_heroesid`
    FOREIGN KEY (`HeroID`)
    REFERENCES `heroes`.`heroes` (`HeroID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_orgs_orgsid`
    FOREIGN KEY (`OrgID`)
    REFERENCES `heroes`.`orgs` (`OrgID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `heroes`.`sightings`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `heroes`.`sightings` ;

CREATE TABLE IF NOT EXISTS `heroes`.`sightings` (
  `SightingID` INT NOT NULL AUTO_INCREMENT,
  `HeroID` INT NOT NULL,
  `LocationID` INT NOT NULL,
  `Date` DATE NOT NULL,
  PRIMARY KEY (`SightingID`),
  INDEX `fk_heroes_heroesid_idx` (`HeroID` ASC),
  INDEX `fk_locations_locationid_idx` (`LocationID` ASC),
  CONSTRAINT `fk_sightings_heroes_heroesid`
    FOREIGN KEY (`HeroID`)
    REFERENCES `heroes`.`heroes` (`HeroID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_sightings_locations_locationid`
    FOREIGN KEY (`LocationID`)
    REFERENCES `heroes`.`locations` (`LocationID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `heroes`.`heroes`
-- -----------------------------------------------------
START TRANSACTION;
USE `heroes`;
INSERT INTO `heroes`.`heroes` (`HeroID`, `HeroName`, `HeroDescription`, `HeroSuperPower`) VALUES (1, 'Superman', 'The one, the only.', 'Indestructible');
INSERT INTO `heroes`.`heroes` (`HeroID`, `HeroName`, `HeroDescription`, `HeroSuperPower`) VALUES (2, 'Batman', 'Super rich.  No Parents.', 'Brooding.');

COMMIT;


-- -----------------------------------------------------
-- Data for table `heroes`.`locations`
-- -----------------------------------------------------
START TRANSACTION;
USE `heroes`;
INSERT INTO `heroes`.`locations` (`LocationID`, `LocationName`, `LocationDescription`, `Street`, `City`, `Lat`, `Longitude`) VALUES (1, 'Undersea Superdome', 'Home base for the good guys', 'undisclosed', 'unknown', 12.123454, 10.125431);
INSERT INTO `heroes`.`locations` (`LocationID`, `LocationName`, `LocationDescription`, `Street`, `City`, `Lat`, `Longitude`) VALUES (2, 'BatCave', 'Batman\'s super awesome cave of loneliness.', 'Burbank', 'CA', 15.123431, 100.123121);

COMMIT;


-- -----------------------------------------------------
-- Data for table `heroes`.`orgs`
-- -----------------------------------------------------
START TRANSACTION;
USE `heroes`;
INSERT INTO `heroes`.`orgs` (`OrgID`, `OrgName`, `OrgDescription`, `LocationID`) VALUES (1, 'Supercrew', 'Good guys only', 1);
INSERT INTO `heroes`.`orgs` (`OrgID`, `OrgName`, `OrgDescription`, `LocationID`) VALUES (2, 'Batman\'s Poker Night', 'Serious players only', 2);

COMMIT;


-- -----------------------------------------------------
-- Data for table `heroes`.`heroesorgbridge`
-- -----------------------------------------------------
START TRANSACTION;
USE `heroes`;
INSERT INTO `heroes`.`heroesorgbridge` (`HeroID`, `OrgID`) VALUES (1, 1);
INSERT INTO `heroes`.`heroesorgbridge` (`HeroID`, `OrgID`) VALUES (2, 2);

COMMIT;


-- -----------------------------------------------------
-- Data for table `heroes`.`sightings`
-- -----------------------------------------------------
START TRANSACTION;
USE `heroes`;
INSERT INTO `heroes`.`sightings` (`SightingID`, `HeroID`, `LocationID`, `Date`) VALUES (1, 1, 1, '2010-10-10');
INSERT INTO `heroes`.`sightings` (`SightingID`, `HeroID`, `LocationID`, `Date`) VALUES (2, 2, 2, '2005-01-01');

COMMIT;

-- MySQL Workbench Forward Engineering for TEST

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;

-- -----------------------------------------------------
-- Schema heroestest
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `heroestest` ;

-- -----------------------------------------------------
-- Schema heroestest
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `heroestest` DEFAULT CHARACTER SET utf8 ;
USE `heroestest` ;

-- -----------------------------------------------------
-- Table `heroestest`.`heroestest`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `heroestest`.`heroes` ;

CREATE TABLE IF NOT EXISTS `heroestest`.`heroes` (
  `HeroID` INT NOT NULL AUTO_INCREMENT,
  `HeroName` VARCHAR(45) NOT NULL,
  `HeroDescription` VARCHAR(128) NOT NULL,
  `HeroSuperPower` VARCHAR(128) NOT NULL,
  PRIMARY KEY (`HeroID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `heroestest`.`locations`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `heroestest`.`locations` ;

CREATE TABLE IF NOT EXISTS `heroestest`.`locations` (
  `LocationID` INT NOT NULL AUTO_INCREMENT,
  `LocationName` VARCHAR(45) NOT NULL,
  `LocationDescription` VARCHAR(128) NOT NULL,
  `Street` VARCHAR(128) NOT NULL,
  `City` VARCHAR(128) NOT NULL,
  `Lat` DECIMAL(9,6) NOT NULL,
  `Longitude` DECIMAL(9,6) NOT NULL,
  PRIMARY KEY (`LocationID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `heroestest`.`orgs`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `heroestest`.`orgs` ;

CREATE TABLE IF NOT EXISTS `heroestest`.`orgs` (
  `OrgID` INT NOT NULL AUTO_INCREMENT,
  `OrgName` VARCHAR(45) NOT NULL,
  `OrgDescription` VARCHAR(128) NOT NULL,
  `LocationID` INT NOT NULL,
  PRIMARY KEY (`OrgID`),
  INDEX `fk_locations_locationid_idx` (`LocationID` ASC),
  CONSTRAINT `fk_locations_locationid_idx`
    FOREIGN KEY (`LocationID`)
    REFERENCES `heroestest`.`locations` (`LocationID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `heroestest`.`heroestestorgbridge`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `heroestest`.`heroesorgbridge` ;

CREATE TABLE IF NOT EXISTS `heroestest`.`heroesorgbridge` (
  `HeroID` INT NOT NULL,
  `OrgID` INT NOT NULL,
  PRIMARY KEY (`HeroID`, `OrgID`),
  INDEX `fk_orgs_orgsid_idx` (`OrgID` ASC),
  INDEX `fk_heroestest_heroestestid_idx` (`HeroID` ASC),
  CONSTRAINT `fk_heroestest_heroestestid`
    FOREIGN KEY (`HeroID`)
    REFERENCES `heroestest`.`heroestest` (`HeroID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_orgs_orgsid`
    FOREIGN KEY (`OrgID`)
    REFERENCES `heroestest`.`orgs` (`OrgID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `heroestest`.`sightings`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `heroestest`.`sightings` ;

CREATE TABLE IF NOT EXISTS `heroestest`.`sightings` (
  `SightingID` INT NOT NULL AUTO_INCREMENT,
  `HeroID` INT NOT NULL,
  `LocationID` INT NOT NULL,
  `Date` DATE NOT NULL,
  PRIMARY KEY (`SightingID`),
  INDEX `fk_heroestest_heroestestid_idx` (`HeroID` ASC),
  INDEX `fk_locations_locationid_idx` (`LocationID` ASC),
  CONSTRAINT `fk_sightings_heroestest_heroestestid`
    FOREIGN KEY (`HeroID`)
    REFERENCES `heroestest`.`heroestest` (`HeroID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_sightings_locations_locationid`
    FOREIGN KEY (`LocationID`)
    REFERENCES `heroestest`.`locations` (`LocationID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;