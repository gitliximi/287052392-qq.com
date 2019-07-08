package com.credit.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;

@Component
@ConfigurationProperties(prefix = "spring.mail")
public class MailConfig {

    private static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    /**
     * SMTP server host.
     */
    //@Value("${host}")
    private String host;

    //@Value("${port}")
    private String port;

    /**
     * Login user of the SMTP server.
     */
    //@Value("${username}")
    private String username;

    /**
     * Login password of the SMTP server.
     */
    //@Value("${password}")
    private String password;

    /**
     * Protocol used by the SMTP server.
     */
    //@Value("${properties.mail.smtp.ssl.enable}")
    private String enable;

    //@Value("${protocol}")
    private String protocol;
    /**
     * Default MimeMessage encoding.
     */
    private Charset defaultEncoding = DEFAULT_CHARSET;


    public static Charset getDefaultCharset() {
        return DEFAULT_CHARSET;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }

    public Charset getDefaultEncoding() {
        return defaultEncoding;
    }

    public void setDefaultEncoding(Charset defaultEncoding) {
        this.defaultEncoding = defaultEncoding;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
}
