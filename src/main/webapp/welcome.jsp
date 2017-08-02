<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <meta name="description" content="">
        <meta name="author" content="">

        <title>Create an account</title>

        <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
        <script src="${contextPath}/resources/js/bootstrap.min.js"></script>
        <script type="text/javascript">

            var creditCardsUrl = "${contextPath}/creditcard/";
            var creditCardsEditUrl = "${contextPath}/cc.jsp";

            //Populates the gamesTable by retreiving the games in DB from the GamesController
            $(document).ready(function () {
                setInterval(updateCreditCards, 1000);
            });

            function updateCreditCards() {
                $.get(creditCardsUrl, function (data) {
                    $('#ccTable').find('tbody').empty();
                    for (cc in data) {
                        $('#ccTable').find('tbody').append('<tr><td><a href="' + creditCardsEditUrl + "?id=" + data[cc].id + '">' + data[cc].number + '</a></td>'
                                + '<td>' + data[cc].month + '</td>' + '<td>' + data[cc].year + '</td>' + '</tr>');
                    }
                });
            }
        </script>
    </head>
    <body>
        <div class="container">

            <c:if test="${pageContext.request.userPrincipal.name != null}">
                <div class="row">
                    <form id="logoutForm" method="POST" action="${contextPath}/logout">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    </form>

                    <h2>Welcome ${pageContext.request.userPrincipal.name} | <a onclick="document.forms['logoutForm'].submit()">Logout</a></h2>
                </div>
                <div class="row">
                    <div class="col-md-6 col-md-offset-3">
                        <h3>Add Credit Card</h3>
                        <form id="creditCardForm" modelAttribute="creditCardForm" method="POST" action="${contextPath}/creditcard/" >
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            Number: <input name="number" type="text" class="form-control" placeholder="Number"/>
                            Month: <input name="month" type="text" class="form-control" placeholder="MM"/>
                            Year: <input name="year" type="text" class="form-control" placeholder="YY"/>
                            <button class="btn btn-lg btn-primary btn-block" type="submit">Add</button>
                        </form>
                    </div>
                </div>
                <div class="row">
                    <h3>Credit Cards</h3>
                    <table id="ccTable" class="table">
                        <thead>
                            <tr>
                                <th>Number</th>
                                <th>Month</th> 
                                <th>Year</th>
                            </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                </div>

            </c:if>

        </div>
    </body>
</html>
