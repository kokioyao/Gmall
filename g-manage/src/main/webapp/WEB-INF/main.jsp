<%--

  User: yaokaikai
  Date: 2018/12/4
  Time: 0:52

--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
        <script src="js/jquery-1.7.2.min.js"></script>

        <link rel="stylesheet" type="text/css" href="js/easyui/themes/default/easyui.css">
        <link rel="stylesheet" type="text/css" href="js/easyui/themes/icon.css">
        <script type="text/javascript" src="js/easyui/jquery.easyui.min.js"></script>

        <script>

            $(function () {
                var url = "${url}";
                var title = "${title}";
                alert(url);
                alert(title);
                if(url!= "" && title!= ""){
                    add_tab(title,url);

                }
            });

            function add_tab(title,url) {
                var b = $('#tt').tabs('exists',title);
                if(!b){
                    $('#tt').tabs('add',{
                        title:title,
                        href:url,
                        closable:true,
                        tools:[{
                            iconCls:'icon-mini-refresh',
                            handler:function(){
                                alert('refresh');
                            }
                        }]
                    });
                }else {
                    $('#tt').tabs('select',title);
                }


            }
        </script>
</head>
<body class="easyui-layout">

        <div data-options="region:'north',border:false" style="height:60px;background:#B3DFDA;padding:10px">
                后台管理首页</div>
        <div data-options="region:'west',split:true,title:'West'" style="width:200px;padding:10px;">
                <div class="easyui-accordion" style="width:170px;">
                        <div title="商品信息管理" data-options="iconCls:'icon-ok'" style="overflow:auto;padding:10px;">
                                <ul>
                                        <li><a href="javascript:add_tab('商品信息管理','goto_spu.do')" >商品信息管理</a></li>
                                        <li><a href="javascript:add_tab('商品属性管理','goto_attr.do')">商品属性管理</a></li>
                                        <li><a href="javascript:add_tab('商品库存单元管理','goto_sku.do')" > 商品库存单元管理</a></li>

                                </ul>

                        </div>
                        <div title="商品信息管理" data-options="iconCls:'icon-ok'" style="overflow:auto;padding:10px;">
                                <ul>
                                        <li>商品缓存管理</li>
                                </ul>

                        </div>
                </div>
        </div>
        <div data-options="region:'east',split:true,collapsed:true,title:'East'" style="width:100px;padding:10px;">east region</div>
        <div data-options="region:'south',border:false" style="height:50px;background:#A9FACD;padding:10px;">south region</div>
        <div data-options="region:'center',title:'Center'">
                <div id="tt" class="easyui-tabs" data-options="fit:true" >
                </div>
        </div>



</body>
</html>
