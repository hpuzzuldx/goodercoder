package com.baidu.test.parser;

/**
 * string 型解析器
 */
public class StringParser implements CommonParser<String> {

    private String result;

    public StringParser() {
    }

    public StringParser(String result) {
        this.result = result;
    }

    @Override
    public String parse(String readIn) {
        setResult(readIn);
        return getResult();
    }

    @Override
    public String getResult() {
        return result;
    }

    @Override
    public void setResult(String result) {
        this.result = result;
    }
}
