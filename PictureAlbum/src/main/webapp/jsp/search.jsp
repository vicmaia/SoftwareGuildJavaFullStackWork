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
                            Jazi Jeni's Jewelery And Junk
                        </a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/displayContactsPage">
                            Login
                        </a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/jsp/adminPage.jsp">
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
                </ul>    
            </div>
            <div class="col-md-12">
                <div class="row myText">
                    <div class="col-md-8">
                        <c:forEach var="searchResult" items="${postList}">
                            <tr>
                                <td>
                                    <a href="displayPostDetails?postID=${searchResult.postID}">
                                        <c:out value="${searchResult.postID}"/>
                                    </a>
                                </td>
                                <td>
                                    <c:out value="${searchResult.title}"/>
                                </td>
                                <td>
                                    <c:out value="${searchResult.content}"/>
                                </td>

                            </tr>

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




            <!-- Placed at the end of the document so the pages load faster -->
            <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
            <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        </div>
    </body>
</html>

