/*
   Select the orders shipping to the USA whose freight is 
   between $10 and $20
*/

USE Northwind;

SELECT
	OrderID
    ,Freight
    ,ShipCountry
FROM
	orders
WHERE
	Freight BETWEEN 10 AND 20;