<%--

  User: yaokaikai
  Date: 2018/12/6
  Time: 10:39

--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<base href="<%=basePath%>">
<html>
<head>
    <title>Title</title>
    <script src="js/jquery-1.7.2.min.js"></script>

</head>
<body>
    属性内嵌页<br>

    <c:forEach items="${list_attr}" var="attr">
        ${attr.shxm_mch}:
        <c:forEach items="${attr.list_value}" var="value">
            ${value.shxzh}${value.shxzh_mch}
        </c:forEach>
        <br>
    </c:forEach>
</body>
</html>
