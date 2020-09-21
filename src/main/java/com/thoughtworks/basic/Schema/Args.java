package com.thoughtworks.basic.Schema;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Args {
    private String args;
    private Schema schema;

    public Args(String argsTest, Schema schema) {
        this.args = argsTest;
        this.schema = schema;
    }

    public List<KeyValuePair> scan() {
        //入参校验
        check(args);

        //入参字符串切割
        List<String> keyValues = splitInput();

        List<KeyValuePair> keyValuePairs = new ArrayList<KeyValuePair>();
        keyValues.forEach(keyValue -> {
            String key;
            String value;
            if(!keyValue.contains(" ")){
                key = keyValue;
                value= "";
            }else{
                String[] splitKeyValue = keyValue.split(" ");
                key = splitKeyValue[0];
                value = splitKeyValue[1];
            }

            //paramCheck(key,value);
            keyValuePairs.add(new KeyValuePair(key,value));
         });
        return keyValuePairs;
    }

//    private void paramCheck(String key, String value) {
//        if (!containsFlagOfSchema(key)) {
//            throw new IllegalArgumentException(key + " is not defined!");
//        }
//        if (key.startsWith(space) || value.trim().contains(space)) {
//            throw new IllegalArgumentException("Param is illegal!");
//        }
//
//    }

    private List<String> splitInput() {
        List<String> keyValues = Arrays.asList(args.split("-"));
        keyValues = keyValues.stream()
                .map(keyValue -> keyValue.trim())
                .collect(Collectors.toList());
        keyValues = keyValues.subList(1,keyValues.size());
        return keyValues;
    }

    private void check(String args) {

        String regEx = "^.*-[A-Za-z]-.*$";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(args);
        if (matcher.matches()) {
            throw new IllegalArgumentException("Param should have space!");
        }

    }

    public Object getValueOf(String flag) {
        List<KeyValuePair> keyValuePairs = scan();

        Object value =  keyValuePairs.stream()
                .filter(keyValue -> flag.equals(keyValue.getKey()))
                .findFirst()
                .map(KeyValuePair::getValue).orElse(null);
        //value为空时获取默认值
        if("".equals(value)){
            value = schema.getDefaultValueOf(flag);
        }
        return transForCorrectType(flag, value);
    }

    /**
     * 返回值类型转换
     * @param flag
     * @param value
     * @return
     */
    private Object transForCorrectType(String flag, Object value) {
        Object type = schema.getTypeOf(flag);
        if(type.equals("java.lang.String")){
            return value;
        }
        //强制类型转换
        if(type.equals("java.lang.Boolean")){
            value = Boolean.parseBoolean(value.toString());
        }
        if(type.equals("java.lang.Integer")){
            value = Integer.parseInt(value.toString());
        }
        return value;
    }
}
