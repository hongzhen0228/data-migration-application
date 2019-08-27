package com.data.migration.vo;

/**
 * Created by hongzhen.cao on 2019/8/26.
 */
public class ResponseVoResult<T> {

    private String code;

    private T data;

    private String message;

    private int status;

    public static <T> ResponseVoResult<T> enumOK(String message) {
        return new ResponseVoResult<>(ResponseVoEnum.SUCCESS, message, null);
    }

    public static <T> ResponseVoResult<T> enumOK(T data) {
        return new ResponseVoResult<>(ResponseVoEnum.SUCCESS, null, data);
    }

    public static <T> ResponseVoResult<T> enumOK(String message, T data) {
        return new ResponseVoResult<>(ResponseVoEnum.SUCCESS, message, data);
    }

    public static <T> ResponseVoResult<T> enumFailed(String message) {
        return new ResponseVoResult<>(ResponseVoEnum.FAILED, message,null);
    }

    public ResponseVoResult(ResponseVoEnum responseVoEnum, String message, T data) {
        this(responseVoEnum);
        if (message != null) {
            this.message = message;
        } else {
            this.message = responseVoEnum.getMessage();
        }
        this.data = data;
    }

    public ResponseVoResult(ResponseVoEnum responseVoEnum) {
        this.code = responseVoEnum.getCode();
        this.status = responseVoEnum.getStatus();
    }


    public enum ResponseVoEnum {
        SUCCESS("0000", "成功", 200),
        ERROR_FAILED("0001", "服务异常", 500),
        FAILED("0002", "服务逻辑异常", 500),
        DUBBO_EXCEPTION("0003", "dubbo调用异常", 500),
        TIME_OUT("0004", "登陆超时", 401),
        NO_AUTHORITIES("0005", "没有权限访问", 403),
        NOT_LOGGED_IN("0006", "未登录", 401),
        NO_HANDLER_FOUND("0007", "未匹配URL", 404),
        NO_DATA_FOUND("0008", "查询不到数据", 200),
        NO_LOCATION_CUST("0009", "客户未定位", 500),
        VERIFY_FAILE("10008", "校验失败", 200);


        private String code;
        private String message;
        private int status;

        public int getStatus() {
            return status;
        }

        public String getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }

        ResponseVoEnum(String code, String message, int status) {
            this.code = code;
            this.message = message;
            this.status = status;
        }
    }
}
