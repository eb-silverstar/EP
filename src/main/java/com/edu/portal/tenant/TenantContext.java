package com.edu.portal.tenant;

public class TenantContext {

    private static final ThreadLocal<String> TENANT = new ThreadLocal<>();

    public static String getTenant() {
        return TENANT.get();
    }

    public static void setTenant(String tenant) {
        TENANT.set(tenant);
    }

}
