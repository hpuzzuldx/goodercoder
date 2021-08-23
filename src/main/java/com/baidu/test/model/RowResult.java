package com.baidu.test.model;

import com.baidu.test.parser.FloatParser;
import com.baidu.test.parser.IntegerParser;
import com.baidu.test.parser.StringParser;
import com.baidu.test.parser.IntAddParse;
import com.baidu.test.parser.IntArrayParse;
import com.baidu.test.utils.ReadingDriverUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * 结果输出管理类
 */
public class RowResult {

    private List<List<Object>> rowResult;

    private List<Class> classes;

    private Map<Class, Class> classMap;

    private Set<Integer> arrayRecord;
    private int colNum;

    public void addResult(List<Object> row, Object value) {
        row.add(value);
        try {
            classes.get(row.size() - 1).cast(value);
        } catch (NumberFormatException ex) {
            row.set(row.size() - 1, null);
        }

    }

    public RowResult() {
        rowResult = new ArrayList<List<Object>>();
        classes = new ArrayList<Class>();
        classMap = new HashMap<Class, Class>();
        arrayRecord = new HashSet<Integer>();
        /**
         * 添加支持的类型
         */
        classMap.put(Integer.class, IntegerParser.class);
        classMap.put(String.class, StringParser.class);
        classMap.put(Float.class, FloatParser.class);
        classMap.put(IntAddNumber.class, IntAddParse.class);
        classMap.put(IntArrayData.class, IntArrayParse.class);
    }

    /**
     * 为每个type添加类型解析器
     *
     * @param types
     */
    private void readTypes(List<String> types) {
        colNum = types.size();
        for (String type : types) {
            if (type.equals("int")) {
                classes.add(Integer.class);
            } else if (type.equals("float")) {
                classes.add(Float.class);
            } else if (type.equals("string")) {
                classes.add(String.class);
            } else if (type.equals("int-add")) {
                classes.add(IntAddNumber.class);
            } else if (type.equals("int-array")) {
                classes.add(IntArrayData.class);
            }
        }
    }

    /**
     * 根据内容和type 调用parse方法，生成最终的结果，
     *
     * @param list
     * @param types
     *
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     */
    public void generate(List<String> list, List<String> types)
            throws IllegalAccessException, InstantiationException, NoSuchMethodException,
            InvocationTargetException {
        List<Object> arrayList = new ArrayList<Object>();
        readTypes(types);
        for (int i = 0; i < list.size(); i++) {
            Class clazz = classMap.get(classes.get(i % colNum));

            Method method = clazz.getMethod("parse", String.class);

            if (arrayRecord.contains(i % colNum)) {
                // 数组情况
                List<String> arrayPieces = ReadingDriverUtils.getArrayContents(list.get(i));
                List<Object> arrayResult = new ArrayList<Object>();
                for (int k = 0; k < arrayPieces.size(); k++) {
                    Object obj = method.invoke(clazz.newInstance(), arrayPieces.get(k));
                    arrayResult.add(obj);
                }
                arrayList.add(arrayResult);

            } else {
                Object obj = method.invoke(clazz.newInstance(), list.get(i));
                arrayList.add(obj);
                if ((i + 1) % colNum == 0) {
                    rowResult.add(arrayList);
                    arrayList = new ArrayList<Object>();
                }
            }
        }
    }

    public List<List<Object>> getRowResult() {
        return rowResult;
    }

    public void setRowResult(List<List<Object>> rowResult) {
        this.rowResult = rowResult;
    }

    public List<Class> getClasses() {
        return classes;
    }

    public void setClasses(List<Class> classes) {
        this.classes = classes;
    }
}
