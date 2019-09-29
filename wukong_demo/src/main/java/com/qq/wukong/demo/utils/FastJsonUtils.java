package com.qq.wukong.demo.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.JSONLibDataFormatSerializer;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;

import javax.xml.crypto.dsig.keyinfo.KeyValue;
import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * @Auther: 天雪
 * @Date: 2019/9/29 10:48
 * @Description:
 */
public class FastJsonUtils {
    private static final SerializeConfig config;

    static {
        config = new SerializeConfig();
        config.put(java.util.Date.class, new JSONLibDataFormatSerializer()); // 使用和json-lib兼容的日期输出格式
        config.put(java.sql.Date.class, new JSONLibDataFormatSerializer()); // 使用和json-lib兼容的日期输出格式
    }

    private static final SerializerFeature[] features = {
            SerializerFeature.WriteMapNullValue, // 输出空置字段
            SerializerFeature.WriteNullListAsEmpty, // list字段如果为null，输出为[]，而不是null
            SerializerFeature.WriteNullNumberAsZero, // 数值字段如果为null，输出为0，而不是null
            SerializerFeature.WriteNullBooleanAsFalse, // Boolean字段如果为null，输出为false，而不是null
            SerializerFeature.WriteNullStringAsEmpty // 字符类型字段如果为null，输出为""，而不是null
    };


    // 将对象转化为string
    public static String toJSONString(Object object) {
        return JSON.toJSONString(object, config, features);
    }

    public static String toJSONNoFeatures(Object object) {
        return JSON.toJSONString(object, config);
    }


    // 将string转化为Object
    public static Object toBean(String text) {
        return JSON.parse(text);
    }

    // 将String转化为指定类型对象
    public static <T> T toBean(String text, Class<T> clazz) {
        return JSON.parseObject(text, clazz);
    }

    // 转换为数组
    public static <T> Object[] toArray(String text) {
        return toArray(text, null);
    }

    // 转换为数组
    public static <T> Object[] toArray(String text, Class<T> clazz) {
        return JSON.parseArray(text, clazz).toArray();
    }

    // 转换为List
    public static <T> List<T> toList(String text, Class<T> clazz) {
        return JSON.parseArray(text, clazz);
    }

    /**
     * 将javabean转化为序列化的json字符串
     *
     * @param keyvalue
     * @return
     */
    public static Object beanToJson(KeyValue keyvalue) {
        String textJson = JSON.toJSONString(keyvalue);
        Object objectJson = JSON.parse(textJson);
        return objectJson;
    }

    /**
     * 将string转化为序列化的json字符串
     *
     * @param text
     * @return
     */
    public static Object stringToJson(String text) {
        Object objectJson = JSON.parse(text);
        return objectJson;
    }

    /**
     * json字符串转化为map
     *
     * @param s
     * @return
     */
    public static Map stringToCollect(String s) {
        Map m = JSONObject.parseObject(s);
        return m;
    }

    /**
     * 将map转化为string
     *
     * @param m
     * @return
     */
    public static String collectToString(Map m) {
        String s = JSONObject.toJSONString(m);
        return s;
    }

    public static <T> void writeJsonToFile(T t, File file) throws IOException {
        String jsonStr = JSONObject.toJSONString(t, SerializerFeature.PrettyFormat);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
        bw.write(jsonStr);
        bw.close();
    }

    public static <T> void writeJsonToFile(T t, String filename) throws IOException {
        writeJsonToFile(t, new File(filename));
    }

    public static <T> T readJsonFromFile(Class<T> cls, File file) throws IOException {
        StringBuilder strBuilder = revertFileToJson(file);
        return JSONObject.parseObject(strBuilder.toString(), cls);
    }

    public static <T> T readJsonFromFile(Class<T> cls, String filename) throws IOException {
        return readJsonFromFile(cls, new File(filename));
    }

    public static <T> T readJsonFromFile(TypeReference<T> typeReference, File file) throws IOException {
        StringBuilder strBuilder = revertFileToJson(file);
        return JSONObject.parseObject(strBuilder.toString(), typeReference);
    }

    public static <T> T readJsonFromFile(TypeReference<T> typeReference, String filename) throws IOException {
        return readJsonFromFile(typeReference, new File(filename));
    }

    private static StringBuilder revertFileToJson(File file) throws IOException {
        StringBuilder strBuilder = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        String line = null;
        while ((line = br.readLine()) != null) {
            strBuilder.append(line);
        }
        br.close();
        return strBuilder;
    }
}
