package com.thoughtworks.basic.Schema;

public class FlagSchema {

    private final String flag;
    private ValueType valueType;
//    private final Object type;


    public FlagSchema(String flag, ValueType valueType) {
        this.flag = flag;
        this.valueType = valueType;
//        this.type = type;
    }

    public boolean equalsWith(String flag) {
        return flag.equalsIgnoreCase(this.flag);
    }

    public String getType() {
       //return type;
        return valueType.getType();
    }
}
