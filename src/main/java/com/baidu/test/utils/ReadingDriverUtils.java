package com.baidu.test.utils;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * 读取工具类
 */
public class ReadingDriverUtils {

    /**
     * 读取每一种类型，添加到list中
     *
     * @throws IOException
     */
    public List<String> readType(Reader reader) throws IOException {
        StringBuilder builder = new StringBuilder();
        List<String> result = new ArrayList<String>();
        int c = reader.read();
        boolean beginFlag = false;
        while (c != -1) {
            if (beginFlag) {
                // 读取结束
                if ((char) c == '>') {
                    beginFlag = false;
                    result.add(builder.toString());
                    builder.delete(0, builder.length());
                } else {
                    builder.append((char) c);
                }
            } else {
                // 读取结尾
                if ((char) c == '\n') {
                    return result;
                }
                // 读取开始
                if ((char) c == '<') {
                    beginFlag = true;
                }
            }
            c = reader.read();
        }
        return result;
    }

    /**
     * 读取全部<>内的token
     *
     * @param reader 文件reader
     * @param num    总的type数
     *
     * @throws Exception
     */
    public List<String> getLineToken(Reader reader, int num) throws Exception {
        int count = 0;
        StringBuilder builder = new StringBuilder();
        List<String> result = new ArrayList<String>();
        int c = reader.read();
        boolean beginFlag = false;
        while (c != -1) {
            if (beginFlag) {
                if ((char) c == '>') {
                    beginFlag = false;
                    result.add(builder.toString());
                    builder.delete(0, builder.length());
                } else {
                    builder.append((char) c);
                }
            } else {
                if ((char) c == '\n') {
                    if (count != num) {
                        throw new Exception();
                    }
                    count = 0;
                }
                if ((char) c == '<') {
                    beginFlag = true;
                    count++;
                }
            }
            c = reader.read();
        }

        return result;
    }

    // 由于数组利用逗号分隔，获得数组内容, 即逗号分隔符的内容
    public static List<String> getArrayContents(String input) {
        List<String> result = new ArrayList<String>();
        String[] pieces = input.split(",");
        for (String i : pieces) {
            result.add(i);
        }
        return result;
    }

}
