DROP SCHEMA IF exists address;

CREATE SCHEMA address;

USE address;

CREATE TABLE address (
	addressID int auto_increment not null,
    lastName varchar(20) not null,
    firstName varchar(20) not null,
    streetAddress varchar (128) not null,
    city varchar(20) not null,
    state varchar(20) not null,
    zip varchar(20) not null,
    primary key (addressID));
    
    INSERT INTO address VALUES (1, "Gardiner","Ken","Beauty Hill","Barrington","NH","03825");