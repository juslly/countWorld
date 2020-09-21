package com.thoughtworks.basic.Schema;

import java.util.Set;

public class Schema {
    private Set<FlagSchema> flagSchemaSet;

    public Schema(Set<FlagSchema> flagSchemaSet) {
        this.flagSchemaSet = flagSchemaSet;
    }

    public String getTypeOf(String flag) {
        return flagSchemaSet.stream()
                .filter(flagSchema -> flagSchema.equalsWith(flag))
                .findFirst()
                .get()
                .getType();
    }

    public Object getDefaultValueOf(String flag){
//        Object result;
//        for(int i = 0;i < flagSchemaSet.size();i++){
//            if (flag.equals(.getType())){
//                result = flagSchema.getDefaultValue();
//            }
//        }
//        flagSchemaSet.forEach(flagSchema -> {
//
//        });

        return flagSchemaSet.stream()
                .filter(flagSchema -> flag.equals(flagSchema.getFlag()))
                .findFirst()
                .map(FlagSchema::getDefaultValue)
                .orElse(null);

    }
}
