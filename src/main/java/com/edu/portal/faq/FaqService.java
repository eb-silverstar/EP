package com.edu.portal.faq;

import com.edu.portal.common.ApiException;
import com.edu.portal.common.Constants;
import com.edu.portal.common.Utils;
import com.edu.portal.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class FaqService {

    private final UserService userService;
    private final FaqMapper faqMapper;

    /**
     * FAQ 목록 조회
     *
     * @param map
     * @return Map<String, Object>
     */
    public Map<String, Object> getFaqs(Map<String, String> map) {
        map.putIfAbsent("page", "1");
        map.putIfAbsent("limit", "10");
        map.putIfAbsent("filter", "");
        map.putIfAbsent("order", "uno");
        map.putIfAbsent("sort", "desc");
        map.put("offset", String.valueOf(Integer.parseInt(map.get("limit")) * (Integer.parseInt(map.get("page")) - 1)));

        // filter parsing
        for(Map<String, String> fMap : Utils.parseFilter(map.get("filter"))) {
            map.put(fMap.get("col"), fMap.get("con"));
        }

        // order parsing
        map.put("order_by", Utils.parseOrder(map.get("order"), map.get("sort")));

        List<FaqDTO> faqs = faqMapper.getFaqs(map);
        faqs.forEach(i -> {
            if(i.getRgtrUno() != null) i.setUser(userService.getUser(i.getRgtrUno()));
        });

        Map<String, Object> result = new HashMap<>();
        result.put("count", faqMapper.cntFaqs(map));
        result.put("rows", faqs);

        return result;
    }

    /**
     * FAQ 내용 조회
     *
     * @param uno
     * @return FaqDTO
     */
    public FaqDTO getFaq(int uno) {
        return faqMapper.getFaq(uno);
    }

    /**
     * FAQ 등록
     *
     * @param faq
     * @return FaqDTO
     */
    public FaqDTO createFaq(FaqDTO faq) {
        faqMapper.insertFaq(faq);
        return getFaq(faq.getUno());
    }

    /**
     * FAQ 수정
     *
     * @param uno
     * @param faq
     * @return int
     */
    public int modifyFaq(int uno, FaqDTO faq) {
        faq.setUno(uno);
        int result = faqMapper.updateFaq(faq);

        if(result == 0) {
            throw new ApiException(Constants.NO_DATA);
        }

        return result;
    }

    /**
     * FAQ 삭제
     *
     * @param uno
     * @return int
     */
    public int deleteFaq(int uno) {
        int result = faqMapper.deleteFaq(uno);

        if(result == 0) {
            throw new ApiException(Constants.NO_DATA);
        }

        return result;
    }

}
