package com.thoughtworks.basic.Schema;

public enum ValueType {
    /**
     * 设置默认值
     */
    BOOLEAN(false),
    INTEGER(0),
    STRING("");

    private Object defaultValue;

    ValueType(Object defaultValue) {
        this.defaultValue = defaultValue;
    }

    public Object getDefaultValue() {
        return defaultValue;
    }

    public String getType() {
        return this.getDefaultValue().getClass().getTypeName();

    }
}
