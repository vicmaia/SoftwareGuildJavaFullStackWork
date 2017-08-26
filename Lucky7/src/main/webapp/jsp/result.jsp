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
            You bet ${youBet}
        </p>
        <p>
            Rounds you played: ${rounds}
        </p>
        <p>
           Your best round: ${highestTotal}
        </p>
        <p>
                  
        <p>
            <a href="index.jsp">Lose some more money?</a>
        </p>
    </body>
</html>