package wz.test.springmvc.exception;

public class IndexBusinessException extends IndexException {

    public IndexBusinessException(IndexExceptionCode code) {
        this(code.getCode(), code.getDefaultMsg());
    }

    public IndexBusinessException(int code, String msg) {
        super(code, msg);
    }
}
