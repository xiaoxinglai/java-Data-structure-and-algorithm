package com.lxx.tokenParser;

import com.lxx.enums.TokenType;

/**
 * @ClassName Token
 * @Author laixiaoxing
 * @Date 2020/3/2 下午8:48
 * @Description TODO
 * @Version 1.0
 */
public class Token {

    /**
     * tokenKey
     */
    private TokenType tokenType;

    /**
     * token value
     */
    private String value;

    public Token(TokenType tokenType, String value) {
        this.tokenType = tokenType;
        this.value = value;
    }

    public TokenType getTokenType() {
        return tokenType;
    }

    public void setTokenType(TokenType tokenType) {
        this.tokenType = tokenType;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

