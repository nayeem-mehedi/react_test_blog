package com.nayeem.example.demo.slumber.configuration.settings;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
@ConfigurationProperties(prefix = "jwt.token")
public class JwtSettings {
    private int accessExpirationInSeconds;
    private int refreshExpirationInSeconds;
    private String authUri;
    private String refreshUri;
    private String httpRequestHeader;
    private String accessKey;
    private String refreshKey;

    public int getAccessExpirationInSeconds() {
        return accessExpirationInSeconds;
    }

    public void setAccessExpirationInSeconds(int accessExpirationInSeconds) {
        this.accessExpirationInSeconds = accessExpirationInSeconds;
    }

    public int getRefreshExpirationInSeconds() {
        return refreshExpirationInSeconds;
    }

    public void setRefreshExpirationInSeconds(int refreshExpirationInSeconds) {
        this.refreshExpirationInSeconds = refreshExpirationInSeconds;
    }

    public String getAuthUri() {
        return authUri;
    }

    public void setAuthUri(String authUri) {
        this.authUri = authUri;
    }

    public String getRefreshUri() {
        return refreshUri;
    }

    public void setRefreshUri(String refreshUri) {
        this.refreshUri = refreshUri;
    }

    public String getHttpRequestHeader() {
        return httpRequestHeader;
    }

    public void setHttpRequestHeader(String httpRequestHeader) {
        this.httpRequestHeader = httpRequestHeader;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getRefreshKey() {
        return refreshKey;
    }

    public void setRefreshKey(String refreshKey) {
        this.refreshKey = refreshKey;
    }
}