package com.eureka.client2.feign;

import com.alibaba.fastjson.JSON;
import com.eureka.client2.EurekaClient2Application;
import com.eureka.client2.entity.Store;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <p>Description: </p>
 *
 * @author Rock Jiang
 * @version 1.0
 * @date 2019/3/4 0004
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EurekaClient2Application.class)
public class StoreClientTest {
    @Autowired
    private StoreClient storeClient;

    @Test
    public void getStores() {
        System.out.println(storeClient.getStores());
    }

    @Test
    public void update() {
        Integer id = 100;

        Store store = new Store();
        store.setId(101);
        store.setName("alice");

        storeClient.update(id, store);
    }
}
