<%--

  User: yaokaikai
  Date: 2018/12/11
  Time: 23:45

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
    商品列表<br>
    <c:forEach items="${list_sku}" var = "sku">
        <div style="margin:10px 10px 0 0;float:left;border:1px black solid;width:280px;height:330px">
            <img src="upload/image/${sku.spu.shp_tp}" width="280px" height="250px"> <br>
            <a href="gotoSkuDetail.do?sku_id=${sku.id}&spu_id=${sku.shp_id}" target="_blank">${sku.sku_mch}</a><br>
            ${sku.jg}<br>
            ${sku.sku_xl}<br>

        </div>
    </c:forEach>
</body>
</html>
