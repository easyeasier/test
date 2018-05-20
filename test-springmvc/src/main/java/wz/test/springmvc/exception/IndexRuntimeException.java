package wz.test.springmvc.exception;

public class IndexRuntimeException extends IndexException {
    public IndexRuntimeException(int code, String msg) {
        super(code, msg);
    }

    public IndexRuntimeException(IndexExceptionCode code) {
        this(code.getCode(), code.getDefaultMsg());
    }
}
