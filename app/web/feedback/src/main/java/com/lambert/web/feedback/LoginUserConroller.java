package com.lambert.web.feedback;


import com.lambert.common.service.facade.model.LoginInfo;
import com.lambert.common.uitl.result.DefaultResult;
import com.lambert.common.uitl.result.DefaultWebUtils;
import com.lambert.common.uitl.result.ResultModel;
import com.lambert.core.service.token.TokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class LoginUserConroller {

    @Autowired
    private TokenManager tokenManager;

    @RequestMapping(value="/queryCurrentLoginUsers.json",method = RequestMethod.POST)
    public @ResponseBody
    ResultModel queryCurrentLoginUsers(String backUrl, String appCode, HttpServletRequest request, ResultModel resultModel) {
        List<LoginInfo> list = tokenManager.getALLToken();
        DefaultResult<List<LoginInfo> > result = new DefaultResult<List<LoginInfo>>(list);
        DefaultWebUtils.putResult2ModelMap(result, resultModel);
        return resultModel;
    }
}
