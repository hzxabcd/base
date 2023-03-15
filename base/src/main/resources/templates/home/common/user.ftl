<style>
    .loginandrigist {
        margin-left: 1000px;
        margin-top: -66px;
    }

    #person_info {
        height: 100%;
        cursor: pointer;
        position: relative;
    }
    .clearfix {
        zoom: 1;
    }
</style>
<header class="ease2">
    <a href="/uniqlo/home">

        <img class="logo ease2" src="/home/imgs/index_logo.png" alt="优衣服装">

    </a>
    <div class="header-main center ease2">
        <a href="/uniqlo/home" class="slogan">
            <h1 class="s-main"></h1>
            <div class="s-submain"></div>

            <img src="/home/imgs/2shoujie_web_title.png" alt="优衣">

        </a>
        <div class="search-box-wr ease2">

            <div class=" center" style="height: 50px;width: 200px;font-size: 34px;color: #82d3bf;text-shadow: 2px 3px 1px rgba(130,211,191,0.61)">
                个人主页

            </div>

        </div>

        <div class="ease2 log-reg" id="have-not-login">
            <#if frontUser??>
                <div id="have_login" class="clearfix">
                    <div id="person_info" class="clearfix">
                        <a href="../student/index">
                            <#if frontUser.headPic??>
                                <img  class="avatar"  style="height:48px;width:48px;" src="/photo/view?filename=${frontUser.headPic}">
                            <#else>
                                <img  class="avatar"  style="height:48px;width:48px;" src="/home/imgs/avatar1.png">
                            </#if>
                        </a>
                        <div  style="display:inline;"  class="person_name">
                            <a href="../student/index" id="id-btn">Hi：${frontUser.userName}</a>
                        </div>
                        <div  style="display:inline;"  class="person_name">
                            <a href="/logout" id="log-btn">&nbsp;&nbsp;&nbsp;&nbsp;退出</a>
                        </div>
                    </div>
                </div>
            <#else>

            </#if>
        </div>
    </div>
</header>
<script type="text/javascript">

</script>