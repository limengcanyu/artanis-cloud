package com.spring.cloud.common.util;

import com.alibaba.fastjson.JSON;
import com.spring.cloud.common.constant.JwtConst;
import com.spring.cloud.common.constant.TokenConst;
import com.spring.cloud.common.entity.TokenEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.impl.crypto.RsaProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Base64Utils;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;

@Slf4j
public class JwtRsaUtil {

    /**
     * 创建token
     *
     */
    public static String creatToken(TokenEntity tokenEntity) {
        Claims claims = Jwts.claims();
        claims.put(TokenConst.TENANT_ID, tokenEntity.getTenantId());
        claims.put(TokenConst.COMPANY_ID, tokenEntity.getCompanyId());
        claims.put(TokenConst.USER_ID, tokenEntity.getUserId());
        claims.put(TokenConst.LOGIN_UUID, tokenEntity.getLoginUUID());

        try {
            return Jwts.builder()
                    .setSubject("Rock")
                    .signWith(JwtRsaUtil.getRSAPrivateKey(JwtConst.PRIVATE_KEY))
                    .setClaims(claims)
                    .compact();
        } catch (NoSuchAlgorithmException e) {
            log.debug("create token error: ", e);
        }

        return null;
    }

    /**
     * 解析token
     *
     */
    public static Claims parseToken(String jwsString) {
        try {
            Jws<Claims> jws = Jwts.parserBuilder()
                    .setSigningKey(JwtRsaUtil.getRSAPublicKey(JwtConst.PUBLIC_KEY))
                    .build()
                    .parseClaimsJws(jwsString);
            return jws.getBody();
        } catch (JwtException | NoSuchAlgorithmException e) {
            log.debug("parse token error: ", e);
        }

        return null;
    }

    /**
     * 获取 RSAPublicKey
     *
     * @param pubKey base64加密公钥
     */
    public static RSAPublicKey getRSAPublicKey(String pubKey) throws NoSuchAlgorithmException {
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64Utils.decodeFromString(pubKey));
        RSAPublicKey publicKey = null;
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        try {
            publicKey = (RSAPublicKey) keyFactory.generatePublic(keySpec);
        } catch (Exception e) {
            log.error("get RSAPublicKey fail ,pubKey:[{}] ,err:[{}]", pubKey, e);
        }
        return publicKey;
    }

    /**
     * 获取 RSAPrivateKey
     *
     * @param priKey base64加密私钥
     */
    public static RSAPrivateKey getRSAPrivateKey(String priKey) throws NoSuchAlgorithmException {
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64Utils.decodeFromString(priKey));
        RSAPrivateKey privateKey = null;
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        try {
            privateKey = (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
        } catch (Exception e) {
            log.error("get RSAPrivateKey fail ,priKey:[{}], err:[{}]", priKey, e);
        }
        return privateKey;
    }

    /**
     * 生成base64加密后的公钥和私钥
     */
    public static void genRsaKeyPair() {
        SecureRandom random = new SecureRandom(JwtConst.SECRET_KEY.getBytes());
        KeyPair keyPair = RsaProvider.generateKeyPair(4096, random);

        byte[] publicKeyBytes = keyPair.getPublic().getEncoded();
        byte[] privateKeyBytes = keyPair.getPrivate().getEncoded();

        HashMap<String, String> keyMap = new HashMap<>();
        keyMap.put("publicKey", Base64Utils.encodeToString(publicKeyBytes));
        keyMap.put("privateKey", Base64Utils.encodeToString(privateKeyBytes));
        log.debug("keyMap: {}", JSON.toJSONString(keyMap));
    }

}
