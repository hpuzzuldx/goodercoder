/*
 * Copyright (C) 2021 Baidu, Inc. All Rights Reserved.
 */
package com.baidu.test.parser;

import com.baidu.test.model.IntAddNumber;

/**
 * int 相加类型解析器
 */
public class IntAddParse implements CommonParser<IntAddNumber> {

    private IntAddNumber result;

    @Override
    public IntAddNumber parse(String readIn) {
        int count = 0;
        try {
            String[] splitData = readIn.split(" ");
            if (splitData != null && splitData.length > 0) {
                for (int i = 0; i < splitData.length; i++) {
                    String result = splitData[i];
                    count = count + Integer.parseInt(result);
                }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        result = new IntAddNumber();
        result.setResult(count);
        return result;
    }

    @Override
    public IntAddNumber getResult() {
        return result;
    }

    @Override
    public void setResult(IntAddNumber result) {
        this.result = result;
    }
}