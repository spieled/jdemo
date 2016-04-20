package com.studease.jdemo.jsch;

/**
 * Author: liushaoping
 * Date: 2016/2/17.
 */
public class JdbcConfig {
    private String host;
    private int port;
    private String dbname;
    private String dbUsername;
    private String dbPassword;
    private String tablename;

    public JdbcConfig() {
    }

    public JdbcConfig(String host, int port, String dbname, String dbUsername, String dbPassword, String tablename) {
        this.host = host;
        this.port = port;
        this.dbname = dbname;
        this.dbUsername = dbUsername;
        this.dbPassword = dbPassword;
        this.tablename = tablename;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getDbname() {
        return dbname;
    }

    public void setDbname(String dbname) {
        this.dbname = dbname;
    }

    public String getDbUsername() {
        return dbUsername;
    }

    public void setDbUsername(String dbUsername) {
        this.dbUsername = dbUsername;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }

    public String getTablename() {
        return tablename;
    }

    public void setTablename(String tablename) {
        this.tablename = tablename;
    }
}
