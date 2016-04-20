package com.studease.jdemo.jsch;

/**
 * Author: liushaoping
 * Date: 2016/2/17.
 */
public class JSchDataSource {
    private String name;
    private int domainId;
    private boolean useSshTunnel;
    private SshConfig sshConfig;
    private PortForwardConfig portForwardConfig;
    private JdbcConfig jdbcConfig;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDomainId() {
        return domainId;
    }

    public void setDomainId(int domainId) {
        this.domainId = domainId;
    }

    public boolean isUseSshTunnel() {
        return useSshTunnel;
    }

    public void setUseSshTunnel(boolean useSshTunnel) {
        this.useSshTunnel = useSshTunnel;
    }

    public SshConfig getSshConfig() {
        return sshConfig;
    }

    public void setSshConfig(SshConfig sshConfig) {
        this.sshConfig = sshConfig;
    }

    public PortForwardConfig getPortForwardConfig() {
        return portForwardConfig;
    }

    public void setPortForwardConfig(PortForwardConfig portForwardConfig) {
        this.portForwardConfig = portForwardConfig;
    }

    public JdbcConfig getJdbcConfig() {
        return jdbcConfig;
    }

    public void setJdbcConfig(JdbcConfig jdbcConfig) {
        this.jdbcConfig = jdbcConfig;
    }
}
