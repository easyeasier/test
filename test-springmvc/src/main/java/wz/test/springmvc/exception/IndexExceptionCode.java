package wz.test.springmvc.exception;

import lombok.Getter;

@Getter
public enum IndexExceptionCode {
    EXAMPLE_BUSINESS_EXCEPTION(1_1_1, "example business exception"),

    INDEX_KAFKA_PRODUCER_ERROR(2_1_1, "kafka producer error");
    int code;
    String defaultMsg;

    IndexExceptionCode(int code, String defaultMsg) {
        this.code = code;
        this.defaultMsg = defaultMsg;
    }
}
