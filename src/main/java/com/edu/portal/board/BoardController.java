package com.edu.portal.board;

import com.edu.portal.common.ApiResponseEntity;
import com.edu.portal.common.Constants;
import com.edu.portal.notice.NoticeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    /**
     * 보드 타입 목록 조회
     *
     * @param map
     * @return
     */
    @GetMapping("/type/list/{page}/{limit}/{filter}/{order}/{sort}")
    public ResponseEntity<ApiResponseEntity> getTypes(@PathVariable Map<String, String> map) {
        Map<String, Object> result = boardService.getTypes(map);

        return new ResponseEntity<ApiResponseEntity>(new ApiResponseEntity(true, Constants.SUCCESS, result), HttpStatus.OK);
    }

    /**
     * 보드 목록 조회
     * 
     * @param map
     * @return
     */
    @GetMapping("/list/{page}/{limit}/{filter}/{order}/{sort}")
    public ResponseEntity<ApiResponseEntity> getBoards(@PathVariable Map<String, String> map) {
        Map<String, Object> result = boardService.getBoards(map);

        return new ResponseEntity<ApiResponseEntity>(new ApiResponseEntity(true, Constants.SUCCESS, result), HttpStatus.OK);
    }

    /**
     * 보드 타입 정보 조회
     *
     * @param uno
     * @return
     */
    @GetMapping("/type/{uno}")
    public ResponseEntity<ApiResponseEntity> getType(@PathVariable("uno") int uno) {
        BoardTypeDTO result = boardService.getType(uno);

        if(result != null) {
            return new ResponseEntity<ApiResponseEntity>(new ApiResponseEntity(true, Constants.SUCCESS, result), HttpStatus.OK);
        } else {
            return new ResponseEntity<ApiResponseEntity>(new ApiResponseEntity(false, Constants.NO_DATA, result), HttpStatus.OK);
        }
    }

    /**
     * 보드 내용 조회
     *
     * @param uno
     * @return
     */
    @GetMapping("/{uno}")
    public ResponseEntity<ApiResponseEntity> getBoard(@PathVariable("uno") int uno) {
        BoardDTO result = boardService.getBoard(uno);

        if(result != null) {
            return new ResponseEntity<ApiResponseEntity>(new ApiResponseEntity(true, Constants.SUCCESS, result), HttpStatus.OK);
        } else {
            return new ResponseEntity<ApiResponseEntity>(new ApiResponseEntity(false, Constants.NO_DATA, result), HttpStatus.OK);
        }
    }

    /**
     * 보드 타입 등록
     *
     * @param type
     * @return
     */
    @PostMapping
    public ResponseEntity<ApiResponseEntity> createType(@RequestBody BoardTypeDTO type) {
        BoardTypeDTO result = boardService.createType(type);

        return new ResponseEntity<ApiResponseEntity>(new ApiResponseEntity(true, Constants.SUCCESS, result), HttpStatus.OK);
    }

    /**
     * 보드 등록
     *
     * @param board
     * @return
     */
    @PostMapping
    public ResponseEntity<ApiResponseEntity> creatBoard(@RequestBody BoardDTO board) {
        BoardDTO result = boardService.createBoard(board);

        return new ResponseEntity<ApiResponseEntity>(new ApiResponseEntity(true, Constants.SUCCESS, result), HttpStatus.OK);
    }

}
