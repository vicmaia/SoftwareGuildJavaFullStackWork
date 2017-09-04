use northwind;

SELECT 
CategoryName
, ProductName
, UnitPrice
, UnitsInStock
FROM 
Categories
INNER JOIN 
Products 
ON
Categories.CategoryID = Products.CategoryID
ORDER BY 
CategoryName;

SELECT 
    CategoryName
    ,ProductName
    ,UnitPrice
    ,UnitsInStock
FROM
    Categories
INNER JOIN
    Products 
ON 
	Categories.CategoryID = Products.CategoryID
ORDER BY 
	CategoryName,ProductName;
    
SELECT 
    CategoryName,ProductName,UnitPrice,UnitsInStock
FROM
    Categories
INNER JOIN
    Products
ON 
	Categories.CategoryID = Products.CategoryID
ORDER BY 
	CategoryName , UnitPrice DESC;
    
SELECT 
    CategoryName, ProductName, UnitPrice, UnitsInStock
FROM
    Categories
        INNER JOIN
    Products ON Categories.CategoryID = Products.CategoryID
ORDER BY CategoryName ASC , UnitPrice DESC;

SELECT 
    CategoryName, ProductName, UnitPrice, UnitsInStock
FROM
    Categories
        INNER JOIN
    Products ON Categories.CategoryID = Products.CategoryID
WHERE
    CategoryName = 'Confections'
ORDER BY UnitPrice DESC
LIMIT 0 , 5;


SELECT 
    CategoryName, ProductName, UnitPrice, UnitsInStock
FROM
    Categories
        INNER JOIN
    Products ON Categories.CategoryID = Products.CategoryID
WHERE
    CategoryName = 'Confections'
ORDER BY UnitPrice DESC
LIMIT 5 , 5;