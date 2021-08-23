package com.baidu.test.parser;

/**
 * 通用解析接口，具体解析接口需要实现此接口
 */
public interface CommonParser<T> {

    public T parse(String readIn);

    public T getResult();

    public void setResult(T result);

}
