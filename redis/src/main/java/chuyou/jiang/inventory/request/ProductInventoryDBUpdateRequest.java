package chuyou.jiang.inventory.request;


import chuyou.jiang.inventory.model.ProductInventory;
import chuyou.jiang.inventory.service.ProductInventoryService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

/**
 * @Author: ranter
 * @Date: 2020/10/7 3:53 下午
 * @Description: 数据更新请求
 * 1、删除缓存
 * 2、更新数据库
 */
public class ProductInventoryDBUpdateRequest implements Request {

    private ProductInventory productInventory;

    private ProductInventoryService productInventoryService;

    public ProductInventoryDBUpdateRequest(ProductInventory productInventory, ProductInventoryService productInventoryService) {
        this.productInventory = productInventory;
        this.productInventoryService = productInventoryService;
    }

    @Override
    public void process() {
        System.out.println("==============日志==============：数据库更新请求开始执行：商品ID = " + productInventory.getProductId() + ", 商品库存数量 = " + productInventory.getInventoryCnt());
        //删除redis中的缓存
        productInventoryService.removeProductInventoryCache(productInventory);
        System.out.println("==============日志==============：已删除商品ID = " + productInventory.getProductId() + "缓存");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //更新数据库
        productInventoryService.updateProductInventory(productInventory);
        System.out.println("==============日志==============：已修改商品ID = " + productInventory.getProductId() + "的库存");
    }

    @Override
    public Integer getProductId() {
        return productInventory.getProductId();
    }

    @Override
    public Boolean isForceRefresh() {
        return false;
    }
}
