package cn.bugstack.middleware.test;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RBlockingQueue;
import org.redisson.api.RBucket;
import org.redisson.api.RDelayedQueue;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiTest {

    @Resource
    private RedissonClient redissonClient;

    @Test
    public void test() {
        // 设置字符串
        RBucket<String> keyObj = redissonClient.getBucket("user");
        keyObj.set("小傅哥");

        System.out.println(keyObj.get());
    }

    @Test
    public void test_delay_queue() throws InterruptedException {
        RBlockingQueue<Object> blockingQueue = redissonClient.getBlockingQueue("TASK");
        RDelayedQueue<Object> delayedQueue = redissonClient.getDelayedQueue(blockingQueue);

        new Thread(() -> {
            try {
                while (true){
                    Object take = blockingQueue.take();
                    System.out.println(take);
                    Thread.sleep(10);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        int i = 0;
        while (true){
            delayedQueue.offerAsync("测试" + ++i, 100L, TimeUnit.MILLISECONDS);
            Thread.sleep(1000L);
        }

    }

}
