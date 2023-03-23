package com.edu.portal.board;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class BoardTypeDTO {

    /**
     * 일련번호
     */
    private Integer uno;

    /**
     * 게시판명
     */
    private String bbsTypeNm;

    /**
     * 게시여부
     */
    private String bbsTypePstg;

    /**
     * 파일 업로드 여부
     */
    private String bbsFileYn;

    /**
     * 등록일시
     */
    private String regDt;

    /**
     * 수정일시
     */
    private String chgDt;

}
