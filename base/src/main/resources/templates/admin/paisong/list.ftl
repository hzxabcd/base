<!DOCTYPE html>
<html lang="zh">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" />
  <title>${siteName!""}|派送单管理-${title!""}</title>
  <#include "../common/header.ftl"/>
  <style>
    td{
      vertical-align:middle;
    }
    .disp{

      display: none;
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
                <form class="pull-right search-bar" method="post" action="/admin/paisong/list" role="form">
                  <div class="input-group">
                    <div class="input-group-btn">
                      <button class="btn btn-default dropdown-toggle" id="search-btn" data-toggle="dropdown" type="button" aria-haspopup="true" aria-expanded="false">
                        商品名 <span class="caret"></span>
                      </button>
                      <ul class="dropdown-menu">
                        <li> <a tabindex="-1" href="javascript:void(0)" data-field="title">商品名</a> </li>
                      </ul>
                    </div>
                    <#----> <input type="text" class="form-control" value="${sn!""}" name="withname" placeholder="请输入商品名">
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

                      <th>订单id</th>
                      <th>商品名称</th>
                      <th>创建时间</th>
                      <th>更新时间</th>
                      <th>订单编号</th>
                      <th>物流单号</th>
                      <th>派送状态</th>
                    </tr>
                    </thead>
                    <tbody class="tb">

                    <#--list开始-->
                    <#if paisongList?size gt 0>
                      <#list paisongList?sort_by("id")?reverse as psl>
                      <#--<#if psl_index==10>
                        <#break>

                      </#if>-->
                        <tr  class="hid" >
                          <td style="vertical-align:middle;">
                            <label class="lyear-checkbox checkbox-primary">
                              <input type="checkbox" name="ids[]" value="${psl.id}"><span></span>
                            </label>
                          </td>
                          <td style="vertical-align:middle;">${psl.dingdan_id}</td>
                          <td style="vertical-align:middle;">${psl.name}</td>
                          <td style="vertical-align:middle;">${psl.create_time?datetime}</td>
                          <td style="vertical-align:middle;">${psl.update_time?datetime}</td>
                          <td style="vertical-align:middle;">${psl.dingdan_no}</td>
                          <td style="vertical-align:middle;">${psl.wuliu_no}</td>
                          <td style="vertical-align:middle;">
                            <#if psl.statue == 1>
                              <font class="" style="color: #1b6d85">用户待收货</font>
                            <#elseif psl.statue == 2>
                              <font class="" style="color: #ffdf80">用户待寄回</font>
                            <#elseif psl.statue == 3>
                              <font class="text-info" color="red">管理员待收货</font>
                            <#elseif psl.statue == 4>
                              <font class="" style="color:red">待审核</font>
                            <#else>
                              <font class="text-success">已完成</font>
                            </#if>
                          </td>
                        </tr>
                      </#list>
                    <#--list结束-->
                    </#if>
                    </tbody>
                  </table>
                </div>

                <#if pageBean.total gt 0>
                  <ul class="pagination">
                    <#if pageBean.currentPage == 1>
                      <li class="disabled"><span>«</span></li>
                    <#else>
                      <li><a href="#"}&currentPage=1">«</a></li>
                    </#if>
                    <#list pageBean.currentShowPage as showPage>
                      <li class="btn1"><a href="#">${showPage}</a></li>
                    <#--<#if pageBean.currentPage == showPage>
                      <li class="active"><span>${showPage}</span></li>
                    <#else>
                      <li><a href="#">${showPage}</a></li>
                    </#if>-->
                    </#list>
                    <#if pageBean.currentPage == pageBean.totalPage>
                      <li class="disabled"><span>»</span></li>
                    <#else>
                      <li><a href="#">»</a></li>
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
<#include "../common/footer.ftl"/>
<script type="text/javascript" src="/admin/js/perfect-scrollbar.min.js"></script>
<script type="text/javascript" src="/admin/js/main.min.js"></script>
<script type="text/javascript">
  $(document).ready(function(){
    /*分页*/
    $(".pagination").children("li").eq(1).addClass("active");
    $(".hid").addClass("disp");
    for (var i = 0; i < 10; i++) {
      $(".tb").children("tr").eq(i).removeClass("disp");
    }
  });

  function up(){
    $(".pagination").children("li").eq(1).addClass("active");
    $(".hid").addClass("disp");
    for (var i = 0; i < 10; i++) {
      $(".tb").children("tr").eq(i).removeClass("disp");
    }
  }

  //点击切换
  $(".btn1").click(function () {
    $(".btn1").removeClass("active");
    $(".hid").addClass("disp");

    $(this).addClass("active");
    var ind=1+1;
    ind=$(this).children().text();
    ind=ind*10;
    for (var i = 0; i <10 ; i++) {
      ind--;
      $(".tb").children("tr").eq(ind).removeClass("disp");
    }

  })


  function update(url){

    if($("input[type='checkbox']:checked").length != 1){
      alert('请选择一条数据进行更新！');
      return;
    }
    var id = $("input[type='checkbox']:checked").val();

    $.confirm({
      title: '确定更新？',
      content: '更新后派送单状态将改变！',
      buttons: {
        confirm: {
          text: '确认',
          action: function(){
            upReq(id,url);
          }
        },
        cancel: {
          text: '关闭',
          action: function(){

          }
        }
      }
    });
  }

  //1s后刷新页面
  function refresh() {
    window.location.reload();
    setTimeout(refresh,1000);
  }

  //调用更新方法
  function upReq(id,url){
    $.ajax({
      url:url,
      type:'POST',
      data:{id:id},
      dataType:'json',
      success:function(data){
        if(data.code == 0){
          showSuccessMsg('派送单状态更新成功!',function(){
            //$("input[type='checkbox']:checked").parents("tr").remove();
            refresh();
          })
        }else{
          if(data.code==-1){
            alert('该状态只有用户可以操作！');
          }else {

            alert('请前往审核界面进行审核！');
          }

          //showErrorMsg(data.msg);
        }
      },
      error:function(data){
        alert('网络错误！');
      }
    });
  }
  //删除
  function del(url){
    if($("input[type='checkbox']:checked").length != 1){
      showWarningMsg('请选择一条数据进行删除！');
      return;
    }
    var id = $("input[type='checkbox']:checked").val();
    $.confirm({
      title: '确定删除？',
      content: '删除后数据不可恢复，请慎重！',
      buttons: {
        confirm: {
          text: '确认',
          action: function(){
            deleteReq(id,url);
          }
        },
        cancel: {
          text: '关闭',
          action: function(){

          }
        }
      }
    });
  }

  //调用删除方法
  function deleteReq(id,url){
    $.ajax({
      url:url,
      type:'POST',
      data:{id:id},
      dataType:'json',
      success:function(data){
        if(data.code == 0){
          showSuccessMsg('派送单删除成功!',function(){
            $("input[type='checkbox']:checked").parents("tr").remove();
          })
        }else{
          //showErrorMsg(data.msg);
          alert('未完成的订单不允许删除');
        }
      },
      error:function(data){
        alert('网络错误!');
      }
    });
  }

</script>
</body>
</html>