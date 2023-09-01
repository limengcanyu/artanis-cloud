package com.spring.cloud.nacos.config.client;

import com.spring.cloud.nacos.config.client.service.ExampleUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * <p>Description: </p>
 *
 * @author rock.jxf
 * @date 2020/12/20 10:46
 */
@SpringBootTest
public class ExampleUserServiceTests {

    @Autowired
    private ExampleUserService exampleUserService;

    @Test
    void getOne() {
        System.out.println(exampleUserService.getById(1));
    }

}
