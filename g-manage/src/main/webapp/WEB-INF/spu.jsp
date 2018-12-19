<%--

  User: yaokaikai
  Date: 2018/12/4
  Time: 1:17

--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<base href="<%=basePath%>">
<html>


<head>
    <%--<script src="js/jquery-1.7.2.min.js"></script>--%>
    <title>Title</title>
    

</head>
<body>
    spu商品信息管理
    <hr>
    一级分类:<select id = "class_1_select" onchange="get_class_2(this.value)"><option>请选择</option></select>
    二级分类:<select id = "class_2_select"><option>请选择</option></select>
    品牌:<select id = "tm_select"><option>请选择</option></select>
    <br>
    查询<br>
    <a href="javascript:goto_spu_add('spu添加')">spu添加</a><br>
    删除<br>
    修改<br>


    <script>
        $(function x() {

            $.getJSON("js/json/class_1.js",function (json) {
                $(json).each(function (index, data) {
                    $("#class_1_select").append("<option value=" + data.id +">"+data.flmch1+"</option>")
                })
            })

        });

        function get_class_2(class_1) {
            $.getJSON("js/json/class_2_"+ class_1 +".js",function (json) {
                $("#class_2_select").empty();
                $(json).each(function (index, data) {
                    $("#class_2_select").append("<option value=" + data.id +">"+data.flmch2+"</option>")
                })
            });
            get_tm(class_1);
        }
        function get_tm(class_1) {
            $.getJSON("js/json/tm_class_1_"+ class_1 +".js",function (json) {
                $("#tm_select").empty();
                $(json).each(function (index, data) {
                    $("#tm_select").append("<option value=" + data.id +">"+data.ppmch+"</option>")
                })
            })
        }

        function goto_spu_add(title) {
            class_1_id = $("#class_1_select").val();
            class_2_id = $("#class_2_select").val();
            tm_id = $("#tm_select").val();
            var url ="goto_spu_add.do?flbh1="+class_1_id+"&flbh2="+class_2_id+"&pp_id="+tm_id;

            $.post(url,function (data) {
                $('#tt').tabs('add',{
                    title:title,
                    content:data,
                    closable:true,
                    tools:[{
                        iconCls:'icon-mini-refresh',
                        handler:function(){
                            alert('refresh');
                        }
                    }]
                });
            })


        }

    </script>
</body>
</html>
