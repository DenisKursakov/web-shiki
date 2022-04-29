<%--
  Created by IntelliJ IDEA.
  User: dariapumpaleva
  Date: 28.04.22
  Time: 15:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
    <h1>${faculty}</h1>
    <script>
        function checkInput() {
            reg.account.value = reg.account.value.trim();
            var regName = reg.account.value;
            if (regName === "db") {
                document.getElementById("regMsg").innerHTML =
                    "Unfortunately, your application has not been registered. Some problem with the data source on the server...";
                return false;
            }
            document.getElementById("regMsg").innerHTML = regName + ", has been registered successful";
            reg.submit();
        }
    </script>
</head>
<body>
<p>${account}</p>
<p>
<a href="index.jsp">Conferences</a>
</p>
</body>
</html>
