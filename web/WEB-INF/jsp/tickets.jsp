<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fh" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%@ include file="header.jsp"%>
    <c:if test="${not empty requestScope.tickets}">
    <h1>Список перелётов:</h1>
    <ul>
        <c:forEach var="ticket" items="${requestScope.tickets}">
            <li>${fh:toLowerCase(ticket.seatNo)}</li>
        </c:forEach>
    </ul>
    </c:if>
</body>
</html>

