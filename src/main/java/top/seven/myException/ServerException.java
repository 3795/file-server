package top.seven.myException;

import top.seven.enums.ResponseCodeEnum;
import lombok.Data;

@Data
public class ServerException extends RuntimeException {

    private int code;

    public ServerException(int code, String message) {
        super(message);
        this.code = code;
    }

    public ServerException(ResponseCodeEnum codeEnum) {
        super(codeEnum.getMessage());
        this.code = codeEnum.getCode();
    }


}
