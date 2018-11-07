package com.james.ftpexample;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author james
 * @since 11/7/18
 */
@ConfigurationProperties(prefix = "example.ftp")
public class FtpExampleProperties {

    private String ip;
    private String username;
    private String password;
    private String localdir;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
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

    public String getLocaldir() {
        return localdir;
    }

    public void setLocaldir(String localdir) {
        this.localdir = localdir;
    }
}
