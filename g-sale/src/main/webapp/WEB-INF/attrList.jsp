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
    <script>
        function save_param(shxm_id, shxzh_id, shxmch) {
            $("#paramArea").append("<input name='shxparam' type = 'text' value='{\"shxm_id\":"+shxm_id+",\"shxzh_id\":"+shxzh_id+"}'>"+shxmch);
            //异步调用
            getListByAttr();
        }
        
        function getListByAttr() {

            var attrJson = {};
            // var jsonStr = "";
            $("#paramArea input[name='shxparam']").each(function (i, data) {
                var json = $.parseJSON(data.value);
                attrJson["list_attr["+i+"].shxm_id"] = json.shxm_id;
                attrJson["list_attr["+i+"].shxzh_id"] = json.shxzh_id;
                // jsonStr = jsonStr + "&list_attr["+i+"].shxm_id="+json.shxm_id + "&list_attr["+i+"].shxzh_id="+json.shxzh_id;
            });
            attrJson["flbh2"]=${flbh2};

            $.post("getListByAttr.do",attrJson,function (data) {
                $("#skuListInner").html(data);
            })
            
        }
    </script>
</head>
<body>
    <div id="paramArea"></div>
    属性列表<br>

    <c:forEach items="${list_attr}" var="attr">
        ${attr.shxm_mch}:
        <c:forEach items="${attr.list_value}" var="value">
            <a href="javascript:save_param(${attr.id},${value.id},'${value.shxzh}${value.shxzh_mch}')">${value.shxzh}${value.shxzh_mch}</a>
        </c:forEach>
        <br>
    </c:forEach>
</body>
</html>
