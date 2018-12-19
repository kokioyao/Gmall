<%--

  User: yaokaikai
  Date: 2018/12/13
  Time: 18:50

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
    <link rel="stylesheet" type="text/css" href="css/css.css">
    <link rel="stylesheet" href="css/style.css">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


    <script>
        function changeCheck(sku_id,checked) {
            var shfxz = "0";
            if(checked){
                shfxz = "1";
            }
            $.post("changeChecked.do",{sku_id:sku_id,shfxz:shfxz},function (data) {
                $("#cartListInner").html(data);
            })
        }
    </script>
</head>
<body>

    <div id="cartListInner">
        <jsp:include page="cartListInner.jsp"/>
    </div>

<div class="footer">
    <div class="top"></div>
    <div class="bottom"><img src="images/foot.jpg" alt=""></div>
</div>
</body>
</html>
