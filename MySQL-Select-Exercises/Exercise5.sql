/*
   Select the orders whose freight is more than $100.00
*/

USE Northwind;
USE Northwind;

SELECT
	OrderID
FROM
	orders
WHERE
	Freight > 100;