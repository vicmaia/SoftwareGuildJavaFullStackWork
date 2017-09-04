/*
	Challenge: 
	Show the total amount of orders by
	year and country.  Data should be ordered 
	by year ascending and total descending.
	
	TotalSales    Year     Country
	41907.80      1996     USA
	37804.60      1996     Germany
	etc...
	
	Hint: Research the DatePart() function
*/

USE Northwind;

SELECT
	SUM(od.Quantity * od.UnitPrice) as TotalSales, c.Country,
	YEAR(o.OrderDate) as `Year`
FROM
Orders o
JOIN
order_details od 
ON o.OrderID = od.OrderID
JOIN
Customers c 
ON o.CustomerID = c.CustomerID
GROUP BY
c.Country, YEAR(o.OrderDate)
ORDER BY
`Year`, TotalSales DESC;