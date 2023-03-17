package com.edu.portal.tenant;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class TenantDataSource extends AbstractRoutingDataSource {

    @Override
    protected String determineCurrentLookupKey() {
        return TenantContext.getTenant();
    }

}
