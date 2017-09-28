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
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-12">
                        <div class="row">
                            <div class="col-md-8 myBackground">
                                <c:forEach var="currentPost" items="${postList}">
                                    <h2>
                                        <c:out value="${currentPost.item.itemName}"/>
                                    </h2>
                                    <h3>
                                        <c:out value="${currentPost.title}"/>
                                        <br>
                                        </br>
                                        $<c:out value="${currentPost.item.itemPrice}"/>
                                    </h3>

                                    <c:out value="${currentPost.content}" escapeXml="false"/>   
                                    <br>
                                    </br>
                                    <c:out value="${currentPost.category.name}"/>
                                    <br>
                                    </br>                                
                                    <c:out value="${currentPost.hashtags}"/>
                                </c:forEach>
                            </div>
                            <div class="col-md-4">
                                <form class="form-horizontal" role="form" id="search-hashtag"
                                      method="GET" action="searchHashTag">
                                    <div class="form-group">
                                        <div class="col-md-8">
                                            <input type="text" class="form-control" id="search-first-name" name="hashTagToSearch" placeholder="Enter hashtag to search"/>
                                        </div>
                                        <button type="submit"
                                                class="btn btn-default"
                                                name="hashTagToSearch">
                                            Search
                                        </button>
                                    </div>
                                </form>

                                <h3>
                                    Categories
                                </h3>
                                <ul>
                                    <c:forEach var="currentCategory" items="${categoryList}">
                                        <li>
                                            <c:out value="${currentCategory.name}"/>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </div>
                    </div>

                                    <a href="displayEditBlogForm?postID=${currentPost.postID}">
                                        Edit
                                    </a>
                </div>
            </div>
        </div>
    </div>

    <!-- Placed at the end of the document so the pages load faster -->
    <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
</html>

