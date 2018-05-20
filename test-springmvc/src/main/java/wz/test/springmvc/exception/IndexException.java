package wz.test.springmvc.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IndexException extends RuntimeException {
    int code;

    public IndexException(int code, String msg) {
        super(msg);
        this.code = code;
    }
}
