package com.edu.portal.user;

import com.edu.portal.center.CenterService;
import com.edu.portal.common.ApiException;
import com.edu.portal.common.ApiStatusEntity;
import com.edu.portal.common.Constants;
import com.edu.portal.common.Utils;
import com.edu.portal.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtProvider jwtProvider;
    private final CenterService centerService;
    private final UserMapper userMapper;

    /**
     * 회원 목록 조회
     *
     * @param map
     * @return Map<String, Object>
     */
    public Map<String, Object> getUsers(Map<String, String> map) {
        if(map.isEmpty())  map = new HashMap<>();
        map.putIfAbsent("page", "1");
        map.putIfAbsent("limit", "10");
        map.putIfAbsent("filter", "");
        map.putIfAbsent("order", "uno");
        map.putIfAbsent("sort", "desc");
        map.put("offset", String.valueOf(Integer.parseInt(map.get("limit")) * (Integer.parseInt(map.get("page")) - 1)));

        // filter parsing
        for(Map<String, String> fMap : Utils.parseFilter(map.get("filter"))) {
            if("center".equals(fMap.get("tbl")) && "uno".equals(fMap.get("col"))) {
                map.put("cntr_uno", fMap.get("con"));
            } else {
                map.put(fMap.get("col"), fMap.get("con"));
            }
        }

        // order parsing
        map.put("order_by", Utils.parseOrder(map.get("order"), map.get("sort")));

        List<UserDTO> users = userMapper.getUsers(map);
        users.forEach(i -> {
            if(i.getCntrUno() != null) i.setCenter(centerService.getCenter(i.getCntrUno()));
        });

        Map<String, Object> result = new HashMap<>();
        result.put("count", userMapper.cntUsers(map));
        result.put("rows", users);

        return result;
    }

    /**
     * 회원 정보 조회 (전체 회원)
     *
     * @param uno
     * @return UserDTO
     */
    public UserDTO getUser(int uno) {
       return  userMapper.getUser(uno);
    }

    /**
     * 회원 정보 조회
     *
     * @param uno
     * @return UserDTO
     */
    public UserDTO getActiveUser(int uno) {
        return userMapper.getActiveUser(Map.of("uno", uno));
    }

    /**
     * 회원 정보 조회
     *
     * @param mbrId
     * @return UserDTO
     */
    public UserDTO getActiveUser(String mbrId) {
        return userMapper.getActiveUser(Map.of("mbrId", mbrId));
    }

    /**
     * 회원 정보 조회
     *
     * @param mbrNm
     * @param mbrTelno
     * @return UserDTO
     */
    public UserDTO getActiveUser(String mbrNm, String mbrTelno) {
        Map<String, Object> map = new HashMap<>();
        map.put("mbrNm", mbrNm);
        map.put("mbrTelno", mbrTelno);

        return userMapper.getActiveUser(map);
    }

    /**
     * 회원가입
     *
     * @param user
     * @return UserDTO
     */
    public UserDTO createUser(UserDTO user) {
        if(getActiveUser(user.getMbrId()) != null) {
            ApiStatusEntity status = Constants.ALREADY_DATA;
            status.setMessage("[ " + user.getMbrId() + " ]는 이미 등록된 회원 아이디 입니다.");
            throw new ApiException(status);
        }

        userMapper.insertUser(user);

        return getUser(user.getUno());
    }

    /**
     * 로그인
     *
     * @param user
     * @return UserDTO
     */
    public UserDTO login(UserDTO user) {
        Map<String, Object> map = new HashMap<>();
        map.put("mbrId", user.getMbrId());
        map.put("mbrAuthrt", user.getMbrAuthrt().split(","));

        UserDTO result = userMapper.getLoginUser(map);

        // 로그인 실패 (유효성 검증)
        if(result == null) {
            throw new ApiException(Constants.INVALID_USER_LOGIN_ID);
        }

        if("Y".equals(result.getUseLock())) {
            throw new ApiException(Constants.LOGIN_USE_LOCK);
        }

        if(!"Y".equals(result.getMbrAprvCdYn())) {
            throw new ApiException(Constants.LOGIN_APRV_CD_NO);
        }

        if(!result.getMbrPswd().equals(user.getMbrPswd())) {
            if(result.getMbrPswdErrCnt() < 5) {
                result.setMbrPswdErrCnt(result.getMbrPswdErrCnt() + 1);
                result.setUseLock(result.getMbrPswdErrCnt() >= 5 ? "Y" : "N");
                userMapper.updatePswdErr(result);
            }

            if("Y".equals(result.getUseLock())) {
                throw new ApiException(Constants.LOGIN_USE_LOCK);
            } else {
                throw new ApiException(Constants.INVALID_USER_LOGIN_PWRD);
            }
        }

        // 로그인 성공
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getMbrId(), user.getMbrPswd());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        result.setApiToken(jwtProvider.generateToken(authentication));
        result.setCenter(centerService.getCenter(result.getCntrUno()));

        if(result.getMbrPswdErrCnt() > 0) {
            result.setMbrPswdErrCnt(0);
            result.setUseLock("N");
            userMapper.updatePswdErr(result);
        }

        return result;
    }

    /**
     * 회원 정보 수정
     *
     * @param uno
     * @param user
     * @return UserDTO
     */
    public UserDTO modifyUser(int uno, UserDTO user) {
        UserDTO chkUser = getActiveUser(uno);

        if(chkUser != null && uno != chkUser.getUno()) {
            ApiStatusEntity status = Constants.ALREADY_DATA;
            status.setMessage("[ " + user.getMbrId() + " ]는 이미 등록된 회원 아이디 입니다.");
            throw new ApiException(status);
        }

        user.setUno(uno);
        if(userMapper.updateUser(user) == 0) {
            throw new ApiException(Constants.NO_DATA);
        }

        user = userMapper.getUser(uno);
        user.setCenter(centerService.getCenter(user.getCntrUno()));

        return user;
    }

    /**
     * 회원 비밀번호 변경
     *
     * @param user
     * @return String`
     */
    public String modifyPwd(UserDTO user) {
        String result = RandomStringUtils.randomAlphanumeric(8);
        user.setMbrPswd(DigestUtils.sha1Hex(result));

        if(userMapper.updatePswd(user) == 0) {
            throw new ApiException(Constants.NO_DATA);
        }

        return result;
    }

    /**
     * 회원 삭제
     *
     * @param uno
     */
    public void deleteUser(int uno) {
        if(userMapper.deleteUser(uno) == 0) {
            throw new ApiException(Constants.NO_DATA);
        }
    }

}
