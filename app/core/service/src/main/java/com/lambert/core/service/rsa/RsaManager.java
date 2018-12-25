package com.lambert.core.service.rsa;

import com.lambert.core.model.model.PublicRsaKey;

import java.util.Map;

/**
 * @Auther: lambert
 * @Date: 2018/12/25 21:41
 * @Description:
 */
public interface RsaManager {



    /**
     *
     * @return
     */
    public PublicRsaKey generateRSAKey(String key);



    public String checkRSAKey(String key,String obj);

}
