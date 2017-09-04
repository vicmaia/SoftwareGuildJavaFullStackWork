/*
	Find a list of all the Employees who have never found a Grant
*/

USE SWCCorp;

SELECT 
*
FROM
employee e
LEFT JOIN
`grant` g
ON
e.EmpID = g.EmpID
WHERE g.GrantID IS NULL;