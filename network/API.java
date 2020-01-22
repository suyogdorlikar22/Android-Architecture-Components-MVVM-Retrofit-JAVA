package com.snipex.suyog.mvvm.network;

public interface API {

    public static final String BASEURL="http://uat-securesaathi.apacfin.com";

    public static final String validateCustomer="/crbl/1.0/validate/customer";

    public static final String genrateOTP="/api/generate-otp";


    public static final String validateOTPlogin ="/api/validate-otp-login";

    public static final String get_loan_list ="/api/get-loan-list";

}
