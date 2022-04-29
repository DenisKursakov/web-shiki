<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Conferences</title>
</head>
<body>
<h2>GSU Conferences </h2>
<form name='prog' action="/confs2020/prog" method="post">
    <table border=0>
        <tbody>
        <c:forEach var="i" items="${list}">
            <tr>
                <td><a href='/confs2020/prog?idConf=${i.getKey()}'>${i.getValue().getName()}</a></td>
                <td>${i.getValue().getDate()}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</form>
</body>
</html>