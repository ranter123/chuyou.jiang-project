package chuyou.jiang.cache.eshopcache.service;

import chuyou.jiang.cache.eshopcache.model.ProductInfo;
import chuyou.jiang.cache.eshopcache.model.ShopInfo;

/**
 * @Author: ranter
 * @Date: 2020/10/8 11:38 上午
 * @Description:
 */
public interface CacheService {

    /**
     * 保存缓存到本地
     * @param productInfo
     * @return
     */
    public ProductInfo saveLocalCache(ProductInfo productInfo);

    /**
     * 从本地缓存中获取商品信息
     * @param id
     * @return
     */
    public ProductInfo getLocalCache(Long id);

    /**
     * 将商品信息保存在ehcache中
     * @param productInfo
     */
    public ProductInfo saveProductInfo2LocalCache(ProductInfo productInfo);

    /**
     * 将缓存保存在redis中
     * @param productInfo
     */
    public void saveProductInfo2RedisCache(ProductInfo productInfo);

    /**
     * 保存店铺信息到redis缓存中
     * @param shopInfo
     */
    public void saveShopInfo2RedisCache(ShopInfo shopInfo);

    /**
     * 将店铺信息保存到ehcache中
     * @param shopInfo
     */
    public ShopInfo saveShopInfo2LocalCache(ShopInfo shopInfo);

    /**
     * 从ehcache中获取商品信息
     * @param productId
     */
    public ProductInfo getProductInfo2LocalCache(Long productId);

    /**
     * 从redis中获取商品信息
     * @param productId
     */
    public ProductInfo getProductInfo2RedisCache(Long productId);

    /**
     * 从redis中获取商户信息
     * @param shopId
     */
    public ShopInfo getShopInfo2RedisCache(Long shopId);

    /**
     * 从本地缓存中获取商品信息
     * @param shopId
     */
    public ShopInfo getShopInfo2LocalCache(Long shopId);
}
