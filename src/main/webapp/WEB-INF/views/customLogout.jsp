<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Logout</title>
</head>
<body>
  <h1>Logout Page</h1>

  <form action="/customLogout" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" >
    <button>로그아웃</button>
  </form>
</body>
</html>
