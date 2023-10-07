package com.spectrum.notesapi.utils;

import org.springframework.http.HttpHeaders;

import java.util.regex.Pattern;

/**
 * Created by "Mohammad Shah Alam" on 19 Oct, 2020
 */
public interface CommonUtils {
    static String getCustomerType(String customerType) {
        switch (customerType) {
            case "R":
                return "RESIDENTIAL";
            case "M":
                return "RESIDENTIAL_MULTI_FAMILY";
            case "S":
                return "RESIDENTIAL_SINGLE_FAMILY";
            case "C":
                return "COMMERCIAL";
        }

        return "NA";
    }

    static HttpHeaders getHttpHeaders(String tracingID, String clientApp) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Tracing-Id", tracingID);
        responseHeaders.set("Client-App", clientApp);
        return responseHeaders;
    }

    String IPV4_REGEX = "(([0-1]?[0-9]{1,2}\\.)|(2[0-4][0-9]\\.)|(25[0-5]\\.)){3}(([0-1]?[0-9]{1,2})|(2[0-4][0-9])|(25[0-5]))";
    Pattern IPV4_PATTERN = Pattern.compile(IPV4_REGEX);

    static boolean isValidIPV4(final String s) {
        return IPV4_PATTERN.matcher(s).matches();
    }
}
