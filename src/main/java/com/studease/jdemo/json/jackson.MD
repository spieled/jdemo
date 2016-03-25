# fastxml.jackson使用技巧总结
## Domain转JSON字符串
### 添加注解控制输出JSON字符串的key
- `@JsonProperty`控制json-key
- `@JsonIgnore`忽略property，输出JSON中不包含该key
- `@JsonSerialize`自定义输出value的值
- 示例
1. `@JsonProperty(value = "v")
 private String value;`
2. `@JsonIgnore
  private String nobody;`
3. `@JsonSerialize(using = DateSerializer.class)
  private Date createTime;`
  
## JSON字符串转Domain
### JSON字符串key与Domain的properties个数不一致的情况。
- JSON字符串key少，Domain的properties多，转换成功，未提供的key，在properties中使用默认值。
- JSON字符串key多，Domain的properties少，转换失败，报异常`UnRecognizedPropertyException`(默认情况下)。
- 如果要兼容异常`UnRecognizedPropertyException`，请禁用`ObjectMapper`的`FAIL_ON_UNKNOWN_PROPERTIES` Feture。
- `objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);`
### 处理Date类型
- Date类型，默认只接受时间戳。
- Date类型可以接受format字符串（仍然能接受时间戳），`objectMapper.setDateFormat(Constant.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS);`
### 处理布尔类型
- boolean类型仅接受`true`或`false`，大小写不敏感。
### 处理枚举类型
- 默认取`name()`