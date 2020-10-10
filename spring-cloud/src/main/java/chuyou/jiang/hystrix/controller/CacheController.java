package chuyou.jiang.hystrix.controller;

import chuyou.jiang.utils.HttpClientUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: ranter
 * @Date: 2020/10/10 4:58 下午
 * @Description:
 */
@RestController
public class CacheController {

    @RequestMapping("/change/product")
    public String changeProduct(Long productId) {
        String url = "http://127.0.0.1:8080/getProductInfo?productId=" + productId;
        String response = HttpClientUtil.doGet(url);
        System.out.println(response);
        return "success";
    }
}
