package com.lee.plcanalysis.security;

public class SecurityConstants {
    public static final String SECRET = "MapsSecretKeyToGenJWTs";
    public static final long EXPIRATION_TIME = 86_400_000; // 1 day
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";

    public static final String[] AUTH_WHITELIST = {
            "/user/*",
            "/login",
            "/login*",
            "/*.js",
            "/fontawesome*",
            "/favicon.ico",
            "/data-table*",
            "/"
    };
}
