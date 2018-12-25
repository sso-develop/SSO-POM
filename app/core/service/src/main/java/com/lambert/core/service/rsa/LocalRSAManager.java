package com.lambert.core.service.rsa;

import com.lambert.common.uitl.RSAUtil;
import com.lambert.core.model.model.PublicRsaKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Auther: lambert
 * @Date: 2018/12/25 21:48
 * @Description:
 */
public class LocalRSAManager implements RsaManager {

    private static Logger LOGGER = LoggerFactory.getLogger(LocalRSAManager.class);

    private static final ConcurrentHashMap<String, RSAPrivateKey> rsaMap = new ConcurrentHashMap<String,RSAPrivateKey>();

    @Override
    public PublicRsaKey generateRSAKey(String key) {
        try{
            HashMap<String, Object> keys = RSAUtil.getKeys();
            RSAPublicKey publicKey = (RSAPublicKey) keys.get("public");
            RSAPrivateKey privateKey = (RSAPrivateKey) keys.get("private");
            rsaMap.put(key,privateKey);

            //公钥信息保存在页面，用于加密
            String publicKeyExponent = publicKey.getPublicExponent().toString(16);
            String publicKeyModulus = publicKey.getModulus().toString(16);
            return new PublicRsaKey(publicKeyExponent,publicKeyModulus);
        }catch (Exception e){
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    @Override
    public String checkRSAKey(String key,String obj) {
        try {
            RSAPrivateKey privateKey = rsaMap.get(key);
            rsaMap.remove(key);
            return RSAUtil.decryptByPrivateKey(obj, privateKey);
        }catch (Exception e){
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
        return null;
    }
}
