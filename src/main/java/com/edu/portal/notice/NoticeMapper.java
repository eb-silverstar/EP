package com.edu.portal.notice;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface NoticeMapper {

    public int cntNotices(Map<String, String> map);
    public List<NoticeDTO> getNotices(Map<String, String> map);
    public NoticeDTO getNotice(int uno);
    public int insertNotice(NoticeDTO notice);
    public int updateNotice(NoticeDTO notice);
    public int deleteNotice(int uno);

}
