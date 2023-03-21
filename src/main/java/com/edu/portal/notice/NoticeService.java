package com.edu.portal.notice;

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
public class NoticeService {

    private final UserService userService;
    private final NoticeMapper noticeMapper;

    /**
     * 공지 목록 조회
     *
     * @param map
     * @return Map<String, Object>
     */
    public Map<String, Object> getNotices(Map<String, String> map) {
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

        List<NoticeDTO> notices = noticeMapper.getNotices(map);
        notices.forEach(i -> {
            if(i.getRgtrUno() != null) i.setUser(userService.getUser(i.getRgtrUno()));
        });

        Map<String, Object> result = new HashMap<>();
        result.put("count", noticeMapper.cntNotices(map));
        result.put("rows", notices);

        return result;
    }

    /**
     * 공지 내용 조회
     *
     * @param uno
     * @return NoticeDTO
     */
    public NoticeDTO getNotice(int uno) {
        return noticeMapper.getNotice(uno);
    }

    /**
     * 공지 등록
     *
     * @param notice
     * @return NoticeDTO
     */
    public NoticeDTO createNotice(NoticeDTO notice) {
        noticeMapper.insertNotice(notice);
        return getNotice(notice.getUno());
    }

    /**
     * 공지 내용 수정
     *
     * @param uno
     * @param notice
     * @return int
     */
    public int modifyNotice(int uno, NoticeDTO notice) {
        notice.setUno(uno);
        int result = noticeMapper.updateNotice(notice);

        if(result == 0) {
            throw new ApiException(Constants.NO_DATA);
        }

        return result;
    }

    /**
     * 공지 삭제
     *
     * @param uno
     * @return int
     */
    public int deleteNotice(int uno) {
        int result = noticeMapper.deleteNotice(uno);

        if(result == 0) {
            throw new ApiException(Constants.NO_DATA);
        }

        return result;
    }

}
