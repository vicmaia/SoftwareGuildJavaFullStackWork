-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;

-- -----------------------------------------------------
-- Schema hotel
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `hotel`;

-- -----------------------------------------------------
-- Schema hotel
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `hotel` DEFAULT CHARACTER SET utf8 ;
USE `hotel`;

-- -----------------------------------------------------
-- Table `hotel`.`roomtypes`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `hotel`.`roomtypes` ;

CREATE TABLE IF NOT EXISTS `hotel`.`roomtypes` (
  `RoomTypeID` INT(6) NOT NULL AUTO_INCREMENT,
  `RoomTypeName` VARCHAR(45) NOT NULL,
  `RoomTypeDescription` VARCHAR(256) NOT NULL,
  PRIMARY KEY (`RoomTypeID`));


-- -----------------------------------------------------
-- Table `hotel`.`rooms`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `hotel`.`rooms` ;

CREATE TABLE IF NOT EXISTS `hotel`.`rooms` (
  `RoomID` INT(9) NOT NULL AUTO_INCREMENT,
  `RoomNumber` INT(6) NOT NULL,
  `RoomFloor` INT(4) NOT NULL,
  `RoomOccupancy` INT(3) NOT NULL,
  `RoomTypeID` INT(6) NOT NULL,
  PRIMARY KEY (`RoomID`),
  UNIQUE INDEX `rooms_RoomNumber_UNIQUE` (`RoomNumber` ASC),
  INDEX `fk_Rooms_RoomTypeID_Idx` (`RoomTypeID` ASC),
  CONSTRAINT `fk_Rooms_RoomTypeID`
    FOREIGN KEY (`RoomTypeID`)
    REFERENCES `hotel`.`roomtypes` (`RoomTypeID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `hotel`.`amenities`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `hotel`.`amenities` ;

CREATE TABLE IF NOT EXISTS `hotel`.`amenities` (
  `RoomAmenityID` INT(6) NOT NULL AUTO_INCREMENT,
  `RoomAmenityName` VARCHAR(45) NOT NULL,
  `RoomAmenityDescription` VARCHAR(256) NOT NULL,
  PRIMARY KEY (`RoomAmenityID`));


-- -----------------------------------------------------
-- Table `hotel`.`postalcode`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `hotel`.`postalcode` ;

CREATE TABLE IF NOT EXISTS `hotel`.`postalcode` (
  `ZipCode` CHAR(5) NOT NULL,
  `City` VARCHAR(45) NOT NULL,
  `State` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ZipCode`));


-- -----------------------------------------------------
-- Table `hotel`.`customeraddresses`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `hotel`.`customeraddresses` ;

CREATE TABLE IF NOT EXISTS `hotel`.`customeraddresses` (
  `AddressID` INT(12) NOT NULL,
  `StreetAddress` VARCHAR(256) NOT NULL,
  `ZipCode` CHAR(5) NOT NULL,
  PRIMARY KEY (`AddressID`),
  INDEX `fk_CustomerAddresses_ZipCode_Idx` (`ZipCode` ASC),
  CONSTRAINT `fk_CustomerAddresses_ZipCode`
    FOREIGN KEY (`ZipCode`)
    REFERENCES `hotel`.`postalcode` (`ZipCode`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `hotel`.`customers`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `hotel`.`customers` ;

CREATE TABLE IF NOT EXISTS `hotel`.`customers` (
  `CustomerID` INT(12) NOT NULL,
  `CustomerFirstName` VARCHAR(45) NOT NULL,
  `CustomerLastName` VARCHAR(45) NOT NULL,
  `CustomerEmail` VARCHAR(256) NULL,
  `AddressID` INT(12) NOT NULL,
  PRIMARY KEY (`CustomerID`),
  INDEX `fk_Customers_AddressID_Idx` (`AddressID` ASC),
  CONSTRAINT `fk_customers_addressID`
    FOREIGN KEY (`AddressID`)
    REFERENCES `hotel`.`customeraddresses` (`AddressID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `hotel`.`guests`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `hotel`.`guests` ;

CREATE TABLE IF NOT EXISTS `hotel`.`guests` (
  `GuestID` INT(12) NOT NULL,
  `CustomerID` INT(9) NOT NULL,
  `GuestName` VARCHAR(45) NOT NULL,
  `GuestAge` INT(3) NOT NULL,
  PRIMARY KEY (`GuestID`));


-- -----------------------------------------------------
-- Table `hotel`.`reservations`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `hotel`.`reservations` ;

CREATE TABLE IF NOT EXISTS `hotel`.`reservations` (
  `ReservationID` INT(12) NOT NULL AUTO_INCREMENT,
  `ReservationCheckInDate` DATE NOT NULL,
  `ReservationCheckOutDate` DATE NOT NULL,
  `CustomerID` INT(12) NOT NULL,
  INDEX `fk_Customers_CustomerID_Idx` (`CustomerID` ASC),
  PRIMARY KEY (`ReservationID`),
  CONSTRAINT `fk_Customers_CustomerID`
    FOREIGN KEY (`CustomerID`)
    REFERENCES `hotel`.`customers` (`CustomerID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `hotel`.`rates`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `hotel`.`rates` ;

CREATE TABLE IF NOT EXISTS `hotel`.`rates` (
  `RoomRateID` INT(12) NOT NULL,
  `RoomRateName` VARCHAR(64) NOT NULL,
  `RoomRatePrice` DECIMAL(8,2) NOT NULL,
  PRIMARY KEY (`RoomRateID`));


-- -----------------------------------------------------
-- Table `hotel`.`reservationsroomsguestsbridge`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `hotel`.`reservationsroomsguestsbridge` ;

CREATE TABLE IF NOT EXISTS `hotel`.`reservationsroomsguestsbridge` (
  `ReservationID` INT(12) NOT NULL,
  `RoomID` INT(9) NOT NULL,
  `GuestID` INT(12) NOT NULL,
  PRIMARY KEY (`ReservationID`, `RoomID`, `GuestID`),
  INDEX `fk_Rooms_RoomID_Idx` (`RoomID` ASC),
  INDEX `fk_Guests_GuestID_Idx` (`GuestID` ASC),
  INDEX `fk_Reservations_ReservationID_Idx` (`ReservationID` ASC),
  CONSTRAINT `fk_Reservations.ReservationID`
    FOREIGN KEY (`ReservationID`)
    REFERENCES `hotel`.`reservations` (`ReservationID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Rooms.RoomID`
    FOREIGN KEY (`RoomID`)
    REFERENCES `hotel`.`rooms` (`RoomID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Guests.GuestID`
    FOREIGN KEY (`GuestID`)
    REFERENCES `hotel`.`guests` (`GuestID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `hotel`.`addons`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `hotel`.`addons` ;

CREATE TABLE IF NOT EXISTS `hotel`.`addons` (
  `AddOnID` INT(12) NOT NULL,
  `AddOnName` VARCHAR(45) NOT NULL,
  `AddOnPrice` DECIMAL(6,2) NOT NULL,
  PRIMARY KEY (`AddOnID`));


-- -----------------------------------------------------
-- Table `hotel`.`addonsreservationsbridge`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `hotel`.`addonsreservationsbridge` ;

CREATE TABLE IF NOT EXISTS `hotel`.`addonsreservationsbridge` (
  `AddOnID` INT(12) NOT NULL,
  `ReservationID` INT(12) NOT NULL,
  `BeginDate` DATE NOT NULL,
  `EndDate` DATE NOT NULL,
  PRIMARY KEY (`AddOnID`, `ReservationID`, `BeginDate`, `EndDate`),
  INDEX `fk_addons_reservations_reservations_Idx` (`ReservationID` ASC),
  INDEX `fk_addons_reservations_addons_Idx` (`AddOnID` ASC),
  CONSTRAINT `fk_addons_reservations_addons`
    FOREIGN KEY (`AddOnID`)
    REFERENCES `hotel`.`addons` (`AddOnID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_addons_reservations_reservations1`
    FOREIGN KEY (`ReservationID`)
    REFERENCES `hotel`.`reservations` (`ReservationID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `hotel`.`promotioncodes`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `hotel`.`promotioncodes` ;

CREATE TABLE IF NOT EXISTS `hotel`.`promotioncodes` (
  `PromotionCodesID` INT(12) NOT NULL,
  `PromotionName` VARCHAR(45) NOT NULL,
  `PromotionPercent` DECIMAL(3,2) NULL,
  `PromotionAmountDiscount` DECIMAL(5,2) NULL,
  PRIMARY KEY (`PromotionCodesID`));


-- -----------------------------------------------------
-- Table `hotel`.`promotioncodesreservationsbridge`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `hotel`.`promotioncodesreservationsbridge` ;

CREATE TABLE IF NOT EXISTS `hotel`.`promotioncodesreservationsbridge` (
  `PromotionCodesID` INT(12) NOT NULL,
  `ReservationID` INT(12) NOT NULL,
  `BeginDate` DATE NOT NULL,
  `EndDate` DATE NOT NULL,
  PRIMARY KEY (`PromotionCodesID`, `ReservationID`, `BeginDate`, `EndDate`),
  INDEX `fk_Reservations_ReservationID_Idx` (`ReservationID` ASC),
  INDEX `fk_PromotionCodes_PromotionCodesID_Idx` (`PromotionCodesID` ASC),
  CONSTRAINT `fk_PromotionCodes_PromotionCodesID`
    FOREIGN KEY (`PromotionCodesID`)
    REFERENCES `hotel`.`promotioncodes` (`PromotionCodesID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Reservations_ReservationID`
    FOREIGN KEY (`ReservationID`)
    REFERENCES `hotel`.`reservations` (`ReservationID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `hotel`.`billdetails`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `hotel`.`billdetails` ;

CREATE TABLE IF NOT EXISTS `hotel`.`billdetails` (
  `BillDetailID` INT(12) NOT NULL,
  `RoomCharges` DECIMAL(8,2) NULL,
  `MealCharges` DECIMAL(6,2) NULL,
  `MovieCharges` DECIMAL(6,2) NULL,
  `OtherAddOns` DECIMAL(6,2) NULL,
  `ReservationID` INT(12) NULL,
  PRIMARY KEY (`BillDetailID`),
  INDEX `fk_Reservations_ReservationID_BillDetails_Idx` (`ReservationID` ASC),
  CONSTRAINT `fk_Reservations_ReservationID_BillDetails`
    FOREIGN KEY (`ReservationID`)
    REFERENCES `hotel`.`reservations` (`ReservationID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `hotel`.`billtotalsheader`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `hotel`.`billtotalsheader` ;

CREATE TABLE IF NOT EXISTS `hotel`.`billtotalsheader` (
  `BillTotalsID` INT(12) NOT NULL,
  `RoomTotal` DECIMAL(8,2) NULL,
  `AddOnTotal` DECIMAL(8,2) NULL,
  `TaxesTotal` DECIMAL(8,2) NULL,
  `GrandTotal` DECIMAL(8,2) NULL,
  `BillDetailsID` INT(12) NULL,
  PRIMARY KEY (`BillTotalsID`),
  INDEX `fk_BillDetals_BillDetailsID_Idx` (`BillDetailsID` ASC),
  CONSTRAINT `fk_BillDetails_BillDetailID`
    FOREIGN KEY (`BillDetailsID`)
    REFERENCES `hotel`.`billdetails` (`BillDetailID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `hotel`.`roomsamenitiesbridge`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `hotel`.`roomsamenitiesbridge` ;

CREATE TABLE IF NOT EXISTS `hotel`.`roomsamenitiesbridge` (
  `RoomID` INT(9) NOT NULL,
  `RoomAmenityID` INT(6) NOT NULL,
  PRIMARY KEY (`RoomID`, `RoomAmenityID`),
  INDEX `fk_Amenities_AmenitiesID_Idx` (`RoomAmenityID` ASC),
  INDEX `fk_Rooms_RoomID_Idx` (`RoomID` ASC),
  CONSTRAINT `fk_Rooms_RoomID`
    FOREIGN KEY (`RoomID`)
    REFERENCES `hotel`.`rooms` (`RoomID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Amenities_RoomAmenityid`
    FOREIGN KEY (`RoomAmenityID`)
    REFERENCES `hotel`.`amenities` (`RoomAmenityID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `hotel`.`roomtypesratesbridge`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `hotel`.`roomtypesratesbridge` ;

CREATE TABLE IF NOT EXISTS `hotel`.`roomtypesratesbridge` (
  `RoomTypeID` INT(6) NOT NULL,
  `RoomRateID` INT(12) NOT NULL,
  `BeginDate` DATE NOT NULL,
  `Enddate` DATE NOT NULL,
  PRIMARY KEY (`RoomTypeID`, `RoomRateID`, `BeginDate`, `Enddate`),
  INDEX `fk_Rates_RoomRateID_Idx` (`RoomRateID` ASC),
  INDEX `fk_RoomTypes_RoomTypeID_Idx` (`RoomTypeID` ASC),
  CONSTRAINT `fk_Roomtypes_RoomTypeID`
    FOREIGN KEY (`RoomTypeID`)
    REFERENCES `hotel`.`roomtypes` (`RoomTypeID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Rates_RoomRatesID`
    FOREIGN KEY (`RoomRateID`)
    REFERENCES `hotel`.`rates` (`RoomRateID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
 