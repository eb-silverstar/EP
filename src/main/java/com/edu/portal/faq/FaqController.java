package com.edu.portal.faq;

import com.edu.portal.common.ApiResponseEntity;
import com.edu.portal.common.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/faq")
@RequiredArgsConstructor
public class FaqController {

    private final FaqService faqService;

    /**
     * FAQ 목록 조회
     *
     * @param map
     * @return
     */
    @GetMapping("/list/{page}/{limit}/{filter}/{order}/{sort}")
    public ResponseEntity<ApiResponseEntity> getFaqs(@PathVariable Map<String, String> map) {
        Map<String, Object> result = faqService.getFaqs(map);

        return new ResponseEntity<ApiResponseEntity>(new ApiResponseEntity(true, Constants.SUCCESS, result), HttpStatus.OK);
    }

    /**
     * FAQ 내용 조회
     *
     * @param uno
     * @return
     */
    @GetMapping("/{uno}")
    public ResponseEntity<ApiResponseEntity> getFaq(@PathVariable("uno") int uno) {
        FaqDTO result = faqService.getFaq(uno);

        if(result != null) {
            return new ResponseEntity<ApiResponseEntity>(new ApiResponseEntity(true, Constants.SUCCESS, result), HttpStatus.OK);
        } else {
            return new ResponseEntity<ApiResponseEntity>(new ApiResponseEntity(false, Constants.NO_DATA, result), HttpStatus.OK);
        }
    }

    /**
     * FAQ 등록
     *
     * @param faq
     * @return
     */
    @PostMapping
    public ResponseEntity<ApiResponseEntity> createFaq(@RequestBody FaqDTO faq) {
        FaqDTO result = faqService.createFaq(faq);

        return new ResponseEntity<ApiResponseEntity>(new ApiResponseEntity(true, Constants.SUCCESS, result), HttpStatus.OK);
    }

    /**
     * FAQ 수정
     *
     * @param uno
     * @param faq
     * @return
     */
    @PutMapping("/{uno}")
    public ResponseEntity<ApiResponseEntity> modifyFaq(@PathVariable("uno") int uno, @RequestBody FaqDTO faq) {
        int result = faqService.modifyFaq(uno, faq);

        return new ResponseEntity<ApiResponseEntity>(new ApiResponseEntity(true, Constants.SUCCESS, result), HttpStatus.OK);
    }

    /**
     * FAQ 삭제
     *
     * @param uno
     * @return
     */
    @DeleteMapping("/{uno}")
    public ResponseEntity<ApiResponseEntity> deleteFaq(@PathVariable("uno") int uno) {
        int result = faqService.deleteFaq(uno);

        return new ResponseEntity<ApiResponseEntity>(new ApiResponseEntity(true, Constants.SUCCESS, result), HttpStatus.OK);
    }

}
