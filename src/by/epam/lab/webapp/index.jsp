<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: dariapumpaleva
  Date: 16.04.22
  Time: 16:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Index page</title>
</head>
<body>
<script>
    function checkData() {
        const MIN_NUM = 2;
        const MAX_NUM = 10;
        const err = document.getElementById("errorText");
        const statsNumber = new Number(document.statsNumber.number.value);
        if(!isInteger(statsNumber) || statsNumber < MIN_NUM || statsNumber > MAX_NUM) {
            err.innerHTML = "input integer number between " + MIN_NUM + " and " + MAX_NUM;
            return false;
        }

        return true;

        function isInteger(number) {
            return Math.floor(number) == number;
        }
    }

    function sendForm() {
        if(checkData()) {
            document.statsNumber.submit();
        }
    }
</script>
<p id="errorText" style="color:red;"></p>
<p/>
<form name="statsNumber" action="<c:url value='/start'/>" onsubmit="return checkData()">
    <label for="quantity">stats number (between 2 and 10):</label>
    <input type="number" id="quantity" name="number" min="2" max="10" step="1" value="3">
    <br/>
    <a href="JavaScript:sendForm()">generate stats</a>
</form>
</body>
</html>
