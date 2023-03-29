package com.edu.portal.term;

import com.edu.portal.user.UserDTO;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TermDTO {

    /**
     * 일련번호
     */
    private Integer uno;

    /**
     * 타입 (1: 이용약관, 2: 개인정보 처리방침)
     */
    private Integer trmsSe;

    /**
     * 타이틀
     */
    private String trmsTtl;

    /**
     * 내용
     */
    private String trmsCn;

    /**
     * 버전 정보
     */
    private String trmsVer;

    /**
     * 고정 여부
     */
    private String fixedYn;

    /**
     * 등록자 고유번호
     */
    private Integer RgtrUno;

    /**
     * 등록일시
     */
    private String regDt;

    /**
     * 수정일시
     */
    private String chgDt;

    /**
     * 등록자
     */
    private UserDTO user;

}
