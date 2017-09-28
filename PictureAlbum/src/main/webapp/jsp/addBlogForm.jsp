<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- #1 - Directive for Spring Form tag libraries -->
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Jazi Jeni's Jewelry And Junk</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">  
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/mystyle.css">
        <link href="https://fonts.googleapis.com/css?family=Indie+Flower" rel="stylesheet">
    </head>
    <body>
        <div class="container myBackground">
            <img alt="Bootstrap Image Preview" src="http://lorempixel.com/140/140/" id="icon" />
            <h1>Jazi Jeni's Jewelry And Junk</h1>
            <br>
            <br/>
            <br>
            <br/>
            <br>
            <br/>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation" 
                        class="active">
                        <a href="${pageContext.request.contextPath}/">
                            Jazi Jeni's Jewelry And Junk
                        </a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/displayContactsPage">
                            Login
                        </a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/adminPage">
                            AdminPage
                        </a>
                    </li>
                </ul>    
            </div>
            <c:if test="${pageContext.request.userPrincipal.name != null}">
                <p>Hello : ${pageContext.request.userPrincipal.name}
                    | <a href="<c:url value="/j_spring_security_logout" />" >Logout</a>
                </p>
            </c:if>
            <sf:form class="form-horizontal" role="form" modelAttribute="post" action="addBlog" method="POST">
                <div class="form-group myText">
                    <label for="add-blog-title" class="col-md-4 control-label">Blog Post Title:</label>
                    <div class="col-md-8">
                        <input type="text" class="form-control" id="add-blog-title"
                               name="title" placeholder="Blog Post Title"/>
                    </div>
                </div>
                <div class="form-group myText">
                    <label for="add-author-firstName" class="col-md-4 control-label">Author First Name:</label>
                    <div class="col-md-8">
                        <input type="text" class="form-control" id="add-author-firstName"
                               name="first_name" placeholder="Author First Name"/>
                    </div>
                </div>
                <div class="form-group myText">
                    <label for="add-author-lastName" class="col-md-4 control-label">Author Last Name:</label>
                    <div class="col-md-8">
                        <input type="text" class="form-control" id="add-author-lastName"
                               name="last_name" placeholder="Author Last Name"/>
                    </div>
                </div>
                <div class="form-group myText">
                    <label for="add-author-title" class="col-md-4 control-label">Author Title:</label>
                    <div class="col-md-8">
                        <input type="text" class="form-control" id="add-author-title"
                               name="author_title" placeholder="Author Title"/>
                    </div>
                </div>
                <div class="form-group myText" role="form" id="add-category" method="POST" action="addCategory">
                    <label for="add-category" class="col-md-4 control-label">Category:</label>
                    <div class="col-md-8">
                        <select name="category" class="form-control col-md-3" id="category" required>
                            <c:forEach var="currentCategory" items="${categoryList}">
                                <c:choose>
                                    <c:when test="${currentCategory.name eq post.category.name}">
                                        <option selected="true">
                                            <c:out value="${currentCategory.name}"/>
                                        </option>
                                    </c:when>
                                    <c:otherwise>
                                        <option>
                                            <c:out value="${currentCategory.name}"/>
                                        </option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                        <sf:errors path="${currentCategory.name}" cssclass="error"></sf:errors>
                        </div>
                    </div>   
                    <div class="form-group myText">
                        <label for="add-price" class="col-md-4 control-label">Price:</label>
                        <div class="col-md-8">
                            <input type="text" class="form-control" id="add-price"
                                   name="item_price" placeholder="Price"/>
                        </div>
                    </div>
                    <div class="form-group myText">
                        <label for="add-quantity" class="col-md-4 control-label">Quantity:</label>
                        <div class="col-md-8">
                            <input type="text" class="form-control" id="add-quantity"
                                   name="item_quantity" placeholder="Quantity"/>
                        </div>
                    </div>
                    <div class="form-group myText">
                        <label for="add-hashtags" class="col-md-4 control-label">Hashtag(s):</label>
                        <div class="col-md-8">
                            <input type="text" class="form-control" id="add-hashtags"
                                   name="hashtags" placeholder="Hashtags"/>
                        </div>
                    </div>   
                <div>
                    <div class="col-md-12">
                        <textarea id="blogpost" name="content" path="content" type="text" class="form-control" placeholder="Text" ></textarea>
                        <br>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-offset-4 col-md-6">
                        <input type="submit" name="cancelButton" class="btn btn-default" value="Cancel"/>
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

