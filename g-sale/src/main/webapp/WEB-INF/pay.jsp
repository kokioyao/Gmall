<%--

  User: yaokaikai
  Date: 2018/12/17
  Time: 0:56

--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@page isELIgnored="false"  %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";%>
<html>
<head>
    <base href="<%=basePath %>">
    <script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
    收银
    <form action="pay_success.do">
        <input type="submit" value="支付">
    </form>
</body>
</html>
