package com.edu.portal.term;

import com.edu.portal.common.ApiException;
import com.edu.portal.common.Constants;
import com.edu.portal.common.Utils;
import com.edu.portal.notice.NoticeDTO;
import com.edu.portal.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TermService {

    private final UserService userService;
    private final TermMapper termMapper;

    /**
     * 버전 체크
     *
     * @param trmsSe
     * @return Map<String, Object>
     */
    public Map<String, Object> chkVer(int trmsSe) {
        Map<String, Object> result = new HashMap<>();
        result.put("count", termMapper.cntTermsBySe(trmsSe));
        result.put("rows", termMapper.getLastVer(trmsSe));

        return result;
    }

    /**
     * 약관 목록 조회
     *
     * @param map
     * @return Map<String, Object>
     */
    public Map<String, Object> getTerms(Map<String, String> map) {
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

        List<TermDTO> terms = termMapper.getTerms(map);
        terms.forEach(i -> {
            if(i.getRgtrUno() != null) i.setUser(userService.getUser(i.getRgtrUno()));
        });

        Map<String, Object> result = new HashMap<>();
        result.put("count", termMapper.cntTerms(map));
        result.put("rows", terms);

        return result;
    }

    /**
     * 약관 내용 조회
     *
     * @param uno
     * @return TermDTO
     */
    public TermDTO getTerm(int uno) {
        return termMapper.getTerm(uno);
    }

    /**
     * 약관 등록
     *
     * @param term
     * @return TermDTO
     */
    public TermDTO createTerm(TermDTO term) {
        termMapper.insertTerm(term);
        return getTerm(term.getUno());
    }

    /**
     * 약관 수정
     *
     * @param uno
     * @param term
     * @return int
     */
    public int modifyTerm(int uno, TermDTO term) {
        term.setUno(uno);
        int result = termMapper.updateTerm(term);

        if(result == 0) {
            throw new ApiException(Constants.NO_DATA);
        }

        return result;
    }

    /**
     * 약관 삭제
     *
     * @param uno
     * @return int
     */
    public int deleteTerm(int uno) {
        int result = termMapper.deleteTerm(uno);

        if(result == 0) {
            throw new ApiException(Constants.NO_DATA);
        }

        return result;
    }

}
