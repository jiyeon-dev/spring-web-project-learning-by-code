<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>/sample/all page</h1>

    <sec:authorize access="isAnonymous()">
        <a href="/customLogin">로그인</a>
    </sec:authorize>

    <sec:authorize access="isAuthenticated()">
        <a href="/customLogout">로그아웃</a>
    </sec:authorize>

</body>
</html>
