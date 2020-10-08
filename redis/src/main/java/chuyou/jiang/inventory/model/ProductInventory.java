package chuyou.jiang.inventory.model;

/**
 * @Author: ranter
 * @Date: 2020/10/7 4:13 下午
 * @Description:
 */
public class ProductInventory {

    /**
     *  商品id
     */
    private Integer productId;

    /**
     *  库存数量
     */
    private Long inventoryCnt;


    public ProductInventory() {
    }

    public ProductInventory(Integer productId, Long inventoryCnt) {
        this.productId = productId;
        this.inventoryCnt = inventoryCnt;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Long getInventoryCnt() {
        return inventoryCnt;
    }

    public void setInventoryCnt(Long inventoryCnt) {
        this.inventoryCnt = inventoryCnt;
    }
}
