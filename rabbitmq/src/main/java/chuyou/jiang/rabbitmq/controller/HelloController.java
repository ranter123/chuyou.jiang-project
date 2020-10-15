package chuyou.jiang.rabbitmq.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: ranter
 * @Date: 2020/10/15 6:36 下午
 * @Description:
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping("/world")
    public String helloWorld() {
        return "success";
    }
}
