/*
	Find the gross total of all orders (sum of quantity * unit price) 
	for each customer, order it in descending order by the total.
*/

USE Northwind;

select
	c.CompanyName
	,sum(od.Quantity*od.UnitPrice) Gross
from
orders o
join
order_details od
ON
o.OrderID = od.OrderID
JOIN
customers c
on
c.CustomerID = o.CustomerID
group by
c.CompanyName
ORDER BY Gross DESC;
