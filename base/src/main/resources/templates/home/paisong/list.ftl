<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta content="text/html; charset=UTF-8" http-equiv="Content-Type">
	<title>优衣会员时装共享平台</title>
	<link rel="icon" href="/home/imgs/favicon.ico" type="image/x-icon">
	<link media="all" href="/home/css/want_list.css" type="text/css" rel="stylesheet">
	<link media="all" href="/home/css/index.css" type="text/css" rel="stylesheet">

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
		<div class="want-title"></div>
		<div class="wrap-want-list">
			<ul class="want-list" id="want-list">
				<#if plist??>
					<#list plist?sort_by("id")?reverse as plt>
						<li class="want-item">
							<div class="want-li clearfix">
								<div class="left">
									<#if plt.statue == 1>
										<div class="div1">
											<input  class="btn1" type="button" name="pid" value="确认收货"
													style="background-color:#73e9c3;color:white;width: 70px;height: 40px;font-size: 10px;border-radius: 20px;">
											<text style="display: none">${plt.id}</text>
										</div>


									<#elseif plt.statue == 2>
										<div class="div2">
											<input  class="btn1" type="button" name="pid" value="去寄回"
													style="background-color:#dce92d;color:white;width: 70px;height: 40px;font-size: 10px;border-radius: 20px;">
											<text style="display: none">${plt.id}</text>
										</div>
									<#elseif plt.statue == 3>
										<div style="color: rgba(191,156,29,0.69); font-size: 17px; font-weight: bolder ;margin-left: 10px;padding-top: 30px">已寄出</div>
									<#elseif plt.statue == 4>
										<div style="color: rgba(191,14,12,0.62); font-size: 17px; font-weight: bolder ;margin-left: 10px;padding-top: 30px">待审核</div>
									<#else>
										<div style="color: rgba(135,191,119,0.62); font-size: 17px; font-weight: bolder ;margin-left: 10px;padding-top: 30px">已完成</div>
									</#if>


								</div>
								<div class="right">
									<h4 class="want-name">【服装名称】
										<span>${plt.name}</span>
									</h4>

									<p class="want-attr">
										<span>派送单状态</span>
										<#if plt.statue == 1>
											<span class="want-price">待收货</span>
										<#elseif plt.statue == 2>
											<span class="want-price">待寄回</span>
										<#elseif plt.statue == 3>
											<span class="want-price">已寄回，待管理员收货</span>
										<#elseif plt.statue == 4>
											<span class="want-price">待审核</span>
										<#else >
											<span class="want-price">已完成</span>
										</#if>
										<span>订单编号：</span>
										<span class="want-add">${plt.dingdan_no}</span>
										<span>派送单更新时间：</span>
										<span>${plt.update_time?datetime}</span>
									</p>
									<p class="want-contact">

										<span class="mr10">物流单号：${plt.wuliu_no!""}</span>

									</p>
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

<script  src="/home/js/jquery-3.1.1.min.js"></script>
<#--<script src="/home/js/common.js"></script>-->
<script src="/home/js/index.js"></script>


<script type="text/javascript">
	$(document).ready(function(){

	});

	/*确认收货*/
	$(".div1").click(function () {
		var id=$(this).children('text').html();


		var msg=confirm("确定已收到商品？");
		if(msg==true){
			upReq1(id);
		}else{
			alert("已取消操作")
		}

	})
	//调用更新方法
	function upReq1(id){
		$.ajax({
			url:"/uniqlo/upaisong/update",
			type:'POST',
			data:{id:id},
			dataType:'json',
			success:function(data){
				if(data.code == 0){
					alert("确认收货成功！")
					refresh();

				}else{

					alert(data.msg);
				}
			},
			error:function(data){
				alert('网络错误！');
			}
		});
	}

	/*确认寄回*/
	$(".div2").click(function () {
		var id=$(this).children('text').html();

		var wuliu_no=prompt("请输入物流订单编号");
		upReq2(id,wuliu_no);

	})
	//调用更新方法
	function upReq2(id,wuliu_no){
		$.ajax({
			url:"/uniqlo/upaisong/update2",
			type:'POST',
			data:{id:id,wuliu_no:wuliu_no},
			dataType:'json',
			success:function(data){
				if(data.code == 0){
					alert("您已成功寄出，等待管理员确认收货并审核！");
					refresh();

				}else{

					alert(data.msg);
				}
			},
			error:function(data){
				alert('网络错误！');
			}
		});
	}

	//1s后刷新页面
	function refresh() {
		window.location.reload();
		setTimeout(refresh,1000);
	}

</script>

</body>
</html>