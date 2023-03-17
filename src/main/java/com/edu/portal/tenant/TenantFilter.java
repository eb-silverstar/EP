package com.edu.portal.tenant;

import com.edu.portal.jwt.JwtProvider;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
@Order(1)
public class TenantFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String tenant = JwtProvider.getTenant((HttpServletRequest) request);
        TenantContext.setTenant(tenant);

        try {
            chain.doFilter(request, response);
        } finally {
            TenantContext.setTenant("");
        }
    }

}
