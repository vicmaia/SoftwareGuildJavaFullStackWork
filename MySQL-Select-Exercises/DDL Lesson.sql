DROP DATABASE IF EXISTS MovieCatalogue;
CREATE DATABASE MovieCatalogue;

USE MovieCatalogue;

DROP TABLE IF EXISTS Movie;
CREATE TABLE Movie (
    MovieID INT(9) NOT NULL AUTO_INCREMENT,
    GenreID INT(9) NOT NULL,
    DirectorID INT(9) NULL,
    RatingID INT(3) NULL,
    Title VARCHAR(128) NOT NULL,
    ReleaseDate DATE NULL,
    PRIMARY KEY (MovieID)
);

DROP TABLE IF EXISTS Genre;
CREATE TABLE Genre (
    GenreID INT(9) NOT NULL AUTO_INCREMENT,
    GenreName VARCHAR(30) NOT NULL,
    PRIMARY KEY (GenreID)
);

DROP TABLE IF EXISTS Director;
CREATE TABLE Director (
    DirectorID INT(9) NOT NULL AUTO_INCREMENT,
    FirstName VARCHAR(30) NOT NULL,
    LastName VARCHAR(30) NOT NULL,
    BirthDate date NULL,
    PRIMARY KEY (DirectorID)
);

DROP TABLE IF EXISTS Rating;
CREATE TABLE Rating (
    RatingID INT(3) NOT NULL AUTO_INCREMENT,
    RatingName VARCHAR(5) NOT NULL,
    PRIMARY KEY (RatingID)
);

-- Add FKs for Movie table

ALTER TABLE Movie
 ADD CONSTRAINT fk_MovieGenre FOREIGN KEY (GenreID) REFERENCES Genre
(GenreID) ON DELETE NO ACTION;
ALTER TABLE Movie
 ADD CONSTRAINT fk_MovieDirector FOREIGN KEY (DirectorID) REFERENCES Director
(DirectorID) ON DELETE NO ACTION;
ALTER TABLE Movie
 ADD CONSTRAINT fk_MovieRating FOREIGN KEY (RatingID) REFERENCES Rating
(RatingID) ON DELETE NO ACTION;

DROP TABLE IF EXISTS Actor;
CREATE TABLE Actor (
    ActorID INT(9) NOT NULL AUTO_INCREMENT,
    FirstName VARCHAR(30) NOT NULL,
    LastName VARCHAR(30) NOT NULL,
    BirthDate date NULL,
    PRIMARY KEY (ActorID)
);

DROP TABLE IF EXISTS CastMembers;
CREATE TABLE CastMembers (
    CastMemberID INT(9) NOT NULL AUTO_INCREMENT,
    ActorID INT(9) NOT NULL,
    MovieID INT(9) NOT NULL,
    Role VARCHAR(50) NOT NULL,
    PRIMARY KEY (CastMemberID)
);

-- Add FKs for CastMembers table
ALTER TABLE CastMembers
 ADD CONSTRAINT fk_ActorID FOREIGN KEY (ActorID) REFERENCES Actor
(ActorID) ON DELETE NO ACTION;
ALTER TABLE CastMembers
 ADD CONSTRAINT fk_MovieID FOREIGN KEY (MovieID) REFERENCES Movie
(MovieID) ON DELETE NO ACTION;
