package com.edu.portal.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponseEntity {

    /**
     * 성공 여부
     */
    private boolean success;

    /**
     * 처리 상태
     */
    private ApiStatusEntity status;

    /**
     * 응답 내용
     */
    private Object data;

}
