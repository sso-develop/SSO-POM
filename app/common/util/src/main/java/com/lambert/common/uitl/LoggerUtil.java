package com.lambert.common.uitl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LoggerUtil {
	
	
	 /** 线程编号修饰符 */
    private static final char  THREAD_RIGHT_TAG = ']';

    /** 线程编号修饰符 */
    private static final char  THREAD_LEFT_TAG  = '[';

    /** 换符 */
    public static final char   ENTERSTR         = '\n';

    /** 逗号 */
    public static final char   COMMA            = ',';

    /**
     * 事件接入渠道重要操作日志, 配置了延迟输出
     */
    public static final Logger channelLog       = LoggerFactory.getLogger("channelLog");
    /**
     * 风险识别阶段重要操作日志, 配置了延迟输出
     */
    public static final Logger scanLog          = LoggerFactory.getLogger("riskScanLog");
    /**
     * 风险运营阶段重要操作日志, 配置了延迟输出
     */
    public static final Logger riskManageLog    = LoggerFactory.getLogger("riskManageLog");
    /**
     * 系统级统一错误日志
     */
    public static final Logger defaultErrorLog  = LoggerFactory.getLogger("ERROR");
    /**
     * 后台重要配置操作日志,请使用LoggerUtil.infoOpLog才记录
     */
    private static final Logger opLog = LoggerFactory.getLogger("operationLog");

    /**
     * 最严重的错误.每个都会触发报警
     */
    public static final Logger fatalErrorLog = LoggerFactory.getLogger("fatalErrorLog");
    /**
     * 单条日志阶段字符数
     */
    public static final int    MAX_CHARS        = 1024;

    /**
     * 禁构函数
     */
    private LoggerUtil() {
        // 禁构函数
    }

    /**
     * 生成<font color="blue">调试</font>级闭志<br>
     *
     * @param logger
     * @param obj
     */
    public static void debug(Logger logger, Object... obj) {
        if (logger.isDebugEnabled()) {
            logger.debug(getLogString(obj));
        }
    }

    /**
     * 生成<font color="blue">info</font>级日志<br>
     *
     * @param logger
     * @param obj
     */
    public static void info(Logger logger, Object... obj) {
        if (logger.isInfoEnabled()) {
            logger.info(getLogString(obj));
        }
    }

    /**
     * 生成<font color="brown">警告</font>级日志<br>
     *
     * @param logger
     * @param obj
     */
    public static void warn(Logger logger, Object... obj) {
        if (logger.isWarnEnabled()) {
            logger.warn(getLogString(obj));
        }
    }

    /**
     * 生成输出到日志的字符串
     *
     * @param obj 任意个要输出到日志的参数
     * @return
     */
    public static String getLogString(Object... obj) {
        StringBuilder log = new StringBuilder();
        log.append(THREAD_LEFT_TAG).append(Thread.currentThread().getId()).append(THREAD_RIGHT_TAG);
        for (Object o : obj) {
            log.append(o);
        }
        return log.toString();
    }

    public static void trace(Logger logger, Object... obj) {
        if (logger.isDebugEnabled()) {
            logger.debug(getLogString(obj));
        }
    }

    /**
     * 记录操作日志
     */
    public static void infoOpLog(OpLog opLog) {
        LoggerUtil.opLog.info(opLog.toString());
    }
}
