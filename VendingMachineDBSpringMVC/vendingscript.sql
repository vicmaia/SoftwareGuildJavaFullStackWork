DROP SCHEMA IF exists vending;

CREATE SCHEMA vending;

USE vending;

CREATE TABLE item (
	itemId int auto_increment not null,
    itemName varchar(20) not null,
    price decimal (4,2) not null,
    quantity int (50) not null,
    primary key (itemId));
    
    INSERT INTO item VALUES (1, "Goldfish",.75, 8);
    INSERT INTO item VALUES (2, "Doritos", 1.00, 8);
    INSERT INTO item VALUES (3, "M&Ms",1.25, 7);
    INSERT INTO item VALUES (4, "Swedish Fish",1.50, 6);
    INSERT INTO item VALUES (5, "Snickers", 1.75, 5);
    INSERT INTO item VALUES (6, "Hershey Bar",2.00, 4);
    INSERT INTO item VALUES (7, "Slim Jim",2.25, 3);
    INSERT INTO item VALUES (8, "Trail Mix",2.50, 2);
    INSERT INTO item VALUES (9, "Party Mix",2.75, 0);

    