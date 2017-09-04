/*
	Get the count of how many employees 
	report to someone else in the company 
	without using a WHERE clause.
*/

USE Northwind;

SELECT 
	Count(*) as HasManager
FROM 
	Employees e1 
INNER JOIN 
	Employees e2 
ON 
	e1.EmployeeID = e2.ReportsTo;
    -- Join to itself but restrict to employeesid that have managers
