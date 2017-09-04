/*
	Find the average freight paid for orders 
	placed by companies in the USA
*/

USE Northwind;

select
	avg(o.Freight)
from
orders o
where 
o.ShipCountry = 'USA';
