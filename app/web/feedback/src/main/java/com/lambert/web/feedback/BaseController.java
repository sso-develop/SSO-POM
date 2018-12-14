package com.lambert.web.feedback;

import com.alibaba.fastjson.JSON;
import com.lambert.common.uitl.ResponseUtil;

import javax.servlet.http.HttpServletResponse;

/**
 * @Auther: lambert
 * @Date: 2018/12/11 20:36
 * @Description:
 */
public class BaseController {

    public void writeSuccess2Response(HttpServletResponse response , String result){
        ResponseUtil.writeSuccess2String(response,result);
    }

    public void writeSuccess2Response(HttpServletResponse response , Object result){
        ResponseUtil.writeSuccess2String(response,JSON.toJSONString(result));
    }


}
