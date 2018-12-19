<%--

  User: yaokaikai
  Date: 2018/12/5
  Time: 20:24

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

    <div class="easyui-layout" data-options="fit:true">
        <div data-options="region:'north',split:true" style="height:50px">
            <div style="margin-top:10px">
            商品属性信息维护
            一级分类:<select data-options="width:300"  class="easyui-combobox" id = "attr_class_1_select" onchange="get_attr_class_2(this.value)"><option>请选择</option></select>
            二级分类:<select data-options="width:300" class="easyui-combobox" id = "attr_class_2_select" onchange="get_attr_list_json(this.value)"><option>请选择</option></select>
            <a href="javascript:goto_attr_add()">添加</a><br>
            </div>

        </div>
        <div data-options="region:'west',split:true" style="width:100px">
            查询<br>

            删除<br>
            修改<br>
        </div>
        <div data-options="region:'center'">
            <div id="attrListInner"></div>
        </div>
    </div>







    <script>
        $(function x() {

            // $.getJSON("js/json/class_1.js",function (json) {
            //     $(json).each(function (index, data) {
            //         $("#attr_class_1_select").append("<option value=" + data.id +">"+data.flmch1+"</option>")
            //     })
            // })

            $('#attr_class_1_select').combobox({
                url:'js/json/class_1.js',
                valueField:'id',
                textField:'flmch1',
                width:300,
                onChange: function get_attr_class_2() {
                    //获取当前的下拉列表选中的id
                    var class_1 = $(this).combobox("getValue");
                    // $.getJSON("js/json/class_2_"+ class_1 +".js",function (json) {
                    //     $("#attr_class_2_select").empty();
                    //     $(json).each(function (index, data) {
                    //         $("#attr_class_2_select").append("<option value=" + data.id +">"+data.flmch2+"</option>")
                    //     })
                    // });
                    $('#attr_class_2_select').combobox({
                        url: "js/json/class_2_"+class_1+".js",
                        valueField: 'id',
                        textField: 'flmch2',
                        width: 300,
                        onChange:function () {
                            var flbh2 = $(this).combobox("getValue");
                            get_attr_list_json(flbh2);
                        }
                    });

                }
            });

        });

        function get_attr_class_2(class_1) {
            $.getJSON("js/json/class_2_"+ class_1 +".js",function (json) {
                $("#attr_class_2_select").empty();
                $(json).each(function (index, data) {
                    $("#attr_class_2_select").append("<option value=" + data.id +">"+data.flmch2+"</option>")
                })
            });

        }

        function goto_attr_add() {
            // class_2_id = $("#attr_class_2_select").val();
            class_2_id =  $("#attr_class_2_select").combobox("getValue");
            // window.location.href="goto_attr_add.do?flbh2="+class_2_id;
            add_tab("添加属性","goto_attr_add.do?flbh2="+class_2_id);
        }

        function get_attr_list(flbh2) {
            异步查询
            $.post("get_attr_list.do",{flbh2:flbh2},function (data) {
                $("#attrListInner").html(data)

            });




        }

        function get_attr_list_json(flbh2) {


            $('#attrListInner').datagrid({
                url:'get_attr_list_json.do',
                queryParams: {
                    flbh2: flbh2
                },

                columns:[[
                    {field:'id',title:'id',width:100},
                    {field:'shxm_mch',title:'属性名称',width:100},
                    {field:'list_value',title:'属性值',width:300,
                        formatter: function(value,row,index){
                            var str = "";
                           //处理字段值
                            $(value).each(function (i,json) {
                                str = str + json.shxzh + json.shxzh_mch + " ";
                            });
                            return str;
                        }
                    },
                    {field:'chjshj',title:'创建时间',width:200,
                        formatter: function(value,row,index){
                            var d = new Date(value);
                            var str = d.toLocaleString();

                            return str;
                        }}
                ]]
            });


        }
    </script>
</body>
</html>
