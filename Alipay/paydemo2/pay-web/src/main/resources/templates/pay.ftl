<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    </meta>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </meta>
    <meta com.dwh.http-equiv="X-UA-Compatible" content="ie=edge">
    <title>获取商品页面</title>
    <link rel="stylesheet" href="/layui/css/layui.css" type="text/css">
    </link>
    <script type = "text/javascript" src="js/qrcode.js"></script>
</head>
<script type="text/html" id="barDemo">
    {{# if(d.pArea!="0"){}}
    <a class="layui-btn layui-btn-sm" lay-event="add">添加</a>
    {{# }else{}}
    <a class="layui-btn layui-btn-sm layui-btn-disabled">添加</a>
    {{# } }}
</script>
<script type="text/html" id="barDemo2">
    <a class="layui-btn layui-btn-sm" lay-event="add">增加</a>
    <a class="layui-btn layui-btn-sm" lay-event="del">减少</a>
    <a class="layui-btn layui-btn-sm" lay-event="que">取消</a>
</script>
<body class="layui-layout-body">
<div id="pay-load" hidden="hidden"></div>
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">选购界面</div>
    </div>
    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
        </div>
    </div>

    <div class="layui-body">
        <fieldset class="layui-elem-field">
            <legend>商品列表</legend>
            <div class="layui-field-box" >
                <table id="demo1" lay-filter="test1" class="layui-table"></table>
            </div>
        </fieldset>
        <fieldset class="layui-elem-field">
            <legend>购物车</legend>
            <div class="layui-field-box" >
                <table id="demo2" lay-filter="test2" class="layui-table"></table>
            </div>
        </fieldset>
    </div>


    <div class="layui-footer">
        <button  id = "paysure" type="button" class="layui-btn">确定付款</button>
    </div>
</div>
<script src="/layui/layui.js"></script>
<script src="js/Consumer.js"></script>
</body>
</html>