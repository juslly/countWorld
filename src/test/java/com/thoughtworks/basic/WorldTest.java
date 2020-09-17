package com.thoughtworks.basic;

import org.junit.Test;

import java.util.Map;

public class WorldTest {

    @Test
    public void calculateWorld(){
        World world = new World();
        String str = "White tigers live mostly in India Wild lions live mostly in Africa";
        String result = world.translate(str);
        String[] afterCut = world.cut(result);
        Map<String,Integer> cal = world.calculate(afterCut);
        Map<String,Integer> resultCal = world.sortWorld(cal);
        System.out.println(resultCal.toString());

    }
}
