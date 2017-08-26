<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Vending Machine</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
    </head>
    <body>

        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <div class="page-header">
                        <h1>
                            Vending Machine
                            <small>Getting you through 3 hour afternoon Skype meetings since 1990.</small>
                        </h1>
                    </div>
                </div>
            </div>


            <div class="row">
                <div class="col-md-12">
                    <div class="col-md-9" id="items-div">
                        <c:forEach var="item" items="${items}">
                            <div class="btn col-md-3 dynamic-items">
                                <a href="${pageContext.request.contextPath}/selectItem?id=${item.itemId}">
                                    <p class="item-id" name="selection" value="${item.itemId}"><c:out value="${item.itemId}"/></p>
                                    <p class="item-name"><c:out value="${item.itemName}"/></p>
                                    <p class="item-price">$<c:out value="${item.price}"/></p>
                                    <p class="item-quantity">Quantity Left: <c:out value="${item.quantity}"/></p>
                                </a>
                            </div>
                        </c:forEach>
                    </div>

                    <div class="col-md-3" id="left-column">
                        <h2>
                            Total $ In:
                        </h2>

                        <div id="money-in" class="displays">
                            <p id="amount">${money}</p>
                        </div>

                        <form action="addMoney" method="POST">
                            <input name="money" value="Add Dollar"
                                   type="submit" id="Add dollar"
                                   class="col-md-5 btn btn-default"/>
                        </form>
                        <form action="addMoney" method="POST">
                            <input name="money" value="Add Quarter"
                                   type="submit" id="Add quarter"
                                   class="col-md-5 btn btn-default"/>
                        </form>
                        <form action="addMoney" method="POST">
                            <input name="money" value="Add Dime"
                                   type="submit" id="Add dollar"
                                   class="col-md-5 btn btn-default"/>
                        </form>
                        <form action="addMoney" method="POST">
                            <input name="money" value="Add Nickel"
                                   type="submit" id="Add nickel"
                                   class="col-md-5 btn btn-default"/>
                        </form>
                        
                        <hr/>

                        <h2>
                            Messages
                        </h2>

                        <div id="messages" class="col-md-12 displays" style="font-size: 12pt;">
                            <p id="message-out">${message}</p>
                        </div>

                        <div id="item-selection-label" class="col-md-12">
                             <p id="selection">Item Selected: ${selection}</p>
                        </div>


                            <a class="col-md-11 btn btn-default" href="${pageContext.request.contextPath}/makePurchase?id=${selection}">Make Purchase </a>
                   

                        <hr/>

                        <h2>
                            Change
                        </h2>

                        <div id="change-out" class="displays">
                            <p id="change"></p>
                            <button type="button" class="btn btn-default" id="change-button">
                                Return Change
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>

