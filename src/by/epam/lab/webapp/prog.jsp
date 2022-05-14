<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Program</title>
    <script>
        function checkInput() {
            reg.account.value = reg.account.value.trim();
            if (reg.account.value === "") {
                document.getElementById("errMsg").innerHTML = "Account is empty";
                return false;
            }
            var checked = false;
            for (var i = 0; i < reg.idEvent.length; i++) {
                if (reg.idEvent[i].checked) {
                    checked = true;
                    break;
                }
            }
            if (!checked) {
                document.getElementById("errMsg").innerHTML = "No checked events";
                return false;
            }
            reg.submit();
        }
    </script>
</head>
<body>
<h2>${conferences.getName()}</h2>
<table border=0>
    <tr>
        <td>Faculty:&nbsp;</td>
        <td>${conferences.getFaculty()}</td>
    </tr>
    <tr>
        <td>Date:</td>
        <td>${conferences.getDate()}</td>
    </tr>
</table>
<p/>


<form name="reg" action="/confs2020/reg" method=post onsubmit="return false">
    <table border=0>
        <thead>
        <th>&nbsp;</th>
        <th>Event</th>
        <th>Time</th>
        </thead>
        <tbody>
        <c:forEach var="i" items="${events}">
            <tr>
                <td><input type=checkbox name=idEvent value="${i.id}" checked/></td>
                <td>${i.stage}</td>
                <td>${i.time}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <input type="hidden" name="confName" value="${conferences.name}">
    <input type="hidden" name="idConf" value="${idConf}">
    <p/>
    Account: <input type=text name="account"/>
    <p id="errMsg" style="color:red;"></p>
    <input type=button value="Register participant" onclick="return checkInput()"/>
</form>

<p/>
<a href="index.jsp">Conferences</a>
</body>
</html>