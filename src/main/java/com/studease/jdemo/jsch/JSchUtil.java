package com.studease.jdemo.jsch;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

/**
 * Author: liushaoping
 * Date: 2016/2/17.
 */
public class JSchUtil {

    public static Session openSshTunnelSession(SshConfig sshConfig, PortForwardConfig portForwardConfig) {
        String user = sshConfig.getUser();//SSH连接用户名
        String password = sshConfig.getPassword();//SSH连接密码
        String host = sshConfig.getHost();//SSH服务器
        int port = sshConfig.getPort();//SSH访问端口
        int lport = portForwardConfig.getLport();//本地端口
        String rhost = portForwardConfig.getRhost();//远程MySQL服务器
        int rport = portForwardConfig.getRport();//远程MySQL服务端口
        try {
            JSch jsch = new JSch();
            Session session = jsch.getSession(user, host, port);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();
            System.out.println(session.getServerVersion());//这里打印SSH服务器版本信息
            int assinged_port = session.setPortForwardingL(lport, rhost, rport);
            System.out.println("localhost:" + assinged_port + " -> " + rhost + ":" + rport);
            return session;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
