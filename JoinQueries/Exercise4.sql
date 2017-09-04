/*
	Write a query to show every combination of employee and location.
*/

USE SWCCorp;
SELECT
	*
    FROM
    employee e
    CROSS JOIN
    location l

