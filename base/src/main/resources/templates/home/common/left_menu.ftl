<nav class="ease2">
    <ul>
        <li class="blank-head">
            <a href="/uniqlo/home"></a>
        </li>

        <li class="category-12 catg">
            <a href="/uniqlo/home" target="_top">
                <i class="nav-icons">
                    <img src="/home/imgs/12.png" alt="全部商品"></i>
                <h3>全部商品</h3>
            </a>
        </li>
        <#list goodsCategorys as goodsCategory>
            <li class="category-${goodsCategory.id} catg">
                <a href="/home/goods/list?cid=${goodsCategory.id}" target="_top">
                    <i class="nav-icons">
                        <img src="/home/imgs/8.png" alt="${goodsCategory.name}"></i>
                    <h3>${goodsCategory.name}</h3>
                </a>
                <#--            <div class="sub-nav">-->
                <#--                <span>-->
                <#--                    <#list goodsCategorys as secondGoodsCategory>-->
                <#--                    <#if secondGoodsCategory.parent??>-->
                <#--                    <#if secondGoodsCategory.parent.id == goodsCategory.id>-->
                <#--                    <a href="/home/goods/list?cid=${secondGoodsCategory.id}" target="_top">${secondGoodsCategory.name}</a>-->
                <#--                   	</#if>-->
                <#--                   	</#if>-->
                <#--                    </#list>-->
                <#--                </span>-->
                <#--            </div>-->
            </li>
        </#list>
    </ul>
</nav>