package com.edu.portal.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.edu.portal.center.CenterDTO;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserDTO {

    /**
     * 일련번호
     */
    private Integer uno;

    /**
     * 회원 아이디
     */
    private String mbrId;

    /**
     * 회원 이름
     */
    private String mbrNm;

    /**
     * 회원 비밀번호
     */
    private String mbrPswd;

    /**
     * 회원 전화번호
     */
    private String mbrTelno;

    /**
     * 회원 이메일
     */
    private String mbrEmlAddr;

    /**
     * 회원 구분코드 (1: 일반회원, 2: 멘토회원, 3:센터관리자, 4: 지자체관리자, 5: 플랫폼관리자)
     */
    private String mbrAuthrt;

    /**
     * 인증코드 승인여부
     */
    private String mbrAprvCdYn;

    /**
     * 비밀번호 만료 일시 (최초 3일)
     */
    private String mbrPswdVldDt;

    /**
     * 로그인 비밀번호 틀린 횟수
     */
    private Integer mbrPswdErrCnt;

    /**
     * 계정 잠김 (비밀번호 오류 횟수 초과, 비밀번호 만료일 초과 시)
     */
    private String useLock;

    /**
     * 사용여부 (탈퇴 등)
     */
    private String useYn;

    /**
     * 가입상태 (1: 직접가입, 2: 개별등록, 3: 일괄등록)
     */
    private String useState;

    /**
     * 센터고유번호
     */
    private Integer cntrUno;

    /**
     * 등록처리 관리자 참조번호
     */
    private Integer regMntrUno;

    /**
     * 승인처리 관리자 참조번호
     */
    private Integer aprvMntrUno;

    /**
     * 개인정보 동의 여부
     */
    private String mbrPrvcAgreYn;

    /**
     * 등록일시
     */
    private String regDt;

    /**
     * 수정일시
     */
    private String chgDt;

    /**
     * 센터
     */
    private CenterDTO center;

    /**
     * Access Token
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String apiToken;

}