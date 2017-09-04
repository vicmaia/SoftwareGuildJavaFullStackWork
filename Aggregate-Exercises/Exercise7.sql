/*
	Find the total sales by supplier 
	ordered from most to least.
*/

USE Northwind;
SELECT
	s.CompanyName
	,sum( od.UnitPrice * od.Quantity) as TotalSales
FROM
suppliers s
JOIN
products p
ON
s.SupplierID = p.SupplierID
JOIN
order_details od
ON
od.ProductId = p.ProductID
JOIN
orders o 
ON
o.OrderID = od.OrderID
group by
s.SupplierID
ORDER by
TotalSales DESC;