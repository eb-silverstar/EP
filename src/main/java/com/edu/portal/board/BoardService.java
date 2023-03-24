package com.edu.portal.board;

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
public class BoardService {

    private final UserService userService;
    private final BoardMapper boardMapper;

    /**
     * 보드 타입 목록 조회
     *
     * @param map
     * @return Map<String, Object>
     */
    public Map<String, Object> getTypes(Map<String, String> map) {
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

        Map<String, Object> result = new HashMap<>();
        result.put("count", boardMapper.cntTypes(map));
        result.put("rows", boardMapper.getTypes(map));

        return result;
    }

    /**
     * 보드 목록 조회
     *
     * @param map
     * @return Map<String, Object>
     */
    public Map<String, Object> getBoards(Map<String, String> map) {
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

        List<BoardDTO> boards = boardMapper.getBoards(map);
        boards.forEach(i -> {
            if(i.getBbsTypeUno() != null) i.setBoardType(getType(i.getBbsTypeUno()));
            if(i.getRgtrUno() != null) i.setUser(userService.getUser(i.getRgtrUno()));
        });

        Map<String, Object> result = new HashMap<>();
        result.put("count", boardMapper.cntBoards(map));
        result.put("rows", boards);

        return result;
    }

    /**
     * 보드 타입 정보 조회
     *
     * @param uno
     * @return BoardTypeDTO
     */
    public BoardTypeDTO getType(int uno) {
        return boardMapper.getType(uno);
    }

    /**
     * 보드 타입 정보 조회
     *
     * @param typeNm
     * @return BoardTypeDTO
     */
    public BoardTypeDTO getType(String typeNm) {
        return boardMapper.getTypeByNm(typeNm);
    }

    /**
     * 보드 내용 조회
     * 
     * @param uno
     * @return BoardDTO
     */
    public BoardDTO getBoard(int uno) {
        return boardMapper.getBoard(uno);
    }

    /**
     * 보드 타입 등록
     *
     * @param type
     * @return BoardTypeDTO
     */
    public BoardTypeDTO createType(BoardTypeDTO type) {
        if(getType(type.getBbsTypeNm()) != null) {
            throw new ApiException(Constants.ALREADY_DATA);
        }

        boardMapper.insertType(type);

        return getType(type.getUno());
    }

    /**
     * 보드 등록
     *
     * @param board
     * @return BoardDTO
     */
    public BoardDTO createBoard(BoardDTO board) {
        boardMapper.insertBoard(board);
        return getBoard(board.getUno());
    }

    /**
     * 보드 타입 수정
     *
     * @param uno
     * @param type
     * @return int
     */
    public int modifyType(int uno, BoardTypeDTO type) {
        type.setUno(uno);
        int result = boardMapper.updateType(type);

        if(result == 0) {
            throw new ApiException(Constants.NO_DATA);
        }

        return result;
    }

    /**
     * 보드 수정
     *
     * @param uno
     * @param board
     * @return int
     */
    public int modifyBoard(int uno, BoardDTO board) {
        board.setUno(uno);
        int result = boardMapper.updateBoard(board);

        if(result == 0) {
            throw new ApiException(Constants.NO_DATA);
        }

        return result;
    }

    /**
     * 보드 타입 삭제
     *
     * @param uno
     * @return int
     */
    public int deleteType(int uno) {
        int result = boardMapper.deleteType(uno);

        if(result == 0) {
            throw new ApiException(Constants.NO_DATA);
        }

        return result;
    }

    /**
     * 보드 삭제
     *
     * @param uno
     * @return int
     */
    public int deleteBoard(int uno) {
        int result = boardMapper.deleteBoard(uno);

        if(result == 0) {
            throw new ApiException(Constants.NO_DATA);
        }

        return result;
    }

}
