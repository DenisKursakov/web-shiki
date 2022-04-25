<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%--
  Created by IntelliJ IDEA.
  User: dariapumpaleva
  Date: 16.04.22
  Time: 14:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>JSP start testing</title>
</head>
<body>
<script>
    function sendForm(statOperation) {
        const err = document.getElementById("errorText");
        for (let i = 0; i < document.result.stats.length; i++) {
            const strValue = document.result.stats[i].value;
            const num = new Number(strValue);
            if (strValue === "" || !isInteger(num)) {
                err.innerHTML = "box " + i + " is not integer";
                return;
            }
            if (isOut(num)) {
                err.innerHTML = "box " + i + ": index must be less than " + ${maxValue};
                return;
            }
        }
        document.result.operation.value = statOperation;
        document.result.submit();

        function isInteger(number) {
            return Math.floor(number) == number;
        }

        function isOut(number) {
            return number < 0 || number >= ${maxValue};
        }
    }

</script>
<p id="errorText" style="color:red;"></p>
<p/>
<form name="result" action="<c:url value='/result'/>" onsubmit="return false">
    <p>input indices (less than 25):</p>
    <c:forEach var="i" begin="1" end="${number}">
        ${i}: <input name="stats" type="number" value="0" min="0" max="25"/>
        <br/><br/>
    </c:forEach>
    <input type=hidden name="operation" value="no">
    <br/>
    <a href="JavaScript:sendForm('sum')">sum</a>&nbsp;&nbsp;
    <a href="JavaScript:sendForm('max')">max</a>&nbsp;&nbsp;
    <a href="JavaScript:sendForm('min')">min</a>&nbsp;&nbsp;
    <a href="JavaScript:sendForm('avg')">avg</a>
</form>
<br/>
<a href="<c:url value='/'/>">Back</a>
</body>
</html>
