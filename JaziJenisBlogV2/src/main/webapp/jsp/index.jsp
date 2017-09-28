<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Jazi Jeni's Jewelry And Junk</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">  
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/mystyle.css">
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
                        <a href="${pageContext.request.contextPath}/displaySearchPage">
                            Search
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
            <c:if test="${pageContext.request.userPrincipal.name != null}">
                <p>Hello : ${pageContext.request.userPrincipal.name}
                    | <a href="<c:url value="/j_spring_security_logout" />" > Logout</a>
                </p>
            </c:if>              
            <p>
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-12">
                        <div class="row">
                            <div class="col-md-8">
                                <h3>
                                    Jazi Jeni's Jewelry And Junk
                                </h3>
                                <h2>Posts</h2>
                                <table id="postsTable" class="table table-hover">
                                    <tr>
                                        <th width="10%">PostID</th>
                                        <th width="20%">Title</th>
                                        <th width="70%">Content</th>
                                    </tr>
                                    <c:forEach var="currentPost" items="${postList}">
                                        <tr>
                                            <td>
                                                <a href="displayPostDetails?postID=${currentPost.postID}">
                                                    <c:out value="${currentPost.postID}"/>
                                                </a>
                                            </td>
                                            <td>
                                                <c:out value="${currentPost.title}"/>
                                            </td>
                                            <td>
                                                <c:out value="${currentPost.content}"/>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </table>  

                            </div>
                            <div class="col-md-4">
                                <form action="/action_page.php">
                                    Search: <input type="text" name="search"><br>
                                    <input type="submit" value="Submit">
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


                </div>
            </div>
        </div>
    </div>
</p>
</div>
<!-- Placed at the end of the document so the pages load faster -->
<script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

</body>
</html>

