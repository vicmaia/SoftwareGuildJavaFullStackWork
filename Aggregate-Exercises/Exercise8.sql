/*
	Show the number of orders placed by customers 
	from fewest to most provided the customer has 
	a minimum of 4 orders.
*/

USE Northwind;
SELECT 
    COUNT(OrderID) AS TotalOrders, CompanyName
FROM
    Orders o
        INNER JOIN
    Customers c ON o.CustomerID = c.CustomerID
GROUP BY 
CompanyName
HAVING COUNT
(OrderID) >= 4
ORDER BY 
TotalOrders;