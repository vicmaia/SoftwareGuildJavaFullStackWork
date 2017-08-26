<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Lucky 7s</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" 
              rel="stylesheet">        
    </head>
    <body>
        <div class="container">
            <h1>Lucky 7s</h1>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation" 
                        class="active">
                            <a href="${pageContext.request.contextPath}/index.jsp">
                                Home
                            </a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/hello/sayhi">
                            Hello Controller
                        </a>
                    </li>
                </ul>    
            </div>
        <h1>Lucky 7s</h1>
        <p>
            You enter money, I roll dice until you are out. If I get a 7 you add 4, if not, you lose 1.
        </p>
        <form method="post" action="LuckyServlet">
            <input type="text" name="bet"/><br/>
            <input type="submit" value="Bet!"/>
        </form>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js">
        </script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js">
        </script>

    </body>
</html>

