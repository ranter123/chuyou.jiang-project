package chuyou.jiang.hystrix.command;

import chuyou.jiang.hystrix.model.ProductInfo;
import chuyou.jiang.utils.HttpClientUtil;
import com.alibaba.fastjson.JSONObject;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

/**
 * @Author: ranter
 * @Date: 2020/10/10 10:45 下午
 * @Description:
 */
public class GetProductInfoCommand extends HystrixCommand<ProductInfo> {

    private Long productId;

    public GetProductInfoCommand(Long productId) {
        super(HystrixCommandGroupKey.Factory.asKey("GetProductInfoGroup"));
        this.productId = productId;
    }

    @Override
    protected ProductInfo run() throws Exception {
        String url = "http://127.0.0.1:8080/getProductInfo?productId=" + productId;
        String response = HttpClientUtil.doGet(url);
        return JSONObject.parseObject(response, ProductInfo.class);
    }

    @Override
    protected String getCacheKey() {
        return "product_info_" + productId;
    }

    @Override
    protected ProductInfo getFallback() {
        return super.getFallback();
    }
}
