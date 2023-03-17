package com.edu.portal.user;

import com.edu.portal.common.ApiResponseEntity;
import com.edu.portal.common.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 사용자 ID 중복 체크
     *
     * @param mbrId
     * @return
     */
    @GetMapping("/id/dup/{mbrId}")
    public ResponseEntity<ApiResponseEntity> chkId(@PathVariable("mbrId") String mbrId) {
        UserDTO result = userService.getActiveUser(mbrId);

        if(result != null) {
            return new ResponseEntity<ApiResponseEntity>(new ApiResponseEntity(true, Constants.SUCCESS, result), HttpStatus.OK);
        } else {
            return new ResponseEntity<ApiResponseEntity>(new ApiResponseEntity(false, Constants.NO_DATA, result), HttpStatus.OK);
        }
    }

    /**
     * 회원 목록 조회
     *
     * @param map
     * @return
     */
    @GetMapping({"/list", "/list/{page}/{limit}/{filter}/{order}/{sort}"})
    public ResponseEntity<ApiResponseEntity> getUsers(@PathVariable Map<String, String> map) {
        Map<String, Object> result = userService.getUsers(map);

        return new ResponseEntity<ApiResponseEntity>(new ApiResponseEntity(true, Constants.SUCCESS, result), HttpStatus.OK);
    }

    /**
     * 회원 정보 조회
     *
     * @param uno
     * @return
     */
    @GetMapping("/{uno}")
    public ResponseEntity<ApiResponseEntity> getUser(@PathVariable("uno") int uno) {
        UserDTO result = userService.getUser(uno);

        if(result != null) {
            return new ResponseEntity<ApiResponseEntity>(new ApiResponseEntity(true, Constants.SUCCESS, result), HttpStatus.OK);
        } else {
            return new ResponseEntity<ApiResponseEntity>(new ApiResponseEntity(false, Constants.NO_DATA, result), HttpStatus.OK);
        }
    }

    /**
     * 회원 ID 찾기
     *
     * @param mbrNm
     * @param mbrTelno
     * @return
     */
    @GetMapping("/id/find/by/name/{mbrNm}/{mbrTelno}")
    public ResponseEntity<ApiResponseEntity> findId(@PathVariable("mbrNm") String mbrNm, @PathVariable("mbrTelno") String mbrTelno) {
        UserDTO result = userService.getActiveUser(mbrNm, mbrTelno);

        if(result != null) {
            return new ResponseEntity<ApiResponseEntity>(new ApiResponseEntity(true, Constants.SUCCESS, result), HttpStatus.OK);
        } else {
            return new ResponseEntity<ApiResponseEntity>(new ApiResponseEntity(false, Constants.NO_DATA, result), HttpStatus.OK);
        }
    }

    /**
     * 회원 비밀번호 찾기
     *
     * @param mbrId
     * @return
     */
    @GetMapping("/pwd/find/{mbrId}")
    public ResponseEntity<ApiResponseEntity> findPwd(@PathVariable("mbrId") String mbrId) {
        UserDTO result = userService.getActiveUser(mbrId);

        if(result != null) {
            return new ResponseEntity<ApiResponseEntity>(new ApiResponseEntity(true, Constants.SUCCESS, result), HttpStatus.OK);
        } else {
            return new ResponseEntity<ApiResponseEntity>(new ApiResponseEntity(false, Constants.NO_DATA, result), HttpStatus.OK);
        }
    }

    /**
     * 회원가입
     *
     * @param user
     * @return
     */
    @PostMapping
    public ResponseEntity<ApiResponseEntity> createUser(@RequestBody UserDTO user) {
        UserDTO result = userService.createUser(user);

        return new ResponseEntity<ApiResponseEntity>(new ApiResponseEntity(true, Constants.SUCCESS, result), HttpStatus.OK);
    }

    /**
     * 로그인
     *
     * @param user
     * @return
     */
    @PostMapping("/login")
    public ResponseEntity<ApiResponseEntity> login(@RequestBody UserDTO user) {
        UserDTO result = userService.login(user);

        return new ResponseEntity<ApiResponseEntity>(new ApiResponseEntity(true, Constants.SUCCESS, result), HttpStatus.OK);
    }

    /**
     * 회원 정보 수정
     *
     * @param uno
     * @return
     */
    @PutMapping("/{uno}")
    public ResponseEntity<ApiResponseEntity> modifyUser(@PathVariable("uno") int uno, @RequestBody UserDTO user) {
        UserDTO result = userService.modifyUser(uno, user);

        return new ResponseEntity<ApiResponseEntity>(new ApiResponseEntity(true, Constants.SUCCESS, result), HttpStatus.OK);
    }

    /**
     * 회원 비밀번호 변경`
     *
     * @param user
     * @return
     */
    @PutMapping("/pwd/change")
    public ResponseEntity<ApiResponseEntity> modifyPwd(@RequestBody UserDTO user) {
        String result = userService.modifyPwd(user);

        return new ResponseEntity<ApiResponseEntity>(new ApiResponseEntity(true, Constants.SUCCESS, result), HttpStatus.OK);
    }

    /**
     * 회원 삭제
     *
     * @param uno
     * @return
     */
    @DeleteMapping("/{uno}")
    public ResponseEntity<ApiResponseEntity> deleteUser(@PathVariable("uno") int uno) {
        userService.deleteUser(uno);

        return new ResponseEntity<ApiResponseEntity>(new ApiResponseEntity(true, Constants.SUCCESS, null), HttpStatus.OK);
    }

}