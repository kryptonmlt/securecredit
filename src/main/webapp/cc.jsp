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
        <meta name="_csrf" content="${_csrf.token}"/>
        <!-- default header name is X-CSRF-TOKEN -->
        <meta name="_csrf_header" content="${_csrf.headerName}"/>

        <title>Edit Credit Card</title>

        <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
        <script src="${contextPath}/resources/js/bootstrap.min.js"></script>
        <script type="text/javascript">

            var creditCardsUrl = "${contextPath}/creditcard/";

            //Populates the gamesTable by retreiving the games in DB from the GamesController
            $(document).ready(function () {
                var url = new URL(window.location.href);
                var creditId = url.searchParams.get("id");

                $.get(creditCardsUrl + creditId, function (data) {
                    $('#ccNumber').val(data.number);
                    $('#ccMonth').val(data.month);
                    $('#ccYear').val(data.year);
                });
                $("#creditCardForm").submit(function (event) {
                    event.preventDefault();
                    console.log("sending update");
                    var form = $('#creditCardForm');
                    $.ajax({
                        url: creditCardsUrl + creditId,
                        data: form.serialize(),
                        headers: {
                            Accept: "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8",
                            "Content-Type": "application/x-www-form-urlencoded"
                        },
                        type: 'PUT',
                        processData: false,
                        success: function (result) {
                            window.location.href = "${contextPath}";
                        }
                    });
                });
            });

            $(function () {
                var token = $("meta[name='_csrf']").attr("content");
                var header = $("meta[name='_csrf_header']").attr("content");
                $(document).ajaxSend(function (e, xhr, options) {
                    xhr.setRequestHeader(header, token);
                });
            });
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
                        <h3>Update Credit Card</h3>
                        <form id="creditCardForm" modelAttribute="creditCardForm" action="" method="POST">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            Number: <input id="ccNumber" name="number" type="text" class="form-control" placeholder="Number" readonly/>
                            Month <input id="ccMonth" name="month" type="text" class="form-control" placeholder="MM"/>
                            Year: <input id="ccYear" name="year" type="text" class="form-control" placeholder="YY"/>
                            <button class="btn btn-lg btn-primary btn-block" type="submit">Update</button>
                        </form>
                    </div>
                </div>

            </c:if>

        </div>
    </body>
</html>
