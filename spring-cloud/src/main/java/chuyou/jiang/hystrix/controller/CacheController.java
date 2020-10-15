package chuyou.jiang.hystrix.controller;

import chuyou.jiang.hystrix.command.GetProductInfoCommand;
import chuyou.jiang.hystrix.command.GetProductInfosCollapser;
import chuyou.jiang.hystrix.command.GetProductInfosCommand;
import chuyou.jiang.hystrix.model.Product;
import chuyou.jiang.hystrix.model.ProductInfo;
import chuyou.jiang.hystrix.service.UserService;
import chuyou.jiang.utils.HttpClientUtil;
import com.alibaba.fastjson.JSON;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixObservableCommand;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rx.Observable;
import rx.Observer;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @Author: ranter
 * @Date: 2020/10/10 4:58 下午
 * @Description:
 */
@RestController
public class CacheController {

    @Resource
    private UserService userService;

    @RequestMapping("/change/product")
    public String changeProduct(Long productId) {
        HystrixCommand<ProductInfo> getProductInfoCommand = new GetProductInfoCommand(productId);
        ProductInfo productInfo = getProductInfoCommand.execute();
//        Future<ProductInfo> queue = getProductInfoCommand.queue();
//        queue.get();
        System.out.println(JSON.toJSONString(productInfo));
        System.out.println(getProductInfoCommand.isResponseFromCache());
        return "success";
    }

    @GetMapping("/selectUser")
    public String selectUser() {
        Long startTime = System.currentTimeMillis();
        for (int i = 0; i < 400; i ++) {
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Product product = userService.findProductInfo();
        }
        Long endTime = System.currentTimeMillis();
        System.out.println("请求耗时: " + (endTime - startTime) + "ms");
        return "success";
    }

    @RequestMapping("/getProductInfos")
    public String getProductInfos(String productIds) {
        HystrixObservableCommand<ProductInfo> getProductInfosCommand = new GetProductInfosCommand(productIds.split(","));
        Observable<ProductInfo> observe = getProductInfosCommand.observe();

//        Observable<ProductInfo> observe = getProductInfosCommand.toObservable();

        ArrayList<ProductInfo> arrayList = new ArrayList<>();
//        CountDownLatch countDownLatch = new CountDownLatch(1);
        observe.subscribe(new Observer<ProductInfo>() {
            @Override
            public void onCompleted() {
//                countDownLatch.countDown();
                System.out.println(JSON.toJSONString(arrayList));
                System.out.println("获取完了所有的商品数据");
            }

            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();
            }

            @Override
            public void onNext(ProductInfo productInfo) {
                System.out.println(JSON.toJSONString(productInfo));
                arrayList.add(productInfo);
            }
        });
//        try {
//            countDownLatch.await();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return "success";
    }

    @RequestMapping("/getProductInfos2")
    public String getProductInfos2(String productIds) {
        List<Future<ProductInfo>> futures = new ArrayList<>();
        for (String productId : productIds.split(",")) {
            GetProductInfosCollapser getProductInfosCollapser = new GetProductInfosCollapser(Long.valueOf(productId));
            futures.add(getProductInfosCollapser.queue());
        }
        for (Future<ProductInfo> future : futures) {
            try {
                System.out.println(future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        return "success";
    }
}
