<%--

  User: yaokaikai
  Date: 2018/12/18
  Time: 10:56

--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@page isELIgnored="false"  %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";%>
<% String ran = System.currentTimeMillis()+""; %>
<html>
<head>
    <base href="<%=basePath %>">
    <script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <script>
        $(function () {
            $("#a").submit();
        })
    </script>
</head>
<body>
    <form id="a" action="http://localhost:8083/payservice/payment"  method="POST" target="_blank">
        <input type="hidden" name="trade_no" id="out_trade_no" value="硅谷商城订单<%=ran%>">
        <input type="hidden" name="total_fee" value="0.01">
        <input type="hidden" name="busi_return_url" value="http://localhost:8081/sale/order_success.do">
        <input type="hidden" name="busi_notify_url" value="http://localhost:8081/sale/real_pay_success.do">
        <input type="hidden" name="subject" value="支付测试专用">
        <input type="hidden" name="body" value="即时到账测试">
    </form>
</body>
</html>
