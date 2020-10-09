package chuyou.jiang.cache.eshopcache.controller;

import chuyou.jiang.cache.eshopcache.model.ProductInfo;
import chuyou.jiang.cache.eshopcache.service.CacheService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: ranter
 * @Date: 2020/10/8 11:58 上午
 * @Description: 缓存controller
 */
@RestController
public class CacheController {

    @Resource
    private CacheService cacheService;

    @RequestMapping("/testPutCache")
    public String testPutCache(ProductInfo productInfo) {
        cacheService.saveLocalCache(productInfo);
        return "success";
    }

    @RequestMapping("/testGetCache")
    public ProductInfo testGetCache(Long id) {
        return cacheService.getLocalCache(id);
    }
}
