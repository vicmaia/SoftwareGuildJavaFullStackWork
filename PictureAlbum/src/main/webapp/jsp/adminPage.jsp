<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
        <link href="https://fonts.googleapis.com/css?family=Permanent+Marker" rel="stylesheet">
    </head>
    <body>
        <div class="container myBackground">
            <img alt="Jeni's Icon" src="<c:url value="/img/mbunicorn.png"/>" id="icon" />
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
                            Jazi Jeni's Jewelery And Junk
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
                    | <a href="<c:url value="/j_spring_security_logout" />" > Logout</a>
                </p>
            </c:if>
            <p>
            </p>
            <hr/>
            <div class="form-group">
                <div class="col-md-2">
                    <a href="${pageContext.request.contextPath}/displayAddBlogForm">
                        <input type="newpost" class="btn btn-default" value="New Post"/>
                    </a>
                </div>
            </div>
            <div class="form-group">
                <div class="col-md-2">
                    <a href="${pageContext.request.contextPath}/displayBlogpost">
                        <input type="blogpost" class="btn btn-default" value="Blog Post"/>
                    </a>
                </div>
            </div>
            <div class="form-group">
                <div class="col-md-2">
                    <a href="${pageContext.request.contextPath}/displayStaticpages">
                        <input type="static" class="btn btn-default" value="Static Page"/>
                    </a>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12 myText">
                    <table id="blogTable" class="table">
                        <tr>
                            <th width="20%">Blog Title</th>
                            <th width="20%">Author</th>
                            <th width="20%">Category</th>
                            <th width="20%">Preview Blog</th>
                            <th width="5%">Price</th>
                            <th width="5%">Quantity</th>
                            <th width="5%"></th>
                            <th width="5%"></th>
                        </tr>
                        <c:forEach var="currentPost" items="${postList}"> 
                            <tr>
                                <td>
                                    <c:out value="${currentPost.title}"/>
                                </td>
                                <td>
                                    <c:out value="${currentPost.author.firstName}"/> <c:out value="${currentPost.author.lastName}"/>
                                </td>
                                <td>
                                    <c:out value="${currentPost.category.name}"/> 
                                </td>
                                <td>
                                    <c:out value="${currentPost.content}" escapeXml="false"/> 
                                </td>
                                <td>
                                    <c:out value="${currentPost.item.itemPrice}"/> 
                                </td>
                                <td>
                                    <c:out value="${currentPost.item.itemQuantity}"/> 
                                </td>
                                <td>
                                    <a href="displayEditBlogForm?postID=${currentPost.postID}">
                                        Edit
                                    </a>
                                </td>
                                <td>
                                    Delete
                                </td>
                            </tr>
                        </c:forEach>
                    </table> 
                </div>
            </div>
            <div class="container-fluid myText">
                <p>
                    Pending Edits
                    <!--for blogs with status of pending edit
                    need to move from post list row and move to pending  edits
                    If user role = admin - go to the edit page and and move to pending edits???-->
                </p>
                <div class="row">
                    <div class="col-md-12">
                        <table id="blogTable" class="table">
                            <tr>
                                <th width="20%">Blog Title</th>
                                <th width="20%">Author</th>
                                <th width="20%">Category</th>
                                <th width="20%">Preview Blog</th>
                                <th width="5%">Price</th>
                                <th width="5%">Quantity</th>
                                <th width="5%"></th>
                                <th width="5%"></th>
                            </tr>
                            <c:forEach var="currentPost" items="${postList}"> 
                                <tr>
                                    <td>
                                        <c:out value="${currentPost.title}"/>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table> 
                    </div>
                </div>
            </div>
            <div class="container-fluid myText">
                <p>
                    Pending Deletes
                    <!--for blogs with status of pending edit
                    need to remove from post list row and move to pending deletes
                    If user role = Admin - is there a need to move to pending deletes???-->
                </p>
                <div class="row">
                    <div class="col-md-12">
                        <table id="blogTable" class="table">
                            <tr>
                                <th width="20%">Blog Title</th>
                                <th width="20%">Author</th>
                                <th width="20%">Category</th>
                                <th width="20%">Preview Blog</th>
                                <th width="5%">Price</th>
                                <th width="5%">Quantity</th>
                                <th width="5%"></th>
                                <th width="5%"></th>
                            </tr>
                            <c:forEach var="currentPost" items="${postList}"> 
                                <tr>
                                    <td>
                                        <c:out value="${currentPost.title}"/>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table> 
                    </div>
                </div>
            </div>
        </div>
        <h2></h2>

        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>