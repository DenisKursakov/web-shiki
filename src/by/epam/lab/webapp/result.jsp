<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: dariapumpaleva
  Date: 16.04.22
  Time: 17:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Result page</title>
</head>
<body>
<h1>
    ${operation} (
    <c:forEach items="${stats}" var="stat" varStatus="status">
        ${stat}
        <c:if test = "${not status.last}">
        ,
        </c:if>
    </c:forEach>
    ) is ${result}

    <br />
</h1>
<a href="<c:url value='/'/>">Main</a>
</body>
</html>
