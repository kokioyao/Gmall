<%--

  User: yaokaikai
  Date: 2018/12/13
  Time: 12:39

--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@page isELIgnored="false"  %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <base href="<%=basePath %>">
    <script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <script>
        function show_cart() {
            $.post("getCartList.do",function (data) {
                $("#cartList").html(data);
            });

            $("#cartList").show();
        }

        function hide_cart() {
            $("#cartList").hide()

        }
    </script>

</head>
<body>
    <div class="card">
        <a href="gotoCartList.do;" onmouseover="show_cart()" onmouseout="hide_cart()">购物车<div class="num">0</div></a>

        <!--购物车商品-->
        <div id="cartList" class="cart_pro" style="display:none">
           <jsp:include page="miniCartList.jsp"></jsp:include>
        </div>

    </div>
</body>
</html>
