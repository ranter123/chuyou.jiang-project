package chuyou.jiang.inventory.service.impl;

import chuyou.jiang.inventory.dao.RedisDAO;
import chuyou.jiang.inventory.mapper.ProductInventoryMapper;
import chuyou.jiang.inventory.model.ProductInventory;
import chuyou.jiang.inventory.service.ProductInventoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: ranter
 * @Date: 2020/10/7 4:50 下午
 * @Description: 商品库存service 实现类
 */
@Service("productInventoryService")
public class ProductInventoryServiceImpl implements ProductInventoryService {

    @Resource
    private ProductInventoryMapper productInventoryMapper;

    @Resource
    private RedisDAO redisDAO;

    @Override
    public void updateProductInventory(ProductInventory productInventory) {
        productInventoryMapper.updateProductInventory(productInventory);
    }

    @Override
    public void removeProductInventoryCache(ProductInventory productInventory) {
        String key = "product:inventory:" + productInventory.getProductId();
        redisDAO.delete(key);
    }

    @Override
    public ProductInventory findProductInventory(Integer productId) {
        return productInventoryMapper.findProductInventory(productId);
    }

    @Override
    public void setProductInventoryCache(ProductInventory productInventory) {
        String key = "product:inventory:" + productInventory.getProductId();
        redisDAO.set(key, String.valueOf(productInventory.getInventoryCnt()));
        System.out.println("==============日志==============：已更新商品库存数量，商品ID=" + productInventory.getProductId() + "，商品库存为：" + productInventory.getInventoryCnt());
    }

    @Override
    public ProductInventory getProductInventoryCache(Integer productId) {
        Long inventoryCnt = 0L;
        String key = "product:inventory:" + productId;
        String value = redisDAO.get(key);
        if (value != null && !"".equals(value)) {
            try {
                inventoryCnt = Long.valueOf(value);
                return new ProductInventory(productId, inventoryCnt);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
