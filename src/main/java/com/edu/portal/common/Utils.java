package com.edu.portal.common;

import lombok.val;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Utils {

    public static List<Map<String, String>> parseFilter(String filter) {
        List<Map<String, String>> result = new ArrayList<>();

        if(StringUtils.hasText(filter) && filter.contains(":")) {
            for(String sFilter : filter.split("\\|")) {
                String[] arr = sFilter.split(":");

                Map<String, String> map = new HashMap<>();
                try {
                    map.put("tbl", arr[0]);
                    map.put("col", arr[1]);
                    map.put("op", arr[2]);
                    map.put("val", arr[3]);
                    map.put("val2", arr[4]);
                } catch (IndexOutOfBoundsException e) {}

                if("between".equals(map.get("op"))) {
                    if((map.get("val")).length() == 10) map.put("val", map.get("val") + " 00:00:00");
                    if(map.get("val2").length() == 10) map.put("val2", map.get("val2") + " 23:59:59");
                }

                if(!map.get("tbl").isEmpty() && !map.get("col").isEmpty()) {
                    //TODO
                    if(map.get("col").contains(",")) {
                        for(String col : map.get("col").split(",")) {
                            if("in".equals(map.get("op")) && !map.get("val").isEmpty()) {
                            }
                        }

                    } else {
                        if("in".equals(map.get("op")) && !map.get("val").isEmpty()) {
                            //TODO

                        } else if("like".equals(map.get("op")) && !map.get("val").isEmpty()) {
                            map.put("con", "LIKE '%" + map.get("val") + "%'");

                        } else if("between".equals(map.get("op")) && !map.get("val").isEmpty() && !map.get("val2").isEmpty()) {
                            map.put("con", "BETWEEN '" + map.get("val") + "' AND '" + map.get("val2") + "'");

                        } else if("and".equals(map.get("op")) && !map.get("val").isEmpty()) {
                            map.put("con", "= '" + map.get("val") + "'");

                        } else {
                            //TODO
                        }
                    }
                }

                result.add(map);
            }
        }

        return result;
    }

    public static String parseOrder(String order, String sort) {
        String result = "";
        String[] orders = order.split(",");
        String[] sorts = sort.split(",");

        if(orders.length == sorts.length) {
            for(int i = 0; i < orders.length; i++) {
                result += orders[i] + " " + sorts[i].toUpperCase();
                if(i < orders.length - 1) result += ", ";
            }
        }

        return result;
    }

}
