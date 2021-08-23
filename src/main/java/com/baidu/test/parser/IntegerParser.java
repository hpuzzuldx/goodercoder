package com.baidu.test.parser;

/**
 * int 型解析器
 */
public class IntegerParser implements CommonParser<Integer> {

    private Integer result;

    public IntegerParser() {

    }

    public IntegerParser(Integer result) {
        this.result = result;
    }

    @Override
    public Integer parse(String readIn) {
        try {
            setResult(Integer.valueOf(readIn));
        } catch (NumberFormatException ex) {
            setResult(null);
        }
        return getResult();
    }

    @Override
    public Integer getResult() {
        return result;
    }

    @Override
    public void  setResult(Integer result) {
        this.result = result;
    }

}
