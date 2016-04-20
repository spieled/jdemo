package com.studease.jdemo.jsch;

/**
 * Author: liushaoping
 * Date: 2016/2/17.
 */
public class PortForwardConfig {
    private int lport = 3306;//本地端口
    private String rhost = "127.0.0.1";//远程MySQL服务器
    private int rport = 3306;//远程MySQL服务端口

    public PortForwardConfig() {
    }

    public PortForwardConfig(int lport, String rhost, int rport) {
        this.lport = lport;
        this.rhost = rhost;
        this.rport = rport;
    }

    public int getLport() {
        return lport;
    }

    public void setLport(int lport) {
        this.lport = lport;
    }

    public String getRhost() {
        return rhost;
    }

    public void setRhost(String rhost) {
        this.rhost = rhost;
    }

    public int getRport() {
        return rport;
    }

    public void setRport(int rport) {
        this.rport = rport;
    }
}
