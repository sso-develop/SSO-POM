package com.lambert.common.service.facade.result;

import java.io.Serializable;

public class SSOResultCode implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 481855398964178212L;

	/**
     * 返回�?
     */
    private int code = RESULT_CODE_SUCCESS_DEFAULT;
    public int getCode() {
        return code;
    }

    /**
     * 获取返回消息�?
     *
     * @return
     */
    public String getMessage() {
        return message;
    }

    /**
     * 消息�?
     */
    private String message = RESULT_REASON_SUCCESS_DEFAULT;

    private static final int RESULT_CODE_SUCCESS_DEFAULT = 0;
    private static final String RESULT_REASON_SUCCESS_DEFAULT = "success";

    private static final int RESULT_CODE_ILLEGAL_PARAMS_DEFAULT = -1;//参数错误
    private static final int RESULT_CODE_SERVER_EXCEPTION = -2;
    private static final int RESULT_CODE_STATUS_ERROR = -3;
    //�?要登�?
    private static final int RESULT_CODE_LOGIN_REQUIRED = -4;

    //权限不足
    private static final int RESULT_CODE_ACCESS_DENIE = -5;
    //数据重复
    private static final int RESULT_CODE_DUPLICATE_DATA = -6;
    //数据重复
    private static final int RESULT_CODE_NOT_FOUND_DATA = -7;
    //数据重复
    private static final int RESULT_CODE_CREAT_PROCESS_FAILE = -8;

    //权限不足,并且�?要页面重定向到没有权限的页面
    public static final int RESULT_CODE_DENIE_REDIRECT = -10101;
    /**
     * success
     */
    public static final SSOResultCode SUCCESS = new SSOResultCode(RESULT_CODE_SUCCESS_DEFAULT, RESULT_REASON_SUCCESS_DEFAULT);
    /**
     * 参数不对
     */
    public static final SSOResultCode ILLEGAL_PARAMS = new SSOResultCode(RESULT_CODE_ILLEGAL_PARAMS_DEFAULT, "参数错误");
       /**
        * 后端异常
        */
    public static final SSOResultCode SERVER_EXCEPTION = new SSOResultCode(RESULT_CODE_SERVER_EXCEPTION,"服务端异�?");

    /**
     * 状�?�异�?
     */
    public static final SSOResultCode SATTUS_ERROR = new SSOResultCode(RESULT_CODE_STATUS_ERROR, "状�?�异�?");

    /**
     * �?要登�?
     */
    public static final SSOResultCode LOGIN_REQIRE = new SSOResultCode(RESULT_CODE_LOGIN_REQUIRED, "�?要登�?");

    /**
     * 权限不足
     */
    public static final SSOResultCode ACCESS_DENIE = new SSOResultCode(RESULT_CODE_ACCESS_DENIE, "权限不足,禁止访问");
    /**
     * 数据已经存在
     */
    public static final SSOResultCode DUPLICATE_DATA = new SSOResultCode(RESULT_CODE_DUPLICATE_DATA, "数据已经存在，保存失�?");
    /**
     * 数据已经存在
     */
    public static final SSOResultCode CREAT_PROCESS_FAILE = new SSOResultCode(RESULT_CODE_CREAT_PROCESS_FAILE, "流程创建失败");
    /**
     *
     * @param code
     * @param message
     */
    public SSOResultCode(int code, String message) {
        super();
        this.code = code;
        this.message = message;
    }


    /**
     *
     *
     * @param message
     * @return
     */
    public SSOResultCode clone(String message){
        return new SSOResultCode(this.code, message);
    }

    /**
     * return code >=0
     *
     * @return
     */
    public boolean isSuccess(){
       return this.code >= 0;
   }


    /**
     *
     * 为了前端json序列�?,此方法不应该被直接调�?
     *
     */
    @Deprecated
    public void setCode(int code) {
        this.code = code;
    }

    /**
     *
     * 为了前端Json序列�?,此方法不应该直接被调�?
     */
    @Deprecated
    public void setMessage(String message) {
        this.message = message;
    }

}
