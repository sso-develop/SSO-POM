package com.lambert.common.uitl.result;

import java.io.Serializable;

public class Result<T> implements Serializable {

	 /** serialId */
	private static final long serialVersionUID = -3035296152778492506L;



	 /**
     * 返回的数据对象
     */
    private T                 value;

    /**
     * 自定义错误信息
     */
    private String            customErrMsg;

    /**
     * 获取数据
     *
     * @return
     */
    public T getData() {
        return value;
    }

    /**
     * 此方法不应该被调用,此方法只是为了能够正常json序列化,请走构造函数
     *
     */
    @Deprecated
    public void setData(T value) {
        this.value = value;
    }

    /**
     * 获取返回码
     *
     * @return
     */
    public int getResultCode() {
        return resultCode.getCode();
    }

    @Deprecated
    /**
     * 此方法不应该被调用.此方法只是为了能够正常json序列化.请走构造函数
     *
     */
    public void setResultCode(ResultCode resultCode) {
        this.resultCode = resultCode;
    }

    /**
     * 通过value和code进行构造
     * @param value
     * @param resultCode
     */
    public Result(T value, ResultCode resultCode) {
        super();
        this.value = value;
        this.resultCode = resultCode;
    }

    /**
     *
     * 返回码
     */
    private ResultCode resultCode;

    /**
     * 只用value进行构造,code总是为success
     * @param valueObject
     */
    public Result(T valueObject) {
        this(valueObject, ResultCode.SUCCESS);
    }

    /**
     * 只用code进行构造,value为null
     * @param resultCode
     */
    public Result(ResultCode resultCode) {
        this(null, resultCode);
    }

    public Result(ResultCode resultCode, String customErrMsg) {
        this(null, resultCode);
        this.customErrMsg = customErrMsg;
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
