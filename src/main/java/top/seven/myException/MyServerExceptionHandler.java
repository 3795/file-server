package top.seven.myException;

import top.seven.vo.ServerResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class MyServerExceptionHandler {

    @ExceptionHandler(ServerException.class)
    @ResponseStatus(HttpStatus.OK)
    public ServerResponseVO handleServerException(ServerException se) {
        log.warn("业务异常，Code: {}, ErrMessage: {}", se.getCode(), se.getMessage());
        return ServerResponseVO.error(se);
    }
}
