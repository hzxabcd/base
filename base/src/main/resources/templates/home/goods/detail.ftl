<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport"  content="width=device-width,user-scalable=yes,initial-scale=0.3,maximum-scale=0.3,shrink-to-fit=no" http-equiv="Content-Type">
<title>服装详情页</title>
<link rel="icon" href="/home/imgs/favicon.ico" type="image/x-icon">
<link media="all" href="/home/css/product_detail.css" type="text/css" rel="stylesheet">
<link media="all" href="/home/css/index.css" type="text/css" rel="stylesheet">
<link href="/admin/css/bootstrap.min.css" rel="stylesheet">
<link href="/admin/css/materialdesignicons.min.css" rel="stylesheet">
<link href="/admin/css/style.min.css" rel="stylesheet">

</head>

<body>
<#include "../common/top_header.ftl"/>
<#--  <#include "../common/left_menu.ftl"/>&ndash;&gt;-->
   <div class="container">
        <div class="main center clearfix">
            <div class="ershou-details">
                <div class="ershou-photos-wr">
	            		 <!-- 收藏功能
	                    <a class="ershou-favorite" href="javascript:void(0);" style="background-image: url('heart.png');" onclick="favorites();">0</a> -->
                       <!-- 轮播大图-->
                        <div class="bigger-photo-box">
                        	<a class="bigger-photo hide show" rel="img_group" href="">
                        		<img class="bigger" src="/photo/view?filename=${clothes.picture}"  alt="11">
                        	</a>
                        </div>
                </div>
                <div class="ershou-info">
                    <div class="ershou-hd">
                        <p class="ershou-title">${clothes.name}</p>
                        <div class=" discount">
                        	<span class="buy-price">原价：${clothes.price!""}</span>

                        </div>
                        <p class="bro-counts">浏览了<span>${clothes.view_num!""}</span>次</p>
                    </div>
                    <ul class="ershou-detail">
                    	<li class="ershou-place" id="pid">
                    		<div class="name">
                    			<span>商品编号</span>
                    		</div>
                    		<div class="value">
                    			<span id="pid">${clothes.id}</span>
                    		</div>
                        </li>
                        <li class="ershou-time">
                        	<div class="name">
                        		<span>上架时间</span>
                        	</div>
                        	<div class="value">
                        		<span class="real-time"id="creat_time">${clothes.createTime}</span>
                        	</div>
                        </li>
                        <li class="ershou-place">
                        	<div class="name">
                        		<span>具体描述</span>
                        	</div>
                        	<div class="value">${clothes.info}</div>
                        </li>
                        <li class="ershou-cert">
							<div class="name">
								<span>类型</span>
							</div>
							<div class="value">
								<span id="user_college">${clothes.classify.name}</span>

							</div>
						</li>
						<li class="ershou-cert">
							<div class="name">
								<span>品牌</span>
							</div>
							<div class="value">
								<span id="user_college">${clothes.brand.name}</span>

							</div>
						</li>

                    </ul>
					<div class="complain">
						<a href="javascript:void(0);" onclick="report(${clothes.id});">•&nbsp;&nbsp;&nbsp;&nbsp;举报&nbsp;&nbsp;&nbsp;&nbsp;•</a>
					</div>
					<div style="" id="buy-button" >
						<a style="color: white;cursor:pointer;" data-toggle="modal" data-target="#exampleModal" data-whatever="@mdo"> 立即下单</a>
					</div>
					<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
						<div class="modal-dialog" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
									<h4 class="modal-title" id="exampleModalLabel">提交订单</h4>
								</div>
								<div class="modal-body">

									<form action="add" id="order-add-form" method="post" class="row">
										<input type="hidden" name="clothes" id="clothes" value="${clothes.id}">
										<div class="input-group m-b-10">
											<span class="input-group-addon">收货人手机号</span>
											<input type="text" class="form-control required" id="phone" name="phone" value="${frontUser.mobile!""}" placeholder="请输入收货人手机号" tips="请填写收货人手机号" />
<#--											<input type="text" class="form-control required" id="phone" name="phone" value="15623338839" placeholder="请输入收货人手机号" tips="请填写收货人手机号" />-->
										</div>
										<div class="input-group m-b-10">
											<span class="input-group-addon" for="message-text">收货地址</span>
											<input type="text" class="form-control required" id="address" name="address" value="${frontUser.address!""}" placeholder="请输入收货地址" tips="请填写收货地址" />
<#--											<input type="password" class="form-control required" id="address" name="address" value="华中师范大学" placeholder="请输入收货地址" tips="请填写收货地址" />-->
										</div>

										<div class="input-group m-b-10">
											<span class="input-group-addon">租赁天数</span>
											<select name="days" class="form-control" id="days">
												<option value="1">1天</option>
												<option value="2">2天</option>
												<option value="3">3天</option>
												<option value="4">4天</option>
												<option value="5">5天</option>
												<option value="6">6天</option>
												<option value="7">7天</option>
												<option value="8">8天</option>
												<option value="9">9天</option>
												<option value="10">10天</option>
												<option value="11">11天</option>
												<option value="12">12天</option>
												<option value="13">13天</option>
												<option value="14">14天</option>
												<option value="15">15天</option>
											</select>
										</div>
										<div class="input-group m-b-10">
											<span class="input-group-addon">备注</span>
											<input type="email" class="form-control" id="remark" name="remark" value="" />
										</div>
										<div class="form-group col-md-12" type="hidden">
											<button type="button" class="btn btn-primary ajax-post" id="order-form-submit-btn">确 定</button>
										</div>
									</form>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
									<button type="button" class="btn btn-primary"  id="order—add-btn">提交订单</button>
								</div>
							</div>
						</div>
					</div>
                </div>
            </div>

        </div>
    </div>
<#include "../common/right_menu.ftl"/>
<#include "../common/bottom_footer.ftl"/>
<script  src="/home/js/jquery-3.1.1.min.js"></script>
<#--<script src="/home/js/common.js"></script>-->
<script src="/home/js/add.js"></script>
<script type="text/javascript" src="/admin/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/admin/js/perfect-scrollbar.min.js"></script>

<script type="text/javascript" src="/admin/js/main.min.js"></script>
<script type="text/javascript" src="/admin/js/common.js"></script>
<script type="text/javascript">
  $(document).ready(function(){
	  //提交按钮监听事件
	  $("#order—add-btn").click(function () {
		  $("#order-form-submit-btn").trigger('click');
	  });
	  $("#order-form-submit-btn").click(function(){

		  var clothesid = ${clothes.id};
		  var phone = $('#phone').val();
		  var address = $('#address').val();
		  var days = $("#days option:selected").val();
		  var remark = $('#remark').val();
		  console.log(clothesid);
		  $.ajax({
			  url:'/uniqlo/order/add',
			  type:'POST',
			  data:{
			  	"clothesid":clothesid,
				  "phone":phone,
				  "address":address,
				  "days":days,
				  "remark":remark
			  },
			  dataType:'json',
			  success:function(data){
				  if(data.code == 0){
					  alert("下单成功！！！");
					  // window.location.href = 'list';
					  window.location.reload();
				  }else{
					  alert(data.msg);
				  }
			  },
			  error:function(data){
				  alert('网络错误!');
			  }
		  });
	  });



  });
  </script>
</body>
</html>