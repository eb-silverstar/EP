package com.edu.portal.board;

import com.edu.portal.user.UserDTO;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class BoardDTO {

    /**
     * 일련번호
     */
    private Integer uno;

    /**
     * 게시판 타입 참조
     */
    private Integer bbsTypeUno;

    /**
     * 타이틀
     */
    private String bbsTtl;

    /**
     * 내용
     */
    private String bbsCn;

    /**
     * 파일명
     */
    private String bbsFile;

    /**
     * 원본 파일명
     */
    private String bbsFileOrgm;

    /**
     * 고정 여부
     */
    private String fixedYn;

    /**
     * 등록자
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
     * 보드 타입
     */
    private BoardTypeDTO boardType;

    /**
     * 회원
     */
    private UserDTO user;

}
