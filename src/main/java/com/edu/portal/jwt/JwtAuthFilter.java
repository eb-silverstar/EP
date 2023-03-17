package com.edu.portal.jwt;

import com.edu.portal.common.ApiException;
import com.edu.portal.common.ApiResponseEntity;
import com.edu.portal.common.Constants;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
        try {
            if(StringUtils.hasText(request.getHeader("x-access-token"))) {
                Authentication authentication = JwtProvider.getAuthentication(request);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

            filterChain.doFilter(request, response);

        } catch (ApiException e) {
            setErrorResponse(response, e);
        } catch (Exception e) {
            setErrorResponse(response);
        }
    }

    public void setErrorResponse(HttpServletResponse response, ApiException e) {
        response.setStatus(HttpStatus.OK.value());
        response.setContentType("application/json");

        ApiResponseEntity apiResponseEntity = new ApiResponseEntity(false, e.getApiStatusEntity(), e.getApiStatusEntity());

        try {
            response.getWriter().print(new ObjectMapper().convertValue(apiResponseEntity, JSONObject.class));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void setErrorResponse(HttpServletResponse response) {
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setContentType("application/json");

        ApiResponseEntity apiResponseEntity = new ApiResponseEntity(false, Constants.FAILURE, Constants.FAILURE);

        try {
            response.getWriter().print(new ObjectMapper().convertValue(apiResponseEntity, JSONObject.class));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
