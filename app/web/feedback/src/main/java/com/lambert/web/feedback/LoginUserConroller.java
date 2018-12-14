package com.lambert.web.feedback;


import com.lambert.common.service.facade.model.LoginInfo;
import com.lambert.common.uitl.result.Result;
import com.lambert.core.service.token.TokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class LoginUserConroller extends BaseController{

    @Autowired
    private TokenManager tokenManager;

    @RequestMapping(value="/queryCurrentLoginUsers.json",method = RequestMethod.POST)
    public void queryCurrentLoginUsers(HttpServletResponse response,String backUrl, String appCode, HttpServletRequest request) {
        List<LoginInfo> list = tokenManager.getALLToken();
        Result<List<LoginInfo> > result = new Result<List<LoginInfo>>(list);
        writeSuccess2Response(response,result);
    }
}
