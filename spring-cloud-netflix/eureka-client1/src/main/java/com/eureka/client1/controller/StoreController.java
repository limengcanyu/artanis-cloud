package com.eureka.client1.controller;

import com.eureka.client1.entity.Store;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Description: </p>
 *
 * @author Rock Jiang
 * @version 1.0
 * @date 2019/3/4 0004
 */
@RestController
public class StoreController {
    private static final Logger logger = LoggerFactory.getLogger(StoreController.class);

    @GetMapping("/stores")
    public List<Store> getStores(){
        logger.debug("============ getStores ============");

        List<Store> storeList = new ArrayList<>();

        Store store = new Store();
        store.setId(1);
        store.setName("rock");
        storeList.add(store);

        store = new Store();
        store.setId(2);
        store.setName("alita");
        storeList.add(store);

        return storeList;
    }

    @PostMapping("/stores/{id}")
    public Store update(@PathVariable("id") Integer id, Store store){
        logger.debug("============ update ============");
        logger.debug("id: " + id + " store: " + store);

        return store;
    }
}
