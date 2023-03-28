package com.edu.portal.faq;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface FaqMapper {

    public int cntFaqs(Map<String, String> map);
    public List<FaqDTO> getFaqs(Map<String, String> map);
    public FaqDTO getFaq(int uno);
    public int insertFaq(FaqDTO faq);
    public int updateFaq(FaqDTO faq);
    public int deleteFaq(int uno);

}
