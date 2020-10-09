package chuyou.jiang.cache.eshopcache.service.impl;

import chuyou.jiang.cache.eshopcache.model.ProductInfo;
import chuyou.jiang.cache.eshopcache.model.ShopInfo;
import chuyou.jiang.cache.eshopcache.service.CacheService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisCluster;

import javax.annotation.Resource;

/**
 * @Author: ranter
 * @Date: 2020/10/8 11:39 上午
 * @Description:
 */
@Service("cacheService")
public class CacheServiceImpl implements CacheService {

    private final String CACHE_NAME = "local";

    @Resource
    private JedisCluster jedisCluster;

    @Override
    @CachePut(value = CACHE_NAME, key = "'key_' + #productInfo.id")
    public ProductInfo saveLocalCache(ProductInfo productInfo) {
        return productInfo;
    }

    @Override
    @Cacheable(value = CACHE_NAME, key = "'key_' + #id")
    public ProductInfo getLocalCache(Long id) {
        return null;
    }

    @Override
    @CachePut(value = CACHE_NAME, key = "'product_info_' + #productInfo.id")
    public ProductInfo saveProductInfo2LocalCache(ProductInfo productInfo) {
        return productInfo;
    }

    @Override
    public void saveProductInfo2RedisCache(ProductInfo productInfo) {
        jedisCluster.set("product_info_" + productInfo.getId(), JSON.toJSONString(productInfo));
    }

    @Override
    public void saveShopInfo2RedisCache(ShopInfo shopInfo) {
        jedisCluster.set("shopInfo_info_" + shopInfo.getId(), JSON.toJSONString(shopInfo));
    }

    @Override
    @CachePut(value = CACHE_NAME, key = "'shop_info_' + #shopInfo.id")
    public ShopInfo saveShopInfo2LocalCache(ShopInfo shopInfo) {
        return shopInfo;
    }

    @Override
    @Cacheable(value = CACHE_NAME, key = "'product_info_' + #productId")
    public ProductInfo getProductInfo2LocalCache(Long productId) {
        return null;
    }

    @Override
    public ProductInfo getProductInfo2RedisCache(Long productId) {
        String productJson = jedisCluster.get("product_info_" + productId);
        ProductInfo productInfo = JSONObject.parseObject(productJson, ProductInfo.class);
        return productInfo;
    }

    @Override
    public ShopInfo getShopInfo2RedisCache(Long shopId) {
        String shopJson = jedisCluster.get("shop_info_" + shopId);
        ShopInfo shopInfo = JSONObject.parseObject(shopJson, ShopInfo.class);
        return shopInfo;
    }

    @Override
    @Cacheable(value = CACHE_NAME, key = "'shop_info_' + #shopId")
    public ShopInfo getShopInfo2LocalCache(Long shopId) {
        return null;
    }
}
