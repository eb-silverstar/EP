package com.edu.portal.center;

import com.edu.portal.common.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CenterService {

    private static CenterMapper centerMapper;

    /**
     * 센터 목록 조회
     *
     * @param map
     * @return Map<String, String>
     */
    public Map<String, Object> getCenters(Map<String, String> map) {
        map.putIfAbsent("page", "1");
        map.putIfAbsent("limit", "10");
        map.putIfAbsent("filter", "");
        map.putIfAbsent("order", "uno");
        map.putIfAbsent("sort", "desc");
        map.put("offset", String.valueOf(Integer.parseInt(map.get("limit")) * (Integer.parseInt(map.get("page")) - 1)));

        // filter parsing
        for(Map<String, String> fMap : Utils.parseFilter((String) map.get("filter"))) {
            if("center".equals(fMap.get("tbl"))) {
                map.put(fMap.get("col"), fMap.get("con"));
            }
        }

        // order parsing
        map.put("order_by", Utils.parseOrder(map.get("order"), map.get("sort")));

        Map<String, Object> result = new HashMap<>();
        result.put("count", centerMapper.cntCenters(map));
        result.put("rows", centerMapper.getCenters(map));

        return result;
    }

}
