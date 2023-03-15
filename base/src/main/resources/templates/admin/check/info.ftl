<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" />
    <title>${siteName!""}|审核管理-审核服装</title>
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
                            <div class="card-header"><h4>审核服装</h4></div>
                            <div class="card-body">
                                <form action="add" id="class-add-form" method="post" class="row">
                                    <input type="hidden" id="id" name="id" value="${check.getId()}">
                                    <div class="input-group" style="margin-top:15px;margin-bottom:15px;padding-left:25px;">
                                        服装状态：
                                        <label class="lyear-radio radio-inline radio-primary" style="margin-left:30px;">
                                            <input type="radio"  name="type" value="0" <#if check.type == 0>checked</#if>>
                                            <span>未知</span>
                                        </label>
                                        <label class="lyear-radio radio-inline radio-primary">
                                            <input type="radio" name="type" value="1" <#if check.type == 1>checked</#if>>
                                            <span>完好</span>
                                        </label>
                                        <label class="lyear-radio radio-inline radio-primary">
                                            <input type="radio" name="type" value="2" <#if check.type == 2>checked</#if>>
                                            <span>破损</span>
                                        </label>
                                    </div>
                                    <div class="input-group m-b-10">
                                        <span class="input-group-addon">赔偿金额：</span>
                                        <input type="text" class="form-control required" name="compensation" value="${check.order.compensation!""}" placeholder="请输入赔偿金额" tips="请填写赔偿金额" />
                                    </div>
                                    <div class="input-group" style="margin-top:15px;margin-bottom:15px;padding-left:25px;">
                                        审核状态：
                                        <label class="lyear-radio radio-inline radio-primary" style="margin-left:30px;">
                                            <input type="radio" name="status" value="0" <#if check.status == 0>checked</#if> >
                                            <span>未审核</span>
                                        </label>
                                        <label class="lyear-radio radio-inline radio-primary">
                                            <input type="radio" name="status" value="1" <#if check.status == 1>checked</#if>>
                                            <span>已审核</span>
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
            if(!checkForm("class-add-form")){
                return;
            }
            var id = $("#id").val();
            var type = $("input[name='type']:checked").val();
            var status = $("input[name='status']:checked").val();
            var compensation = $("input[name='compensation']").val();
            //alert("id="+id+" type="+type + " status="+status + "  compensation=" + compensation);
            $.ajax({
                url:'/admin/check/check_submit',
                type:'POST',
                data: {id:id, status:status, type:type, compensation:compensation},
                dataType:'json',
                success:function(data){
                    if(data.code == 0){
                        showSuccessMsg('审核成功!',function(){
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
    });

</script>
</body>
</html>