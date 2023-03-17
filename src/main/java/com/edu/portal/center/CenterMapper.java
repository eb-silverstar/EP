package com.edu.portal.center;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CenterMapper {

    public int cntCenters(Map<String, String> map);
    public List<CenterDTO> getCenters(Map<String, String> map);
    public CenterDTO getCenter(int uno);

}
