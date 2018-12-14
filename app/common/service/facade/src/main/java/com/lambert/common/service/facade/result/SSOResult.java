package com.lambert.common.service.facade.result;

import java.io.Serializable;

public class SSOResult<T> implements Serializable {

	 /** serialId */
	private static final long serialVersionUID = -3035296152778492506L;

	 /**
     * 返回的数据对�?
     */
    private T                 value;

    /**
     * 自定义错误信�?
     */
    private String            customErrMsg;
    /**
     *
     * 返回�?
     */
    private SSOResultCode resultCode;

    /**
     * 通过value和code进行构�??
     * @param value
     * @param resultCode
     */
    public SSOResult(T value, SSOResultCode resultCode) {
        super();
        this.value = value;
        this.resultCode = resultCode;
    }

    /**
     * 只用code进行构�??,value为null
     * @param resultCode
     */
    public SSOResult(SSOResultCode resultCode) {
        this(null, resultCode);
    }
    /**
     * 只用value进行构�??,code总是为success
     * @param valueObject
     */
    public SSOResult(T valueObject) {
        this(valueObject, SSOResultCode.SUCCESS);
    }

    /**
     *
     * @param resultCode
     * @param customErrMsg
     */
    public SSOResult(SSOResultCode resultCode, String customErrMsg) {
        this(null, resultCode);
        this.customErrMsg = customErrMsg;
    }
    /**
     * 获取数据
     *
     * @return
     */
    public T getData() {
        return value;
    }

    /**
     * 此方法不应该被调�?,此方法只是为了能够正常json序列�?,请走构�?�函�?
     *
     */
    public void setData(T value) {
        this.value = value;
    }

    /**
     * 获取返回code
     *
     * @return
     */
    public int getResultCode() {
        return resultCode.getCode();
    }

    /**
     * 此方法不应该被调�?.此方法只是为了能够正常json序列�?.请走构�?�函�?
     *
     */
    public void setResultCode(SSOResultCode resultCode) {
        this.resultCode = resultCode;
    }









    /**
     * 返回code.iscess
     *
     * @return
     */
    public boolean isSuccess() {
        return this.resultCode.isSuccess();
    }

    public String getMsg() {
        if (this.customErrMsg != null)
            return this.customErrMsg;
        return this.resultCode.getMessage();
    }


    public void setCustomErrMsg(String customErrMsg) {
        this.customErrMsg = customErrMsg;
    }


}
