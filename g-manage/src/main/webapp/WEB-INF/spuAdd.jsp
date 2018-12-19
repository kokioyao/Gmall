<%--

  User: yaokaikai
  Date: 2018/12/4
  Time: 1:20

--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<base href="<%=basePath%>">
<html>
<head>
    <title>Title</title>
    <script src="js/jquery-1.7.2.min.js"></script>


</head>
<body>
    spu信息添加
    ${spu.flbh1}${spu.flbh2}${spu.pp_id}
    <form action="spu_add.do"  enctype="multipart/form-data" method="post">
        <input type="hidden" name="flbh1" value="${spu.flbh1}">
        <input type="hidden" name="flbh2" value="${spu.flbh2}">
        <input type="hidden" name="pp_id" value="${spu.pp_id}">

        商品名称：<input type="text" name="shp_mch" ><br>
        商品描述：<textarea name="shp_msh" rows="10" cols="50"></textarea><br>
        商品图片：<br>
        <div id="d_0" style="float:left;">
            <input type="radio" id="radio" name="mainpic" value="0">
            <input id="file_0" type="file" name="files" style="display:none" onchange="replace_image(0)">
            <img id="image_0" onclick="click_image(0)" style="cursor:pointer" src="image/upload_hover.png" width="100px" height="100px">
        </div>

        <input type="submit" value="提交">
    </form>

    <script>
        function click_image(index) {
            if(index < 5) {
                $("#file_" + index).click();
            }else {
                alert("数量超过上限啦")
            }
        }

        function replace_image(index) {

            //获得图片对象
            var blob_image = $("#file_"+index)[0].files[0];
            var url = window.URL.createObjectURL(blob_image);

            //替换image
            $("#image_"+index).attr("src",url);

            var length = $(":file").length;
            if((index+1)==length){
                add_image(index);
            }




        }

        function add_image(index){
            var a = '<div id="d_'+(index+1)+'" style="float:left;margin-left:10px;border:1px rosybrown solid; " >';
            var length = $(":file").length;
            var z ='<input type="radio" id="radio" name="mainpic" value="'+(index+1)+'">';
            var b ='<input id="file_'+(index+1)+'" type="file" name="files" style="display:none" onchange="replace_image('+(index+1)+')">';
            var c = '<img id="image_'+(index+1)+'" onclick="click_image('+(index+1)+')" style="cursor:pointer" src="image/upload_hover.png" width="100px" height="100px">';
            var d = '</div>'
            $("#d_"+index).after(a+z+b+c+d);
        }

    </script>
</body>
</html>
