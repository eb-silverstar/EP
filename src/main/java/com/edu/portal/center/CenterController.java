package com.edu.portal.center;

import com.edu.portal.common.ApiResponseEntity;
import com.edu.portal.common.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/center")
@RequiredArgsConstructor
public class CenterController {

    private final CenterService centerService;

    /**
     * 센터 목록 조회
     *
     * @param map
     * @return
     */
    @GetMapping("/list/{page}/{limit}/{filter}/{order}/{sort}")
    public ResponseEntity<ApiResponseEntity> getCenters(@PathVariable Map<String, String> map) {
        Map<String, Object> result = centerService.getCenters(map);

        return new ResponseEntity<ApiResponseEntity>(new ApiResponseEntity(true, Constants.SUCCESS, result), HttpStatus.OK);
    }

}