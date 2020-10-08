package chuyou.jiang.inventory.controller;

import chuyou.jiang.inventory.model.ProductInventory;
import chuyou.jiang.inventory.request.ProductInventoryCacheRefreshRequest;
import chuyou.jiang.inventory.request.ProductInventoryDBUpdateRequest;
import chuyou.jiang.inventory.request.Request;
import chuyou.jiang.inventory.service.ProductInventoryService;
import chuyou.jiang.inventory.service.RequestAsyncProcessService;
import chuyou.jiang.inventory.vo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: ranter
 * @Date: 2020/10/7 5:52 下午
 * @Description: 商品库存controller
 */
@RestController
public class ProductInventoryController {

    @Autowired
    private ProductInventoryService productInventoryService;

    @Resource
    private RequestAsyncProcessService requestAsyncProcessService;

    @RequestMapping("/updateProductInventory")
    public Response updateProductInventory(ProductInventory productInventory) {

        System.out.println("==============日志==============：接受到了更新商品库存请求，商品ID=" + productInventory.getProductId());

        Response response = null;

        try {
            Request request = new ProductInventoryDBUpdateRequest(productInventory, productInventoryService);
            requestAsyncProcessService.process(request);
            response = new Response(Response.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            response = new Response(Response.FAIL, e.getMessage());
        }
        return response;
    }

    @RequestMapping("/getProductInventory")
    public ProductInventory getProductInventory(Integer productId) {
        System.out.println("==============日志==============：接受到了商品库存读请求，商品ID=" + productId);
        Response response = null;
        try {
            Request request = new ProductInventoryCacheRefreshRequest(productId, false, productInventoryService);
            requestAsyncProcessService.process(request);
            //将请求扔给service异步处理之后，就需要while true 一会
            long startTime = System.currentTimeMillis();
            long endTime = 0L;
            long waitTime = 0L;
            while (true) {
                if (waitTime > 200L) {
                    break;
                }
                ProductInventory productInventory = productInventoryService.getProductInventoryCache(productId);
                //如果读取到了缓存
                if (productInventory != null) {
                    System.out.println("==============日志=============：在200ms内读取到了redis中库存缓存，商品ID=" + productInventory.getProductId() + ", 商品库存数量 = " + productInventory.getInventoryCnt());
                    return productInventory;
                } else {
                    //没有读取到缓存
                    Thread.sleep(10000);
                    endTime = System.currentTimeMillis();
                    waitTime = endTime - startTime;
                }
            }
            //如果200ms还没有读取到数据，那么直接从数据库里读取数据
            ProductInventory productInventory = productInventoryService.findProductInventory(productId);
            if (productInventory != null) {
                //刷新缓存
                Request request1 = new ProductInventoryCacheRefreshRequest(productId, true, productInventoryService);
                requestAsyncProcessService.process(request1);
                return productInventory;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ProductInventory(productId, -1L);
    }
}
