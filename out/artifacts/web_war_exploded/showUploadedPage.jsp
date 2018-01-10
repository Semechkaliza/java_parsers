<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Type of xml</title>
</head>
<body>
<div>
    <table border="1">
        <tr>
            <td>status</td>
            <td>type</td>
            <td>thema</td>
            <td>country</td>
            <td>author</td>
            <td>valuable</td>
            <td>year</td>
        </tr>

        <c:forEach items="${entities}" var="entity">
            <tr>
                <td>${entity.status}</td>
                <td>${entity.type}</td>
                <td>${entity.thema}</td>
                <td>${entity.country}</td>
                <td>${entity.author}</td>
                <td>${entity.valuable}</td>
                <td>${entity.year}</td>
            </tr>
        </c:forEach>

    </table>
</div>
<div>
    <table>
        <tr>
            <c:forEach items="${pages}" var="page">
                <td>
                    <form action="page" method="GET">
                        <input type="hidden" name="command" value="getPage">
                        <input type="hidden" name="number" value=${page}>
                        <input type="submit" value=${page}>
                    </form>
                </td>
            </c:forEach>
        </tr>
    </table>
</div>

</div>
</body>
</html>