package com.studease.jdemo.json;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.IOException;
import java.util.*;

/**
 * Author: liushaoping
 * Date: 2016/3/25.
 */
// means that if we see "foo" or "bar" in JSON, they will be quietly skipped
// regardless of whether POJO has such properties
@JsonIgnoreProperties({"foo", "bar"})
public class Domain {
    private String name;
    @JsonProperty(value = "v")
    private String value;
    private String ext;
    @JsonIgnore
    private String nobody;
    // @JsonSerialize(using = DateSerializer.class)
    private Date createTime;
    private boolean normal;
    private String other;
    private String[] arr;
    private List<String> list;
    // @JsonSerialize(using = EnumCountrySerializer.class)
    private Country country;

    public static void main(String[] args) throws IOException {
        final ObjectMapper objectMapper = new ObjectMapper();
        // 日期format
        objectMapper.setDateFormat(Constant.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS);

        // 输出JSON带缩进(pretty output)
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        // 禁用“找不到any getter方法，报错” 特性，遇到该情况会输出{}
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        // 禁用“日期输出为时间戳” 特性， 默认format字符串"yyyy-MM-dd'T'HH:mm:ss.SSSZ"
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        // 禁用“不识别的属性，报错” 特性 to prevent exception when encountering unknown property
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        // to allow coercion of JSON empty String ("") to null Object value
        // 似乎没什么用
        // objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

        // to allow (non-standard) unquoted field names in JSON:
        objectMapper.enable(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES);
        // to allow use of apostrophes (single quotes), non standard
        objectMapper.enable(JsonParser.Feature.ALLOW_SINGLE_QUOTES);

        Domain t = new Domain();
        t.setName("john");
        t.setValue("powerful");
        t.setExt("ext");
        t.setNobody("无名小卒");
        t.setCreateTime(new Date());
        t.setNormal(true);
        t.setCountry(Country.CHINA);
        String[] arr = new String[]{"one", "two"};
        List<String> tmpList = new ArrayList<>();
        tmpList.add("three");
        tmpList.add("four");
        t.setArr(arr);
        t.setList(tmpList);
        System.out.println(objectMapper.writeValueAsString(t));

        // String jsonStr = "{\"name\":\"john\",\"ext\":\"ext\",\"createTime\":\"2016-03-25 10:54:04\",\"normal\":true,\"other\":null,\"arr\":[\"one\",\"two\"],\"list\":[\"three\",\"four\"],\"country\":\"CHINA\",\"v\":\"powerful\"}";
        String jsonStr = "{name:'john',\"ext\":\"ext\",\"createTime\":\"2016-03-25 10:54:04\",\"normal\":true,\"other\":null,\"arr\":[\"one\",\"two\"],\"list\":[\"three\",\"four\"],\"country\":\"CHINA\",\"v\":\"powerful\"}";
        final Domain domain = objectMapper.readValue(jsonStr, Domain.class);
        System.out.println(objectMapper.writeValueAsString(domain));

        Map<String, Domain> map = new HashMap<>();
        map.put("first key", t);
        System.out.println(objectMapper.writeValueAsString(map));

        String mapJsonStr = "{\"first key\":{\"name\":\"john\",\"ext\":\"ext\",\"createTime\":\"2016-03-25 11:18:13\",\"normal\":true,\"other\":\"\",\"arr\":[\"one\",\"two\"],\"list\":[\"three\",\"four\"],\"country\":\"CHINA\",\"v\":\"powerful\"}}";
        Map<String, Domain> resultMap = objectMapper.readValue(mapJsonStr, new TypeReference<Map<String, Domain>>() {
        });
        System.out.println(objectMapper.writeValueAsString(resultMap));

        System.out.println(objectMapper.writeValueAsString(new NonAccessDomain()));

        List<Integer> sourceList = new ArrayList<>();
        sourceList.add(23);
        sourceList.add(67);
        sourceList.add(33);
        int[] ints = objectMapper.convertValue(sourceList, int[].class);
        System.out.println(objectMapper.writeValueAsString(sourceList));
        System.out.println(objectMapper.writeValueAsString(ints));

    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String[] getArr() {
        return arr;
    }

    public void setArr(String[] arr) {
        this.arr = arr;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public boolean isNormal() {
        return normal;
    }

    public void setNormal(boolean normal) {
        this.normal = normal;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getNobody() {
        return nobody;
    }

    public void setNobody(String nobody) {
        this.nobody = nobody;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
