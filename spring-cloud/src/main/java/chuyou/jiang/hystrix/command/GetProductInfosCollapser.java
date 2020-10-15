package chuyou.jiang.hystrix.command;

import chuyou.jiang.hystrix.model.ProductInfo;
import chuyou.jiang.utils.HttpClientUtil;
import com.alibaba.fastjson.JSONArray;
import com.netflix.hystrix.*;

import java.util.Collection;
import java.util.List;

/**
 * @Author: ranter
 * @Date: 2020/10/12 3:17 下午
 * @Description:
 */
public class GetProductInfosCollapser extends HystrixCollapser<List<ProductInfo>, ProductInfo, Long> {

    private Long productId;

    public GetProductInfosCollapser(Long productId) {
        super(Setter.withCollapserKey(HystrixCollapserKey.Factory.asKey("GetProductInfosCollapser"))
                    .andCollapserPropertiesDefaults(HystrixCollapserProperties.Setter()
                    .withMaxRequestsInBatch(100)
                    .withTimerDelayInMilliseconds(20)));
        this.productId = productId;
    }

    @Override
    public Long getRequestArgument() {
        return productId;
    }

    @Override
    protected HystrixCommand<List<ProductInfo>> createCommand(Collection<CollapsedRequest<ProductInfo, Long>> collapsedRequests) {
        StringBuilder paramsBuilder = new StringBuilder("");
        for (CollapsedRequest<ProductInfo, Long>  request : collapsedRequests) {
            paramsBuilder.append(request.getArgument()).append(",");
        }
        String params = paramsBuilder.toString();
        params = params.substring(0, params.length() - 1);
        System.out.println("createCommand方法执行， params" + params);
        return new BatchCommand(collapsedRequests);
    }

    @Override
    protected void mapResponseToRequests(List<ProductInfo> batchResponse, Collection<CollapsedRequest<ProductInfo, Long>> collapsedRequests) {
        int count = 0;
        for (CollapsedRequest<ProductInfo, Long> request : collapsedRequests) {
            request.setResponse(batchResponse.get(count++));
        }
    }

    @Override
    protected String getCacheKey() {
        return "product_info_" + productId;
    }

    private static final class BatchCommand extends HystrixCommand<List<ProductInfo>> {

        public final Collection<CollapsedRequest<ProductInfo, Long>> collapsedRequests;

        public BatchCommand(Collection<CollapsedRequest<ProductInfo, Long>> collapsedRequests) {
            super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("ProductInfoService"))
                            .andCommandKey(HystrixCommandKey.Factory.asKey("GetProductInfosCollapserBatchCommand")));
            this.collapsedRequests = collapsedRequests;
        }

        @Override
        protected List<ProductInfo> run() throws Exception {
            //将一个批次内的商品ID给拼接在一起
            StringBuilder paramsBuilder = new StringBuilder("");
            for (CollapsedRequest<ProductInfo, Long>  request : collapsedRequests) {
                paramsBuilder.append(request.getArgument()).append(",");
            }
            String params = paramsBuilder.toString();
            params = params.substring(0, params.length() - 1);

            //将多个商品ID合并在一个batch内, 直接发送一次网络请求，获取到所有的结果。
            String url = "http://localhost:8080/getProductInfos?productIds=" + params;
            String response = HttpClientUtil.doGet(url);
            List<ProductInfo> productInfos = JSONArray.parseArray(response, ProductInfo.class);

            for (ProductInfo productInfo : productInfos) {
                System.out.println("BatchCommand内部， productInfo=" + productInfo);
            }
            return productInfos;
        }
    }
}
