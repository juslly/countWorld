package com.thoughtworks.basic.SchemcalTest;

import com.thoughtworks.basic.Schema.*;
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
        flagSchemaSet.add(new FlagSchema("l",ValueType.BOOLEAN));
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
        flagSchemaSet.add(new FlagSchema("l", ValueType.BOOLEAN));
        Schema schema = new Schema(flagSchemaSet);

        String argsTest = "-l true -p 8080 -d usr/logs";
        Args args = new Args(argsTest,schema);
        Object value = args.getValueOf("l");

        assertEquals(value,true);
    }

    @Test
    public void shouldReturnIntTypeValueWhenScanGetValueOfFlag(){

        Set<FlagSchema> flagSchemaSet = new HashSet<FlagSchema>();
        flagSchemaSet.add(new FlagSchema("p",ValueType.INTEGER));
        Schema schema = new Schema(flagSchemaSet);

        String argsTest = "-l true -p 8080 -d usr/logs";
        Args args = new Args(argsTest,schema);
        Object value = args.getValueOf("p");

        assertEquals(value,8080);
    }

    @Test
    public void shouldReturnStringTypeValueWhenScanGetValueOfFlag(){

        Set<FlagSchema> flagSchemaSet = new HashSet<FlagSchema>();
        flagSchemaSet.add(new FlagSchema("d",ValueType.STRING));
        Schema schema = new Schema(flagSchemaSet);

        String argsTest = "-l true -p 8080 -d usr/logs";
        Args args = new Args(argsTest,schema);
        Object value = args.getValueOf("d");

        assertEquals(value,"usr/logs");
    }

    /**
     * 默认值测试
     */
    @Test
    public void shouldReturnDefaultValueWhenUseGetValueOf(){
        Set<FlagSchema> flagSchemaSet = new HashSet<FlagSchema>();
        flagSchemaSet.add(new FlagSchema("l", ValueType.BOOLEAN));
        Schema schema = new Schema(flagSchemaSet);

        String argsTest = "-l  -p 8080 -d usr/logs";
        Args args = new Args(argsTest,schema);
        Object value = args.getValueOf("l");

        assertEquals(value,true);
    }

    /**
     * 默认值测试
     */
    @Test
    public void shouldReturnDefaultValueFor0WhenUseGetValueOf(){
        Set<FlagSchema> flagSchemaSet = new HashSet<FlagSchema>();
        flagSchemaSet.add(new FlagSchema("p", ValueType.INTEGER));
        Schema schema = new Schema(flagSchemaSet);

        String argsTest = "-l  -p  -d usr/logs";
        Args args = new Args(argsTest,schema);
        Object value = args.getValueOf("p");

        assertEquals(value,0);
    }

    /**
     * 默认值测试
     */
    @Test
    public void shouldReturnDefaultValueForNullStrWhenUseGetValueOf(){
        Set<FlagSchema> flagSchemaSet = new HashSet<FlagSchema>();
        flagSchemaSet.add(new FlagSchema("d", ValueType.STRING));
        Schema schema = new Schema(flagSchemaSet);

        String argsTest = "-l true -p 8080 -d ";
        Args args = new Args(argsTest,schema);
        Object value = args.getValueOf("d");

        assertEquals(value,"");
    }

}
