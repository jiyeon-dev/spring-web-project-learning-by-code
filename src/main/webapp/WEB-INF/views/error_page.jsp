<%--
  Created by IntelliJ IDEA.
  User: WMP
  Date: 2021-08-02
  Time: 오후 3:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>ERROR PAGE</title>
</head>
<body>
  <h4><c:out value="${exception.getMessage() }"></c:out></h4>

  <ul>
    <c:forEach items="${exception.getStackTrace() }" var="stack">
      <li><c:out value="${stack }"></c:out></li>
    </c:forEach>
  </ul>
</body>
</html>