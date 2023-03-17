package com.edu.portal.center;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CenterDTO {

    /**
     * 일련번호
     */
    private Integer uno;

    /**
     * 센터명
     */
    private String cntrNm;

    /**
     * 주소
     */
    private String cntrAddr;

    /**
     * 센터 전화번호
     */
    private String cntrTelno;

    /**
     * 인증코드 (등록 시 랜덤생성)
     */
    private String cntrAprvCd;

    /**
     * MR 스포츠 기기 고유번호
     */
    private String devcId;

    /**
     * 등록자 고유번호
     */
    private Integer rgtrUno;

    /**
     * 등록일시
     */
    private String regDt;

    /**
     * 수정일시
     */
    private String chgDt;

}
