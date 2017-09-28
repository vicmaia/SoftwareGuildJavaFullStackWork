<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Jazi Jeni's Jewelry And Junk</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">  
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/mystyle.css">
        <link href="https://fonts.googleapis.com/css?family=Indie+Flower" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Permanent+Marker" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <img alt="Jeni's Icon" src="<c:url value="/img/mbunicorn.png"/>" id="icon" />
            <h1>Login</h1>
            <br>
            <br/>
        </div>
        <div>
            <form class="col-md-8 col-md-offset-2">
            <c:if test="${param.login_error == 1}">
                <h3>Wrong id or password!</h3>
            </c:if>
            </form>
            <br>
            <form class="form-horizontal myBackground col-md-8 col-md-offset-2" 
                  role="form" 
                  method="post" 
                  action="j_spring_security_check">
                <div class="form-group myText">
                    <label for="j_username" 
                           class="col-md-4 control-label">Username:</label>
                    <div class="col-md-4">
                        <input type="text" 
                               class="form-control" 
                               name="j_username" 
                               placeholder="Username"/>
                    </div>
                </div>
                <div class="form-group myText">
                    <label for="j_password" 
                           class="col-md-4 control-label">Password:</label>
                    <div class="col-md-4">
                        <input type="password" 
                               class="form-control" 
                               name="j_password" 
                               placeholder="Password"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-offset-4 col-md-8">
                        <input type="submit" 
                               class="btn btn-default" 
                               id="search-button" 
                               value="Sign In"/>
                    </div>
                </div>
            </form>    
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>

