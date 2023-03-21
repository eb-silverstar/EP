package com.edu.portal.notice;

import com.edu.portal.common.ApiResponseEntity;
import com.edu.portal.common.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/notice")
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    /**
     * 공지 목록 조회
     *
     * @param map
     * @return
     */
    @GetMapping("/list/{page}/{limit}/{filter}/{order}/{sort}")
    public ResponseEntity<ApiResponseEntity> getNotices(@PathVariable Map<String, String> map) {
        Map<String, Object> result = noticeService.getNotices(map);

        return new ResponseEntity<ApiResponseEntity>(new ApiResponseEntity(true, Constants.SUCCESS, result), HttpStatus.OK);
    }

    /**
     * 공지 내용 조회
     *
     * @param uno
     * @return
     */
    @GetMapping("/{uno}")
    public ResponseEntity<ApiResponseEntity> getNotice(@PathVariable("uno") int uno) {
        NoticeDTO result = noticeService.getNotice(uno);

        if(result != null) {
            return new ResponseEntity<ApiResponseEntity>(new ApiResponseEntity(true, Constants.SUCCESS, result), HttpStatus.OK);
        } else {
            return new ResponseEntity<ApiResponseEntity>(new ApiResponseEntity(false, Constants.NO_DATA, result), HttpStatus.OK);
        }
    }

    /**
     * 공지 등록
     *
     * @param notice
     * @return
     */
    @PostMapping
    public ResponseEntity<ApiResponseEntity> createNotice(@RequestBody NoticeDTO notice) {
        NoticeDTO result = noticeService.createNotice(notice);

        return new ResponseEntity<ApiResponseEntity>(new ApiResponseEntity(true, Constants.SUCCESS, result), HttpStatus.OK);
    }

    /**
     * 공지 내용 수정
     *
     * @param uno
     * @param notice
     * @return
     */
    @PutMapping("/{uno}")
    public ResponseEntity<ApiResponseEntity> modifyNotice(@PathVariable("uno") int uno, @RequestBody NoticeDTO notice) {
        int result = noticeService.modifyNotice(uno, notice);

        return new ResponseEntity<ApiResponseEntity>(new ApiResponseEntity(true, Constants.SUCCESS, result), HttpStatus.OK);
    }

    /**
     * 공지 삭제
     *
     * @param uno
     * @return
     */
    @DeleteMapping("/{uno}")
    public ResponseEntity<ApiResponseEntity> deleteNotice(@PathVariable("uno") int uno) {
        int result = noticeService.deleteNotice(uno);

        return new ResponseEntity<ApiResponseEntity>(new ApiResponseEntity(true, Constants.SUCCESS, result), HttpStatus.OK);
    }

}
