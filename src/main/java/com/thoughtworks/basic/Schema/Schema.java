package com.thoughtworks.basic.Schema;

import java.util.Set;

public class Schema {
    private Set<FlagSchema> flagSchemaSet;

    public Schema(Set<FlagSchema> flagSchemaSet) {
        this.flagSchemaSet = flagSchemaSet;
    }

    public Object getTypeOf(String flag) {
        return flagSchemaSet.stream()
                .filter(flagSchema -> flagSchema.equalsWith(flag))
                .findFirst()
                .get()
                .getType();
    }
}
