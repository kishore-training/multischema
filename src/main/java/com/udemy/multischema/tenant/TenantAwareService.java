package com.udemy.multischema.tenant;

public class TenantAwareService {

    public static final String TENANT_COOKIE_NAME="TENANT_ID";
    public static final String ADMIN_TENANT="ADMIN_TENANT";

    private static ThreadLocal<String> tenantContext = new ThreadLocal<>();

    public static String getTenant() {
        return tenantContext.get();
    }

    public static void setTenant(String tenant) {
        tenantContext.set(tenant);
    }
}
