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
        <title>Interest Calculator</title>
    </head>
    <body>
        <h1>Interest Calculator</h1>
        <p>
            Please enter your Interest Calculator variables below:
        </p>
        <p>
        <form method="POST" action="InterestServlet">
            <label for="width">Annual Interest Rate:</label>
            <input type="test" name="interest" id="width">
            <br/>
            <label for="length">Initial amount of principle:</label>
            <input type="test" name="principle" id="length">
            <br/>
            <label for="cpsf">The number of years the money is to stay in the fund:</label>
            <input type="test" name="years" id="cpsf">
            <br/>
            <input type="submit" value="Calculate interest!!!">
        </form>
    </p> 

</body>
</html>
