package com.lambert.common.uitl.result;

import java.io.Serializable;

public class ResultCode implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 481855398964178212L;

	/**
     * 返回码
     */
    private int code = RESULT_CODE_SUCCESS_DEFAULT;
    public int getCode() {
        return code;
    }

    /**
     * 获取返回消息体
     *
     * @return
     */
    public String getMessage() {
        return message;
    }

    /**
     * 消息体
     */
    private String message = RESULT_REASON_SUCCESS_DEFAULT;

    private static final int RESULT_CODE_SUCCESS_DEFAULT = 0;
    private static final String RESULT_REASON_SUCCESS_DEFAULT = "success";

    private static final int RESULT_CODE_ILLEGAL_PARAMS_DEFAULT = -1;//参数错误
    private static final int RESULT_CODE_SERVER_EXCEPTION = -2;
    private static final int RESULT_CODE_STATUS_ERROR = -3;
    //需要登录
    private static final int RESULT_CODE_LOGIN_REQUIRED = -4;

    //权限不足
    private static final int RESULT_CODE_ACCESS_DENIE = -5;
    //数据重复
    private static final int RESULT_CODE_DUPLICATE_DATA = -6;
    //数据重复
    private static final int RESULT_CODE_NOT_FOUND_DATA = -7;
    //数据重复
    private static final int RESULT_CODE_CREAT_PROCESS_FAILE = -8;

    //权限不足,并且需要页面重定向到没有权限的页面
    public static final int RESULT_CODE_DENIE_REDIRECT = -10101;
    /**
     * success
     */
    public static final ResultCode SUCCESS = new ResultCode(RESULT_CODE_SUCCESS_DEFAULT, RESULT_REASON_SUCCESS_DEFAULT);
    /**
     * 参数不对
     */
    public static final ResultCode ILLEGAL_PARAMS = new ResultCode(RESULT_CODE_ILLEGAL_PARAMS_DEFAULT, "参数错误");
       /**
        * 后端异常
        */
    public static final ResultCode SERVER_EXCEPTION = new ResultCode(RESULT_CODE_SERVER_EXCEPTION,"服务端异常");

    /**
     * 状态异常
     */
    public static final ResultCode SATTUS_ERROR = new ResultCode(RESULT_CODE_STATUS_ERROR, "状态异常");

    /**
     * 需要登录
     */
    public static final ResultCode LOGIN_REQIRE = new ResultCode(RESULT_CODE_LOGIN_REQUIRED, "需要登录");

    /**
     * 权限不足
     */
    public static final ResultCode ACCESS_DENIE = new ResultCode(RESULT_CODE_ACCESS_DENIE, "权限不足,禁止访问");
    /**
     * 数据已经存在
     */
    public static final ResultCode DUPLICATE_DATA = new ResultCode(RESULT_CODE_DUPLICATE_DATA, "数据已经存在，保存失败");
    /**
     * 数据已经存在
     */
    public static final ResultCode CREAT_PROCESS_FAILE = new ResultCode(RESULT_CODE_CREAT_PROCESS_FAILE, "流程创建失败");
    /**
     *
     * @param code
     * @param message
     */
    public ResultCode(int code, String message) {
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
    public ResultCode clone(String message){
        return new ResultCode(this.code, message);
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
     * 为了前端json序列化,此方法不应该被直接调用
     *
     */
    @Deprecated
    public void setCode(int code) {
        this.code = code;
    }

    /**
     *
     * 为了前端Json序列化,此方法不应该直接被调用
     */
    @Deprecated
    public void setMessage(String message) {
        this.message = message;
    }

}
