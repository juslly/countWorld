package com.thoughtworks.basic.SchemcalTest;

import com.thoughtworks.basic.Schema.Args;
import com.thoughtworks.basic.Schema.FlagSchema;
import com.thoughtworks.basic.Schema.KeyValuePair;
import com.thoughtworks.basic.Schema.Schema;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ArgsTest {
    @Test
    public void shouldReturnStringListWhenScanGivenString(){

        Set<FlagSchema> flagSchemaSet = new HashSet<FlagSchema>();
        flagSchemaSet.add(new FlagSchema("l",Boolean.TYPE));
        Schema schema = new Schema(flagSchemaSet);

        String argsTest = "-l true -p 8080 -d usr/logs";
        Args args = new Args(argsTest, schema);
        List<KeyValuePair> keyValuePairs = args.scan();
        assertEquals(3,keyValuePairs.size());
        assertTrue(keyValuePairs.contains(new KeyValuePair("l", "true")));
    }

    @Test
    public void shouldReturnCorrectValueWhenScanGetValueOfFlag(){

        Set<FlagSchema> flagSchemaSet = new HashSet<FlagSchema>();
        flagSchemaSet.add(new FlagSchema("l",Boolean.TYPE));
        Schema schema = new Schema(flagSchemaSet);

        String argsTest = "-l true -p 8080 -d usr/logs";
        Args args = new Args(argsTest,schema);
        Object value = args.getValueOf("l");

        assertEquals(value,true);
    }

    @Test
    public void shouldReturnIntTypeValueWhenScanGetValueOfFlag(){

        Set<FlagSchema> flagSchemaSet = new HashSet<FlagSchema>();
        flagSchemaSet.add(new FlagSchema("p",Integer.TYPE));
        Schema schema = new Schema(flagSchemaSet);

        String argsTest = "-l true -p 8080 -d usr/logs";
        Args args = new Args(argsTest,schema);
        Object value = args.getValueOf("p");

        assertEquals(value,8080);
    }
}
