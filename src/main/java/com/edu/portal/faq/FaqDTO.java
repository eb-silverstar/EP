package com.edu.portal.faq;

import com.edu.portal.user.UserDTO;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class FaqDTO {

    /**
     * 일련번호
     */
    private Integer uno;

    /**
     * 카테고리 (1: 이용안내, 2: 멘토링, 3: 온라인학습, 4: 온라인독서, 5: MR스포츠)
     */
    private String faqSe;

    /**
     * 질문
     */
    private String qstnCn;

    /**
     * 답변
     */
    private String ansCn;

    /**
     * 등록자 고유번호
     */
    private Integer rgtrUno;

    /**
     * 관리자 고유번호
     */
    private Integer mngrUno;

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
