package com.example.demo.sms.tool;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 基础工具类
 *
 * @author wenjiahui 2018/4/20 16:32
 * @version V1.0.0
 * @Modify: {原因} by wenjiahui 2018/4/20 16:32
 **/

/**
 *
 * @version 1.0
 * @date: 2018/11/12
 * @author: xx
 * @email: xx@hikvision.com.cn
 * @since 110
 */
 public class JacksonTool {

    /**
     * jackson 中的objectMapper，writeValueAsString以及readValue是线程安全的 所以可以全局new一个static的实例
     */
    private static final ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static String bean2Json(Object obj) {
        try {
            StringWriter sw = new StringWriter();
            JsonGenerator gen = new JsonFactory().createGenerator(sw);
            mapper.writeValue(gen, obj);
            gen.close();
            return sw.toString();
        } catch (Exception e) {
            return null;
        }

    }

    public static String getKeyFromMap(String json, String key) {
        Map<String, Object> map = json2Map(json);
        if (null == map || map.isEmpty()) {
            return null;
        }
        return bean2Json(map.get(key));
    }

    /**
     * java bean 转换为json string
     *
     * @return java.lang.String
     * @author wenjiahui 2018-05-03
     * @since 1.0.0
     */
    public static String json2Str(Object obj) {
        if (null == obj) {
            return null;
        } else if (obj instanceof String) {
            return (String) obj;
        } else {
            try {
                return mapper.writeValueAsString(obj);
            } catch (JsonProcessingException e) {
                return null;
            }
        }
    }

    /**
     * json string 转换为java bean（简单类 类数组）
     *
     * @return T
     * @author wenjiahui 2018-05-21
     * @since 1.0.0
     */
    public static <T> T json2Bean(Object obj, Class<T> clazz) {

        String json;

        if (null == obj) {
            return null;
        } else if (obj instanceof String) {
            json = (String) obj;
        } else {
            json = json2Str(obj);
        }

        if (StringUtils.isEmpty(json)) {
            return null;
        }

        try {
            return mapper.readValue(json, clazz);
        } catch (IOException e) {
            return null;
        }
    }

    public static Map<String, Object> json2Map(Object obj) {

        String json;

        if (null == obj) {
            return null;
        } else if (obj instanceof String) {
            json = (String) obj;
        } else {
            json = json2Str(obj);
        }

        if (StringUtils.isEmpty(json)) {
            return null;
        }

        JavaType javaType =
                mapper.getTypeFactory()
                        .constructParametricType(HashMap.class, String.class, Object.class);
        try {
            return mapper.readValue(json, javaType);
        } catch (Exception e) {
            return null;
        }

    }

    public static List<Object> json2List(Object obj) {

        String json;

        if (null == obj) {
            return null;
        } else if (obj instanceof String) {
            json = (String) obj;
        } else {
            json = json2Str(obj);
        }

        if (StringUtils.isEmpty(json)) {
            return null;
        }

        JavaType javaType =
                mapper.getTypeFactory().constructParametricType(ArrayList.class, Object.class);

        try {
            return mapper.readValue(json, javaType);
        } catch (Exception e) {
            return null;
        }

    }

    public static <T> List<T> json2List(Object obj, Class<T> clazz) {

        String json;

        if (null == obj) {
            return null;
        } else if (obj instanceof String) {
            json = (String) obj;
        } else {
            json = json2Str(obj);
        }

        if (StringUtils.isEmpty(json)) {
            return null;
        }

        JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, clazz);
        try {
            return mapper.readValue(json, javaType);
        } catch (Exception e) {
            return null;
        }
    }

    public static JsonNode getInnerJsonNodeFromJson(String strInput, String key) {
        try {
            JsonNode arrNode = mapper.readTree(strInput).get(key);
            return arrNode;
        } catch (Exception e) {
            return null;
        }

    }

    public static <T> T json2TypeReference(String json, TypeReference<T> typeReference) {
        try {
            return mapper.readValue(json, typeReference);
        } catch (Exception e) {
            return null;
        }
    }
}
