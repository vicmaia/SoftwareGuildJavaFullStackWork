/*
	Get all the order information for any order where Chai was sold.
*/

USE Northwind;

SELECT
	o.*
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
	p.ProductName LIKE 'Chai%'