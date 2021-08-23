/*
 * Copyright (c) 2018 Baidu.com, Inc. All Rights Reserved
 */
package com.baidu.test;

import com.baidu.test.utils.ReadingDriverUtils;
import com.baidu.test.model.RowResult;

import java.io.FileReader;
import java.io.Reader;
import java.util.List;

/**
 * 测试功能
 * <p>
 * 读取文件
 */
public class App {
    public static void main(String[] args) {
        try {
            // 读取文件
            Reader reader = new FileReader("test");
            ReadingDriverUtils readingDriver = new ReadingDriverUtils();

            // 读取每列的类型
            List<String> types = readingDriver.readType(reader);

            // 读取每行的内容
            List<String> list = readingDriver.getLineToken(reader, types.size());

            RowResult result = new RowResult();
            // 生成最终的结果
            result.generate(list, types);
            // 输出的最终结果
            System.out.println(result.getRowResult());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
