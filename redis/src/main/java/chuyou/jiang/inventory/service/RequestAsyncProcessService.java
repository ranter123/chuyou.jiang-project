package chuyou.jiang.inventory.service;

import chuyou.jiang.inventory.request.Request;

/**
 * @Author: ranter
 * @Date: 2020/10/7 5:17 下午
 * @Description:
 */
public interface RequestAsyncProcessService {


    public void process(Request request) throws InterruptedException;
}
