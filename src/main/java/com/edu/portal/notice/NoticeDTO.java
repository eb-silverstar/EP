package com.edu.portal.notice;

import com.edu.portal.user.UserDTO;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class NoticeDTO {

    /**
     * 일련번호
     */
    private Integer uno;

    /**
     * 타이틀
     */
    private String ntcTtl;

    /**
     * 내용
     */
    private String ntcCn;

    /**
     * 고정 여부
     */
    private String fixedYn;

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

    /**
     * 등록자
     */
    private UserDTO user;
    
}
