<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta content="text/html; charset=UTF-8" http-equiv="Content-Type">
    <title>优衣会员时装共享平台</title>
    <link rel="icon" href="/home/imgs/favicon.ico" type="image/x-icon">
    <link media="all" href="/home/css/want_list.css" type="text/css" rel="stylesheet">
    <link media="all" href="/home/css/index.css" type="text/css" rel="stylesheet">
    <link href="/admin/css/bootstrap.min.css" rel="stylesheet">
    <link href="/admin/css/materialdesignicons.min.css" rel="stylesheet">
    <link href="/admin/css/style.min.css" rel="stylesheet">
    <!--对话框-->
    <link rel="stylesheet" href="/admin/js/jconfirm/jquery-confirm.min.css">
    <!--通知消息-->
    <link href="/admin/css/animate.css" rel="stylesheet">
</head>
<style type="text/css">
    .btn1:hover{

        border: rgba(84, 96, 44, 0.32) solid 2px;
        box-shadow: 0px 0px 8px #1D6FA3 ;
    }
</style>
<body>
 <#include "../common/top_header.ftl"/>
<#--<#include "../common/left_menu.ftl"/> -->
<div class="container">
    <div class="main center clearfix">
<#--        <div class="want-title"></div>-->
        <div class="wrap-want-list">
            <ul class="want-list" id="want-list">
                <#if orders??>
                    <#list orders as order>
                        <li class="want-item">
                            <div class="want-li clearfix">
                                <div style="height:auto; float:left; display:inline">
                                    <#if order.clothes.getPicture()??>
                                        <#if order.clothes.getPicture()?length gt 0>
                                            <img src="/photo/view?filename=${order.clothes.getPicture()}" width="300px" height="300px">
                                        <#else>
                                            <img src="/admin/images/default_clothes_pic.jpg" width="300px" height="300px">
                                        </#if>
                                    </#if>
                                </div>
                                <div style="height:auto; float:left; display:inline; margin-left: 20px">
                                    <h4 class="want-name">
                                        【服装名称】
                                        <span>${order.clothes.name}</span>
                                    </h4>
                                    <div style=" height:auto; float:left; display:inline; margin-left: 20px">
                                        <p class="want-attr">
                                            <span>订单状态：</span>
                                            <#if order.status == 0>
                                            <span class="want-price">待配送</span>
                                            <#elseif order.status == 1>
                                                <span class="want-price">配送中</span>
                                            <#elseif order.status == 2>
                                                <span class="want-price">待寄回</span>
                                            <#elseif order.status == 3>
                                                <span class="want-price">寄回中</span>
                                            <#elseif order.status == 4>
                                                <span class="want-price">待审核</span>
                                            <#elseif order.status == 5>
                                                <span class="want-price">已完成</span>
                                            <#elseif order.status == -1>
                                            <span class="want-price">异常订单</span>
                                        <p class="want-attr">
                                            <span>赔偿金额：</span>
                                            <span id="damage" class="want-add">${order.compensation}</span>
                                            <input id="comsenpation" type="button" value="支付赔偿">
                                        </p>
                                        <#elseif order.status == -2>
                                            <span class="want-price">已取消</span>
                                        </#if>
                                        </p>
                                        <p class="want-attr">
                                            <span>订单编号：</span>
                                            <span class="want-add">${order.sn}</span>
                                        </p>
                                        <p class="want-attr">
                                            <span>租赁天数：</span>
                                            <span class="want-add">${order.days}</span>
                                        </p>
                                        <p class="want-attr">
                                            <span>创建时间：</span>
                                            <span class="want-add">${order.createTime?datetime}</span>
                                        </p>
                                        <p class="want-attr">
                                            <span>收货地址：</span>
                                            <span class="want-add">${order.address}</span>
                                        </p>
                                        <p class="want-attr">
                                            <span>收货人电话：</span>
                                            <span class="want-add">${order.phone}</span>
                                        </p>
                                        <p class="want-attr">
                                            <span>订单备注：</span>
                                            <span class="want-add">${order.remark}</span>
                                        </p>
                                        <div class="cancel_order">
                                            <#if order.status == 0>
                                                <input class="btn btn1 btn-primary btn-block" type="button" value="取消订单">
                                            <#elseif order.status == 1>
                                                <input class="btn btn2 btn-primary btn-block" type="button" value="确认收货" style="background-color: rgba(31,172,255,0.73)">
                                            <#elseif order.status == 2>
                                                <input class="btn btn2 btn-primary btn-block" type="button" value="我要寄回" style="background-color: rgba(70,255,226,0.73)">
                                            <#elseif order.status == -1>
                                                <input class="btn btn3 btn-primary btn-block" type="button" value="我要赔偿">
                                            </#if>
                                            <text class="order_id" style="display: none">${order.id}</text>
                                            <#-- 保存order_id-->
                                        </div>
                                    </div>

                                </div>


                            </div>
                        </li>
                    </#list>
                </#if>
            </ul>
        </div>
    </div>
</div>
<div class="return-to-top"><a href="#"></a></div><!--返回顶部-->
<#include "../common/right_menu.ftl"/>
<#include "../common/bottom_footer.ftl"/>
<script type="text/javascript" src="/admin/js/jquery.min.js"></script>
<script type="text/javascript" src="/admin/js/bootstrap.min.js"></script>
<!--对话框-->
<script src="/admin/js/jconfirm/jquery-confirm.min.js"></script>
<script src="/admin/js/common.js"></script>
<!--消息提示-->
<script src="/admin/js/bootstrap-notify.min.js"></script>
<script type="text/javascript" src="/admin/js/lightyear.js"></script>
<script type="text/javascript">
    $(document).ready(function(){

    });
    $(".btn2").click(function () {
        alert("请前往派送单页面操作");
        window.location.href="/uniqlo/upaisong/list";
    });
    $('.btn3').on('click', function () {
        var order_id = $(this).parent().children('text').html();
        var  money = $('#damage').html();

        $.ajax({
            url:'/uniqlo/order/damage_pay_log',
            type:'POST',
            data:{
                "orderid":order_id,
                "money":money
            },
            dataType:'json',
            success:function(data){
                if(data.code == 0){
                    window.location.href = '/uniqlo/pay/pay_money?sn=' + data.data;
                }else{
                    alert(data.msg);
                }
            },
            error:function(data){
                alert('网络错误!');
            }
        });
    });


    // 取消订单
    $('.btn1').on('click', function () {
        var order_id = $(this).parent().children('text').html();
        $.confirm({
            title: '警告',
            content: '确定取消订单？取消后不可恢复。',
            type: 'orange',
            typeAnimated: false,
            buttons: {
                omg: {
                    text: '确定',
                    btnClass: 'btn-orange',
                    action: function(){
                        // alert(order_id);
                        $.ajax({
                            url: "/uniqlo/order/cancel_order",
                            type:'POST',
                            data:{order_id:order_id},
                            dataType:'json',
                            success:function (data){
                                console.log(data);
                                if(data.code == 0){
                                    refresh();
                                    // setTimeout(function() {
                                    //     lightyear.loading('show');
                                    //     lightyear.notify('修改成功，页面即将自动跳转~', 'success', 3000);
                                    // }, 1500)
                                }else{
                                    alert(data.msg);
                                }
                            },
                            error:function (data){
                                alert("网络错误!");
                            }
                        });
                    }
                },
                close: {
                    text: '取消',
                }
            }
        });
    });



    //1s后刷新页面
    function refresh() {
        window.location.reload();
        setTimeout(refresh,1000);
    }

</script>

</body>
</html>