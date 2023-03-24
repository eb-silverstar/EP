package com.edu.portal.board;

import com.edu.portal.common.ApiResponseEntity;
import com.edu.portal.common.Constants;
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
    @PostMapping("/type")
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

    /**
     * 보드 타입 수정
     *
     * @param uno
     * @param type
     * @return
     */
    @PutMapping("/type/{uno}")
    public ResponseEntity<ApiResponseEntity> modifyType(@PathVariable("uno") int uno, @RequestBody BoardTypeDTO type) {
        int result = boardService.modifyType(uno, type);

        return new ResponseEntity<ApiResponseEntity>(new ApiResponseEntity(true, Constants.SUCCESS, result), HttpStatus.OK);
    }

    /**
     * 보드 수정
     *
     * @param uno
     * @param board
     * @return
     */
    @PutMapping("/{uno}")
    public ResponseEntity<ApiResponseEntity> modifyBoard(@PathVariable("uno") int uno, @RequestBody BoardDTO board) {
        int result = boardService.modifyBoard(uno, board);

        return new ResponseEntity<ApiResponseEntity>(new ApiResponseEntity(true, Constants.SUCCESS, result), HttpStatus.OK);
    }

    /**
     * 보드 타입 삭제
     *
     * @param uno
     * @return
     */
    @DeleteMapping("/type/{uno}")
    public ResponseEntity<ApiResponseEntity> deleteType(@PathVariable("uno") int uno) {
        int result = boardService.deleteType(uno);

        return new ResponseEntity<ApiResponseEntity>(new ApiResponseEntity(true, Constants.SUCCESS, result), HttpStatus.OK);
    }

    /**
     * 보드 삭제
     *
     * @param uno
     * @return
     */
    @DeleteMapping("/{uno}")
    public ResponseEntity<ApiResponseEntity> deleteBoard(@PathVariable("uno") int uno) {
        int result = boardService.deleteBoard(uno);

        return new ResponseEntity<ApiResponseEntity>(new ApiResponseEntity(true, Constants.SUCCESS, result), HttpStatus.OK);
    }

}
