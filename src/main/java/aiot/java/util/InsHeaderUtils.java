package aiot.java.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpHeaders;

import io.github.jhipster.web.util.HeaderUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Slf4j
public class InsHeaderUtils {

    public static HttpHeaders searchEntityAlert(String applicationName, boolean enableTranslation, String entityName, String message, long count) {
        message = enableTranslation ? applicationName + "." + entityName + "." + message : "Search " + entityName + " result : " + count;
        return HeaderUtil.createAlert(applicationName, message, Long.toString(count));
    }

    public static HttpHeaders depositEntityAlert(String applicationName, boolean enableTranslation, String entityName, String message, String orgId) {
        message = enableTranslation ? applicationName + "." + entityName + "." + message : "Deposit to:" + orgId;
        return HeaderUtil.createAlert(applicationName, message, orgId);
    }

    public static HttpHeaders killEntityAlert(String applicationName, String entityName, String message) {
        try {
            message = URLEncoder.encode(message, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
//            log.error(ExceptionUtils.getStackTrace(e));
            e.printStackTrace();
        }
        return HeaderUtil.createAlert(applicationName, message, message);
    }

}
