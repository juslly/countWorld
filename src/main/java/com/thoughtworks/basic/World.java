package com.thoughtworks.basic;

import java.util.*;

public class World {
    public String translate(String str) {
        String afterTransStr = str.toLowerCase();
        return afterTransStr;
    }

    public Map<String,Integer> calculate(String[] result) {
        Map<String, Integer> calWorld = new HashMap<String, Integer>();
        for(int i = 0;i < result.length;i++){
            if(result[i] != null && !calWorld.containsKey(result[i])){
                calWorld.put(result[i],new Integer(1));
            }else{
                int value = calWorld.get(result[i]) + 1;
                calWorld.put(result[i],new Integer(value));
            }
        }
        return calWorld;
    }

    public String[] cut(String str) {
        String[] afterCutStr = str.split(" ");
        return afterCutStr;
    }

    public Map<String,Integer> sortWorld(Map<String, Integer> oriMap) {
        if (oriMap == null || oriMap.isEmpty()) {
            return null;
        }
        Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
        List<Map.Entry<String, Integer>> entryList = new ArrayList<Map.Entry<String, Integer>>(
                oriMap.entrySet());

        Collections.sort(entryList, new MapValueComparator());
        Iterator<Map.Entry<String, Integer>> iter = entryList.iterator();
        Map.Entry<String, Integer> tmpEntry = null;
        while (iter.hasNext()) {
            tmpEntry = iter.next();
            sortedMap.put(tmpEntry.getKey(), tmpEntry.getValue());
        }
        return sortedMap;
        //对象排序
//        for(){
//
//        }


    }
}
