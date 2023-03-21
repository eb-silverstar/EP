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
                    map.put("fmt", arr[5]);
                    map.put("ptn", arr[6]);
                    map.put("isnull", arr[7]);
                } catch (IndexOutOfBoundsException e) {}

                if("between".equals(map.get("op"))) {
                    if((map.get("val")).length() == 10) map.put("val", map.get("val") + " 00:00:00");
                    if(map.get("val2").length() == 10) map.put("val2", map.get("val2") + " 23:59:59");
                }

                if(!map.get("tbl").isEmpty() && !map.get("col").isEmpty()) {
                    if(map.get("col").contains(",")) {
                        for(String col : map.get("col").split(",")) {
                            Map<String, String> iMap = new HashMap<>();
                            iMap.put("tbl", map.get("tbl"));
                            iMap.put("col", col);

                            if("in".equals(map.get("op")) && !map.get("val").isEmpty()) {
                                iMap.put("con", "IN (" + map.get("val").replaceAll("([^,]*)", "'$1',") + ")");
                            } else if("like".equals(map.get("op")) && !map.get("val").isEmpty()) {
                                iMap.put("con", "LIKE '%" + map.get("val") + "%'");
                            } else if("between".equals(map.get("op")) && !map.get("val").isEmpty() && !map.get("val2").isEmpty()) {
                                iMap.put("con", "BETWEEN '" + map.get("val") + "' AND '" + map.get("val2") + "'");
                            } else if("and".equals(map.get("op")) && !map.get("val").isEmpty()) {
                                iMap.put("con", "= '" + map.get("val") + "'");
                            } else if("or".equals(map.get("op")) && !map.get("val").isEmpty()) {
                                //TODO
                            }
                            if("Y".equals(map.get("isnull"))) {
                                iMap.put("con", "IS NULL");
                            } else if("N".equals(map.get("isnull"))) {
                                iMap.put("con", "IS NOT NULL");
                            }

                            result.add(iMap);
                        }

                    } else {
                        if("in".equals(map.get("op")) && !map.get("val").isEmpty()) {
                            map.put("con", "IN (" + map.get("val").replaceAll("([^,]*)", "'$1',") + ")");
                        } else if("like".equals(map.get("op")) && !map.get("val").isEmpty()) {
                            map.put("con", "LIKE '%" + map.get("val") + "%'");
                        } else if("between".equals(map.get("op")) && !map.get("val").isEmpty() && !map.get("val2").isEmpty()) {
                            map.put("con", "BETWEEN '" + map.get("val") + "' AND '" + map.get("val2") + "'");
                        } else if("and".equals(map.get("op")) && !map.get("val").isEmpty()) {
                            map.put("con", "= '" + map.get("val") + "'");
                        } else {
                            if("Y".equals(map.get("isnull"))) {
                                map.put("con", "IS NULL");
                            } else if("N".equals(map.get("isnull"))) {
                                map.put("con", "IS NOT NULL");
                            } else {
                                if("in".equals(map.get("op")) || "like".equals(map.get("op")) || "between".equals(map.get("op"))
                                        || "and".equals(map.get("op")) || "or".equals(map.get("op"))) {
                                    //TODO
                                }
                            }
                        }

                        result.add(map);
                    }
                }
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
