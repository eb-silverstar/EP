package com.edu.portal.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiException extends RuntimeException {

    private ApiStatusEntity apiStatusEntity;

}
