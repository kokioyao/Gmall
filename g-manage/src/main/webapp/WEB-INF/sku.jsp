<%--

  User: yaokaikai
  Date: 2018/12/6
  Time: 14:24

--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<base href="<%=basePath%>">
<html>



<head>
    <script src="js/jquery-1.7.2.min.js"></script>
    <title>Title</title>

</head>
<body>

    sku商品信息管理
    <hr>
    一级分类:<select id = "sku_class_1_select" onchange="get_class_2(this.value)"><option>请选择</option></select>
    二级分类:<select id = "sku_class_2_select"><option>请选择</option></select>
    <br>
    查询<br>
    <a href="javascript:goto_sku_add()">添加</a><br>
    删除<br>
    修改<br>

    <script>
        $(function x() {

            $.getJSON("js/json/class_1.js",function (json) {
                $(json).each(function (index, data) {
                    $("#sku_class_1_select").append("<option value=" + data.id +">"+data.flmch1+"</option>")
                })
            })

        });

        function get_class_2(class_1) {
            $.getJSON("js/json/class_2_"+ class_1 +".js",function (json) {
                $("#sku_class_2_select").empty();
                $(json).each(function (index, data) {
                    $("#sku_class_2_select").append("<option value=" + data.id +">"+data.flmch2+"</option>")
                })
            });

        }



        function goto_sku_add() {
            class_1_id = $("#sku_class_1_select").val();
            class_2_id = $("#sku_class_2_select").val();
            window.location.href="goto_sku_add.do?flbh1="+class_1_id+"&flbh2="+class_2_id;
        }



    </script>
</body>
</html>
