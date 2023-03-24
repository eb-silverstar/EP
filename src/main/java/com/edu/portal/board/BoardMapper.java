package com.edu.portal.board;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface BoardMapper {

    public int cntTypes(Map<String, String> map);
    public List<BoardTypeDTO> getTypes(Map<String, String> map);
    public int cntBoards(Map<String, String> map);
    public List<BoardDTO> getBoards(Map<String, String> map);
    public BoardTypeDTO getType(int uno);
    public BoardTypeDTO getTypeByNm(String bbsTypeNm);
    public BoardDTO getBoard(int uno);
    public int insertType(BoardTypeDTO type);
    public int insertBoard(BoardDTO board);
    public int updateType(BoardTypeDTO type);
    public int updateBoard(BoardDTO board);
    public int deleteType(int uno);
    public int deleteBoard(int uno);

}
