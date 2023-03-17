package com.edu.portal.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class ApiStatusEntity {

    /**
     * 상태 코드
     */
    private int code;

    /**
     * 메세지
     */
    private String message;

    /**
     * 상세 내용
     */
    private String detail;

}
