/*
 * Copyright (C) 2021 Baidu, Inc. All Rights Reserved.
 */
package com.baidu.test.parser;

import java.util.ArrayList;
import java.util.List;

import com.baidu.test.utils.ReadingDriverUtils;
import com.baidu.test.model.IntArrayData;

/**
 * int 相加类型解析器
 */
public class IntArrayParse implements CommonParser<IntArrayData> {

    private IntArrayData result;

    @Override
    public IntArrayData parse(String readIn) {
        int count = 0;
        ArrayList<Integer> list = new ArrayList<Integer>();
        try {
            List<String> listData = ReadingDriverUtils.getArrayContents(readIn);
            for (String data : listData) {
                list.add(Integer.parseInt(data));
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        result = new IntArrayData();
        result.setResult(list);
        return result;
    }

    @Override
    public IntArrayData getResult() {
        return result;
    }

    @Override
    public void setResult(IntArrayData result) {
        this.result = result;
    }
}