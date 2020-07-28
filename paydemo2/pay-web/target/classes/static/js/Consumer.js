layui.use(['layer', 'table','jquery'], function () {
    var $ = layui.jquery,
        layer = layui.layer,
        table = layui.table;

    //弹窗
    var url = "";
    var qrcode = new createQRCode(document.querySelector("#pay-load"), url, 250, 250);

    var day2 = new Date();
    day2.setTime(day2.getTime());
    var tradeid = "test"+day2.getFullYear()+ (day2.getMonth()+1) + day2.getDate()+day2.getHours()+day2.getMinutes()+day2.getSeconds();

    var list1 = table.render({
        elem: '#demo1',
        url: "/show/data",
        cellMinWidth:100,
        id:'demo1',
        page:true,
        cols: [
            [
                {field:'gname',title:'商品名',align:"center"},
                {field:'price',title:'价格',align:"center"},
                {field:'pArea',title:'剩余数量',align:"center"},
                {field:'gid',title:'商品id',hide:true},
                {field:'version',title:'版本号',hide:true},
                {title: '操作', fixed: 'right', align: 'center', toolbar: '#barDemo'}
            ]
        ]
    });
    var list2 = table.render({
        elem:'#demo2',
        cellMinWidth:100,
        totalRow: true,
        page: true,
        cols:[[
            {field:'gname',title:'商品名',align:"center",totalRowText: '合计'},
            {field:'dprice',title:'单价格',align:"center"},
            {field:'number',title:'已购数量',align:"center"},
            {field:'aprice',title:'总价',align:"center",totalRow: true},
            {field:'gid',title:'商品id',hide:true},
            {field:'version',title:'版本号',hide:true},
            {title: '操作', fixed: 'right', align: 'center', toolbar: '#barDemo2',width:200}
        ]],
        data:[]
    });
    //点击操作
    //商品列表
    table.on('tool(test1)', function(obj){
        var d = obj.data;
        var t = table.cache['demo2'];
        console.log(d.gid);
        var boo = false;
        for (var i = 0;i<t.length;i++){
            if(d.gid == t[i].gid){
                t[i].number++;
                t[i].aprice =Number(t[i].aprice) +Number(d.price);
                boo = true;
                break;
            }
        }
        if (boo==false) {
            d.number = 1;
            d.dprice = d.price;
            d.aprice = d.price;
            t.push(d);
        }
        d.pArea--;
        //刷新表格选中行
        obj.update({
            pArea:d.pArea
        });
        //更新购物车列表
        table.reload("demo2", {
            data: t
        });
    });

    //购物车
    table.on('tool(test2)', function(obj){
        var d = obj.data;
        var t = table.cache['demo1'];
        switch (obj.event) {
            case 'add':
                //修改商品表中对应的剩余数量
                for (var i = 0;i<t.length;i++){
                    if(d.gid == t[i].gid){
                        t[i].pArea--;
                        break;
                    }
                }
                d.aprice = Number(d.aprice)+Number(d.dprice);
                d.number++;
                //刷新表格选中行
                obj.update({
                    aprice:d.aprice,
                    number:d.number
                });
                list2.reload({
                    data:table.cache['demo2']
                });
                table.reload('demo1',{
                    url:'',
                    data:t
                });
                break;
            case 'del':
                //修改商品表中对应的剩余数量
                for (var i = 0;i<t.length;i++){
                    if(d.gid == t[i].gid){
                        t[i].pArea++;
                        break;
                    }
                }
                d.aprice = Number(d.aprice)-Number(d.dprice);
                d.number--;
                if (d.number<1) {
                    obj.del();
                }
                //刷新表格选中行
                obj.update({
                    aprice:d.aprice,
                    number:d.number
                });
                list2.reload({
                    data:table.cache['demo2']
                });
                table.reload('demo1',{
                    url:'',
                    data:t
                });
                break;
            case 'que':
                //修改商品表中对应的剩余数量
                for (var i = 0;i<t.length;i++){
                    if(d.gid == t[i].gid){
                        t[i].pArea = Number(t[i].pArea)+Number(d.number);
                        break;
                    }
                }
                obj.del();
                list2.reload({
                    data:table.cache['demo2']
                });
                table.reload('demo1',{
                    url:'',
                    data:t
                });
                break;
            default:
                layer.alert("页面异常，请重试");
        }
    });

    function presuccess(res){
        layer.open({
            title:res.msg,
            type: 1,
            content: $('#pay-load'),
            closeBtn:0,
            btn:['撤销支付'],
            yes:function (index,layero) {//取消交易
                $.ajax({
                    url:'/trade/cancel',
                    type:'POST',
                    data:{
                        id:tradeid
                    },
                    complete:function (res) {
                        location.href="ftl";
                        /*//刷新页面
                        table.reload('demo1');
                        table.reload('demo2',{
                            data:[]
                        });
                        //重置id
                        day2.setTime(day2.getTime());
                        var tradeid = "test"+day2.getFullYear()+ (day2.getMonth()+1) + day2.getDate()+day2.getHours()+day2.getMinutes()+day2.getSeconds();
                    */}
                });
            },
            success: function(layero, index1){
                var timer=setTimeout(function(){
                    //超时后提示
                    layer.open({
                        title:"支付超时",
                        type: 0,
                        content: '支付超时，请重新选择',
                        cancel: function(index, layero){
                            location.href="ftl";
                            /* //刷新页面
                             table.reload('demo1');
                             table.reload('demo2',{
                                 data:[]
                             });
                             //重置id
                             day2.setTime(day2.getTime());
                             var tradeid = "test"+day2.getFullYear()+ (day2.getMonth()+1) + day2.getDate()+day2.getHours()+day2.getMinutes()+day2.getSeconds();
                             layer.closeAll();*/
                        },
                        yes:function (index,layero){
                            location.href="ftl";
                        }
                    });
                },60000);
                //查询支付状态，后台轮询
                $.ajax({
                    url:'/trade/queresult',
                    dataType: 'json',
                    type: 'POST',
                    data:{
                        id:tradeid
                    },
                    success:function (res) {
                        switch (res.code) {
                            case "000":
                                clearTimeout(timer);
                                layer.open({
                                    title:"支付成功",
                                    type: 0,
                                    content: '支付成功',
                                    cancel: function(index, layero){
                                        location.href="ftl";
                                    },
                                    yes:function (index,layero){
                                        location.href="ftl";
                                    }
                                });
                                break;
                            default:
                        }
                    }
                });
            }
        });
    }


    //支付宝确认订单按钮
    $(document).on("click", "#paysure", function () {
        //
        var t = table.cache['demo2'];
        var tradelist = [];
        var totalprice="0";
        for (var i = 0;i<t.length;i++){
            var goods = {
                "gid":t[i].gid,
                "version":t[i].version,
                "number":t[i].number,
                "dprice":t[i].dprice,
                "aprice":t[i].aprice
            };
            totalprice = Number(totalprice) + Number(t[i].aprice);
            tradelist.push(goods);
        }
        $.ajax({
            url: '/trade/precreate',
            dataType: 'json',
            type: 'POST',
            data:{
                outTradeNo:tradeid,
                totalAmount:totalprice,
                goods:JSON.stringify(tradelist)
            },
            success: function (res) {
                switch (res.code) {
                    case "000":
                        qrcode.change(res.url);
                        //请求二维码弹窗
                        var p = presuccess(res);
                        break;
                    default:
                        qrcode.change(res.url);
                        var def=layer.open({
                            title:res.msg,
                            type: 1,
                            content: $('#pay-load'),
                            closeBtn:0,
                            btn:['撤销支付','刷新二维码'],
                            yes:function (index,layero) {//取消交易
                                location.href="ftl";
                            },
                            btn2:function (index,layero) {//重新生成二维码
                                //重置id
                                day2.setTime(day2.getTime());
                                tradeid = "test"+day2.getFullYear()+ (day2.getMonth()+1) + day2.getDate()+day2.getHours()+day2.getMinutes()+day2.getSeconds();
                                $.ajax({
                                    url: '/trade/precreate',
                                    dataType: 'json',
                                    type: 'POST',
                                    data:{
                                        outTradeNo:tradeid,
                                        totalAmount:totalprice,
                                        goods:JSON.stringify(tradelist)
                                    },
                                    success:function (res) {
                                        switch (res.code) {
                                            case "000":
                                                qrcode.change(res.url);
                                                layer.title(res.msg,def);
                                                var timer=setTimeout(function(){
                                                    //超时后提示
                                                    layer.open({
                                                        title:"支付超时",
                                                        type: 0,
                                                        content: '支付超时，请重新选择',
                                                        cancel: function(index, layero){
                                                            location.href="ftl";
                                                        },
                                                        yes:function (index,layero){
                                                            location.href="ftl";
                                                        }
                                                    });
                                                },60000);
                                                //查询支付状态，后台轮询
                                                $.ajax({
                                                    url:'/trade/queresult',
                                                    dataType: 'json',
                                                    type: 'POST',
                                                    data:{
                                                        id:tradeid
                                                    },
                                                    success:function (res) {
                                                        switch (res.code) {
                                                            case "000":
                                                                clearTimeout(timer);
                                                                layer.open({
                                                                    title:"支付成功",
                                                                    type: 0,
                                                                    content: '支付成功',
                                                                    cancel: function(index, layero){
                                                                        location.href="ftl";
                                                                    },
                                                                    yes:function (index,layero){
                                                                        location.href="ftl";
                                                                    }
                                                                });
                                                                break;
                                                            default:
                                                        }
                                                    }
                                                });
                                                break;
                                            default:
                                                qrcode.change(res.url);
                                                layer.title(res.msg,def);
                                        }
                                    },
                                    error:function () {
                                        layer.open({
                                            title:'请求出错',
                                            type: 1,
                                            content: '请求出错，请重试'
                                        });
                                    }
                                });
                                return false
                            }
                        });
                        break;
                }
            },
            error:function () {
                layer.open({
                    title:'请求出错',
                    type: 1,
                    content: '请求出错，请重试'
                });
            }
        });
    });
});