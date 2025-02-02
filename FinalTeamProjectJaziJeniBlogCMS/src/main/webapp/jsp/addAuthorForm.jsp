<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- #1 - Directive for Spring Form tag libraries -->
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

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
        <div class="container myBackground">
            <img alt="Jeni's Icon" src="<c:url value="/img/mbunicorn.png"/>" id="icon" />
            <h1>Jazi Jeni's Jewelry And Junk</h1>
            <br>
            <br/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation" 
                        class="active">
                        <a href="${pageContext.request.contextPath}/">
                            Jazi Jeni's Jewelry And Junk
                        </a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/adminPage">
                            AdminPage
                        </a>
                    </li>
                    <c:forEach var="currentStatic" items="${staticList}">
                        <li role="presentation">
                            <a href="${pageContext.request.contextPath}/staticPage?staticID=${currentStatic.staticID}">
                                ${currentStatic.title}
                            </a>
                        </li>
                    </c:forEach>
                </ul>    
            </div>
            <div>
                <c:if test="${pageContext.request.userPrincipal.name != null}">
                    <div class="col-md-12">
                        <h3>
                        Hello : ${pageContext.request.userPrincipal.name}
                        | <a href="<c:url value="/j_spring_security_logout" />" > Logout</a>
                        </h3>
                    </div>
                </c:if>
                <br/>
            </div> 
            <sf:form class="form-horizontal" role="form" modelAttribute="author" action="addAuthor" method="POST">
                <div class="form-group myText">
                    <label for="add-author-firstName" class="col-md-4 control-label">Author First Name:</label>
                    <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="add-author-firstName"
                                  path="firstName" placeholder="Author First Name"/>
                        <sf:errors path="firstName" cssclass="error"></sf:errors>
                        </div>
                    </div>
                    <div class="form-group myText">
                        <label for="add-author-lastName" class="col-md-4 control-label">Author Last Name:</label>
                        <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="add-author-lastName"
                                  path="lastName" placeholder="Author Last Name"/>
                        <sf:errors path="lastName" cssclass="error"></sf:errors>
                        </div>
                    </div>
                    <div class="form-group myText">
                        <label for="add-author-title" class="col-md-4 control-label">Author Title:</label>
                        <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="add-author-title"
                                  path="title" placeholder="Author Title"/>
                        <sf:errors path="title" cssclass="error"></sf:errors>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-offset-5">
                            <a href ="adminPage">
                                <input type="button" name="cancelButton" class="btn btn-default" value="Cancel"/>
                            </a>
                            <input type="submit" name="submitButton" class="btn btn-default" value="Submit"/>
                        </div>
                    </div>
            </sf:form>                
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/tinymce/tinymce.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/tinymce/init-tinymce.js"></script>

    </body>
</html>


