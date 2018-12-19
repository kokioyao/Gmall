<%--

  User: yaokaikai
  Date: 2018/12/5
  Time: 20:30

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
    添加商品属性
    <hr>
    <form action="attr_add.do" method="post">
        <input type="hidden" name="flbh2" value="${flbh2}">
        <table border="1" width="700px">
            <tr><td>属性名:<input type="text" name="list_attr[0].shxm_mch"></td><td></td><td>添加值</td></tr>
            <tr><td>属性值:<input type="text" name="list_attr[0].list_value[0].shxzh"></td><td>单位:<input type="text"  name="list_attr[0].list_value[0].shxzh_mch"></td><td>删除</td></tr>
            <tr><td>属性值:<input type="text" name="list_attr[0].list_value[1].shxzh"></td><td>单位:<input type="text" name="list_attr[0].list_value[1].shxzh_mch"></td><td>删除</td></tr>
        </table>
        <table border="1" width="700px">
            <tr><td>属性名:<input type="text" name="list_attr[1].shxm_mch"></td><td></td><td>添加值</td></tr>
            <tr><td>属性值:<input type="text" name="list_attr[1].list_value[0].shxzh"></td><td>单位:<input type="text" name="list_attr[1].list_value[0].shxzh_mch"></td><td>删除</td></tr>
            <tr><td>属性值:<input type="text" name="list_attr[1].list_value[1].shxzh"></td><td>单位:<input type="text" name="list_attr[1].list_value[1].shxzh_mch"></td><td>删除</td></tr>
        </table>
        添加:<input type="submit" value="提交">
    </form>
</body>
</html>
