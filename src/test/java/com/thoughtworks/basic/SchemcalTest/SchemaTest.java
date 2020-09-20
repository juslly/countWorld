package com.thoughtworks.basic.SchemcalTest;

import com.thoughtworks.basic.Schema.FlagSchema;
import com.thoughtworks.basic.Schema.Schema;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class SchemaTest {
    @Test
    public void shouldReturnFlagType(){
        Set<FlagSchema> flagSchemaSet = new HashSet<FlagSchema>();
        flagSchemaSet.add(new FlagSchema("l",Boolean.TYPE));
        Schema schema = new Schema(flagSchemaSet);
        Object type = schema.getTypeOf("l");
        assertEquals(Boolean.TYPE,type);
    }

    @Test
    public void shouldReturnFlagTypeWithP(){
        Set<FlagSchema> flagSchemaSet = new HashSet<FlagSchema>();
        flagSchemaSet.add(new FlagSchema("p",Integer.TYPE));
        Schema schema = new Schema(flagSchemaSet);
        Object type = schema.getTypeOf("p");
        assertEquals(Integer.TYPE,type);
    }

}
