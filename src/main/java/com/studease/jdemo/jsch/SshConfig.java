package com.studease.jdemo.jsch;

/**
 * Author: liushaoping
 * Date: 2016/2/17.
 */
public class SshConfig {
    private String host;
    private String user;
    private String password;
    private int port;

    public SshConfig() {
    }

    public SshConfig(String host, String user, String password, int port) {
        this.host = host;
        this.user = user;
        this.password = password;
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
