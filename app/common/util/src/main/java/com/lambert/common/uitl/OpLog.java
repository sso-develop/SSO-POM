package com.lambert.common.uitl;

public class OpLog {
	
	 public enum opLogEnum {
	        VISIT, MODIFY, CREATE, DROP, ACTION
	    }

	    /**
	     * 操作人
	     */
	    private String opStaffNo;
	    /**
	     * 操作方式
	     */
	    private String opType;
	    /**
	     * 操作对象类型
	     */
	    private String opTarget;

	    /**
	     * 详细信息
	     */
	    private String detail;

	    public OpLog(String opStaffNo, opLogEnum opType, String opTarget) {
	        this(opStaffNo, opType, opTarget, "");
	    }

	    public OpLog(String opStaffNo, opLogEnum opType, String opTarget, String detail) {
	        this.opStaffNo = opStaffNo;
	        this.opType = opType.name();
	        this.opTarget = opTarget;
	        this.detail = detail;
	    }

	    public String toString() {
	        return String.format("{%s}{%s}{%s}{%s}", opStaffNo, opType, opTarget, detail);
	    }
}
