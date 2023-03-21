package com.edu.portal.center;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CenterMapper {

    public int cntCenters(Map<String, String> map);
    public List<CenterDTO> getCenters(Map<String, String> map);
    public CenterDTO getCenter(int uno);
    public CenterDTO getCenterByNm(String cntrNm);
    public int insertCenter(CenterDTO center);
    public int updateCenter(CenterDTO center);
    public int deleteCenter(int uno);

}
