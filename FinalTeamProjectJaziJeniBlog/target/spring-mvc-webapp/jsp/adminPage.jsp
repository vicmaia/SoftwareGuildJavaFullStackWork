<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/">
                            Jazi Jeni's Jewelery And Junk
                        </a>
                    </li>
                    <li role="presentation" class="active">
                        <a href="${pageContext.request.contextPath}/adminPage">
                            AdminPage
                        </a>
                    </li>
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <li role="presentation">
                            <a href="${pageContext.request.contextPath}/displayUserList">
                                User Admin
                            </a>
                        </li>                        
                    </sec:authorize>
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

            <div>
                <c:if test="${message eq 'Please update or remove all posts for this item before deleting.' || message eq 'Please update or remove all posts for this category before deleting.'}">
                    <div id="message" class="form-group col-md-12 displays alert alert-danger">
                        <span class="close" data-dismiss="alert">&times;</span>
                        <strong>Error!</strong> ${message}
                    </div>
                </c:if>
            </div>
            <div>
                <div class="form-group">
                    <div class="col-md-2">
                        <a href="${pageContext.request.contextPath}/displayAddBlogForm">
                            <input type="newpost" class="btn btn-default" value="New Post"/>
                        </a>
                    </div>
                </div>

                <sec:authorize access="hasRole('ROLE_ADMIN')">  

                    <div class="form-group">
                        <div class="col-md-2">
                            <a href="${pageContext.request.contextPath}/displayAddStaticPage">
                                <input type="static" class="btn btn-default" value="Static Page"/>
                            </a>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-md-2">
                            <a href="${pageContext.request.contextPath}/displayAddCategoryForm">
                                <input type="static" class="btn btn-default" value="New Category"/>
                            </a>
                        </div>
                    </div>        

                    <div class="form-group">
                        <div class="col-md-2">
                            <a href="${pageContext.request.contextPath}/displayAddAuthorForm">
                                <input type="static" class="btn btn-default" value="New Author"/>
                            </a>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-md-2">
                            <a href="${pageContext.request.contextPath}/displayAddItemForm">
                                <input type="static" class="btn btn-default" value="New Item"/>
                            </a>
                        </div>
                    </div>
                </sec:authorize> 
            </div>
            <br>
            <br/>
            <div class="container-fluid row">
                <br>
                <br/>
                <div class="myAdminBackground">
                    <h3>
                        Posts
                    </h3>
                </div>
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
                            <sec:authorize access="hasRole('ROLE_ADMIN')">
                                <c:if test="${currentPost.pendingEdit eq 0 && currentPost.pendingDelete eq 0}">
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
                                            $<c:out value="${currentPost.item.itemPrice}"/> 
                                        </td>
                                        <td>
                                            <c:out value="${currentPost.item.itemQuantity}"/> 
                                        </td>
                                        <td>
                                            <a href="displayEditBlogForm?postID=${currentPost.postID}">
                                                <div id="editDeleteLink">
                                                    Edit
                                                </div>
                                            </a>
                                        </td>
                                        <td>
                                            <a href="pendingDeleteBlog?postID=${currentPost.postID}">
                                                <div id="editDeleteLink">
                                                    Delete
                                                </div>
                                            </a>
                                        </td>
                                    </tr>
                                </c:if>
                            </sec:authorize>         
                            <sec:authorize access="hasRole('ROLE_USER')">
                                <c:if test="${currentPost.pendingEdit eq 0 && currentPost.pendingDelete eq 0 && pageContext.request.userPrincipal.name eq currentPost.author.firstName}">
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
                                            $<c:out value="${currentPost.item.itemPrice}"/> 
                                        </td>
                                        <td>
                                            <c:out value="${currentPost.item.itemQuantity}"/> 
                                        </td>
                                        <td>
                                            <a href="displayEditBlogForm?postID=${currentPost.postID}">
                                                <div id="editDeleteLink">
                                                    Edit
                                                </div>
                                            </a>
                                        </td>
                                        <td>
                                            <a href="pendingDeleteBlog?postID=${currentPost.postID}">
                                                <div id="editDeleteLink">
                                                    Delete
                                                </div>
                                            </a>
                                        </td>
                                    </tr>
                                </c:if>
                            </sec:authorize> 
                        </c:forEach>
                    </table> 
                </div>
            </div>

            <sec:authorize access="hasRole('ROLE_ADMIN')"> 
                <div class="container-fluid row">
                    <div class="myAdminBackground">
                        <h3>
                            Categories
                        </h3>
                    </div>
                    <div class="col-md-12 myText">
                        <table id="blogTable" class="table">
                            <tr>
                                <th width="20%">Category Name</th>
                                <th width="20%">Category Description</th>
                                <th width="5%"></th>
                                <th width="5%"></th>
                            </tr>
                            <c:forEach var="currentCategory" items="${categoryList}"> 
                                <tr>
                                    <td>
                                        <c:out value="${currentCategory.name}"/>
                                    </td>
                                    <td>
                                        <c:out value="${currentCategory.description}"/>
                                    </td>
                                    <td>
                                        <a href="displayEditCategoryForm?categoryID=${currentCategory.categoryID}">
                                            <div id="editDeleteLink">
                                                Edit
                                            </div>
                                        </a>
                                    </td>
                                    <td>
                                        <a href="deleteCategory?categoryID=${currentCategory.categoryID}">
                                            <div id="editDeleteLink">
                                                Delete
                                            </div>
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table> 
                    </div>
                </div>   

                <div class="container-fluid row">
                    <div class="myAdminBackground">
                        <h3>
                            Items
                        </h3>
                    </div>
                    <div class="col-md-12 myText">
                        <table id="blogTable" class="table">
                            <tr>
                                <th width="20%">Item Name</th>
                                <th width="20%">Item Description</th>
                                <th width="15%">Item Price</th>
                                <th width="15%">Item Quantity</th>
                                <th width="5%"></th>
                                <th width="5%"></th>
                            </tr>
                            <c:forEach var="currentItem" items="${itemList}"> 
                                <tr>
                                    <td>
                                        <c:out value="${currentItem.itemName}"/>
                                    </td>
                                    <td>
                                        <c:out value="${currentItem.itemDescription}"/>
                                    </td>
                                    <td>
                                        <c:out value="${currentItem.itemPrice}"/>
                                    </td>
                                    <td>
                                        <c:out value="${currentItem.itemQuantity}"/>
                                    </td>
                                    <td>
                                        <a href="displayEditItemForm?itemID=${currentItem.itemID}">
                                            <div id="editDeleteLink">
                                                Edit
                                            </div>
                                        </a>
                                    </td>
                                    <td>
                                        <a href="deleteItem?itemID=${currentItem.itemID}">
                                            <div id="editDeleteLink">
                                                Delete
                                            </div>
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table> 
                    </div>
                </div>
            </sec:authorize> 
            <div class="container-fluid row">
                <div class="myAdminBackground">
                    <h3>
                        Pending Edits
                    </h3>
                </div>
                <div class="myText ">
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
                                <sec:authorize access="hasRole('ROLE_ADMIN')">
                                    <c:if test="${currentPost.pendingEdit eq 1}">
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
                                                $<c:out value="${currentPost.item.itemPrice}"/> 
                                            </td>
                                            <td>
                                                <c:out value="${currentPost.item.itemQuantity}"/> 
                                            </td>
                                            <sec:authorize access="hasRole('ROLE_ADMIN')"> 
                                                <td>
                                                    <a href="approveEditBlog?postID=${currentPost.postID}">
                                                        Approve
                                                    </a>
                                                </td>
                                                <td>
                                                    <a href="rejectEditBlog?postID=${currentPost.postID}">
                                                        Reject
                                                    </a>
                                                </td>
                                            </sec:authorize> 
                                        </tr>
                                    </c:if>
                                </sec:authorize>
                                <sec:authorize access="hasRole('ROLE_USER')">
                                    <c:if test="${currentPost.pendingEdit eq 1 && pageContext.request.userPrincipal.name eq currentPost.author.firstName}">
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
                                                $<c:out value="${currentPost.item.itemPrice}"/> 
                                            </td>
                                            <td>
                                                <c:out value="${currentPost.item.itemQuantity}"/> 
                                            </td>
                                            <sec:authorize access="hasRole('ROLE_ADMIN')"> 
                                                <td>
                                                    <a href="approveEditBlog?postID=${currentPost.postID}">
                                                        Approve
                                                    </a>
                                                </td>
                                                <td>
                                                    <a href="rejectEditBlog?postID=${currentPost.postID}">
                                                        Reject
                                                    </a>
                                                </td>
                                            </sec:authorize> 
                                        </tr>
                                    </c:if>
                                </sec:authorize>
                            </c:forEach>
                        </table> 
                    </div>
                </div>
            </div>
            <div class="container-fluid row">
                <div class="myAdminBackground">
                    <h3>
                        Pending Deletes
                    </h3>
                </div>
                <div class="myText">
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
                                <sec:authorize access="hasRole('ROLE_ADMIN')">
                                    <c:if test="${currentPost.pendingDelete eq 1}">
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
                                                $<c:out value="${currentPost.item.itemPrice}"/> 
                                            </td>
                                            <td>
                                                <c:out value="${currentPost.item.itemQuantity}"/> 
                                            </td>
                                            <sec:authorize access="hasRole('ROLE_ADMIN')"> 
                                                <td>
                                                    <a href="approveDeleteBlog?postID=${currentPost.postID}">
                                                        <div id="editDeleteLink">
                                                            Approve
                                                        </div>
                                                    </a>
                                                </td>
                                                <td>
                                                    <a href="rejectDeleteBlog?postID=${currentPost.postID}">
                                                        Reject
                                                    </a>
                                                </td>
                                            </sec:authorize> 
                                        </tr>
                                    </c:if>
                                </sec:authorize>
                                <sec:authorize access="hasRole('ROLE_USER')">
                                    <c:if test="${currentPost.pendingDelete eq 1 && pageContext.request.userPrincipal.name eq currentPost.author.firstName}">
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
                                                $<c:out value="${currentPost.item.itemPrice}"/> 
                                            </td>
                                            <td>
                                                <c:out value="${currentPost.item.itemQuantity}"/> 
                                            </td>
                                            <sec:authorize access="hasRole('ROLE_ADMIN')"> 
                                                <td>
                                                    <a href="approveDeleteBlog?postID=${currentPost.postID}">
                                                        <div id="editDeleteLink">
                                                            Approve
                                                        </div>
                                                    </a>
                                                </td>
                                                <td>
                                                    <a href="rejectDeleteBlog?postID=${currentPost.postID}">
                                                        Reject
                                                    </a>
                                                </td>
                                            </sec:authorize> 
                                        </tr>
                                    </c:if>
                                </sec:authorize>
                            </c:forEach>
                        </table> 
                    </div>
                </div>
            </div>
        </div>

        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>