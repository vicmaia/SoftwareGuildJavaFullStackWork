<%-- 
    Document   : result
    Created on : Aug 22, 2017, 3:01:38 PM
    Author     : n0252282
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" 
              content="text/html; charset=UTF-8">
        <title>Result</title>
    </head>
    <body>

        <h1>Result</h1>
        <p>
            Your project consisted of: 
            <br/>
            ${totalSqFt} Square feet of flooring
            <br/>
            ${totalCost} Total material cost  
            <br/>
            ${totalLabor} Total labor cost
            </br>
            ${overall} Total project cost
        </p>     
        <p>
            <a href="index.jsp">Factor Another One!</a>
        </p>
    </body>
</html>