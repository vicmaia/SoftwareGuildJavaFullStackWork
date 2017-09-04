/*
   Select the Suppliers whose contact has a title that starts with the word 
   Sales
*/

USE Northwind;

SELECT
	*
FROM
suppliers
WHERE
ContactTitle LIKE 'Sales%'