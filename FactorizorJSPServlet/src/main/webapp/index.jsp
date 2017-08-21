<%-- 
    Document   : index
    Created on : Aug 21, 2017, 1:00:06 PM
    Author     : n0252282
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Factorizor</title>
    </head>
    <body>
        <h1>Factorizor</h1>
        <p>
            Please enter the number that you would like to factor.
        </p>
        <p>
        <form method="POST" action="FactorizorServlet">
            <input type="test" name="numberToFactor">
            <input type="submit" value="Find Factors!!!">
        </form>
    </p>

</body>
</html>
