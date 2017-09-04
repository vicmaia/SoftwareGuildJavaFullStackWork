/*
	Get a list of each employee first name and lastname
	and the territory names they are associated with
*/

USE Northwind;

SELECT 
e.EmployeeID,
e.LastName,
e.FirstName,
t.TerritoryDescription
FROM 
employees e
JOIN
employeeterritories et
ON
e.EmployeeID = et.EmployeeID
JOIN
territories t
ON 
et.TerritoryID = t.TerritoryID
