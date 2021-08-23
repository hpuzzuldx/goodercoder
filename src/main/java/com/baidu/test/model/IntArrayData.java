/*
 * Copyright (C) 2021 Baidu, Inc. All Rights Reserved.
 */
package com.baidu.test.model;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Integer数组
 */
public class IntArrayData {
    ArrayList<Integer> result;

    public ArrayList<Integer> getResult() {
        return result;
    }

    public void setResult(ArrayList<Integer> result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return Arrays.toString(result.toArray());
    }
}
