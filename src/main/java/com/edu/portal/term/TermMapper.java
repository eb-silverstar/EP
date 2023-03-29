package com.edu.portal.term;

import com.edu.portal.notice.NoticeDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface TermMapper {

    public int cntTerms(Map<String, String> map);
    public int cntTermsBySe(int trmsSe);
    public List<TermDTO> getTerms(Map<String, String> map);
    public TermDTO getTerm(int uno);
    public TermDTO getLastVer(int trmsSe);
    public int insertTerm(TermDTO term);
    public int updateTerm(TermDTO term);
    public int deleteTerm(int uno);

}
