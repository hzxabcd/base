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

			<#--<img class="logo ease2" src="/photo/view?filename=${userinfo.headPic}!''" alt="11">-->
			


			<img class="logo ease2" src="/home/imgs/index_logo.png" alt="11">

	</a>
	<div class="header-main center ease2">
		<a href="/uniqlo/home" class="slogan">
			<h1 class="s-main"></h1>
			<div class="s-submain"></div>

				<img src="/home/imgs/2shoujie_web_title.png" alt="优衣">

		</a>
		<div class="search-box-wr ease2">
			<div class="search-box center" >
				<button class="search-submit" id="search-button" onclick="searchPro()">搜索</button>
				<div class="input-wr">
					<img class="search-icon" src="/home/imgs/search-icon.png">
					<div class="search-input">
						<input name="keyword" id="keyword" x-webkit-speech="" placeholder="搜索你想要的商品" value="" type="text" style="height: 28px">
					</div>
				</div>
			</div>

			<div class="search-hots center ease2">


			</div>
		</div>

		<div class="ease2 log-reg" id="have-not-login">

				<div id="have_login" class="clearfix">
					<div id="person_info" class="clearfix">
						<a href="#">

							<#if frontUser.headPic??>
								<img  class="avatar"  style="height:48px;width:48px;" src="/photo/view?filename=${frontUser.headPic}">
							<#else>
								<img  class="avatar"  style="height:48px;width:48px;" src="/home/imgs/avatar1.png">
							</#if>

						</a>
						<div  style="display:inline;"  class="person_name">
							<a href="/user/usercenter" id="id-btn">个人主页</a>
						</div>
						<div  style="display:inline;"  class="person_name">
							<a href="/logout" id="log-btn">&nbsp;&nbsp;&nbsp;&nbsp;退出</a>
						</div>
					</div>
				</div>

		</div>
	</div>
</header>
<script type="text/javascript">
	function searchPro(){
		var keyword=$("#keyword").val();
		window.location.href="/uniqlo/home?name="+keyword;
	}
</script>