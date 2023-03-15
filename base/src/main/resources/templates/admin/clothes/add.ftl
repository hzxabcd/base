<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" />
    <title>${siteName!""}|服装管理-添加服装</title>
    <#include "../common/header.ftl"/>

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
                            <div class="card-header"><h4>添加服装</h4></div>
                            <div class="card-body">
                                <form action="add" id="clothes-add-form" method="post" class="row">
                                    <div class="form-group col-md-12">
                                        <label>照片上传</label>
                                        <div class="form-controls">
                                            <ul class="list-inline clearfix lyear-uploads-pic">
                                                <li class="col-xs-4 col-sm-3 col-md-2">
                                                    <figure>
                                                        <img src="/admin/images/default_clothes_pic.jpg" id="show-picture-img" alt="默认服装照片">
                                                    </figure>
                                                </li>
                                                <input type="hidden" name="picture" id="picture">
                                                <input type="file" id="select-file" style="display:none;" onchange="upload('show-picture-img','picture')">
                                                <li class="col-xs-4 col-sm-3 col-md-2">
                                                    <a class="pic-add" id="add-pic-btn" href="javascript:void(0)" title="点击上传"></a>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                    <div class="input-group m-b-10">
                                        <span class="input-group-addon">服装名称</span>
                                        <input type="text" class="form-control required" id="name" name="name" value="" placeholder="请输入服装名称" tips="请填写服装名称" />
                                    </div>
                                    <div class="input-group m-b-10">
                                        <span class="input-group-addon">服装信息</span>
                                        <input type="text" class="form-control required" id="info" name="info" value="" placeholder="请输入服装信息，如尺寸，颜色，肩宽，胸围等" tips="请填写服装信息，如尺寸，颜色，肩宽，胸围等" />
                                    </div>
                                    <div class="input-group m-b-10">
                                        <span class="input-group-addon">服装价格</span>
                                        <input type="text" class="form-control required" id="price" name="price" value="" placeholder="请输入服装价格" tips="请填写服装价格" />
                                    </div>
                                    <div class="input-group m-b-10">
                                        <span class="input-group-addon">服装品牌</span>
                                        <select name="brand" class="form-control required" id="brand">
                                            <#list brands as brand>
                                                <option value="${brand.getId()}">${brand.getName()}</option>
                                            </#list>
                                        </select>
                                    </div>
                                    <div class="input-group m-b-10">
                                        <span class="input-group-addon">服装类型</span>
                                        <select name="type" class="form-control required" id="type">
                                            <option value="1">常服</option>
                                            <option value="2">礼服</option>
                                        </select>
                                    </div>
                                    <div class="input-group m-b-10">
                                        <span class="input-group-addon">服装分类</span>
                                        <select name="classify" class="form-control required" id="class">
                                            <#list classes as class>
                                                <option value="${class.getId()}">${class.getName()}</option>
                                            </#list>
                                        </select>
                                    </div>
                                    <div class="input-group" style="margin-top:15px;margin-bottom:15px;padding-left:25px;">
                                        推荐：
                                        <label class="lyear-radio radio-inline radio-primary" style="margin-left:30px;">
                                            <input type="radio" name="recommend" value="1" checked="" >
                                            <span>推荐</span>
                                        </label>
                                        <label class="lyear-radio radio-inline radio-primary">
                                            <input type="radio" name="recommend" value="0">
                                            <span>不推荐</span>
                                        </label>
                                    </div>
                                    <div class="input-group" style="margin-top:15px;margin-bottom:15px;padding-left:25px;">
                                        状态：
                                        <label class="lyear-radio radio-inline radio-primary" style="margin-left:30px;">
                                            <input type="radio" name="status" value="1" checked="">
                                            <span>待租赁</span>
                                        </label>
                                        <label class="lyear-radio radio-inline radio-primary">
                                            <input type="radio" name="status" value="2">
                                            <span>已租赁</span>
                                        </label>
                                        <label class="lyear-radio radio-inline radio-primary">
                                            <input type="radio" name="status" value="3">
                                            <span>已下架</span>
                                        </label>
                                        <label class="lyear-radio radio-inline radio-primary">
                                            <input type="radio" name="status" value="4">
                                            <span>异常</span>
                                        </label>
                                    </div>
                                    <div class="form-group col-md-12">
                                        <button type="button" class="btn btn-primary ajax-post" id="add-form-submit-btn">确 定</button>
                                        <button type="button" class="btn btn-default" onclick="javascript:history.back(-1);return false;">返 回</button>
                                    </div>
                                </form>

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
        //提交按钮监听事件
        $("#add-form-submit-btn").click(function(){
            if(!checkForm("clothes-add-form")){
                return;
            }
            var data = $("#clothes-add-form").serialize();
            $.ajax({
                url:'add',
                type:'POST',
                data:data,
                dataType:'json',
                success:function(data){
                    if(data.code == 0){
                        showSuccessMsg('服装添加成功!',function(){
                            window.location.href = 'list';
                        })
                    }else{
                        showErrorMsg(data.msg);
                    }
                },
                error:function(data){
                    alert('网络错误!');
                }
            });
        });
        //监听上传图片按钮
        $("#add-pic-btn").click(function(){
            $("#select-file").click();
        });
    });

</script>
</body>
</html>