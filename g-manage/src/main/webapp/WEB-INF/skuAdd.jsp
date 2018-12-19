<%--

  User: yaokaikai
  Date: 2018/12/6
  Time: 14:38

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


    <script>
        $(function () {
            var flbh1 = "${flbh1}";
            // 需要双引号，js的代码在服务端的代码之后
            $.getJSON("js/json/tm_class_1_"+ flbh1 +".js",function (json) {
                $("#sku_tm_select").empty();
                $(json).each(function (index, data) {
                    $("#sku_tm_select").append("<option value=" + data.id +">"+data.ppmch+"</option>")
                })
            })
        })


        function get_spu_list(pp_id) {
            var flbh2 = "${flbh2}";
            $.post("get_spu_list.do",{pp_id:pp_id,flbh2:flbh2},function (data) {
                $("#spu_list").empty();
                $(data).each(function (i,json) {
                    $("#spu_list").append("<option value=" + json.id +">"+json.shp_mch+"</option>")
                })
            })
        }
        
        
        function show_val(attr_id,checked) {
            if(checked){
                $("#val_"+attr_id).show();
            }else {
                $("#val_"+attr_id).hide();

            }

        }
        
    </script>

</head>
<body>
<form action="save_sku.do" method="post">
    <input type="hidden" value="${flbh1}" name="flbh1">
    <input type="hidden" value="${flbh2}" name="flbh2">

    品牌：<select id="sku_tm_select" name="pp_id" onchange="get_spu_list(this.value)"></select>
    商品：<select id="spu_list" name="id"></select>
    <hr>
    分类属性:
    <br>
    <c:forEach items="${list_attr}" var="attr" varStatus="s">
        <input type="checkbox" name="list_attr[${s.index}].shxm_id" value="${attr.id}"
               onclick="show_val(${attr.id},this.checked)" >${attr.shxm_mch}
    </c:forEach>
    <br>
    <c:forEach items="${list_attr}" var="attr" varStatus="s" >
        <div id="val_${attr.id}" style="display:none">
            <c:forEach items="${attr.list_value}" var="value">
                <input name="list_attr[${s.index}].shxzh_id" value="${value.id}" type="radio">
                ${value.shxzh}${value.shxzh_mch}
            </c:forEach>
        </div>
    </c:forEach>



    商品库存名称：<input type="text" name="sku_mch"><br>
    商品库存数量：<input type="text" name="kc"><br>
    商品库存价格：<input type="text" name="jg"><br>
    商品库存地址：<input type="text" name="kcdz"><br>
    <input type="submit" value="提交">
</form>
</body>
</html>
