package chuyou.jiang.inventory.mapper;

import chuyou.jiang.inventory.model.ProductInventory;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: ranter
 * @Date: 2020/10/7 4:06 下午
 * @Description: 库存数量Mapper
 */
public interface ProductInventoryMapper {

    /**
     * 更新产品库存数量
     */
    void updateProductInventory(ProductInventory productInventory);

    /**
     * 查询商品库存
     * @param productId
     * @return
     */
    ProductInventory findProductInventory(Integer productId);
}
