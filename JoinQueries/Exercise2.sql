/*
	Get the Company Name, Order Date, and each order details 
	Product name for USA customers only.
*/

USE Northwind;

SELECT
	c.CompanyName
    ,o.OrderDate
    ,od.OrderID
    ,p.ProductName
    
FROM
	orders o
JOIN
	customers c
ON
	o.CustomerID = c.CustomerID
JOIN
	order_details od
ON
	o.OrderID = od.OrderID
JOIN
	products p
ON
	od.ProductID = p.ProductID
WHERE
	o.ShipCountry = 'USA' and ProductName LIKE 'Chai%'