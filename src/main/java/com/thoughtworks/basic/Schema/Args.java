package com.thoughtworks.basic.Schema;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Args {
    private String args;
    private Schema schema;

    public Args(String argsTest, Schema schema) {
        this.args = argsTest;
        this.schema = schema;
    }

    public List<KeyValuePair> scan() {
        List<String> keyValues = Arrays.asList(args.split("-"));
        keyValues = keyValues.stream()
                .map(keyValue -> keyValue.trim())
                .collect(Collectors.toList());
        keyValues = keyValues.subList(1,keyValues.size());

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

            keyValuePairs.add(new KeyValuePair(key,value));
         });
        return keyValuePairs;
    }

    public Object getValueOf(String flag) {
        List<KeyValuePair> keyValuePairs = scan();

        Object value =  keyValuePairs.stream()
                .filter(keyValue -> flag.equals(keyValue.getKey()))
                .findFirst()
                .map(KeyValuePair::getValue).orElse(null);
        if("".equals(value)){
            value = schema.getDefaultValueOf(flag);
        }
//        Object value;
//        for (KeyValuePair keyValue : keyValuePairs) {
//            if (flag.equals(keyValue.getKey())) {
//                value = keyValue.getValue();
//                if("".equals(value)){
//                    value = schema.getDefaultValueOf(flag);
//
//                }
//            }
//        }

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
