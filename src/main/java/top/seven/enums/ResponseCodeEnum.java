package top.seven.enums;

/**
 * 系统返回值枚举
 */
public enum ResponseCodeEnum {

    SUCCESS(0, "SUCCESS"),

    ERROR(10, "ERROR"),
    LOGIN_FAILED(11, "账号或密码错误"),

    ILLEGAL_PARAMETER(400, "参数非法"),
    NOT_LOGGED_IN(401, "用户未登录"),
    UNAUTHORIZED(403, "权限不足"),
    NOT_FOUND(404, "资源不存在"),
    INTERNAL_SERVER_ERROR(500, "服务器内部错误"),
    ;
    private int code;

    private String message;

    ResponseCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
