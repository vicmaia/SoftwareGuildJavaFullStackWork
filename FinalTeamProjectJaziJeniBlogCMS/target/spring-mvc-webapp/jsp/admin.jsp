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
    </head>
    <body>
        <div class="container">
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
                            Jazi Jeni's Jewelery And Junk
                        </a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/displayContactsPage">
                            Login
                        </a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/jsp/search.jsp">
                            Search
                        </a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/jsp/adminPage.jsp">
                            AdminPage
                        </a>
                    </li>
                </ul>    
            </div>
            <p>
            </p>
            <hr/>
            <div class="form-group">
                <div class="col-md-2">
                    <input type="newpost" class="btn btn-default" value="New Post"/>
                    <a href="${pageContext.request.contextPath}/displayNewpost">
                    </a>
                </div>
            </div>
            <div class="form-group">
                <div class="col-md-2">
                    <input type="blogpost" class="btn btn-default" value="Blog Post"/>
                    <a href="${pageContext.request.contextPath}/displayBlogpost">
                </div>
            </div>
            <div class="form-group">
                <div class="col-md-2">
                    <input type="static" class="btn btn-default" value="Static Page"/>
                    <a href="${pageContext.request.contextPath}/displayStaticpages">
                </div>
            </div>
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
                                    <a href="displayPost?postId=${currentPost.title}">
                                        <c:out value="${currentPost.title}"/>
                                    </a>
                                </td>
                                <td>
                                    <c:out value="${currentPost.firstName}"/> <c:out value="${current.lastName}"/>
                                </td>
                                <td>
                                    <c:forEach var="currentCategory" items="${categoryList}">
                                <option>
                                    <c:out value="${currentCategory.category}"/>
                                </option>
                            </c:forEach>
                            </td>
                            <td>
                                <c:out value="${currentPost.preview}"/>
                            </td>
                            <td>
                                <c:out value="${items.item_price}"/>
                            </td>
                            <td>
                                <c:out value="${currentPost.quantity}"/>
                            </td>   
                            <td>
                                <c:out value="${currentPost.author}"/>
                            </td>
                            <td>
                                <c:out value="${currentPost.edit}"/>
                            </td>
                            <td>
                                <c:out value="${currentPost.delete}"/>
                            </td>
                            </tr>
                        </c:forEach>
                    </table> 
                </div>
            </div>
            <p>
                Pending Edits
            </p>
            <div class="container-fluid">
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
                            <c:forEach var="blogTitle" items="${blogList}">
                                <tr>
                                    <td>
                                        <c:out value="${blogList.blogTitle}"/>
                                    </td>
                                    <td>
                                        <c:out value="${blogList.author}"/>
                                    </td>
                                    <td>
                                        <c:out value="${blogList.preview}"/>
                                    </td>
                                    <td>
                                        <c:out value="${blogList.price}"/>
                                    </td>
                                    <td>
                                        <c:out value="${blogList.quantity}"/>
                                    </td>
                                    <td>
                                        <c:out value="${blogList.author}"/>
                                    </td>
                                    <td>
                                        <c:out value="${blogList.approve_edit}"/>
                                    </td>
                                    <td>
                                        <c:out value="${blogList.reject_edit}"/>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table> 
                    </div>
                </div>
            </div>

            <p>
                Pending Deletes
            </p>
            <div class="container-fluid">
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
                            <c:forEach var="blogTitle" items="${blogList}">
                                <tr>
                                    <td>
                                        <c:out value="${blogList.blogTitle}"/>
                                    </td>
                                    <td>
                                        <c:out value="${blogList.author}"/>
                                    </td>
                                    <td>
                                        <c:out value="${blogList.preview}"/>
                                    </td>
                                    <td>
                                        <c:out value="${blogList.price}"/>
                                    </td>
                                    <td>
                                        <c:out value="${blogList.quantity}"/>
                                    </td>
                                    <td>
                                        <c:out value="${blogList.author}"/>
                                    </td>
                                    <td>
                                        <c:out value="${blogList.approve_delete}"/>
                                    </td>
                                    <td>
                                        <c:out value="${blogList.reject_delete}"/>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table> 
                    </div>
                </div>
            </div>
        </div>
        <h2></h2
        <c:if test="${pageContext.request.userPrincipal.name != null}">
            <a href="<c:url value="/j_spring_security_logout"/> Logout </a>
            </c:if>
            </div>

        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

</body>
</html>
