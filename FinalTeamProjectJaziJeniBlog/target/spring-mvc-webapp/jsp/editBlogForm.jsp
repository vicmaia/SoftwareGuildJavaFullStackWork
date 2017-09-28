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
        <div class="container myBackground myText">
            <img alt="Jeni's Icon" src="<c:url value="/img/mbunicorn.png"/>" id="icon" />
            <h1>Jazi Jeni's Jewelry And Junk</h1>
            <br>
            <br/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation" 
                        class="active">
                        <a href="${pageContext.request.contextPath}/">
                            Jazi Jeni's Jewelery And Junk
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
            <sf:form class="form-horizontal" role="form" modelAttribute="post" action="editBlog" enctype="multipart/form-data" method="POST">
                <div class="form-group">
                    <label for="add-blog-title" class="col-md-4 control-label">Blog Post Title:</label>
                    <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="add-blog-title"
                                  path="title" placeholder="Blog Post Title"/>
                        <sf:errors path="title" cssclass="error"></sf:errors>
                        </div>
                    </div>

                    <div class="form-group" role="form" id="add-author-firstName" method="POST" action="addAuthor">
                        <label for="add-author" class="col-md-4 control-label">Author:</label>    
                        <div class="col-md-8">
                            <select name="author.authorID" path="author.authorID" class="form-control col-md-3" id="author" required>
                            <c:forEach var="currentAuthor" items="${authorList}">
                                <c:choose>
                                    <c:when test="${currentAuthor.firstName eq post.author.firstName}">
                                        <option selected="true" name="author.authorID" value="${currentAuthor.authorID}">
                                            <c:out value="${currentAuthor.firstName} ${currentAuthor.lastName}"/>
                                        </option>
                                    </c:when>
                                    <c:otherwise>
                                        <option name="author.authorID" value="${currentAuthor.authorID}">
                                            <c:out value="${currentAuthor.firstName} ${currentAuthor.lastName}"/>
                                        </option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                        <sf:errors path="${currentAuthor.firstName; currentAuthor.lastName}" cssclass="error"></sf:errors>
                        </div>
                    </div>


                    <div class="form-group" role="form" id="add-category" method="POST" action="addCategory">
                        <label for="add-category" class="col-md-4 control-label">Category:</label>
                        <div class="col-md-8">
                            <select name="category.categoryID" path="category.categoryID" class="form-control col-md-3" id="category" required>
                            <c:forEach var="currentCategory" items="${categoryList}">
                                <c:choose>
                                    <c:when test="${currentCategory.name eq post.category.name}">
                                        <option selected="true" name="category.categoryID" value="${currentCategory.categoryID}">
                                            <c:out value="${currentCategory.name}"/>
                                        </option>
                                    </c:when>
                                    <c:otherwise>
                                        <option name="category.categoryID" value="${currentCategory.categoryID}">
                                            <c:out value="${currentCategory.name}"/>
                                        </option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                        <sf:errors path="${currentCategory.name}" cssclass="error"></sf:errors>
                        </div>
                    </div>    


                    <div class="form-group" role="form" id="add-item" method ="POST" action = "addItem">
                        <label for="add-item" class="col-md-4 control-label">Item:</label>    
                        <div class="col-md-8">
                            <select name="item.itemID" path="item.itemID" class="form-control col-md-3" id="item" required>
                            <c:forEach var="currentItem" items="${itemList}">
                                <c:choose>
                                    <c:when test="${currentItem.itemID eq post.item.itemID}">
                                        <option selected="true" name="item.itemID" value="${currentItem.itemID}">
                                            <c:out value="${currentItem.itemName}; $${currentItem.itemPrice}; Quantity: ${currentItem.itemQuantity}"/>
                                        </option>
                                    </c:when>
                                    <c:otherwise>
                                        <option name="item.itemID" value="${currentItem.itemID}">
                                            <c:out value="${currentItem.itemName}; $${currentItem.itemPrice}; Quantity: ${currentItem.itemQuantity}"/>
                                        </option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                        <sf:errors path="${currentItem.itemName; currentItem.itemPrice; currentItem.itemQuantity}" cssclass="error"></sf:errors>
                        </div>
                    </div> 

                    <div class="form-group">
                        <label for="add-hashtags" class="col-md-4 control-label">Hashtag(s):</label>
                        <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="add-hashtags"
                                  path="hashtags" placeholder="Hashtags"/>
                        <sf:errors path="hashtags" cssclass="error"></sf:errors>
                        </div>
                    </div>    
                    <div>                        
                    <sf:hidden path="postID"/>                   
                    <sf:hidden path="author.authorID"/>
                    <sf:hidden path="item.itemID"/>                      
                    <sf:hidden path="category.categoryID"/>
                </div>
                <div>

                    <div class="col-md-12">
                        <sf:textarea id="blogpost" path="content" type="text" class="form-control" placeholder="Text" ></sf:textarea>
                        <sf:errors path="content" cssclass="error"></sf:errors>
                            <br>
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
        <script src="${pageContext.request.contextPath}/js/tinymce/addblog-init-tinymce.js"></script>

    </body>
</html>

