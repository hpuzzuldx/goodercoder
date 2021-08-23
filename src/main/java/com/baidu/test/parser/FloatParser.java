package com.baidu.test.parser;

/**
 * float 型解析器
 */
public class FloatParser implements CommonParser<Float> {

    private Float result;

    public FloatParser() {

    }

    public FloatParser(Float result) {
        this.result = result;
    }

    @Override
    public Float parse(String readIn) {
        try {
            setResult(Float.parseFloat(readIn));
        } catch (NumberFormatException ex) {
            setResult(null);
        }
        return getResult();
    }
    @Override
    public Float getResult() {
        return result;
    }

    @Override
    public void setResult(Float result) {
        this.result = result;
    }
}
