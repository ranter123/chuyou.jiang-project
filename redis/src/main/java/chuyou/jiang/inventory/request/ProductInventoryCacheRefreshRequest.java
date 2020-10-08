package chuyou.jiang.inventory.request;

import chuyou.jiang.inventory.model.ProductInventory;
import chuyou.jiang.inventory.service.ProductInventoryService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: ranter
 * @Date: 2020/10/7 4:57 下午
 * @Description: 重新加载商品库存的缓存
 */
public class ProductInventoryCacheRefreshRequest implements Request{

    private Integer productId;

    private ProductInventoryService productInventoryService;

    private Boolean forceRefresh;

    public ProductInventoryCacheRefreshRequest(Integer productId, Boolean forceRefresh, ProductInventoryService productInventoryService) {
        this.productId = productId;
        this.forceRefresh = forceRefresh;
        this.productInventoryService = productInventoryService;
    }

    @Override
    public void process() {
        //从数据库中查询最新的商品库存数量
        ProductInventory productInventory = productInventoryService.findProductInventory(productId);
        System.out.println("=============日志=============：已查询到商品最新的库存数量，商品ID=" + productId + ", 商品库存数量=" + productInventory.getInventoryCnt());
        //将最新的商品库存数量刷新到redis中去
        productInventoryService.setProductInventoryCache(productInventory);

    }

    @Override
    public Integer getProductId() {
        return productId;
    }

    @Override
    public Boolean isForceRefresh() {
        return false;
    }

    public Boolean getForceRefresh() {
        return forceRefresh;
    }

    public void setForceRefresh(Boolean forceRefresh) {
        this.forceRefresh = forceRefresh;
    }
}
