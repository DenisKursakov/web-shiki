<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
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
        const MIN_VALUE = -1000;
        const MAX_VALUE = 1000;
        const err = document.getElementById("errorText");
        for (let i = 0; i < document.result.stats.length; i++) {
            const strValue = document.result.stats[i].value;
            const numValue = new Number(strValue);
            if (strValue === "" || numValue < MIN_VALUE || numValue > MAX_VALUE) {
                err.innerHTML = "box " + i + " is NaN or outside [" + MIN_VALUE + "; " + MAX_VALUE + "]";
                return;
            }
        }
        document.result.operation.value = statOperation;
        document.result.submit();
    }

</script>
<p id="errorText" style="color:red;"></p>
<p/>
<form name="result" action="<c:url value='/result'/>" onsubmit="return false">
    <c:forEach var="i" begin="1" end="${number}">
        ${i}: <input name="stats" type="number" value="0" min="-1000" max="1000"/>
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
<a href="<c:url value='/index.jsp'/>">Back</a>
</body>
</html>
