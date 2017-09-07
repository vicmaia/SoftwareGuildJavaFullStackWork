<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <title>My Dvd Library</title>
    <link href="css/bootstrap.css" rel="stylesheet">
</head>

<body>
    <div class=container id="homePage">
        <h1 id="homeHeading">Dvd Library - Home</h1>
        <div class="col-md-3" id="createButton"><a class="button" href="${pageContext.request.contextPath}/jsp/createDvdForm.jsp">Create</a></button></div>
       <div class="col-md-3" id="createButton"><a class="button" href="${pageContext.request.contextPath}/search.jsp">Search</a></button></div>
        <div class="col-md-3">
            <label>Search Category</label>
            <select name="searchType">
            <option value="title">Title</option>
            </select>
        </div>
        <div class="col-md-3">Search Term</div>

        <div id="mainTableContainer">
            <table id="mainTable" style="border: 1px solid black" class="col-md-12 table-bordered table-striped">
                <tr>
                    <th>
                        Title
                    </th>
                    <th>
                        Release Date
                    </th>
                    <th>
                        Director
                    </th>
                    <th>
                        Rating
                    </th>
                    <th>
                        Options
                    </th>
                    <th></th>
                </tr>
                        <c:forEach var="currentDvd" items="${dvdList}">
                            <tr>
                                <td>
                                    <a href="displayDvdDetails?dvdID=${currentDvd.dvdID}">
                                        <c:out value="${currentDvd.dvdTitle}"/>
                                    </a>
                                </td>
                                <td>
                                    <c:out value="${currentDvd.releaseYear}"/>
                                </td>
                                <td>
                                    <c:out value="${currentDvd.director}"/>
                                </td>
                                <td>
                                    <c:out value="${currentDvd.rating}"/>
                                </td>
                                <td>
                                    <a href="displayEditDvdForm?dvdID=${currentDvd.dvdID}">
                                        Edit
                                    </a>
                                </td>
                                <td>
                                    <a href="deleteDvd?dvdID=${currentDvd.dvdID}">
                                        Delete
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
            </table>
        </div>

    </div>


    <script src="js/jquery-3.1.1.min.js"></script>
    <script src="js/dvdList.js"></script>
</body>

</html>

