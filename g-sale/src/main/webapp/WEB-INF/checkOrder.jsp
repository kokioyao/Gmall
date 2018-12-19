<%--

  User: yaokaikai
  Date: 2018/12/16
  Time: 14:14

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
    <link rel="shortcut icon" type="image/icon" href="image/jd.ico">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <title>订单支付</title>
    <script>
        function submit_order() {
            $("#order_form").submit()

        }

        function show_addr(str) {
            $("#show_addr").html(str);
        }
    </script>

</head>
<body>
    <jsp:include page="header.jsp"/>
    <jsp:include page="searchArea.jsp"/>

    <div class="message">
        <div class="msg_title">
            收货人信息
        </div>
        <form id=order_form action="save_order.do">
        <c:forEach items="${addresses}" var="address">
        <div class="msg_addr">
				<span class="msg_left">
					姓名${address.shjr}
				</span>
            <span class="msg_right">
                    ${address.yh_dz}<input onclick="show_addr('${address.yh_dz}${address.shjr}${address.lxfsh}')" type="radio" name="id" value="${address.id}">
				</span>
        </div>
        </c:forEach>
        </form>
        <span class="addrs">查看更多地址信息</span>
        <div class="msg_line"></div>

        <div class="msg_title">
            送货清单
        </div>

        <c:forEach items="${order.list_flow}" var="flow">

            <div style="margin-top:20px">
            <c:forEach items="${flow.list_info}" var="info" varStatus="s">
                <div class="msg_list" >
                    <div class="msg_list_left">
                        <c:if test="${s.index==0}">
                            配送方式:${flow.mqdd}
                            <div class="left_title">
                                    ${flow.psfsh}
                            </div>
                        </c:if>

                    </div>
                    <div class="msg_list_right">
                        <div class="msg_img">
                            <img src="upload/image/${info.shp_tp}" width="80px" height="80px"/>
                        </div>
                        <div class="msg_name">
                                ${info.sku_mch}
                        </div>
                        <div class="msg_price">
                            ￥${info.sku_jg}
                        </div>
                        <div class="msg_mon">
                            *${info.sku_shl}
                        </div>
                        <div class="msg_state">
                            有货
                        </div>
                    </div>
                </div>
            </c:forEach>
            </div>
        </c:forEach>


        <div class="msg_line"></div>

        <div class="msg_sub">
            <div class="msg_sub_tit">
                应付金额：
                <b>￥${order.zje}</b>
            </div>
            <div  class="msg_sub_adds">
                寄送至 ：  <span id="show_addr"></span>
            </div>
            <button class="msg_btn" style="cursor: pointer" onclick="submit_order()">提交订单</button>


        </div>

    </div>
</body>
</html>
