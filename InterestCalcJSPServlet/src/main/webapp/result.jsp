<%-- 
    Document   : result
    Created on : Aug 21, 2017, 1:01:49 PM
    Author     : n0252282
//c for jstl core
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Results</title>
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
        <%--       <p>
                   <c:forEach var="currentFactor" items="${factors}">
                       <c:out value="${currentFactor} "/>
                   </c:forEach>
               </p>
               <p>
                   <c:choose>
                       <c:when test="${isPerfect}">
                           <c:out value="The number is perfect." />
                       </c:when>
                       <c:otherwise>
                           <c:out value="The number is not perfect." />
                       </c:otherwise>  
                   </c:choose>
               </p>
               <c:choose>
                   <c:when test="${isPrime}">
                       <c:out value="The number is prime!" />
                   </c:when>
                   <c:otherwise>
                       <c:out value="The number is not prime." />
                   </c:otherwise>   
               </c:choose>                                          
        --%>
        <p>
            <a href="index.jsp">Enter another project!</a>
        </p>
    </body>
</html>
