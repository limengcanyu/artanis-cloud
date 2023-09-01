package com.spring.cloud.common;

import com.alibaba.fastjson.JSON;
import com.spring.cloud.common.entity.TokenEntity;
import com.spring.cloud.common.util.JwtRsaUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;
import java.util.UUID;

@Slf4j
class JwtRsaUtilTest {

    @Test
    void creatJWS() {
        TokenEntity tokenEntity = TokenEntity.builder().tenantId("T000001").companyId("C000001").userId("U000001")
                .loginUUID(UUID.randomUUID().toString().replaceAll("-", "")).build();
        String jwsString = JwtRsaUtil.creatToken(tokenEntity);
        log.debug("jwsString: {}", jwsString);
    }

    @Test
    void readJWS() {
        String jwsString = "eyJhbGciOiJSUzUxMiJ9.eyJ0ZW5hbnRJZCI6IlQwMDAwMDEiLCJjb21wYW55SWQiOiJDMDAwMDAxIiwidXNlcklkIjoiVTAwMDAwMSIsImxvZ2luVVVJRCI6ImIyNDM5ZmYxYTU4NjQ4NGFhMWRjNjM0NTk2OTE4MDAyIn0.YRecaIrMc8VulzPoJvUhCRQDGRV8_E29MtlbMOJfsko_QNpuOv5ngT6dL75DpRY4-VFjrMw2_zEPcm70I5qOgbTz8Wi_L8uGpL3v0MNw4Y4RYeUxXspe-yYh_zpoYxTVEueBphcY5J6PUExe5uSvf51KIfNC7_cRT5kk_NUo-0IFYbpV3C_o0Q6noCQ3YYbO0k4uOSExR7hYa7L5b4usoxUlzABRY_nkYq2kipGShBofTn-aRdwoANQks16c7QPpoaTe3buOUlDkyNbBbWMbGZie-5Qh77blI7Qc1cFFcrifVeJoRgTBtTeYnqRjhbAY7wEO44MrBKXBUlaVxSFdcyV8iRgtbB4nVnjRkld7LMHgWqU4yPJjNnoNRst3XlyTvQ0nWRMl4AJkGEBExhk12mWxp8IdHeWaY0bLnNyoLq9JbwMXDSqqq3VfVnfrtX_hB4HLcFt5jFhvkc1kQUIy6KN0K6hc8O7N25P1wm3S8b6phULVOpiRaoJMzeI4Ws8cH_ul94ZyiFzWm5tT9uSF7gwvUYVNsYCzEF8nU-6qMsbjPROIgv2B97qgPDGwDuzcIvUC528mLhEi6MTJGg-PWbr4aDABs3jy-UC5WourHphhen5ekla30tw9vJarFB4SxOnt7xFT_x8FtMDfLmvzZ77pH86Vb2NSa3kvqCTx3_Y";

        Claims claims = JwtRsaUtil.parseToken(jwsString);
        log.debug("claims: {}", JSON.toJSONString(claims));
    }

}
