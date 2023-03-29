package com.edu.portal.term;

import com.edu.portal.common.ApiResponseEntity;
import com.edu.portal.common.Constants;
import com.edu.portal.notice.NoticeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/term")
@RequiredArgsConstructor
public class TermController {

    private final TermService termService;

    /**
     * 버전 체크
     *
     * @param trmsSe
     * @return
     */
    @GetMapping("/ver/{trmsSe}")
    public ResponseEntity<ApiResponseEntity> chkVer(@PathVariable("trmsSe") int trmsSe) {
        Map<String, Object> result = termService.chkVer(trmsSe);

        if((int) result.get("count") > 0) {
            return new ResponseEntity<ApiResponseEntity>(new ApiResponseEntity(true, Constants.SUCCESS, result), HttpStatus.OK);
        } else {
            return new ResponseEntity<ApiResponseEntity>(new ApiResponseEntity(false, Constants.NO_DATA, result), HttpStatus.OK);
        }
    }

    /**
     * 약관 목록 조회
     *
     * @param map
     * @return
     */
    @GetMapping("/list/{page}/{limit}/{filter}/{order}/{sort}")
    public ResponseEntity<ApiResponseEntity> getTerms(@PathVariable Map<String, String> map) {
        Map<String, Object> result = termService.getTerms(map);

        return new ResponseEntity<ApiResponseEntity>(new ApiResponseEntity(true, Constants.SUCCESS, result), HttpStatus.OK);
    }

    /**
     * 약관 내용 조회
     *
     * @param uno
     * @return
     */
    @GetMapping("/{uno}")
    public ResponseEntity<ApiResponseEntity> getTerm(@PathVariable("uno") int uno) {
        TermDTO result = termService.getTerm(uno);

        if(result != null) {
            return new ResponseEntity<ApiResponseEntity>(new ApiResponseEntity(true, Constants.SUCCESS, result), HttpStatus.OK);
        } else {
            return new ResponseEntity<ApiResponseEntity>(new ApiResponseEntity(false, Constants.NO_DATA, result), HttpStatus.OK);
        }
    }

    /**
     * 약관 등록
     *
     * @param term
     * @return
     */
    @PostMapping
    public ResponseEntity<ApiResponseEntity> createTerm(@RequestBody TermDTO term) {
        TermDTO result = termService.createTerm(term);

        return new ResponseEntity<ApiResponseEntity>(new ApiResponseEntity(true, Constants.SUCCESS, result), HttpStatus.OK);
    }

    /**
     * 약관 수정
     *
     * @param uno
     * @param term
     * @return
     */
    @PutMapping("/{uno}")
    public ResponseEntity<ApiResponseEntity> modifyTerm(@PathVariable("uno") int uno, @RequestBody TermDTO term) {
        int result = termService.modifyTerm(uno, term);

        return new ResponseEntity<ApiResponseEntity>(new ApiResponseEntity(true, Constants.SUCCESS, result), HttpStatus.OK);
    }

    /**
     * 약관 삭제
     *
     * @param uno
     * @return
     */
    @DeleteMapping("/{uno}")
    public ResponseEntity<ApiResponseEntity> deleteTerm(@PathVariable("uno") int uno) {
        int result = termService.deleteTerm(uno);

        return new ResponseEntity<ApiResponseEntity>(new ApiResponseEntity(true, Constants.SUCCESS, result), HttpStatus.OK);
    }

}
