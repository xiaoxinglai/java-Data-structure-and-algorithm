package com.lxx.enums;

/**
 * @ClassName TokenType
 * @Author laixiaoxing
 * @Date 2020/3/2 下午8:47
 * @Description json关键词
 * @Version 1.0
 */
public enum  TokenType {
    BEGIN_OBJECT(1),
    END_OBJECT(2),
    BEGIN_ARRAY(4),
    END_ARRAY(8),
    NULL(16),
    NUMBER(32),
    STRING(64),
    BOOLEAN(128),
    SEP_COLON(256),
    SEP_COMMA(512),
    END_DOCUMENT(1024);

    TokenType(int code) {
        this.code = code;
    }

    private int code;

    public int getTokenCode() {
        return code;
    }
}
