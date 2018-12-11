package com.lambert.common.uitl;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Auther: lambert
 * @Date: 2018/12/11 20:23
 * @Description:
 */
public class ResponseUtil {

    private static  final String  CONTENT_TYPE = "application/json;charset=utf-8";

    public static void writeSuccess2String(HttpServletResponse response , String result){
        response.setContentType(CONTENT_TYPE);
        try {
            response.getOutputStream().write(result.getBytes());
            response.getOutputStream().flush();
            response.getOutputStream().close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public  static  void writeFail2String(HttpServletResponse response,Exception e){

    }
}
