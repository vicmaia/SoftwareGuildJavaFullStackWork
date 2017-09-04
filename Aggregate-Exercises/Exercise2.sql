/*
	Find the gross total (sum of quantity * unit price) for 
	all orders placed by B's Beverages and Chop-suey Chinese.
*/

USE Northwind;

select
	sum(od.Quantity*od.UnitPrice)
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
where 
c.CompanyName IN ('Chop-suey Chinese', 'B''s Beverages')
