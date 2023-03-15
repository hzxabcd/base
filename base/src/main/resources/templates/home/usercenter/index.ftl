<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta content="text/html; charset=UTF-8" http-equiv="Content-Type">
<title>优衣服装共享平台-个人中心</title>
<link rel="icon" href="/home/imgs/favicon.ico" type="image/x-icon">
<link media="all" href="/home/css/index.css" type="text/css" rel="stylesheet">
<link media="all" href="/home/css/user_center.css" type="text/css" rel="stylesheet">
</head>
<style>
    .buybtn:hover{
        border: 2px solid rgba(191, 56, 34, 0.73);
    }
</style>
<body>
  <#include "../common/user.ftl"/>

    <div class="container">
    <div class="main center">
    <!-- 头像设置开始 -->
   <div class="head-img-box hide" id="head-img-box">
    <div class="wrap-head-img">
        <h3 class="head-title">
            <span>头像设置</span>
            <a id="close-head-img" class="close-head-img" href="javascript:;">X</a>
        </h3>
        <div class="head-img-area">
            <div class="wrap-img" id="wrap-img" style="position: relative;">
                <a href="javascript:;" id="upload-person-img" class="img-attr btn-upload-img " style="position: relative; z-index: 1;"><i>+</i>选择图片</a>
                <p class="img-attr img-limit ">只支持JPG、PNG、GIF，大小不超过1M</p>
                <img class="loading hide" src="/home/imgs/loading.gif" alt="" width="28" height="28">
	            <div id="" class="moxie-shim moxie-shim-html5" style="position: absolute; top: 0px; left: 0px; width: 0px; height: 0px; overflow: hidden; z-index: 0;">
	            	<input id="selected-file" onchange="uploadImg()" style="font-size: 999px; opacity: 0; position: absolute; top: 0px; left: 0px; width: 100%; height: 100%;" accept="image/*" capture="camera" type="file">
	            </div>
            </div>
        </div>
        <div class="head-img-footer">
            <a href="javascript:;" class="btn-ok" id="upload-ok">确定</a>
            <a href="javascript:;" id="cancel-img-box" class="btn-cancel">取消</a>
        </div>
    </div>
</div>
<!-- 头像设置结束 -->
<div class="top clearfix" id ="user-top">
    <div id="user_msg">
        <div class="name" id="user_big_name">
            
        </div>
        
        <ul class="seller_attr">
            <li>已租借次数：<span id="goodsTotal">0</span></li>
            <li>已完成次数：<span id = "soldGoodsTotal">0</span></li>
            <li>待完成订单数：<span id = "upGoodsTotal">0</span></li>
            <li>待退回服装数<span id = "downGoodsTotal">0</span></li>

        </ul>
    </div>
    <div id="user_photo">
          	<#if frontUser.headPic??>
          	<img id="origin_ph" onClick="openUploadPanel()" src="/photo/view?filename=${frontUser.headPic}" old-src="/photo/view?filename=${frontUser.headPic}" alt="大头像">
			<#else>
			<img id="origin_ph" onClick="openUploadPanel()" src="/home/imgs/avatar1.png" old-src="/home/imgs/avatar1.png">
			</#if>
          <img id="change_photo" src="/home/imgs/person_hover.png" alt="更换头像" style="display: none;">
    </div>
</div>
<ul id="middle_nav" class="clearfix">
    <li class="on"><a href="#">个人资料</a></li>
</ul>
<div class="pop-tip hide">
    <div class="pop-tip-area">
        <p class="pop-tip-txt">修改成功</p>
    </div>
</div>
        <div id="my_info">

            <div id="account_info">
            	<h2>账户信息</h2>
                <ul class="infos" id="useename">
                    <li>用户名</li><li class="right_info">${frontUser.userName}</li>
                </ul>
				<ul class="infos" id="userid">
					<li>账号ID</li><li class="right_info">${frontUser.id}</li>
				</ul>
                <ul class="infos" id="usercreatetime">
                    <li>注册时间</li><li class="right_info">${frontUser.createTime?datetime!""}</li>
                </ul>
			    <ul class="infos" id="userstate">
				    <li>账号状态</li>
				    <li class="right_info"><#if frontUser.status == 0>正常<#else>冻结</#if></li>
				</ul>
                <ul class="infos" id="userid">
                    <li>用户类型</li>
                    <#if frontUser.type==2>
                        <li class="right_info">礼服会员</li>
                    <#elseif frontUser.type==1>
                        <li class="right_info">常服会员</li>
                    <#else >
                        <li class="right_info">普通用户</li>
                    </#if>
                </ul>
                <#if frontUser.type gt 0>
                    <ul class="infos" id="userid">
                    <li>到期时间</li><li class="right_info">${frontUser.expireTime?datetime!""}</li>
                </ul>
                </#if>
			</div>

            <div id="account_info">
                <h2 >会员充值</h2>
                <ul class="infos" id="member">
                    <li>充值类型:</li><li style="margin-left: 20px">
                        <select id="membertype">
                            <option value="0">请选择</option>
                            <option value="1">常服会员</option>
                            <option value="2">礼服会员</option>
                            </select>
                    </li>
                    <li style="margin-left: 40px" class="right_info">购买时长(月):</li><li style="margin-left: 10px">
                        <select id="membertime">
                            <option value="0">请选择</option>
                            <option value="1">1</option>
                            <option value="2">2</option>
                            <option value="3">3</option>
                            <option value="4">4</option>
                            <option value="5">5</option>
                            <option value="6">6</option>
                        </select>

                    </li>
                    <li>
                        <input class="buybtn" type="button" value="立刻充值" style="width: 70px;height: 30px;margin-left: 70px; border-radius: 5px;background-color: #ff9f2c">
                    </li>
                </ul>

            </div>



            <div id="base_info">
                <h2>密码管理 <span id="edit_pwd_info">编辑</span><span id="save_pwd_info">保存</span></h2>
                <ul class="infos">
                    <li>原密码</li>
                    <li class="right_info">
                        <span class="editPwd" id="old_pwd_span">******</span>
                        <input class="editPwd" value="" id="old-pwd" type="password">
                    </li> 
                </ul>
                <ul class="infos">
                    <li>新密码</li>
                    <li class="right_info">
                        <span class="editPwd" id="new_pwd_span"></span>
                        <input class="editPwd" value="" id="new-pwd" type="password">
                    </li>
                </ul>
                <ul class="infos">
                    <li>确认密码</li>
                    <li class="right_info">
                        <span class="editPwd" id="re_new_pwd_span"></span>
                        <input class="editPwd" value="" id="re-new-pwd" type="password">
                    </li>
                </ul>
            </div>
            <div id="base_info">
                <h2>基本信息 <span id="edit_info">编辑</span><span id="save_info">保存</span></h2>
                <ul class="infos">
                    <li>手机</li>
                    <li class="right_info">
                        <span class="baseinfo" id="phone_span">${frontUser.mobile!""}</span>
                        <input class="baseinfo" value="${frontUser.mobile!""}" id="mobile" type="text">
                    </li>
                </ul>
                <ul class="infos">
                    <li>地址</li>
                    <li class="right_info">
                        <span class="baseinfo" id="qq_span">${frontUser.address!""}</span>
                        <input class="baseinfo" value="${frontUser.address!""}" id="qq" type="text">
                    </li>
                </ul>
                <ul class="infos">
                    <li>邮箱</li>
                    <li class="right_info">
                        <span class="baseinfo" id="college_span">${frontUser.email!""}</span>
                        <input class="baseinfo" value="${frontUser.email!""}" id="academy" type="text">
                    </li>
                </ul>

            </div>
        </div>
        <ul id="middle_nav" class="clearfix">

		</ul>

    </div>
</div>
 	<#include "../common/right_menu.ftl"/>
	<#include "../common/bottom_footer.ftl"/> 
<script  src="/home/js/jquery-3.1.1.min.js"></script>
<#--<script src="/home/js/common.js"></script>-->
<script src="/home/js/user_center.js"></script>
<script src="/home/js/add.js"></script>
<script>

$(document).ready(function(){

	$("#edit_pwd_info").bind('click',function(){
        $(this).css({
            display: "none"
        })
        $("#save_pwd_info").css({
            display: "block"
        })
        $(".right_info span.editPwd").css({
            display: "none"
        })
        $(".right_info input.editPwd").css({
            display: "inline"
        })
    });

    //提交修改密码功能
    $("#save_pwd_info").bind('click',function(){
        var oldPwd = $("#old-pwd").val();
        var newPwd = $("#new-pwd").val();
        var reNewPwd = $("#re-new-pwd").val();
        if(oldPwd == ''){
        	alert('请填写原密码');
        	return ;
        }
        if(newPwd == ''){
        	alert('请填写新密码');
        	return ;
        }
        if(reNewPwd == ''){
        	alert('请再次填写新密码');
        	return ;
        }
        if(reNewPwd != newPwd){
        	alert('两次密码填写不一致');
        	return ;
        }
        if(newPwd.length <6 ){
        	alert('密码最少六位');
        	return ;
        }

        $.ajax({
            url:'/user/changePassword',
            type:'POST',
            data:{
                oldPassword:oldPwd,
                newPassword:newPwd,
                confirmPassword:reNewPwd,
            },
            success:function (data){
                if(data.code==0){
                    alert(data.msg)
                }
                else {
                    alert("修改失败！")
                }
            },error:function (data){
                alert("网络错误!")
            }

        });


        
    });
});
/*充值会员*/
$(".buybtn").click(function () {
    var month=$("#membertime option:selected").val();
    var type=$("#membertype option:selected").val();
    if(type == 0){
        alert("请选择会员类型！");
        return;
    }
    if(month == 0){
        alert("请选择充值时长！");
        return;
    }
    var temp;
    if(type == 1){
        temp=299;
    }else{
        temp=599;
    }
    var money=month*temp;


    $.ajax({
        url:'/uniqlo/order/generate_pay_log',
        type:'POST',
        data: {mounth:month,type:type,money:money},
        success:function (data){
            if(data.code==0){
                alert("即将跳转支付界面~");
                window.location.href = '/uniqlo/pay/pay_money?sn=' + data.data;
            }else{
                alert("失败")
            }


        },error:function (data){
            alert("网络错误!")
        }
    })

});


</script>
</body>
</html>