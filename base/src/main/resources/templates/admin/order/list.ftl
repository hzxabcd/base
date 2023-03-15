
<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" />
<title>${siteName!""}|订单管理-${title!""}</title>
<#include "../common/header.ftl"/>
<style>
td{
	vertical-align:middle;
}
</style>
</head>

<body>
<div class="lyear-layout-web">
  <div class="lyear-layout-container">
    <!--左侧导航-->
    <aside class="lyear-layout-sidebar">

      <!-- logo -->
      <div id="logo" class="sidebar-header">
        <a href="index.html"><img src="/admin/images/logo-sidebar.png" title="${siteName!""}" alt="${siteName!""}" /></a>
      </div>
      <div class="lyear-layout-sidebar-scroll">
        <#include "../common/left-menu.ftl"/>
      </div>

    </aside>
    <!--End 左侧导航-->

    <#include "../common/header-menu.ftl"/>

    <!--页面主要内容-->
    <main class="lyear-layout-content">

      <div class="container-fluid">

        <div class="row">
          <div class="col-lg-12">
            <div class="card">
              <div class="card-toolbar clearfix">
                <form class="pull-right search-bar" method="get" action="list" role="form">
                  <div class="input-group">
                    <div class="input-group-btn">
                      <button class="btn btn-default dropdown-toggle" id="search-btn" data-toggle="dropdown" type="button" aria-haspopup="true" aria-expanded="false">
                        <#if sn??>订单编号<#elseif phone??>配送手机号<#else>搜索条件</#if> <span class="caret"></span>
                      </button>
                      <ul class="dropdown-menu">
                        <li> <a tabindex="-1" href="javascript:void(0)" data-field="sn">订单编号</a> </li>
                        <li> <a tabindex="-1" href="javascript:void(0)" data-field="phone" >配送手机号</a> </li>
                      </ul>
                    </div>
                    <input type="text" class="form-control" value="${sn!phone!""}" id="search-value" name="<#if sn??>sn<#elseif phone??>phone</#if>" placeholder="请输入搜索内容">
                  	<span class="input-group-btn">
                      <button class="btn btn-primary" type="submit">搜索</button>
                    </span>
                  </div>
                </form>
                <#include "../common/third-menu.ftl"/>
              </div>
              <div class="card-body">

                <div class="table-responsive">
                  <table class="table table-bordered">
                    <thead>
                      <tr>
                        <th>
                          <label class="lyear-checkbox checkbox-primary">
                            <input type="checkbox" id="check-all"><span></span>
                          </label>
                        </th>
                        <th>订单编号</th>
                        <th>所属用户</th>
                        <th>配送手机号</th>
                        <th>订单状态</th>
                        <th>租赁天数</th>
                        <th>下单时间</th>
                      </tr>
                    </thead>
                    <tbody>
                    
                      <#if pageBean.content?size gt 0>
                      <#list pageBean.content as order>
                      <tr>
                        <td style="vertical-align:middle;">
                          <label class="lyear-checkbox checkbox-primary">
                            <input type="checkbox" name="ids[]" value="${order.id}" 
                             data-clothes-name="${order.clothes.name}" 
                             data-clothes-picture="${order.clothes.picture}" 
                             data-clothes-info="${order.clothes.info}" 
                             data-clothes-price="${order.clothes.price}" 
                             data-clothes-brand="${order.clothes.brand.name}" 
                             data-clothes-class="${order.clothes.classify.name}" 
                             data-clothes-sex  ="${order.clothes.classify.sex}" 
                             data-order-phone="${order.phone}" 
                             data-order-address="${order.address}" 
                             data-order-remark="${order.remark}" 
                             data-order-days="${order.days}"
                             data-status="${order.status}"
                             ><span></span>
                          </label>
                        </td>
                        <td style="vertical-align:middle;">
                        	${order.sn}
                        </td>
                        <td style="vertical-align:middle;">${order.frontuserid.userName!""}</td>
                        <td style="vertical-align:middle;">${order.phone!""}</td>
                        <td style="vertical-align:middle;">
                        	<#if order.status == -1>
                        	<font class="text-danger">异常订单</font>
                            <#elseif order.status == 0>
                              <font class="text-primary">待配送</font>
                        	<#elseif order.status == 1>
                        	<font class="text-primary" >配送中</font>
                            <#elseif order.status == 2>
                              <font class="text-primary" >待寄回/font>
                            <#elseif order.status == 3>
                              <font class="text-primary ">寄回中</font>
                            <#elseif order.status == 4>
                              <font class="text-primary" >待审核</font>
                            <#elseif order.status == 5>
                              <font class="text-" >已完成</font>
                            <#else>
                        	<font class="text-warning">已取消</font>
                        	</#if>
                        </td>
                        <td style="vertical-align:middle;">${order.days}</td>

                        <td style="vertical-align:middle;" style="vertical-align:middle;"><font class="text-success">${order.createTime}</font></td>
                      </tr>
                    </#list>
                    <#else>
                    <tr align="center"><td colspan="7">这里空空如也！</td></tr>
					</#if>
                    </tbody>
                  </table>
                </div>
                <#if pageBean.total gt 0>
                <ul class="pagination ">
                  <#if pageBean.currentPage == 1>
                  <li class="disabled"><span>«</span></li>
                  <#else>
                  <li><a href="list?<#if sn??>sn<#elseif phone??>phone</#if>=${sn!phone!""}&currentPage=1">«</a></li>
                  </#if>
                  <#list pageBean.currentShowPage as showPage>
                  <#if pageBean.currentPage == showPage>
                  <li class="active"><span>${showPage}</span></li>
                  <#else>
                  <li><a href="list?<#if sn??>sn<#elseif phone??>phone</#if>=${sn!phone!""}&currentPage=${showPage}">${showPage}</a></li>
                  </#if>
                  </#list>
                  <#if pageBean.currentPage == pageBean.totalPage>
                  <li class="disabled"><span>»</span></li>
                  <#else>
                  <li><a href="list?<#if sn??>sn<#elseif phone??>phone</#if>=${sn!phone!""}&currentPage=${pageBean.totalPage}">»</a></li>
                  </#if>
                  <li><span>共${pageBean.totalPage}页,${pageBean.total}条数据</span></li>
                </ul>
                </#if>
              </div>
            </div>
          </div>

        </div>

      </div>

    </main>
    <!--End 页面主要内容-->
  </div>
</div>
<div class="modal fade" tabindex="-1" role="dialog" aria-labelledby="gridSystemModalLabel" id="show-detail-modal">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="gridSystemModalLabel">查看订单详情</h4>
      </div>
      <div class="modal-body">
        <div class="row">
          <div class="col-md-12" style="">
          	<p id="clothes-title">服装名字</p>
          	<table class="table table-bordered">
                    <thead>
                      <tr>
                      <th rowspan="7"><img id="clothes-picture" src="/photo/view?filename=20200318/aaa.jpg" width="220" height="270"></th>
                      </tr>
                      <tr>
                        <th style="border-bottom:0px;" colspan="2" id="clothes-info">
                          	服装描述：
                        </th>
                        
                      </tr>
                      <tr>
                        <th style="border-bottom:0px;" id="clothes-price">
                          	服装价格：50元
                        </th>
                        <th style="border-bottom:0px;" id="clothes-brand">
                          	品牌
                        </th>
                      </tr>
                      <tr>
                        <th style="border-bottom:0px;" id="clothes-class">
                          	类别
                        </th>
                        <th style="border-bottom:0px;" id="order-mobile">
                          	收货手机号
                        </th>
                      </tr>
                      <tr>
                        <th style="border-bottom:0px;" colspan="2" id="order-address">
                          	收货地址
                        </th>
                        
                      </tr>
                      <tr>
                        <th style="border-bottom:0px;" colspan="2" id="order-remark">
                          	订单备注
                        </th>
                        
                      </tr>
                      
                    </thead>
                  </table>
          </div>
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
      </div>
    </div>
  </div>
</div>
<#include "../common/footer.ftl"/>
<script type="text/javascript" src="/admin/js/perfect-scrollbar.min.js"></script>
<script type="text/javascript" src="/admin/js/main.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
  $(".dropdown-menu li a").click(function(){
    $("#search-btn").html($(this).text() + '<span class="caret"></span>');
    $("#search-value").attr('name',$(this).attr('data-field'));
  });
});
//1s后刷新页面
function refresh() {
  window.location.reload();
  setTimeout(refresh,1000);
}

//打开查看详情页面
function view(url){
	var checked = $("input[type='checkbox']:checked");
	if(checked.length != 1){
		showWarningMsg('请选择一条数据进行查看！');
		return;
	}
	ajaxRequest('view_detail','post',{orderId:checked.val()},function(rst){
		
		$("#clothes-title").text(checked.attr('data-clothes-name'));
		$("#clothes-picture").attr('src','/photo/view?filename='+checked.attr('data-clothes-picture'));
		$("#clothes-info").text('服装详情：'+checked.attr('data-clothes-info'));
		$("#clothes-price").text('服装价格：'+checked.attr('data-clothes-price'));
		$("#clothes-brand").text('品牌：'+checked.attr('data-clothes-brand'));
		$("#clothes-class").text('类别：'+checked.attr('data-clothes-class'));
		$("#order-mobile").text('手机号：'+checked.attr('data-order-phone'));
		$("#order-address").text('派送地址：'+checked.attr('data-order-address'));
		$("#order-remark").text('备注：'+checked.attr('data-order-remark'));
		$("#clothes-sex").text('性别：'+checked.attr('data-clothes-sex'));
		$("#show-detail-modal").modal('show');
			
	});
}
function deliver(url){
  var checked = $("input[type='checkbox']:checked");
  if(checked.length != 1){
    showWarningMsg('请选择一条订单进行派送！');
    return;
  }
  if(checked.attr('data-status') != 0){
    showWarningMsg('该订单状态下不可进行派送！');
    return;
  }
  var wuliu_no=prompt("请输入物流订单编号");

  if(!!wuliu_no){
    $.ajax({
      url:"/admin/paisong/add_paisong",
      type:'POST',
      data:{orderId:checked.val(),wuliu_no:wuliu_no},
      dataType:'json',
      success:function(data){
        if(data.code == 0){
          alert("您已成功寄出！");
          refresh();

        }else{
          alert("寄出失败");
        }
      },
      error:function(data){
        alert('网络错误！');
      }
    });
  }

}
</script>
</body>
</html>