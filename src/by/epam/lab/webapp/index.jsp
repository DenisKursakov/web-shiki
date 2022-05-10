<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Conferences</title>
</head>
<body>
<h2>GSU Conferences </h2>
<table border=0>
    <tbody>
    <c:forEach var="i" items="${ConfsList}">
        <tr>
            <td><a href='/confs2020/prog?idConf=${i.getId()}'>${i.getName()}</a></td>
            <td><fmt:formatDate value="${i.getDate()}" pattern="dd.MM"/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>