package com.accenture.SmartOffice;

public class SecurityConstants {
    public static final String SECRET = "SecretKeyToGenJWTs";
    public static final long EXPIRATION_TIME = 864_000_000; //10 days 300000; // 5 min
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/sign-up";
    public static final String VERIFY_URL = "/accountingDevice/verify";
    public static final String ACCOUNTING_URL = "/accountingDevice/accounting";
}
