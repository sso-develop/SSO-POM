package com.lambert.web.feedback;

import com.lambert.core.model.model.PublicRsaKey;
import com.lambert.core.service.rsa.RsaManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;

/**
 * @Auther: lambert
 * @Date: 2018/12/25 21:29
 * @Description:
 */
@Controller
public class SecurityContrller extends BaseController{
    @Autowired
    private RsaManager rsaManager;

    @RequestMapping(value="/generateRSAKey.json",method = RequestMethod.POST)
    public void generateçKey(HttpServletResponse response,String username){
        PublicRsaKey publicRsaKey = rsaManager.generateRSAKey(username);
        writeSuccess2Response(response,publicRsaKey);
    }
}
