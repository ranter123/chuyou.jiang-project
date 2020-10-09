package chuyou.jiang.cache.eshopcache.kafka;

import chuyou.jiang.cache.eshopcache.model.ProductInfo;
import chuyou.jiang.cache.eshopcache.model.ShopInfo;
import chuyou.jiang.cache.eshopcache.service.CacheService;
import chuyou.jiang.cache.eshopcache.spring.SpringContext;
import com.alibaba.fastjson.JSONObject;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;

/**
 * @Author: ranter
 * @Date: 2020/10/8 2:58 下午
 * @Description:
 */
public class KafkaMessageProcessor implements Runnable {

    private KafkaStream kafkaStream;

    private CacheService cacheService;

    public KafkaMessageProcessor(KafkaStream stream) {
        this.kafkaStream = stream;
        this.cacheService = (CacheService) SpringContext.getApplicationContext().getBean("cacheService");
    }

    @Override
    public void run() {
        ConsumerIterator<byte[], byte[]> iterator = kafkaStream.iterator();
        while (iterator.hasNext()) {
            String message = new String(iterator.next().message());
            System.out.println("接受到的message" + message);
            //首先将message转为json对象
            JSONObject messageJson = JSONObject.parseObject(message);

            //从这里读取消息对应服务标识
            String serviceId = messageJson.getString("serviceId");

            //如果是商品服务
            if ("productInfoService".equals(serviceId)) {
                processProductInfoChangeMessage(messageJson);
            } else if ("shopInfoService".equals(serviceId)) {
                processShopInfoChangeMessage(messageJson);
            }
        }
    }

    /**
     * 处理商品信息变更消息
     * @param messageJson
     */
    private void processProductInfoChangeMessage(JSONObject messageJson) {
        //提取商品ID
        Long productId = messageJson.getLong("productId");
        //调用商品信息服务接口
        //查询商品信息
        String productJson = "{\"id\": 1, \"name\": \"iphone7手机\", \"price\": 5599, \"pictureList\":\"a.jpg,b.jpg\", \"specification\": \"iphone7的规格\", \"service\": \"iphone7的售后服务\", \"color\": \"红色,白色,黑色\", \"size\": \"5.5\", \"shopId\": 1}";
        ProductInfo productInfo = JSONObject.parseObject(productJson, ProductInfo.class);
        cacheService.saveProductInfo2LocalCache(productInfo);
        System.out.println("==========日志：获取本地商品信息");
        cacheService.saveProductInfo2RedisCache(productInfo);
        System.out.println("==========日志：获取Redis商品信息");
    }

    /**
     * 处理店铺信息变更消息
     * @param messageJson
     */
    private void processShopInfoChangeMessage(JSONObject messageJson) {
        //提取商品ID
        Long shopId = messageJson.getLong("shopId");
        //调用商品信息服务接口
        //查询商品信息
        String shopJson = "{\"id\": 1, \"name\": \"小王的手机店\", \"level\": 5, \"goodCommentRate\":0.99}";
        ShopInfo shopInfo = JSONObject.parseObject(shopJson, ShopInfo.class);
        cacheService.saveShopInfo2LocalCache(shopInfo);
        System.out.println("==========日志：获取本地商铺信息");
        cacheService.saveShopInfo2RedisCache(shopInfo);
        System.out.println("==========日志：获取Redis商铺信息");
    }
}
