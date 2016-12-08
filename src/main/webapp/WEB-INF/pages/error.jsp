<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>
        Exception
    </title>
</head>
<body>
    <span>
        <c:choose>
            <c:when test="${not empty exception}">
                <h1>
                    Exception : ${exception}
                </h1>
            </c:when>
            <c:otherwise>
                <h1>
                    Something goes wrong
                </h1>
            </c:otherwise>
        </c:choose>
    </span>
</body>
</html>
