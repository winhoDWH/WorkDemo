layui.use(['form','layer','laydate','table','laytpl'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laydate = layui.laydate,
        laytpl = layui.laytpl,
        table = layui.table;

    //新闻列表
    var tableIns = table.render({
        elem: '#userList',
        url : '/index/userlist',
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limit : 20,
        limits : [10,15,20,25],
        id : "userListTable",
        cols : [[
            {field:'userName',title:'用户名',align:"center",totalRowText: '合计'},
            {field:'usertype',title:'用户类型',align:"center"},
            {field:'phone',title:'电话',align:"center"},
            {field:'time',title:'入店时间',align:"center"}
        ]]
    });

})