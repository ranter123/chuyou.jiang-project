package chuyou.jiang.inventory.request;

/**
 * @Author: ranter
 * @Date: 2020/10/7 3:27 下午
 * @Description:
 */
public interface Request {

    public void process();

    Integer getProductId();

    Boolean isForceRefresh();
}
