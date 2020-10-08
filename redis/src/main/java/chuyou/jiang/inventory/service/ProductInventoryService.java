package chuyou.jiang.inventory.service;

import chuyou.jiang.inventory.model.ProductInventory;
import org.springframework.stereotype.Service;

/**
 * @Author: ranter
 * @Date: 2020/10/7 4:45 下午
 * @Description: 商品库存Service接口
 */
@Service
public interface ProductInventoryService {

    /**
     * 更新商品库存
     * @param productInventory
     */
    void updateProductInventory(ProductInventory productInventory);

    /**
     * 删除商品库存缓存
     * @param productInventory
     */
    void removeProductInventoryCache(ProductInventory productInventory);

    /**
     * 查询商品库存
     * @param productId
     * @return
     */
    ProductInventory findProductInventory(Integer productId);

    /**
     * 设置商品库存到缓存
     * @param productInventory
     */
    void setProductInventoryCache(ProductInventory productInventory);

    /**
     * 获取商品库存的缓存
     * @param productId
     * @return
     */
    ProductInventory getProductInventoryCache(Integer productId);
}
